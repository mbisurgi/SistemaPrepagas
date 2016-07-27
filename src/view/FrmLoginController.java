package view;

import controller.SistemaPrepagas;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrmLoginController implements Initializable{
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    Button btnLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnLoginOnMouseClicked(Event event) {
        boolean login, habilitado;

        login = SistemaPrepagas.getInstancia().login(txtUsername.getText(), txtPassword.getText());
        habilitado = SistemaPrepagas.getInstancia().usuarioHabilitado(txtUsername.getText());

        if (login == true && habilitado == true) {
            loadFrmPrincipal(event);
        }

        if (login == true && habilitado == false) {
            mensaje("El usuario ingresado no se encuentra habilitado. Por favor, comuniquese con el administrador.");
        }

        if (login == false) {
            mensaje("El usuario y / o contraseña no son correctos. Por favor, vuelva a intentarlo.");
        }
    }

    private void loadFrmPrincipal(Event event) {
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("FrmPrincipal.fxml"));
            Stage stage = new Stage();
            //stage.setTitle("Movimientos");
            stage.setScene(new Scene(parent, 900, 600));
            stage.show();
            //stage.setResizable(false);
            stage.setMaximized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mensaje(String msg) {
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle("Login");
        mensaje.setHeaderText(null);
        mensaje.setContentText(msg);

        mensaje.showAndWait();
    }
}
