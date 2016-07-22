package model;

public class ClienteEmpresa extends Cliente {
    private String razonSocial;

    public ClienteEmpresa(String identificacion, String email, String razonSocial) {
        super(identificacion, email);
        this.razonSocial = razonSocial;
    }
}
