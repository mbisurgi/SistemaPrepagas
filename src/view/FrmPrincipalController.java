package view;

import controller.SistemaPrepagas;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.UsuarioView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrmPrincipalController implements Initializable{
    @FXML
    Label lblUsername;
    @FXML
    Label lblSucursal;
    @FXML
    Button btnClientes;
    @FXML
    Button btnPrepagas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UsuarioView usu = SistemaPrepagas.getInstancia().getUsuarioView();

        lblUsername.setText(usu.getEmail());
        lblSucursal.setText(usu.getSucursal());
    }

    @FXML
    private void btnClientesOnClicked(Event event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("FrmClientes.fxml"));
            Stage stage = new Stage();
            //stage.setTitle("Movimientos");
            stage.setScene(new Scene(parent, 550, 400));
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnPrepagasOnClicked(Event event) {

    }
}
