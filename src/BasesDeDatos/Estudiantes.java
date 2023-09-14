package BasesDeDatos;

import java.sql.*;

public class Estudiantes {
    public static void main(String[] args) {
        // Datos de conexión a la base de datos (ajusta estos valores según tu configuración)
        String url = "jdbc:mariadb://localhost:3306/universidad";//Aqui reemplazar mysql por mariadb
        String usuario = "root";/*en localhost >> colocar el puerto que les aparezca en xampp*/
        String pass = "";
        try{
            Class.forName("org.mariadb.jdbc.Driver");


        }catch (ClassNotFoundException ex){
            System.out.println("Error en la conexion.");
        }

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, pass);



            // Crear una declaración SQL
            Statement statement = conexion.createStatement();


            // Ejecutar una consulta para obtener todos los datos de estudiantes
            String consulta = "SELECT * FROM estudiantes";
            ResultSet resultado = statement.executeQuery(consulta);


            // Iterar a través de los resultados y mostrarlos por consola
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String legajo = resultado.getString("legajo");
                String dni = resultado.getString("dni");


                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellido);
                System.out.println("Legajo: " + legajo);
                System.out.println("DNI: " + dni);
                System.out.println("-----------------------");
            }


            // Cerrar la conexión y recursos
            resultado.close();
            statement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
