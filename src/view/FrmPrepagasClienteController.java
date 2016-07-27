package view;

import controller.SistemaPrepagas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import observer.IObserver;

import java.net.URL;
import java.util.ResourceBundle;

public class FrmPrepagasClienteController implements Initializable, IObserver {
    @FXML
    Label lblCliente;
    @FXML
    TableView<PrepagaView> tblPrepagas;
    @FXML
    TableColumn<PrepagaView, Integer> colNro;
    @FXML
    TableColumn<PrepagaView, String> colFecha;
    @FXML
    TableColumn<PrepagaView, String> colSucursal;
    @FXML
    TableColumn<PrepagaView, Float> colHoras;
    @FXML
    TableColumn<PrepagaView, Float> colRestantes;
    @FXML
    TableView<ItemPrepagaView> tblItemsPrepaga;
    @FXML
    TableColumn<ItemPrepagaView, Integer> colId;
    @FXML
    TableColumn<ItemPrepagaView, String> colFechaItem;
    @FXML
    TableColumn<ItemPrepagaView, String> colSucursalItem;
    @FXML
    TableColumn<ItemPrepagaView, Float> colHorasItem;

    private Cliente cliente;
    private ObservableList<PrepagaView> prepagas;
    private ObservableList<ItemPrepagaView> items;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SistemaPrepagas.getInstancia().addObserver(this);

        prepagas = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();
        configurarTableViewPrepagas();
        configurarTableViewItemsPrepaga();
    }

    private void configurarTableViewPrepagas() {
        colNro.setCellValueFactory(new PropertyValueFactory<PrepagaView, Integer>("nroPrepaga"));
        colFecha.setCellValueFactory(new PropertyValueFactory<PrepagaView, String>("fecha"));
        colSucursal.setCellValueFactory(new PropertyValueFactory<PrepagaView, String>("sucursal"));
        colHoras.setCellValueFactory(new PropertyValueFactory<PrepagaView, Float>("horas"));
        colRestantes.setCellValueFactory(new PropertyValueFactory<PrepagaView, Float>("restantes"));

        tblPrepagas.setItems(prepagas);
    }

    private void configurarTableViewItemsPrepaga() {
        colId.setCellValueFactory(new PropertyValueFactory<ItemPrepagaView, Integer>("id"));
        colFechaItem.setCellValueFactory(new PropertyValueFactory<ItemPrepagaView, String>("fecha"));
        colSucursalItem.setCellValueFactory(new PropertyValueFactory<ItemPrepagaView, String>("sucursal"));
        colHorasItem.setCellValueFactory(new PropertyValueFactory<ItemPrepagaView, Float>("horas"));

        tblItemsPrepaga.setItems(items);
    }

    public void cargarCliente(String identificacion) {
        cliente = SistemaPrepagas.getInstancia().getCliente(identificacion);

        if (cliente != null) {
            prepagas.clear();
            prepagas.addAll(SistemaPrepagas.getInstancia().listarPrepagasCliente(identificacion));
            lblCliente.setText("Prepagas - " + cliente.getClienteView().getCliente());
        }
    }

    @FXML
    private void tblPrepagasOnCellClicked(Event event) {
        int nroPrepaga = tblPrepagas.getSelectionModel().getSelectedItem().getNroPrepaga();

        items.clear();
        items.addAll(SistemaPrepagas.getInstancia().listarItemsPrepaga(cliente.getIdentificacion(), nroPrepaga));
    }

    @Override
    public void update() {
        prepagas.clear();
        items.clear();
        prepagas.addAll(SistemaPrepagas.getInstancia().listarPrepagasCliente(cliente.getIdentificacion()));
    }
}
