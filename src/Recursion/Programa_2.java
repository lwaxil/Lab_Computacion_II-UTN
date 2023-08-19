package Recursion;

import java.util.Scanner;

/*Escribe un programa que calcule la sumatoria de los números enteros desde 1 hasta un número dado utilizando una función recursiva.
Implementa esta funcionalidad en una clase separada. Asegúrate de probar la función con diferentes valores de entrada.*/
public class Programa_2 {
    //Funcion recursiva
    public static int Sumatoria(int numero){
        if (numero == 1){
            return 1;
        }
        return numero + Sumatoria(numero-1);
    }

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un numero natural distinto de 0");
        int num = scanner.nextInt();
        System.out.println("El resultado de la sumatoria es: " + Sumatoria(num));
    }

}
