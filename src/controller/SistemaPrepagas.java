package controller;

import model.*;

import java.sql.Date;
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

    public void ingresarClientePersona(String dni, String nombre, String apellido, String email) {
        ClientePersona cli = (ClientePersona)buscarCliente(dni);

        if (cli == null) {
            cli = new ClientePersona(dni, email, nombre, apellido);
            clientes.add(cli);
        }
    }

    public void ingresarClienteEmpresa(String cuit, String razonSocial, String email) {
        ClienteEmpresa cli = (ClienteEmpresa)buscarCliente(cuit);

        if (cli == null) {
            cli = new ClienteEmpresa(cuit, email, razonSocial);
            clientes.add(cli);
        }
    }

    public void ingresarPrepaga(String identificacion, Date fecha, int idSucursal, float horas) {
        int nroPrepaga = (int)Math.random() * 999999 + 100000;

        Cliente cli = buscarCliente(identificacion);
        Sucursal suc = usuarioLogueado.getSucursal();

        if (cli != null) {
            cli.agregarPrepaga(nroPrepaga, fecha, suc, horas);
        }
    }

    public void ingresarItemPrepaga(String identificacion, int nroPrepaga, Date fecha, int idSucursal, float horas) {
        Cliente cli = buscarCliente(identificacion);
        Sucursal suc = usuarioLogueado.getSucursal();

        if (cli != null) {
            Prepaga pre = cli.buscarPrepaga(nroPrepaga);

            if (pre != null) {
                pre.agregarItem(fecha, suc, horas);
            }
        }
    }

    private Usuario buscarUsuario(String username) {
        for (Usuario usu: usuarios) {
            if (usu.getEmail().equals(username)) {
                return usu;
            }
        }

        return null;
    }

    private Cliente buscarCliente(String identificacion) {
        for (Cliente cli: clientes) {
            if (cli.getIdentificacion().equals(identificacion)) {
                return cli;
            }
        }

        return null;
    }

    private Sucursal buscarSucursal(int idSucursal) {
        for (Sucursal suc: sucursales) {
            if (suc.getIdSucursal() == idSucursal) {
                return suc;
            }
        }

        return null;
    }
}
