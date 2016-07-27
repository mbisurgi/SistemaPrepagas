package controller;

import model.*;
import observer.Observable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SistemaPrepagas extends Observable {
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

        ClientePersona cli1 = new ClientePersona("33899255", "mbisurgi@bc-group.com.ar", "Maximiliano", "Bisurgi");
        clientes.add(cli1);

        Prepaga pre1 = new Prepaga(123456, Date.valueOf(LocalDate.now()), suc1, 20);
        Prepaga pre2 = new Prepaga(654321, Date.valueOf(LocalDate.now()), suc1, 10);
        cli1.getPrepagas().add(pre1);
        cli1.getPrepagas().add(pre2);

        pre1.agregarItem(Date.valueOf(LocalDate.now()), suc1, 2);
        pre1.agregarItem(Date.valueOf(LocalDate.now()), suc1, 3);
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
            notifyObservers();
        }
    }

    public void ingresarClienteEmpresa(String cuit, String razonSocial, String email) {
        ClienteEmpresa cli = (ClienteEmpresa)buscarCliente(cuit);

        if (cli == null) {
            cli = new ClienteEmpresa(cuit, email, razonSocial);
            clientes.add(cli);
            notifyObservers();
        }
    }

    public void actualizarClientePersona(String dni, String nombre, String apellido, String email) {
        ClientePersona cli = (ClientePersona)buscarCliente(dni);

        if (cli != null) {
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setEmail(email);
            notifyObservers();
        }
    }

    public void actualizarClienteEmpresa(String cuit, String razonSocial, String email) {
        ClienteEmpresa cli = (ClienteEmpresa)buscarCliente(cuit);

        if (cli != null) {
            cli.setRazonSocial(razonSocial);
            cli.setEmail(email);
            notifyObservers();
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

    public Cliente getCliente(String identificacion) {
        return buscarCliente(identificacion);
    }

    public List<ClienteView> listarClientes() {
        List<ClienteView> listado = new ArrayList<>();

        for (Cliente cli: clientes) {
            listado.add(cli.getClienteView());
        }

        return listado;
    }

    public List<PrepagaView> listarPrepagasCliente(String identificacion) {
        List<PrepagaView> listado = new ArrayList<>();
        Cliente cli = buscarCliente(identificacion);

        if (cli != null) {
            for (Prepaga pre: cli.getPrepagas()) {
                listado.add(pre.getPrepagaView());
            }
        }

        return listado;
    }

    public List<ItemPrepagaView> listarItemsPrepaga(String identificacion, int nroPrepaga) {
        List<ItemPrepagaView> listado = new ArrayList<>();
        Cliente cli = buscarCliente(identificacion);

        if (cli != null) {
            Prepaga prepaga = null;

            for (Prepaga pre: cli.getPrepagas()) {
                if (pre.getNroPrepaga() == nroPrepaga) {
                    prepaga = pre;
                    break;
                }
            }

            for (ItemPrepaga item: prepaga.getItems()) {
                listado.add(item.getItemPrepagaView());
            }
        }

        return listado;
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
