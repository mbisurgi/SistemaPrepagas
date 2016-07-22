package model;


public class UsuarioView {
    private String email;
    private String sucursal;

    public UsuarioView(String email, String sucursal) {
        this.email = email;
        this.sucursal = sucursal;
    }

    public String getEmail() {
        return email;
    }

    public String getSucursal() {
        return sucursal;
    }
}
