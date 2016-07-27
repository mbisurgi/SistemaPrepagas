package model;

public class ItemPrepagaView {
    private int id;
    private String fecha;
    private String sucursal;
    private float horas;

    public ItemPrepagaView(int id, String fecha, String sucursal, float horas) {
        this.id = id;
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.horas = horas;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getSucursal() {
        return sucursal;
    }

    public float getHoras() {
        return horas;
    }
}
