package Practica_PrimerParcial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Precalentando {
    public static void main(String[] args){
        //inicializar el arraylist con elementos
        ArrayList <Integer> miLista = new ArrayList<>(java.util.Arrays.asList(1,2,3,4,5));
        Scanner scanner = new Scanner(System.in);

        //colocar al final un nuevo elemento
        System.out.println("Ingrese un numero: ");
        int nmr = scanner.nextInt();
        miLista.add(nmr);

        //mostrar el elemento de una posicion dada
        System.out.println("Ingrese una posicion para conocer el elemento");
        int posicion = scanner.nextInt();
        try{
            System.out.println("El elementro en la posicion " + posicion + " es: " + miLista.get(posicion));
        }catch (Exception e){
            e.printStackTrace();
        }
        //eliminar un elemento
        System.out.println("Ingrese un indice para eliminarlo");
        int indice = scanner.nextInt();
        try{
            miLista.remove(indice);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("El indice no es correcto");
        }
        System.out.println("El contenido actual es: " + miLista);
    }
}
