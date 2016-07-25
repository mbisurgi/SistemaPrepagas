package view;


import controller.SistemaPrepagas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ClienteView;
import observer.IObserver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrmClientesController implements Initializable, IObserver {
    @FXML
    TableView<ClienteView> tblClientes;
    @FXML
    TableColumn<ClienteView, String> colIdentificacion;
    @FXML
    TableColumn<ClienteView, String> colCliente;
    @FXML
    TableColumn<ClienteView, String> colEmail;
    @FXML
    Button btnNuevo;
    @FXML
    Button btnEditar;

    private ObservableList<ClienteView> clientes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SistemaPrepagas.getInstancia().addObserver(this);

        clientes = FXCollections.observableArrayList();
        configurarTableViewCliente();
    }

    private void configurarTableViewCliente() {
        colIdentificacion.setCellValueFactory(new PropertyValueFactory<ClienteView, String>("identificacion"));
        colCliente.setCellValueFactory(new PropertyValueFactory<ClienteView, String>("cliente"));
        colEmail.setCellValueFactory(new PropertyValueFactory<ClienteView, String>("email"));

        tblClientes.setItems(clientes);
    }

    @FXML
    private void btnNuevoOnMouseClicked(Event event) {
        loadFrmClientesEdicion(event);
    }

    private void loadFrmClientesEdicion(Event event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("FrmClientesEdicion.fxml"));
            Stage stage = new Stage();
            //stage.setTitle("Movimientos");
            stage.setScene(new Scene(parent, 390, 245));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        clientes.clear();
        clientes.addAll(SistemaPrepagas.getInstancia().listarClientes());
    }
}
