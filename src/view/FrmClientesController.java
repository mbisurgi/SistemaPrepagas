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
    @FXML
    Button btnPrepagas;

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
        clientes.addAll(SistemaPrepagas.getInstancia().listarClientes());
    }

    @FXML
    private void btnNuevoOnMouseClicked(Event event) {
        loadFrmClientesEdicionNuevo(event);
    }

    @FXML
    private void btnEditarOnMouseClicked(Event event) {
        ClienteView cli = tblClientes.getSelectionModel().getSelectedItem();

        if (cli != null) {
            loadFrmClientesEdicionEditar(event);
        } else {
            System.out.println("Seleccione un cliente para editar.");
        }
    }

    @FXML
    private void btnPrepagasOnMouseClicked(Event event) {
        ClienteView cli = tblClientes.getSelectionModel().getSelectedItem();

        if (cli != null) {
            loadFrmPrepagasCliente(event);
        } else {
            System.out.println("Seleccione un cliente para editar.");
        }
    }

    private void loadFrmClientesEdicionNuevo(Event event) {
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

    private void loadFrmClientesEdicionEditar(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrmClientesEdicion.fxml"));
            Parent parent = loader.load();
            FrmClientesEdicionController controller = loader.<FrmClientesEdicionController>getController();
            controller.cargarCliente(tblClientes.getSelectionModel().getSelectedItem().getIdentificacion());
            //Parent parent = FXMLLoader.load(getClass().getResource("FrmClientesEdicion.fxml"));

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

    private void loadFrmPrepagasCliente(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrmPrepagasCliente.fxml"));
            Parent parent = loader.load();
            FrmPrepagasClienteController controller = loader.<FrmPrepagasClienteController>getController();
            controller.cargarCliente(tblClientes.getSelectionModel().getSelectedItem().getIdentificacion());
            //Parent parent = FXMLLoader.load(getClass().getResource("FrmClientesEdicion.fxml"));

            Stage stage = new Stage();
            //stage.setTitle("Movimientos");
            stage.setScene(new Scene(parent, 550, 500));
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
