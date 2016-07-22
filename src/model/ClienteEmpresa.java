package model;

public class ClienteEmpresa extends Cliente {
    private String razonSocial;

    public ClienteEmpresa(String identificacion, String email, String razonSocial) {
        super(identificacion, email);
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Override
    public ClienteView getClienteView() {
        return new ClienteView(identificacion, razonSocial, email);
    }
}
