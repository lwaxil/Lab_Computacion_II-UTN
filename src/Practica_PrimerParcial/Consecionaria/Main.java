package Practica_PrimerParcial.Consecionaria;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
    public static Vehiculo crearCoche() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la marca del vehículo: ");
        String marca = scanner.nextLine();
        System.out.println("Ingrese el modelo del vehículo: ");
        String modelo = scanner.nextLine();
        System.out.println("Ingrese el precio del vehículo: ");
        double precio = scanner.nextDouble();
        return new Coche(marca, modelo, precio); // Puedes cambiar a Coche o Moto según lo que elija el usuario
    }
    public static Vehiculo crearMoto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la marca del vehículo: ");
        String marca = scanner.nextLine();
        System.out.println("Ingrese el modelo del vehículo: ");
        String modelo = scanner.nextLine();
        System.out.println("Ingrese el precio del vehículo: ");
        double precio = scanner.nextDouble();
        return new Moto(marca, modelo, precio); // Puedes cambiar a Coche o Moto según lo que elija el usuario
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

    public void setPrecio(double precio) {

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

    public Vehiculo[] getVehiculos() {
        return new Vehiculo[0];
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
        Concesionaria concesionaria = null;

        List<Vehiculo> vehiculos = new ArrayList<>();

        int opcion;
        System.out.println("Seleccione: \n1. Cargar un fichero existente\n2. Guardar un nuevo fichero");
        int opcion1 = scanner.nextInt();

        if (opcion1 == 1) {
            File archivo = new File("concesionaria.dat");

            if (archivo.exists() && archivo.length() > 0) {
                System.out.println("Concesionaria cargada con éxito.");
                try {
                    vehiculos = (List<Vehiculo>) serializador.cargar("concesionaria.dat");
                } catch (Exception e) {
                    System.out.println("Error al cargar la concesionaria desde el archivo.");
                }
            } else {
                System.out.println("El archivo no existe. Creando uno...");
            }
        }

        if (concesionariaCargada == null) {
            do {
                System.out.println("Seleccione qué operación desea realizar en la concesionaria");
                System.out.println("""
                    1. Agregar vehiculo
                    2. Editar vehiculo
                    3. Eliminar vehiculo
                    4. Mostrar vehiculos
                    5. Salir""");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> {
                        System.out.println("¿Qué desea agregar? \n1. Coches \n2. Motos");
                        int otra_opción = scanner.nextInt();
                        switch (otra_opción) {
                            case 1 -> {
                                Vehiculo coche = Vehiculo.crearCoche();
                                vehiculos.add(coche); // Agregar el vehículo a la lista
                            }
                            case 2 -> {
                                Vehiculo moto = Vehiculo.crearMoto();
                                vehiculos.add(moto); // Agregar el vehículo a la lista
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("Ingrese el vehiculo que desea editar: \n1. Coches\n2. Motos");
                        int otra_opcion = scanner.nextInt();
                        switch (otra_opcion) {
                            case 1 -> {
                                System.out.println("Ingrese la marca: ");
                                String marca = scanner.next();
                                System.out.println("Ingrese el modelo: ");
                                String modelo = scanner.next();
                                System.out.println("Ingrese el precio");
                                double precio = scanner.nextDouble();
                                // Lógica para editar un coche
                                for (Vehiculo vehiculo : vehiculos) {
                                    if (vehiculo instanceof Coche && vehiculo.getMarca().equals(marca) &&
                                            vehiculo.getModelo().equals(modelo)) {
                                        // Actualizar el precio del coche
                                        ((Coche) vehiculo).setPrecio(precio);
                                        System.out.println("Coche editado con éxito.");
                                    }
                                }
                            }
                            case 2 -> {
                                System.out.println("Ingrese la marca: ");
                                String marca = scanner.next();
                                System.out.println("Ingrese el modelo: ");
                                String modelo = scanner.next();
                                System.out.println("Ingrese el nuevo precio: ");
                                double precio = scanner.nextDouble();
                                // Lógica para editar una moto
                                for (Vehiculo vehiculo : vehiculos) {
                                    if (vehiculo instanceof Moto && vehiculo.getMarca().equals(marca) &&
                                            vehiculo.getModelo().equals(modelo)) {
                                        // Actualizar el precio de la moto
                                        ((Moto) vehiculo).setPrecio(precio);
                                        System.out.println("Moto editada con éxito.");
                                    }
                                }
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("Ingrese la marca: ");
                        String marca = scanner.next();
                        System.out.println("Ingrese el modelo: ");
                        String modelo = scanner.next();
                        // Lógica para eliminar un vehículo
                        vehiculos.removeIf(vehiculo ->
                                vehiculo.getMarca().equals(marca) && vehiculo.getModelo().equals(modelo));
                        System.out.println("Vehículo eliminado con éxito.");
                    }
                    case 4 -> {
                        System.out.println("Vehículos en la concesionaria:");
                        for (Vehiculo vehiculo : vehiculos) {
                            vehiculo.mostrarInformacion();
                        }
                    }
                    case 5 -> {
                        System.out.println("Saliendo del programa");
                    }
                }
            } while (opcion != 5);

            // Crear una concesionaria con la lista de vehículos
            concesionariaCargada = new Concesionaria((Vehiculo) vehiculos);
            try {
                serializador.guardar((Concesionaria) vehiculos, "concesionaria.dat");
            } catch (IOException e) {
                System.out.println("Error al guardar la concesionaria.");
            }
        } else {
            // Si se cargó desde el archivo, mostrar la información de los vehículos
            System.out.println("Vehículos en la concesionaria:");
            for (Vehiculo vehiculo : concesionariaCargada.getVehiculos()) {
                vehiculo.mostrarInformacion();
            }
        }
    }
}