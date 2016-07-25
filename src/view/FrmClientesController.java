package view;


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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ClienteView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrmClientesController implements Initializable {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
