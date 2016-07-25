package model;

public class ClientePersonaView {
    private String identificacion;
    private String nombre;
    private String apellido;
    private String email;

    public ClientePersonaView(String identificacion, String nombre, String apellido, String email) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }
}
