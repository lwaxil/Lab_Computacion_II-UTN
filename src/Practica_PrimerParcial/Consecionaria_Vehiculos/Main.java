package Practica_PrimerParcial.Consecionaria_Vehiculos;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Consecionaria consecionaria = new Consecionaria();
        Serializador serializador = new Serializador();
        Consecionaria textoDeserializado;
        int opcion, opcion1;
        System.out.println("Seleccione: \n1. Cargar un fichero existente\n2. Guardar un nuevo fichero");
        opcion1= scanner.nextInt();
        if (opcion1 == 1) {
            File archivo = new File("concesionaria.txt");
            if (archivo.exists() && archivo.length() > 0) {
                System.out.println("Vehículos cargados con éxito.");
                textoDeserializado = serializador.cargar();
                System.out.println(textoDeserializado.vehiculos);
            } else {
                System.out.println("El archivo no existe. Creando uno...");
            }
        }

        do{
            System.out.println("Seleccione que operacion desea realizar en la concesionaria");
            System.out.println("""
                    1. Agregar vehiculo
                    2. Editar vehiculo
                    3. Eliminar vehiculo
                    4. Mostrar vehiculos
                    5. Salir""");
            opcion = scanner.nextInt();
            switch (opcion){
                case 1 ->{
                    System.out.println(""" 
                            Que desea agregar?
                            1.Coches
                            2.Motos""");
                    int otra_opcion = scanner.nextInt();
                    switch (otra_opcion){
                        case 1 ->{
                            System.out.println("Ingrese la marca: ");
                            String marca = scanner.next();
                            System.out.println("Ingrese el modelo: ");
                            String modelo = scanner.next();
                            System.out.println("Ingrese el precio");
                            double precio = scanner.nextDouble();
                            Vehiculo newVehiculo = new Coche(marca, modelo, precio);
                            consecionaria.agregarVehiculo(newVehiculo);
                            serializador.guardar(consecionaria);
                        }
                        case 2 ->{
                            System.out.println("Ingrese la marca: ");
                            String marca = scanner.next();
                            System.out.println("Ingrese el modelo: ");
                            String modelo = scanner.next();
                            System.out.println("Ingrese el precio");
                            double precio = scanner.nextDouble();
                            Vehiculo newVehiculo = new Moto(marca,modelo,precio);
                            consecionaria.agregarVehiculo(newVehiculo);
                            serializador.guardar(consecionaria);
                        }
                    }
                }
                case 2 ->{
                    System.out.println("Ingrese el vehiculo que desea editar: \n1. Coches\n2. Motos");
                    int otra_opcion=scanner.nextInt();
                    switch (otra_opcion){
                        case 1 -> {
                            System.out.println("Ingrese la marca: ");
                            String marca = scanner.next();
                            System.out.println("Ingrese el modelo: ");
                            String modelo = scanner.next();
                            System.out.println("Ingrese el precio");
                            double precio = scanner.nextDouble();
                            consecionaria.agregarVehiculo(new Coche(marca, modelo, precio));
                            serializador.guardar(consecionaria);
                        }

                        case 2->{
                            System.out.println("Ingrese la marca: ");
                            String marca = scanner.next();
                            System.out.println("Ingrese el modelo: ");
                            String modelo = scanner.next();
                            System.out.println("Ingrese el nuevo precio: ");
                            double precio = scanner.nextDouble();
                            consecionaria.editarPrecio(marca, modelo, precio);
                        }
                    }
                }
                case 3 ->{
                    System.out.println("Ingrese la marca: ");
                    String marca = scanner.next();
                    System.out.println("Ingrese el modelo: ");
                    String modelo = scanner.next();
                    consecionaria.eliminarVehiculo(marca, modelo);
                }
                case 4 ->{
                    consecionaria.mostrarInventario();
                }
                case 5 ->{
                    System.out.println("Saliendo del programa");
                }
            }

        }while (opcion!=5);
    }
}
