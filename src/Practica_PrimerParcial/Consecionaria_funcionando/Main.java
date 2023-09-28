<<<<<<< HEAD:src/Practica_PrimerParcial/Consecionaria_funcionando/Main.java
package Practica_PrimerParcial.Consecionaria_funcionando;

=======
package Practica_PrimerParcial.Consecionaria;
>>>>>>> 7f830a050ef406aac8d776212eb7f71db82bc184:src/Practica_PrimerParcial/Consecionaria/Main.java
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Clase Vehiculo
abstract class Vehiculo implements Serializable {
    private String marca;
    private String modelo;
    private double precio;

    public Vehiculo(String marca, String modelo, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    // Getters y setters
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Precio: $" + precio;
    }
    abstract double calcularImpuesto();
    abstract void mostrarInformacion();
}
class Coche extends Vehiculo implements Serializable{

    public Coche(String marca, String modelo, double precio) {
        super(marca, modelo, precio);
    }

    @Override
    public double calcularImpuesto() {
        // Lógica para calcular impuesto de coche (puede ser personalizada)
        return getPrecio() * 0.10;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Tipo: Coche");
        System.out.println(this);
    }
}
// Clase Concesionaria
class Concesionaria implements Serializable {
    private ArrayList<Vehiculo> inventario = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public void agregarVehiculo(Vehiculo vehiculo) {
        inventario.add(vehiculo);
    }

    public void eliminarVehiculo(String marca, String modelo) {
        inventario.removeIf(v -> v.getMarca().equals(marca) && v.getModelo().equals(modelo));
    }

    public void editarPrecio(String marca, String modelo, double nuevoPrecio) {
        for (Vehiculo vehiculo : inventario) {
            if (vehiculo.getMarca().equals(marca) && vehiculo.getModelo().equals(modelo)) {
                vehiculo.setPrecio(nuevoPrecio);
            }
        }
    }

    public void mostrarInventario() {
        System.out.println("Inventario de la Concesionaria:");
        for (Vehiculo vehiculo : inventario) {
            System.out.println(vehiculo);
        }
    }

    public void Serializar(String archivo) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(inventario);
            System.out.println("Inventario guardado correctamente en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Deserializar(String archivo) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            ArrayList<Vehiculo> inventario = (ArrayList<Vehiculo>) entrada.readObject();
            System.out.println("Desea ver el inventario cargado desde el archivo? (s/n)");
            String respuesta = scanner.next();
            if (respuesta.equals("s")) {
                System.out.println("Inventario cargado desde " + archivo + ":");
                for (Vehiculo vehiculo : inventario) {
                    System.out.println(vehiculo);
                }
                System.out.println("*".repeat(50));
            }
            //preguntar si desea seguir agregando vehiculos al inventario o empezar de cero
            System.out.println("Desea agregar vehiculos al inventario cargado desde el archivo? (s/n)");
            respuesta = scanner.next();
            if (respuesta.equals("s")) {
                this.inventario.addAll(inventario);//agregar los vehiculos del archivo al inventario
            } else {
                System.out.println("Se eliminara el inventario cargado desde el archivo");
                this.inventario.clear();//eliminar los vehiculos del archivo
            }
            System.out.println("*".repeat(50));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Concesionaria concesionaria = new Concesionaria();
        Scanner scanner = new Scanner(System.in);
        //crear el archivo en caso de que no exista
        File archivo = new File("inventario.dat");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //deserializar el archivo
        concesionaria.Deserializar("inventario.dat");
        //menu de opciones
        System.out.println("Bienvenido a la concesionaria");
        int opcion = 0;
        do {
            System.out.println("Elija una opcion");
            System.out.println("1- Agregar vehiculo");
            System.out.println("2- Eliminar vehiculo");
            System.out.println("3- Editar precio");
            System.out.println("4- Mostrar inventario");
            System.out.println("5- Salir");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese los datos del coche");
                    System.out.print("Marca: ");
                    String marca = scanner.next();
                    System.out.print("Modelo: ");
                    String modelo = scanner.next();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    Vehiculo coche = new Coche(marca, modelo, precio);
                    concesionaria.agregarVehiculo(coche);
                    break;
                case 2:
                    System.out.println("Ingrese la marca y modelo del vehiculo a eliminar");
                    System.out.print("Marca: ");
                    String marcaEliminar = scanner.next();
                    System.out.print("Modelo: ");
                    String modeloEliminar = scanner.next();
                    concesionaria.eliminarVehiculo(marcaEliminar, modeloEliminar);
                    break;
                case 3:
                    System.out.println("Ingrese la marca y modelo del vehiculo a editar");
                    System.out.print("Marca: ");
                    String marcaEditar = scanner.next();
                    System.out.print("Modelo: ");
                    String modeloEditar = scanner.next();
                    System.out.print("Ingrese el nuevo precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    concesionaria.editarPrecio(marcaEditar, modeloEditar, nuevoPrecio);
                    break;
                case 4:
                    concesionaria.mostrarInventario();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (opcion != 5);
        //al finalizar el programa, serializar el inventario
        concesionaria.Serializar("inventario.dat");
        //cerrar el scanner
        scanner.close();
    }
}