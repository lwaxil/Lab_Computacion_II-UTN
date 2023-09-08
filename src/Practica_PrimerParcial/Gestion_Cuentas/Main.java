package Practica_PrimerParcial.Gestion_Cuentas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GestorCuentas cuenta = new GestorCuentas();
        int otra_opcion;
        while (true){
            System.out.println("Seleccione que operacion desea hacer con el Gestor de Cuentas: ");
            System.out.println("""
                1. Crear y agregar cuentas de personas y sociedades.
                2. Eliminar cuentas de personas y sociedades.
                3. Editar el saldo de cuentas de personas y sociedades.
                4. Mostrar la información de todas las cuentas almacenadas.
                5. Salir
                """);
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> {
                    System.out.println("Seleccione que tipo de cuenta desea agregar: \n1. Personas\n2. Sociedades");
                    otra_opcion = scanner.nextInt();
                    if (otra_opcion == 1) {
                        System.out.println("Ingrese el numero de cuenta: ");
                        int numero = scanner.nextInt();
                        System.out.println("Ingrese el saldo de la cuenta: ");
                        double saldo = scanner.nextDouble();
                        System.out.println("Ingrese el nombre del socio: ");
                        String nombre = scanner.next();
                        System.out.println("Ingrese el apellido: ");
                        String apellido = scanner.next();
                        Cuenta nuevoSocio = new CuentaPersona(numero, saldo, nombre, apellido);
                        cuenta.agregarCuentaPersona(nuevoSocio);
                    } else if (otra_opcion == 2) {
                        System.out.println("Ingrese el numero de cuenta: ");
                        int numero = scanner.nextInt();
                        System.out.println("Ingrese el saldo de la cuenta: ");
                        double saldo = scanner.nextDouble();
                        System.out.println("Ingrese el nombre de la empresa: ");
                        String nombreEmpresa = scanner.next();
                        System.out.println("Ingrese el tipo de empresa (S.A, S.L, etc.): ");
                        String tipoEmpresa = scanner.next();
                        Cuenta nuevoSocio = new CuentaSociedad(numero, saldo, nombreEmpresa, tipoEmpresa);
                        cuenta.agregarCuentaSociedad(nuevoSocio);
                    }
                }
                case 2 -> {
                    System.out.println("Seleccione el tipo de cuenta que desea eliminar: \n1. Personas\n2. Sociedades");
                    otra_opcion = scanner.nextInt();
                    if (otra_opcion == 1) {
                        System.out.println("Ingrese el numero de cuenta: ");
                        int numero = scanner.nextInt();
                        cuenta.eliminarCuentaPersona(numero);
                    } else if (otra_opcion == 2) {
                        System.out.println("Ingrese el numero de cuenta: ");
                        int numero = scanner.nextInt();
                        cuenta.eliminarCuentaSociedad(numero);
                    }
                }
                case 3 -> {
                    System.out.println("Seleccione el tipo de cuenta que desea editar: \n1. Personas\n2. Sociedades");
                    otra_opcion = scanner.nextInt();
                    if (otra_opcion == 1) {
                        System.out.println("Ingrese el numero de cuenta: ");
                        int numero = scanner.nextInt();
                        System.out.println("Ahora ingrese el nuevo saldo: ");
                        double saldo = scanner.nextDouble();
                        cuenta.editarCuentaPersona(numero, saldo);
                    } else if (otra_opcion == 2) {
                        System.out.println("Ingrese el numero de cuenta: ");
                        int numero = scanner.nextInt();
                        System.out.println("Ahora ingrese el nuevo saldo: ");
                        double saldo = scanner.nextDouble();
                        cuenta.editarCuentaSociedad(numero, saldo);
                    }
                }
                case 4 -> cuenta.mostrasTodasLasCuentas();
                case 5 -> {
                    System.out.println("Saliendo . . . ");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Error: ingrese una opcion valida");
            }
        }

    }
}
