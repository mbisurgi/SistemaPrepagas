package model;

public class ClientePersona extends Cliente {
    private String nombre;
    private String apellido;

    public ClientePersona(String identificacion, String email, String nombre, String apellido) {
        super(identificacion, email);
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public ClienteView getClienteView() {
        return new ClienteView(identificacion, nombre + ", " + apellido, email);
    }

    public ClientePersonaView getClientePersonaView() {
        return new ClientePersonaView(identificacion, nombre, apellido, email);
    }
}
