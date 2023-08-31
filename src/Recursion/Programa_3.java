package Recursion;

import java.util.Scanner;

/*Crea un programa que calcule el factorial de un número utilizando dos métodos diferentes: uno utilizando recursión y otro utilizando iteración.
Estos métodos deben estar en una clase distinta. Emplea la sobrecarga de métodos para diferenciar entre las dos implementaciones.
Realiza pruebas del programa con distintos números enteros.*/
public class Programa_3 {
    //Funcion recursiva
    public static int Factorial(int numero){
        if (numero == 1 || numero == 0){
            return 1;
        }
        return numero * Factorial(numero-1);
    }
    //Funcion iterativa
    public static int Factorial(int numero, int aux){
        for (int i = 1; i <= numero; i++){
            aux = aux * i;
        }
        return aux;
    }
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un numero para calcular su factorial: ");
        int factorial = scanner.nextInt();
        int cont = 1;

        System.out.println("El resultado usando recursion: " + Factorial(factorial));

        System.out.println("El resultado usando iteracion: " + Factorial(factorial, cont));

    }
}
