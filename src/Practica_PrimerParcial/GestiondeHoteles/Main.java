package Practica_PrimerParcial.GestiondeHoteles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Habitaciones implements MostrarInformacion {
    protected int camas;
    protected int huespedes;
    protected String estado; // Ocupada, Libre

    public Habitaciones() {
    }

    public Habitaciones(int camas, int huespedes, String estado) {
        this.camas = camas;
        this.huespedes = huespedes;
        this.estado = estado;
    }
    public int getCamas() {
        return camas;
    }
    public void setCamas(int camas) {
        this.camas = camas;
    }
    public int getHuespedes() {
        return huespedes;
    }
    public void setHuespedes(int huespedes) {
        this.huespedes = huespedes;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Camas: "+camas);
        System.out.println("Huespedes: "+huespedes);
        System.out.println("Estado: "+estado);
    }
}
class GestionReservas{
    Scanner scanner = new Scanner(System.in);
    //crear habitaciones
    public void crearHabitaciones(){
        System.out.printf("Ingre");
    }
}
 class GestiondeHuespedes {
    ArrayList <Huespedes> huespedes = new ArrayList<>();
    //añadir huespedes
    public void añadirHuespedes(Huespedes huesped){
        huespedes.add(huesped);
    }
    //eliminar huespedes
    public void eliminarHuespedes(Huespedes huesped){
        huespedes.remove(huesped);
    }
}
abstract class Persona{
    protected String nombre;
    protected String apellido;

    public Persona() {
    }
    //constructor
    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
class Huespedes extends Persona implements MostrarInformacion{
    protected int habitacion;
    public Huespedes() {
    }
    //contructor
    public Huespedes(String nombre, String apellido, int habitacion) {
        super(nombre, apellido);
        this.habitacion = habitacion;
    }

    //mostrar informacion de los huespedes
    @Override
    public void mostrarInformacion(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Apellido: "+apellido);
        System.out.println("Habitacion: "+habitacion);
    }
}
interface MostrarInformacion{
    public void mostrarInformacion();
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Habitaciones> habitaciones = new ArrayList<>();
        int opcion;
        do {
            System.out.println("Ingrese que operacion desear relizar");
            System.out.println("1. Ver la lista de habitaciones");
            System.out.println("2. Reservar una habitacion");
            System.out.println("3. Cancelar una reserva");
            System.out.println("4. Guardar las reservas en un archivo");
            System.out.println("5. Cargar las reservas de un archivo");
            System.out.println("6. Salir");
            opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Lista de habitaciones");
                    for (Habitaciones habitacion: habitaciones) {
                        System.out.println(habitacion);
                    }
                    break;
                case 2:

            }
        }while (opcion!=6);
    }
}
