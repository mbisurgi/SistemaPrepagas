package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    protected String identificacion;
    protected String email;
    protected List<Prepaga> prepagas;

    public Cliente(String identificacion, String email) {
        this.identificacion = identificacion;
        this.email = email;
        this.prepagas = new ArrayList<>();
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Prepaga> getPrepagas() {
        return prepagas;
    }

    public void setPrepagas(List<Prepaga> prepagas) {
        this.prepagas = prepagas;
    }

    public void agregarPrepaga(int nroPrepaga, Date fecha, Sucursal sucursal, int horas) {
        Prepaga prepaga = new Prepaga(nroPrepaga, fecha, sucursal, horas);

        prepagas.add(prepaga);
    }
}
