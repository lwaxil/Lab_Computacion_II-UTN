import java.util.Scanner;

public class Ejercicio1 {
    public static void main (String args[]) {
        Scanner scanner=new Scanner(System.in);
        int mayor, medio, menor;
        System.out.println("Ingrese el primer numero: ");
        int num1= scanner.nextInt();
        System.out.println("Ingrese el segundo numero: ");
        int num2= scanner.nextInt();
        System.out.println("Ingrese el tercer numero: ");
        int num3= scanner.nextInt();
        if (num1>num2 && num1>num3) {
            mayor=num1;
            if (num2>num3) {
                medio=num2;
                menor=num3;
            } else {
                medio=num3;
                menor=num2;
            }
        } else if (num2>num1 && num2>num3) {
            mayor=num2;
            if (num1>num3) {
                medio=num1;
                menor=num3;
            } else {
                medio=num3;
                menor=num1;
            }
        } else {
            mayor=num3;
            if (num2>num1) {
                medio=num2;
                menor=num1;
            } else {
                medio=num1;
                menor=num2;
            }
        }
        System.out.println("El mayor es " + mayor+" El del medio es "+medio+" El menor es "+menor);

    }
}

