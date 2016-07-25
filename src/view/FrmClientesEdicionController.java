package view;

import controller.SistemaPrepagas;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FrmClientesEdicionController implements Initializable {
    @FXML
    RadioButton rbPersona;
    @FXML
    RadioButton rbEmpresa;
    @FXML
    Label lblIdentificacion;
    @FXML
    Label lblNombre;
    @FXML
    Label lblApellido;
    @FXML
    TextField txtIdentificacion;
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtApellido;
    @FXML
    TextField txtEmail;
    @FXML
    Button btnAceptar;
    @FXML
    Button btnCancelar;

    ToggleGroup tgEntidad;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tgEntidad = new ToggleGroup();

        rbPersona.setToggleGroup(tgEntidad);
        rbEmpresa.setToggleGroup(tgEntidad);
    }

    @FXML
    private void rbPersonaSelected(Event event) {
        if (rbPersona.isSelected()) {
            borrarCampos();
            lblIdentificacion.setText("DNI:");
            lblIdentificacion.setVisible(true);
            lblNombre.setText("Nombre:");
            lblNombre.setVisible(true);
            lblApellido.setText("Apellido:");
            lblApellido.setVisible(true);
            txtIdentificacion.setVisible(true);
            txtNombre.setVisible(true);
            txtApellido.setVisible(true);
            txtEmail.setDisable(false);
        }
    }

    @FXML
    private void rbEmpresaSelected(Event event) {
        if (rbEmpresa.isSelected()) {
            borrarCampos();
            lblIdentificacion.setText("CUIT:");
            lblIdentificacion.setVisible(true);
            lblNombre.setText("Razon Social:");
            lblNombre.setVisible(true);
            lblApellido.setVisible(false);
            txtIdentificacion.setVisible(true);
            txtNombre.setVisible(true);
            txtApellido.setVisible(false);
            txtEmail.setDisable(false);
        }
    }

    @FXML
    private void btnAceptarOnMouseClicked(Event event) {
        if (!rbPersona.isSelected() && !rbEmpresa.isSelected()) {
            mensaje("Debe seleccionar si el cliente se trata de una persona o una empresa.");
            return;
        }

        if (txtIdentificacion.getText().isEmpty() || txtIdentificacion.getText().trim().length() == 0) {
            if (rbPersona.isSelected()) {
                mensaje("El campo DNI no puede estar vacío, por favor, completelo.");
            }

            if (rbEmpresa.isSelected()) {
                mensaje("El campo CUIT no puede estar vacío, por favor, completelo.");
            }

            txtIdentificacion.requestFocus();
            return;
        }

        if (txtNombre.getText().isEmpty() || txtNombre.getText().trim().length() == 0) {
            if (rbPersona.isSelected()) {
                mensaje("El campo Nombre no puede estar vacío, por favor, completelo.");
            }

            if (rbEmpresa.isSelected()) {
                mensaje("El campo Razon Social no puede estar vacío, por favor, completelo.");
            }

            txtNombre.requestFocus();
            return;
        }

        if (txtApellido.getText().isEmpty() || txtApellido.getText().trim().length() == 0) {
            if (rbPersona.isSelected()) {
                mensaje("El campo Apellido no puede estar vacío, por favor, completelo.");
                txtApellido.requestFocus();
                return;
            }
        }

        if (txtEmail.getText().isEmpty() || txtEmail.getText().trim().length() == 0) {
            mensaje("El campo Email no puede estar vacío, por favor, completelo.");
            txtEmail.requestFocus();
            return;
        }

        if (rbPersona.isSelected()) {
            SistemaPrepagas.getInstancia().ingresarClientePersona(txtIdentificacion.getText(), txtNombre.getText(), txtApellido.getText(), txtEmail.getText());
        }

        if (rbEmpresa.isSelected()) {
            SistemaPrepagas.getInstancia().ingresarClienteEmpresa(txtIdentificacion.getText(), txtNombre.getText(), txtEmail.getText());
        }

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnCancelarOnMouseClicked(Event event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void mensaje(String msg) {
        Alert mensaje = new Alert(Alert.AlertType.ERROR);
        mensaje.setTitle("Campos vacios");
        mensaje.setHeaderText(null);
        mensaje.setContentText(msg);

        mensaje.showAndWait();
    }

    private void borrarCampos() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtEmail.clear();
    }
}
