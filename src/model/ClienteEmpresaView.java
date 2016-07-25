package model;

public class ClienteEmpresaView {
    private String identificacion;
    private String razonSocial;
    private String email;

    public ClienteEmpresaView(String identificacion, String razonSocial, String email) {
        this.identificacion = identificacion;
        this.razonSocial = razonSocial;
        this.email = email;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getEmail() {
        return email;
    }
}
