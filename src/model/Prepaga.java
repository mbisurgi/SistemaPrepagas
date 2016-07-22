package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Prepaga {
    private int nroPrepaga;
    private Date fecha;
    private Sucursal sucursal;
    private float horas;
    private List<ItemPrepaga> items;
    private boolean habilitada;

    public Prepaga() {

    }

    public Prepaga(int nroPrepaga, Date fecha, Sucursal sucursal, float horas) {
        this.nroPrepaga = nroPrepaga;
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.horas = horas;
        this.items = new ArrayList<>();
        this.habilitada = true;
    }

    public Prepaga(int nroPrepaga, Date fecha, Sucursal sucursal, float horas, boolean habilitada) {
        this.nroPrepaga = nroPrepaga;
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.horas = horas;
        this.items = new ArrayList<>();
        this.habilitada = habilitada;
    }

    public int getNroPrepaga() {
        return nroPrepaga;
    }

    public void setNroPrepaga(int nroPrepaga) {
        this.nroPrepaga = nroPrepaga;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public float getHoras() {
        return horas;
    }

    public void setHoras(float horas) {
        this.horas = horas;
    }

    public List<ItemPrepaga> getItems() {
        return items;
    }

    public void setItems(List<ItemPrepaga> items) {
        this.items = items;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public float horasDisponibles() {
        float horasConsumidas = 0;

        for (ItemPrepaga item: items) {
            horasConsumidas = horasConsumidas + item.getHoras();
        }

        return  horas - horasConsumidas;
    }

    public void agregarItem(Date fecha, Sucursal sucursal, float horas) {
        ItemPrepaga item = new ItemPrepaga(fecha, sucursal, horas);

        items.add(item);
    }
}
