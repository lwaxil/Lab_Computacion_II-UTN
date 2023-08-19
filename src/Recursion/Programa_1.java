package Recursion;

import java.util.Scanner;

/*Desarrolla un programa que realice la división de dos números enteros utilizando tanto el enfoque recursivo como el iterativo con restas sucesivas.
Implementa ambos enfoques en la misma clase, utilizando la sobrecarga de métodos para diferenciarlos. Luego, realiza pruebas utilizando distintos pares de números.*/
public class Programa_1 {
    // Enfoque recursivo
    public static int Recursivo(int dividendo, int divisor) {
        if (dividendo < divisor) {
            return 0;
        }
        return 1 + Recursivo(dividendo - divisor, divisor);
    }

    // Enfoque iterarivo
    public static int Iteracion(int dividendo, int divisor) {
        int contador = 0;
        while (dividendo >= divisor) {
            dividendo = dividendo - divisor;
            contador++;
        }

        return contador;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el dividendo");
        int dividendo = scanner.nextInt();
        System.out.println("Ingrese el divisor (debe ser mayor que 0)");
        int divisor = scanner.nextInt();

        int resultadoRecursivo = Recursivo(dividendo, divisor);
        System.out.println("El resultado usando recursion: " + resultadoRecursivo);

        int resultadoIterativo = Iteracion(dividendo, divisor);
        System.out.println("El resultado usando iteracion: " + resultadoIterativo);
    }
}
