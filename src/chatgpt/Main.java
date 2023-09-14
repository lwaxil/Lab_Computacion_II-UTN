package chatgpt;

import java.io.*;
import java.util.Scanner;

abstract class Vehiculo implements Serializable {
    private String marca;
    private String modelo;
    private double precio;

    public Vehiculo(String marca, String modelo, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public abstract double calcularImpuesto();

    public abstract void mostrarInformacion();

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Precio: $" + precio;
    }
}

class Coche extends Vehiculo implements Serializable {
    public Coche(String marca, String modelo, double precio) {
        super(marca, modelo, precio);
    }

    @Override
    public double calcularImpuesto() {
        return getPrecio() * 0.15;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Coche - " + toString());
    }
}

class Moto extends Vehiculo implements Serializable {
    public Moto(String marca, String modelo, double precio) {
        super(marca, modelo, precio);
    }

    @Override
    public double calcularImpuesto() {
        return getPrecio() * 0.1;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Moto - " + toString());
    }
}

class Concesionaria implements Serializable {
    private Vehiculo vehiculo;

    public Concesionaria(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
}

class Serializador {
    public void guardar(Concesionaria concesionaria, String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(concesionaria);
            System.out.println("Concesionaria guardada en " + archivo);
        }
    }

    public Concesionaria cargar(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Concesionaria concesionariaCargada = (Concesionaria) ois.readObject();
            System.out.println("Concesionaria cargada desde " + archivo);
            return concesionariaCargada;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Serializador serializador = new Serializador();
        Concesionaria concesionariaCargada = null;

        System.out.println("Seleccione: \n1. Cargar un fichero existente\n2. Guardar un nuevo fichero");
        int opcion1 = scanner.nextInt();

        if (opcion1 == 1) {
            File archivo = new File("concesionaria.dat");

            if (archivo.exists() && archivo.length() > 0) {
                System.out.println("Vehículo cargado con éxito.");
                try {
                    concesionariaCargada = serializador.cargar("concesionaria.dat");
                } catch (Exception e) {
                    System.out.println("Error al cargar la concesionaria desde el archivo.");
                }
            } else {
                System.out.println("El archivo no existe. Creando uno...");
            }
        }

        if (concesionariaCargada == null) {
            // Crear un vehículo y una concesionaria si no se carga desde el archivo
            Vehiculo coche = new Coche("Toyota", "Hilux", 20000.0);
            Concesionaria concesionaria = new Concesionaria(coche);

            try {
                serializador.guardar(concesionaria, "concesionaria.dat");
            } catch (IOException e) {
                System.out.println("Error al guardar la concesionaria.");
            }
        } else {
            // Si se cargó desde el archivo, mostrar la información del vehículo
            concesionariaCargada.getVehiculo().mostrarInformacion();
        }
    }
}
