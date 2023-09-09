package Serializacion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Block nuevo_block = new Block();
        Serializador serializador = new Serializador();
        Block textoDeserializado;
        int opcion;

        do {
            System.out.println("Ingrese lo que desea realizar:");
            System.out.println("1. Agregar texto");
            System.out.println("2. Mostrar el archivo");
            System.out.println("3. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea después de la lectura de int

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el texto que desea: ");
                    String agregar = scanner.nextLine();
                    nuevo_block.agregarTexto(new Textos(agregar));
                    serializador.serializar(nuevo_block);
                }
                case 2 -> {
                    System.out.println("El contenido del block de notas es: ");
                    textoDeserializado = serializador.deserializar();
                    System.out.println(textoDeserializado.textos);
                }
                case 3 -> System.out.println("Saliendo del programa.");
                default -> System.out.println("Seleccione una opción válida");
            }
        } while (opcion != 4);
    }
}