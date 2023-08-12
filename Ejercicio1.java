import java.util.Scanner;

public class Ejercicio1 {
    public static void main (String args[]) {
        Scanner scanner=new Scanner(System.in);
        int mayor;
        System.out.println("Ingrese el primer numero");
        int num1= scanner.nextInt();
        System.out.println("Ingrese el segundo numero");
        int num2= scanner.nextInt();
        System.out.println("Ingrese el tercer numero");
        int num3= scanner.nextInt();
        if (num1>num2 && num1>num3) {
            mayor=num1;
        } else if (num2>num1 && num2>num3) {
            mayor=num2;
        } else {
            mayor=num3;
        }
        System.out.println("El mayor es " + mayor);

    }
}

