package model;

public class PrepagaView {
    private int nroPrepaga;
    private String fecha;
    private String sucursal;
    private float horas;
    private float restantes;

    public PrepagaView(int nroPrepaga, String fecha, String sucursal, float horas, float restantes) {
        this.nroPrepaga = nroPrepaga;
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.horas = horas;
        this.restantes = restantes;
    }

    public int getNroPrepaga() {
        return nroPrepaga;
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

    public float getRestantes() {
        return restantes;
    }
}
