<<<<<<< HEAD
package Exepcione;

import java.io.IOException;
import java.util.Scanner;

class MisExepciones {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Introduce un numero: ");
        Scanner leer = new Scanner(System.in);

        try {
            evaluarNumero(leer.nextInt());
        } catch (LongitudDelNumero e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Fin del programa");
    }

    static void evaluarNumero(int nroEvaluar) throws LongitudDelNumero {
        if (nroEvaluar <= 100 && nroEvaluar >= 1) {
            LongitudDelNumero miPropiaExcepcion = new LongitudDelNumero("El número esta dentro del rango");
            throw miPropiaExcepcion;
        }
    }
}

class LongitudDelNumero extends IOException {
    public LongitudDelNumero() {
    }
    public LongitudDelNumero(String mensajeError) {
        super(mensajeError);
    }
=======
package Exepcione;

import java.io.IOException;
import java.util.Scanner;

class MisExepciones {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Introduce un numero: ");
        Scanner leer = new Scanner(System.in);

        try {
            evaluarNumero(leer.nextInt());
        } catch (LongitudDelNumero e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Fin del programa");
    }

    static void evaluarNumero(int nroEvaluar) throws LongitudDelNumero {
        if (nroEvaluar <= 100 && nroEvaluar >= 1) {
            LongitudDelNumero miPropiaExcepcion = new LongitudDelNumero("El número esta dentro del rango");
            throw miPropiaExcepcion;
        }
    }
}

class LongitudDelNumero extends IOException {
    public LongitudDelNumero() {
    }
    public LongitudDelNumero(String mensajeError) {
        super(mensajeError);
    }
>>>>>>> b383b76e70b752beca7f19f3ac29ede24aef4237
}