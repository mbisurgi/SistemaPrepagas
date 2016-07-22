package view;

import controller.SistemaPrepagas;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.UsuarioView;

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

    }

    @FXML
    private void btnPrepagasOnClicked(Event event) {

    }
}
