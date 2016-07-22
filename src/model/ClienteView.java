package model;

public class ClienteView {
    private String identificacion;
    private String cliente;
    private String email;

    public ClienteView(String identificacion, String cliente, String email) {
        this.identificacion = identificacion;
        this.cliente = cliente;
        this.email = email;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEmail() {
        return email;
    }
}
