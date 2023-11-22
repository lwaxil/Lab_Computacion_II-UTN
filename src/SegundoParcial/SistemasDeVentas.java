package SegundoParcial;

import java.sql.*;
import java.util.ArrayList;

//1. crear la clase DBHelper
class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:33061/ventas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void ejecutarConsulta(String consulta) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                // Ejecutar la consulta
                statement.executeUpdate();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement statement = connection.prepareStatement(consulta);

            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
//2. Crear la clase Producto
class Producto {
    private int producto_id;
    private String nombre;
    private double precio_por_unidad;
    private int stock;

    @Override
    public String toString() {
        return "ID DEL PRODUCTO: " + producto_id +
                ", NOMBRE: " + nombre +
                ", PRECIO POR UNIDAD: " + precio_por_unidad +
                ", STOCK: " + stock;
    }

    public Producto(int producto_id, String nombre, double precio_por_unidad, int stock) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.precio_por_unidad = precio_por_unidad;
        this.stock = stock;
    }
    //6. Obtener un producto por ID
    public static Producto obtenerProducto(int productoID) {
        String consulta = "SELECT * FROM productos WHERE producto_id = " + productoID;
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        if (resultado != null) {
            try {
                if (resultado.next()) {
                    return new Producto(
                            resultado.getInt("producto_id"),
                            resultado.getString("nombre"),
                            resultado.getDouble("precio_por_unidad"),
                            resultado.getInt("stock")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //7.Obtener el producto mas vendido
    public static Producto obtenerProductoMasVendido() {
        String consulta = "SELECT producto_id, SUM(cantidad_vendida) as total_vendido FROM ventas GROUP BY producto_id ORDER BY total_vendido DESC LIMIT 1";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        if (resultado != null) {
            try {
                if (resultado.next()) {
                    return obtenerProducto(resultado.getInt("producto_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

class Vendedor{
    private int vendedor_id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fecha_nacimiento;
    private Date fecha_contratacion;

    @Override
    public String toString() {
        return "ID DEL VENDEDOR: " + vendedor_id +
                ", NOMBRE: " + nombre +
                ", APELLIDO: " + apellido +
                ", DNI: " + dni +
                ", FECHA DE NACIMIENTO: " + fecha_nacimiento +
                ", FECHA DE CONTRATACION: " + fecha_contratacion + ".\n" ;
    }
    public Vendedor(int vendedor_id, String nombre, String apellido, String dni, Date fecha_nacimiento, Date fecha_contratacion) {
        this.vendedor_id = vendedor_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_contratacion = fecha_contratacion;
    }

    public Vendedor(String consultaBusqueda){
        try {
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consultaBusqueda);
            if (resultado.next()) {
                this.vendedor_id = resultado.getInt("vendedor_id");
                this.nombre = resultado.getString("nombre");
                this.apellido = resultado.getString("apellido");
                this.dni = resultado.getString("dni");
                this.fecha_nacimiento = resultado.getDate("fecha_nacimiento");
                this.fecha_contratacion = resultado.getDate("fecha_contratacion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Comerciales {
    //4. Obtener los datos de un vendedor
    public static Vendedor obtenerVendedorPorID(int vendedorID) {
        String consulta = "SELECT * FROM vendedores WHERE vendedor_id = " + vendedorID;
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        if (resultado != null) {
            try {
                if (resultado.next()) {
                    return new Vendedor(
                            resultado.getInt("vendedor_id"),
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("dni"),
                            resultado.getDate("fecha_nacimiento"),
                            resultado.getDate("fecha_contratacion")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //5. Generacion de informe de productos en stock
    public static void generarInforme() {
        String consulta = "SELECT * FROM productos";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        if (resultado != null) {
            try {
                double valorTotalProductos = 0;
                System.out.printf("%-30s %-7s %-20s %-12s \n", "Nombre", "Stock", "Precio por unidad", "Valor total");
                System.out.println("-----------------------------------------------------------------------------------------------");
                while (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    int stock = resultado.getInt("stock");
                    double precioPorUnidad = resultado.getDouble("precio_por_unidad");
                    double valorTotal = stock * precioPorUnidad;
                    valorTotal = Math.round(valorTotal * 100.0) / 100.0;
                    valorTotalProductos += valorTotal;
                    System.out.printf("%-30s %-7d %-20s %-12s\n", nombre, stock, precioPorUnidad, valorTotal);
                }
                System.out.println("-----------------------------------------------------------------------------------------------");
                valorTotalProductos = Math.round(valorTotalProductos * 100.0) / 100.0;
                System.out.println("\t\t\t\t\t\t\t\t\t\t TOTAL: \t\t\t" + valorTotalProductos);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //8. Obtener listado de vendedores
    public static ArrayList <Vendedor> listadoDeVendedores() {
        ArrayList <Vendedor> vendedores = new ArrayList <>();
        String consulta = "SELECT * FROM vendedores";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        if (resultado != null) {
            try {
                while (resultado.next()) {
                    vendedores.add(new Vendedor(
                            resultado.getInt("vendedor_id"),
                            resultado.getString("nombre"),
                            resultado.getString("apellido"),
                            resultado.getString("dni"),
                            resultado.getDate("fecha_nacimiento"),
                            resultado.getDate("fecha_contratacion")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vendedores;
    }

}
public class SistemasDeVentas {
    static void mostrarVendedores(ArrayList <Vendedor> vendedores){
        for (Vendedor vendedor: vendedores){
            System.out.println(vendedor);
        }
    }
    public static void main(String[] args) {
        System.out.println("Obtener un producto por ID: ");
        Producto producto2 = Producto.obtenerProducto(1);
        System.out.println(producto2 + "\n");

        System.out.println("Obtener el producto mas vendido: ");
        Producto producto3 = Producto.obtenerProductoMasVendido();
        System.out.println(producto3 + "\n");

        System.out.println("Obtener los datos de un vendedor por consulta:");
        String consulta = "SELECT * FROM vendedores WHERE vendedor_id = 1";
        Vendedor vendedor1 = new Vendedor(consulta);
        System.out.println(vendedor1);

        System.out.println("Obtener los datos de un vendedor por ID: ");
        Vendedor vendedor = Comerciales.obtenerVendedorPorID(1);
        System.out.println(vendedor);

        System.out.println("Informe de productos en stock: ");
        Comerciales.generarInforme();

        System.out.println("Listado de vendedores: ");
        ArrayList <Vendedor> vendedores = Comerciales.listadoDeVendedores();
        mostrarVendedores(vendedores);
    }
}
