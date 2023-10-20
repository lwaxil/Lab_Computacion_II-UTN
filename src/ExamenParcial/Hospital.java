package ExamenParcial;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        Administracion administracion = new Administracion();

        administracion.AgregarDoctor(new Doctores("Juan", 12345678, 1990, "Cardiologia"));
        administracion.AgregarDoctor(new Doctores("Pedro", 87654321, 1995, "Neurologia"));

        File archivo = new File("datos.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        do{
            System.out.println("Seleccione una opcion del menu");
            System.out.println("1. Listar doctores");//se carga directamente
            System.out.println("2. Registrar un nuevo paciente");
            System.out.println("3. Actualizar la informacion personal de un paciente");
            System.out.println("4. Consultar el historial medico del paciente");
            System.out.println("5. Nuevo historial para un paciente");
            System.out.println("6. Guardar el historial de pacientes en un archivo");
            System.out.println("7. Cargar el historial de pacientes desde un archivo");
            System.out.println("8. Salir");
            opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    for (Doctores doctor : administracion.doctores) {
                        System.out.println("Nombre: " + doctor.getNombre());
                        System.out.println("DNI: " + doctor.getDNI());
                        System.out.println("Fecha de nacimiento: " + doctor.getFechaNacimiento());
                        System.out.println("Especialidad: " + doctor.getEspecialidad());
                        System.out.println("*".repeat(50));
                    }
                    break;
                case 2:
                    System.out.println("Ingrese los datos del paciente");
                    System.out.print("Nombre: ");
                    String nombre = scanner.next();
                    System.out.print("DNI: ");
                    int dni = 0;
                    try {
                        dni = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese un valor numérico para el DNI.");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.print("Fecha de nacimiento (ddmmyyyy): ");
                    int fechaNacimiento = 0;
                    try {
                        fechaNacimiento = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese una fecha válida en formato ddmmyyyy.");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.print("Tipo de sangre: ");
                    int tipoSangre = 0;
                    try {
                        tipoSangre = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese un valor numérico para el tipo de sangre.");
                        scanner.nextLine();
                        continue;
                    }
                    System.out.print("Fecha del historial medico (ddmmyyyy): ");
                    int fechaHistorial = 0;
                    try {
                        fechaHistorial = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese una fecha válida en formato ddmmyyyy.");
                        scanner.nextLine();
                        continue;
                    }
                    scanner.nextLine();
                    System.out.print("Historial medico: ");
                    String historialMedico = scanner.nextLine();
                    Paciente paciente = new Paciente(nombre, dni, fechaNacimiento, tipoSangre, fechaHistorial, historialMedico);
                    administracion.RegistrarPaciente(paciente);
                    break;
                case 3:
                    System.out.println("Ingrese el DNI del paciente para editarlo");
                    int dniActualizar = scanner.nextInt();
                    administracion.ActualizarInformacionPaciente(dniActualizar);
                    break;
                case 4:
                    System.out.println("Ingrese el DNI del paciente para consultar su historial");
                    int dniConsultar = scanner.nextInt();
                    administracion.ConsultarHistorialPaciente(dniConsultar);
                    break;
                case 5:
                    System.out.println("Ingrese el DNI del paciente para agregar un nuevo historial");
                    int dniNuevoHistorial = scanner.nextInt();
                    administracion.NuevoHistorialPaciente(dniNuevoHistorial);
                    break;
                case 6:
                    administracion.GuardadoyRecuperacion("datos.txt");
                    break;
                case 7:
                    administracion.CargarDatos("datos.txt");
                    break;
                case 8:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (opcion != 8);
    }
}
//crear una clase persona, de esta heredaran las clases paciente y doctor

abstract class Persona implements Serializable{
    String Nombre;
    int DNI;
    int FechaNacimiento; //dd/mm/aaaa
    int FechaHistorial;
    String Historial;
    public Persona() {
    }

    public Persona(String nombre, int DNI, int fechaNacimiento) {
        Nombre = nombre;
        this.DNI = DNI;
        FechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

}
class Doctores extends Persona implements Serializable{
    String Especialidad;

    public Doctores(){
        this.Nombre = "";
        this.DNI = 0;
        this.FechaNacimiento = 0;
        this.Especialidad = "";
    }
    public Doctores(String Nombre, int DNI, int FechaNacimiento, String Especialidad){
        this.Nombre = Nombre;
        this.DNI = DNI;
        this.FechaNacimiento = FechaNacimiento;
        this.Especialidad = Especialidad;
    }
    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }
}
class Paciente extends Persona implements Informacion, Serializable{
    int nroTelefono;
    int tipoSangre;

    public Paciente(String nombre, int nroTelefono, int tipoSangre, int sangre, int fechaHistorial, String historialMedico) {
        this.nroTelefono = nroTelefono;
        this.tipoSangre = tipoSangre;
    }

    public Paciente(String nombre, int DNI, int fechaNacimiento, int nroTelefono, int tipoSangre, int fechahistorial, String historial) {
        super(nombre, DNI, fechaNacimiento);
        this.nroTelefono = nroTelefono;
        this.tipoSangre = tipoSangre;
        this.FechaHistorial = fechahistorial;
        this.Historial = historial;
    }

    public int getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(int nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public int getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(int tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    @Override
    public void verHistorialDeEventos() {
        System.out.println("HISTORIAL MEDICO DEL PACIENTE");

    }

    public String getHistorial() {
        return Historial;
    }
}

class Administracion implements Serializable{
    ArrayList<Paciente> pacientes = new ArrayList<>();
    ArrayList <Doctores> doctores = new ArrayList<>();
    public void AgregarDoctor(Doctores doctor){
        doctores.add(doctor);
    }
    public void RegistrarPaciente(Paciente paciente){
        pacientes.add(paciente);
    }
    public void ActualizarInformacionPaciente(int dni){
        for (Paciente paciente : pacientes) {
            if (paciente.getDNI() == dni) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Paciente: " + paciente.getNombre());
                System.out.println("Ingrese el nuevo nombre del paciente");
                String nombre = scanner.next();
                System.out.println("Ingrese el nuevo tipo de sangre");
                int tipoSangre = scanner.nextInt();
                paciente.setNombre(nombre);
                paciente.setTipoSangre(tipoSangre);
            }else{
                System.out.println("El paciente no existe");
            }
        }
    }
    public void NuevoHistorialPaciente(int dni){
        for (Paciente paciente : pacientes) {
            if (paciente.getDNI() == dni) {
                System.out.println("Paciente: " + paciente.getNombre());
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ingrese la fecha del historial medico");
                int fechaHistorial = scanner.nextInt();
                System.out.println("Ingrese el nuevo historial medico");
                String historialMedico = scanner.next();
                paciente.setFechaNacimiento(fechaHistorial);
                paciente.setNombre(historialMedico);

            }else{
                System.out.println("El paciente no existe");
            }
        }
    }
    public void ConsultarHistorialPaciente(int dni){
        for (Paciente paciente : pacientes) {
            if (paciente.getDNI() == dni) {
                System.out.println("Paciente: " + paciente.getNombre());
                System.out.println("Historial medico: " + paciente.getHistorial());
            }else{
                System.out.println("El paciente no existe");
            }
        }
    }
    //serializar
    protected void GuardadoyRecuperacion(String nombreArchivo) {
        try {
            FileOutputStream file = new FileOutputStream(nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(pacientes);
            out.close();
            file.close();
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    //deserializar
    protected void CargarDatos (String nombreArchivo){
        try {
            FileInputStream file = new FileInputStream(nombreArchivo);
            ObjectInputStream in = new ObjectInputStream(file);
            pacientes = (ArrayList<Paciente>) in.readObject();
            in.close();
            file.close();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar");
        }
    }
}
interface Informacion {
   public void verHistorialDeEventos();

}
