package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class SistemaPrepagas {
    private static SistemaPrepagas instancia = null;

    private List<Usuario> usuarios;
    private List<Sucursal> sucursales;
    private List<Cliente> clientes;
    private Usuario usuarioLogueado;

    private SistemaPrepagas() {
        usuarios = new ArrayList<>();
        sucursales = new ArrayList<>();
        clientes = new ArrayList<>();

        init();
    }

    public static SistemaPrepagas getInstancia() {
        if (instancia == null) {
            instancia = new SistemaPrepagas();
        }

        return instancia;
    }

    private void init() {
        Sucursal suc1 = new Sucursal(1, "Palermo", "El Salvador 4588", "Argentina");
        sucursales.add(suc1);

        Usuario usu1 = new Usuario("central-ar@enjoyurbanstation.com", "maximati", suc1);
        usuarios.add(usu1);
    }

    public boolean login(String username, String password) {
        Usuario usu = buscarUsuario(username);

        if (usu != null) {
            if (usu.getPassword().equals(password)) {
                usuarioLogueado = usu;
                return true;
            }
        }

        return false;
    }

    public boolean usuarioHabilitado(String username) {
        Usuario usu = buscarUsuario(username);

        if (usu != null) {
            if (usu.isHabilitado()) {
                return true;
            }
        }

        return false;
    }

    public UsuarioView getUsuarioView() {
        return usuarioLogueado.getUsuarioView();
    }

    private Usuario buscarUsuario(String username) {
        for (Usuario usu: usuarios) {
            if (usu.getEmail().equals(username)) {
                return usu;
            }
        }

        return null;
    }
}
