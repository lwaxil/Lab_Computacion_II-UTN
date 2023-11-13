package Practica_PrimerParcial.Gestion_Hotel;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        // Crear habitaciones de ejemplo
        hotel.añadirHabitacion(new Habitacion(1, 2, 1));
        hotel.añadirHabitacion(new Habitacion(2, 1, 0));

        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Ver la lista de habitaciones.");
            System.out.println("2. Reservar una habitación.");
            System.out.println("3. Cancelar una reserva.");
            System.out.println("4. Guardar reservas en un archivo.");
            System.out.println("5. Cargar reservas desde un archivo.");
            System.out.println("6. Salir.");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Ver habitaciones
                    System.out.println("Lista de habitaciones:");
                    hotel.verHabitaciones();

                    break;
                case 2:
                    // Reservar una habitación
                    hotel.reservarHabitacion();
                    break;
                case 3:
                    // Cancelar una reserva
                    hotel.cancelarReserva();
                    break;
                case 4:
                    // Guardar reservas en un archivo
                    hotel.Guardar("nombreHotel.txt");
                    break;
                case 5:
                    // Cargar reservas desde un archivo
                    hotel.Recuperacion("nombreHotel.txt");
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        } while (opcion != 6);
    }

    // Clase Persona
    static class Persona implements Serializable {
        protected String nombre;
        protected String apellido;

        public Persona() {
        }

        public Persona(String nombre, String apellido) {
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
    }

    // Interfaz MostrarInformacion
    interface MostrarInformacion {
        void mostrarInformacion();
    }

    // Clase Huesped
    static class Huesped extends Persona implements Serializable, MostrarInformacion {
        protected int habitacion;

        public Huesped() {
        }

        public Huesped(String nombre, String apellido, int habitacion) {
            super(nombre, apellido);
            this.habitacion = habitacion;
        }

        public int getHabitacion() {
            return habitacion;
        }

        public void setHabitacion(int habitacion) {
            this.habitacion = habitacion;
        }

        @Override
        public void mostrarInformacion() {
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Habitacion: " + habitacion);
        }
    }

    // Clase Habitacion
    static class Habitacion implements Serializable, MostrarInformacion {
        protected int camas;
        protected int huespedes;
        protected int estado;
        List<Huesped> huespedesList;

        public Habitacion() {
            huespedesList = new ArrayList<>();
        }

        public Habitacion(int camas, int huespedes, int estado) {
            this.camas = camas;
            this.huespedes = huespedes;
            this.estado = estado;
            huespedesList = new ArrayList<>();
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

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }

        public void añadirHuesped(Huesped huesped) {
            huespedesList.add(huesped);
        }

        public void eliminarHuesped(Huesped huesped) {
            huespedesList.remove(huesped);
        }

        @Override
        public void mostrarInformacion() {
            System.out.println("Camas: " + camas);
            System.out.println("Huespedes: " + huespedes);
            if (estado==0){
                System.out.println("Estado: Disponible");
            }else{
                System.out.println("Estado: Ocupada");
            }
            System.out.println("Huespedes en la habitación:");
            for (Huesped huesped : huespedesList) {
                huesped.getNombre();
                huesped.getApellido();
            }
        }
    }

    // Clase Hotel
    static class Hotel implements Serializable, MostrarInformacion {
        Scanner scanner = new Scanner(System.in);
        protected String nombre;
        protected String direccion;
        protected List<Habitacion> habitaciones;

        public Hotel() {
            nombre = "";
            direccion = "";
            habitaciones = new ArrayList<>();
        }

        public void añadirHabitacion(Habitacion habitacion) {
            habitaciones.add(habitacion);
        }

        public void eliminarHabitacion(Habitacion habitacion) {
            habitaciones.remove(habitacion);
        }

        public void verHabitaciones() {
            for (int i = 0; i < habitaciones.size(); i++) {
                System.out.println("Habitación #" + i);
                habitaciones.get(i).mostrarInformacion();
            }
        }


        public void reservarHabitacion() {
            //solicitar nombre y apellido del huesped
            System.out.println("Ingrese el nombre del huesped: ");
            String nombre = scanner.next();
            System.out.println("Ingrese el apellido del huesped: ");
            String apellido = scanner.next();
            Persona persona = new Persona(nombre, apellido);
            //mostrar las habitaciones disponibles
            System.out.println("Habitaciones disponibles:");
            for (Habitacion habitacion : habitaciones) {
                if (habitacion.getEstado() == 0) {
                    habitacion.mostrarInformacion();
                }
            }
            System.out.println("Desea reservar la habitacion? \n1. Si\n 2. No");
            int opcion = scanner.nextInt();
            //cambiar el estado de la habitacion si dijo que si
            if (opcion == 1){
                System.out.println("Ingrese el numero de la habitacion: ");
                int numero = scanner.nextInt();
                habitaciones.get(numero).setEstado(1);
            }
        }

        public void cancelarReserva() {
            //solicitar los datos del huesped para cancelar la reserva
            System.out.println("Ingrese el nombre del huesped: ");
            String nombre = scanner.next();
            System.out.println("Ingrese el apellido del huesped: ");
            String apellido = scanner.next();
            System.out.println("Desea cancelar la reserva? \n1. Si \n 2. No");
            int opcion = scanner.nextInt();
            //cambiar el estado de la habitacion si dijo que si
            if (opcion == 1){
                System.out.println("Ingrese el numero de la habitacion: ");
                int numero = scanner.nextInt();
                habitaciones.get(numero).setEstado(0);
            }
        }

        public void Guardar(String nombreArchivo) {
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
                salida.writeObject(this);
                System.out.println("Reservas guardadas en archivo exitosamente.");
            } catch (IOException e) {
                System.err.println("Error al guardar las reservas en el archivo: " + e.getMessage());
            }
        }

        public Hotel Recuperacion(String nombreArchivo) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
                return (Hotel) entrada.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar las reservas desde el archivo: " + e.getMessage());
                return null;
            }
        }

        @Override
        public void mostrarInformacion() {
            System.out.println("Nombre: " + nombre);
            System.out.println("Dirección: " + direccion);
            System.out.println("Habitaciones: " + habitaciones.size());
        }
    }
}




