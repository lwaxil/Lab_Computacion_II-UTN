package BasesDeDatos;

import java.sql.*;
import java.util.Scanner;

// 1. Define la clase abstracta Persona con atributos comunes para pacientes y doctores.
abstract class Persona {
    protected String nombre;
    protected int edad;

    // Constructor y métodos necesarios

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}

// 2. Implementa la clase Paciente que hereda de Persona con atributos adicionales como historial médico.
class Paciente extends Persona {
    private String historialMedico;
    private int doctorCabecera;
    private Date fechaIngreso;

    public Paciente(String nombre, int edad, String historialMedico, int doctorCabecera, Date fechaIngreso) {
        super( nombre, edad);
        this.historialMedico = historialMedico;
        this.doctorCabecera = doctorCabecera;
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    public int getDoctorCabecera() {
        return doctorCabecera;
    }
}


// 3. Implementa la clase Doctor que hereda de Persona con atributos como especialidad.
class Doctor extends Persona {
    private String especialidad;

    public Doctor(String nombre, int edad, String especialidad) {
        super(nombre, edad);
        this.especialidad = especialidad;
    }

}

class Hospital {
    public void agregarPaciente(Paciente paciente) {
        // Agregar el paciente a la base de datos
        String consulta = "INSERT INTO pacientes (nombre, edad, historial_medico, doctor, fecha_ingreso) VALUES ('" + paciente.getNombre() + "', " + paciente.getEdad() + ", '" + paciente.getHistorialMedico() + "', " + paciente.getDoctorCabecera() + ", '" + paciente.getFechaIngreso() + "')";
        DBHelper.ejecutarConsulta(consulta);
    }
    // elimine un paciente indicando su nombre
    public void eliminarPaciente(String nombre) {
        // Eliminar el paciente de la base de datos
        String consulta = "DELETE FROM pacientes WHERE nombre = '" + nombre + "'";
        DBHelper.ejecutarConsulta(consulta);
    }
    //editar un paciente apartir de su nombre, preguntando que campo desea editar
    public void editarPaciente(String nombre) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Qué campo desea editar?");
        System.out.println("1. Nombre");
        System.out.println("2. Edad");
        System.out.println("3. Historial Médico");
        System.out.println("4. Doctor de Cabecera");
        System.out.println("5. Salir");
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("Introduce el nuevo nombre: ");
                String nuevoNombre = sc.next();
                String consulta = "UPDATE pacientes SET nombre = '"+nuevoNombre+"' WHERE nombre = '"+nombre+"'";
                DBHelper.ejecutarConsulta(consulta);
                break;
            case 2:
                System.out.println("Introduce la nueva edad: ");
                int nuevaEdad = sc.nextInt();
                consulta = "UPDATE pacientes SET edad = '"+nuevaEdad+"' WHERE nombre = '"+nombre+"'";
                DBHelper.ejecutarConsulta(consulta);
                break;
            case 3:
                System.out.println("Introduce el nuevo historial médico: ");
                String nuevoHistorialMedico = sc.nextLine();
                consulta = "UPDATE pacientes SET historial_medico = '"+nuevoHistorialMedico+"' WHERE nombre = '"+nombre+"'";
                DBHelper.ejecutarConsulta(consulta);
                break;
            case 4:
                System.out.println("Introduce el nuevo doctor de cabecera: ");
                int nuevoDoctorCabecera = sc.nextInt();
                consulta = "UPDATE pacientes SET doctor = '"+nuevoDoctorCabecera+"' WHERE nombre = '"+nombre+"'";
                DBHelper.ejecutarConsulta(consulta);
                break;
            case 5:
                return;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
        sc.close();
    }

    //método para asignar un doctor de cabecera a un paciente indicando el nombre del doctor y el nombre del paciente
    public void asignarDoctorCabecera(String nombreDoctor, String nombrePaciente) {
        String consulta = "UPDATE pacientes SET doctor = (SELECT id FROM doctores WHERE nombre = '"+nombreDoctor+"') WHERE nombre = '"+nombrePaciente+"'";
        DBHelper.ejecutarConsulta(consulta);
    }

    public void listarPacientes() {
        String consulta = "SELECT * FROM pacientes";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        listarPacientes(resultado);
    }

    public void listarPacientesEntreDosFechas(Date fechaDesde, Date fechaHasta) {
        String consulta = "SELECT * FROM pacientes WHERE fecha_ingreso BETWEEN '"+fechaDesde+"' AND '"+fechaHasta+"';";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        listarPacientes(resultado);
    }
    //mostrar listado de pacientes
    public void listarPacientes(ResultSet resultado){
        if (resultado != null) {
            try {
                System.out.println("Lista de Pacientes:");
                System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "ID", "Nombre", "Edad", "Historial Médico", "Fecha Ingreso", "Doctor");

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int edad = resultado.getInt("edad");
                    String historialMedico = resultado.getString("historial_medico");
                    Date fechaIngreso = resultado.getDate("fecha_ingreso");
                    int idDoctor = resultado.getInt("doctor");

                    System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", id, nombre, edad, historialMedico, fechaIngreso, idDoctor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para ejecutar una consulta sin devolver resultados
    public static void ejecutarConsulta(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la declaración
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                // Ejecutar la consulta
                statement.executeUpdate();
            }

            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar una consulta y devolver un conjunto de resultados
    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la declaración
            PreparedStatement statement = connection.prepareStatement(consulta);

            // Ejecutar la consulta y devolver el conjunto de resultados
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class HospitalBasesDeDatos {
    //main del programa
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //creamos un objeto de la clase Hospital
        Hospital hospital = new Hospital();

        //menu de opciones
        int opcion = 0;
        do{
            System.out.println("Ingrese la opcion correspondiente a la gestion de pacientes que desea realizar: ");
            System.out.println("1. Agregar un paciente");
            System.out.println("2. Eliminar un paciente");
            System.out.println("3. Editar un paciente");
            System.out.println("4. Asignar un doctor de cabecera a un paciente");
            System.out.println("5. Listar todos los pacientes");
            System.out.println("6. Listar pacientes entre dos fechas");
            System.out.println("7. Salir\n");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce el nombre del paciente: ");
                    String nombre = sc.nextLine();
                    System.out.println("Introduce la edad del paciente: ");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introduce el historial médico del paciente: ");
                    String historialMedico = sc.nextLine();
                    System.out.println("Introduce el doctor de cabecera del paciente: ");
                    int doctorCabecera = sc.nextInt();
                    System.out.println("Introduce la fecha de ingreso del paciente: ");
                    Date fechaIngreso = new Date(sc.nextInt() - 1900, sc.nextInt() - 1, sc.nextInt());
                    Paciente paciente = new Paciente(nombre, edad, historialMedico, doctorCabecera, fechaIngreso);
                    hospital.agregarPaciente(paciente);
                    break;
                case 2:
                    hospital.listarPacientes();
                    System.out.println("Introduce el nombre del paciente que desea eliminar: ");
                    String nombrePaciente = sc.nextLine();
                    hospital.eliminarPaciente(nombrePaciente);
                    break;
                case 3:
                    hospital.listarPacientes();
                    System.out.println("Introduce el nombre del paciente que desea editar: ");
                    String nombrePacienteEditar = sc.nextLine();
                    hospital.editarPaciente(nombrePacienteEditar);
                    break;
                case 4:
                    System.out.println("Introduce el nombre del doctor de cabecera: ");
                    String nombreDoctor = sc.nextLine();
                    System.out.println("Introduce el nombre del paciente: ");
                    String nombrePacienteAsignar = sc.nextLine();
                    hospital.asignarDoctorCabecera(nombreDoctor, nombrePacienteAsignar);
                    break;
                case 5:
                    hospital.listarPacientes();
                    break;
                case 6:
                    System.out.println("Introduce la fecha desde la que desea listar los pacientes: ");
                    Date fechaDesde = new Date(sc.nextInt() - 1900, sc.nextInt() - 1, sc.nextInt());
                    System.out.println("Introduce la fecha hasta la que desea listar los pacientes: ");
                    Date fechaHasta = new Date(sc.nextInt() - 1900, sc.nextInt() - 1, sc.nextInt());
                    hospital.listarPacientesEntreDosFechas(fechaDesde, fechaHasta);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }while(opcion != 7);

        sc.close();
    }
}
