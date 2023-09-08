package Serializacion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String texto;
        Serializador serializador = new Serializador();
        System.out.printf("""
                Ingrese lo que desea realizar
                1. Agregar texto
                2. Borrar el archivo
                3. Mostrar el archivo
                """);
        int opcion = scanner.nextInt();
        switch (opcion){
            case 1:
                System.out.println("Ingrese un texto");
                texto = scanner.nextLine();
                serializador.serializar(texto);
                break;
            case 2:
                break;
            case 3:
                texto = serializador.deserializar();
                System.out.println(texto);
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }

    }
}