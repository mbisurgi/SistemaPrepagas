package model;

import java.sql.Date;

public class ItemPrepaga {
    private int idItem;
    private Date fecha;
    private Sucursal sucursal;
    private int horas;

    public ItemPrepaga() {

    }

    public ItemPrepaga(Date fecha, Sucursal sucursal, int horas) {
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.horas = horas;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
