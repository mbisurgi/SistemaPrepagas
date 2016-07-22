package model;

public class ClientePersona extends Cliente {
    private String nombre;
    private String apellido;

    public ClientePersona(String identificacion, String email, String nombre, String apellido) {
        super(identificacion, email);
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
