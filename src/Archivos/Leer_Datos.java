package Archivos;

import java.io.FileReader;

public class Leer_Datos {
    public static void main(String[] args){
        try {
            FileReader entrada = new FileReader("C:\\Users\\maxii\\Desktop\\TUP\\Lab_Computacion_II-UTN\\archivo.txt");
            int caracter = entrada.read();
            char letra = (char)caracter;
            while (caracter != -1){
                System.out.print(letra);
                caracter = entrada.read();
                letra = (char)caracter;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
