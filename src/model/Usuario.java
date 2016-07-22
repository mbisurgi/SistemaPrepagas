package model;

public class Usuario {
    private String email;
    private String password;
    private Sucursal sucursal;
    private boolean habilitado;

    public Usuario() {

    }

    public Usuario(String email, String password, Sucursal sucursal) {
        this.email = email;
        this.password = password;
        this.sucursal = sucursal;
        this.habilitado = true;
    }

    public Usuario(String email, String password, Sucursal sucursal, boolean habilitado) {
        this.email = email;
        this.password = password;
        this.sucursal = sucursal;
        this.habilitado = habilitado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public UsuarioView getUsuarioView() {
        return new UsuarioView(email, sucursal.getNombreSucursal());
    }
}
