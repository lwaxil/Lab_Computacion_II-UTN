package Practica_PrimerParcial.Sistema_Gestion_Empleados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorEmpleados gestor = new GestorEmpleados();

        while (true) {
            System.out.println("1. Agregar Empleado");
            System.out.println("2. Modificar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Mostrar Empleados");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = 0;
            try {
                opcion = scanner.nextInt();
                evaluarMenu(opcion);
            } catch (ValorInvalido e) {
                e.printStackTrace();
                System.out.println("\n");
                continue;

            }

            switch (opcion) {
                case 1 -> {
                    // agregar
                    System.out.print("Ingrese el nombre del empleado: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese el ID del empleado: ");
                    int id = scanner.nextInt();
                    System.out.print("Ingrese el sueldo base del empleado: ");
                    double sueldoBase = scanner.nextDouble();
                    System.out.println("Seleccione el tipo de empleado:");
                    System.out.println("1. Empleado por Horas");
                    System.out.println("2. Empleado Asalariado");
                    System.out.println("3. Empleado por Comisión");
                    Empleado nuevoEmpleado = null;
                    try {
                        int tipoEmpleado = scanner.nextInt();
                        evaluarTipoEmpleados(tipoEmpleado);
                        switch (tipoEmpleado) {
                            case 1 -> {
                                System.out.print("Ingrese las horas trabajadas: ");
                                int horasTrabajadas = scanner.nextInt();
                                nuevoEmpleado = new EmpleadoPorHoras(nombre, id, sueldoBase, horasTrabajadas);
                            }
                            case 2 -> nuevoEmpleado = new EmpleadoAsalariado(nombre, id, sueldoBase);
                            case 3 -> {
                                System.out.print("Ingrese las ventas realizadas: ");
                                double ventasRealizadas = scanner.nextDouble();
                                nuevoEmpleado = new EmpleadoComision(nombre, id, sueldoBase, ventasRealizadas);
                            }
                        }
                    } catch (ValorInvalido e) {
                        e.printStackTrace();
                        System.out.println("\n");
                        return;
                    }
                    if (nuevoEmpleado != null) {
                        gestor.agregarEmpleado(nuevoEmpleado);
                        System.out.println("Empleado agregado con éxito.");
                    }
                }
                case 2 -> {
                    // modificar
                    System.out.print("Ingrese el índice del empleado a modificar: ");
                    int indiceModificar = scanner.nextInt();
                    if (indiceModificar >= 0 && indiceModificar < gestor.obtenerEmpleados().size()) {
                        Empleado empleadoModificar = gestor.obtenerEmpleados().get(indiceModificar);
                        System.out.print("Ingrese el nuevo nombre del empleado: ");
                        empleadoModificar.nombre = scanner.next();
                        gestor.modificarEmpleado(indiceModificar, empleadoModificar);
                        System.out.println("Empleado modificado con éxito.");
                    }
                }
                case 3 -> {
                    // eliminar
                    System.out.print("Ingrese el índice del empleado a eliminar: ");
                    int indiceEliminar = scanner.nextInt();
                    if (indiceEliminar >= 0 && indiceEliminar < gestor.obtenerEmpleados().size()) {
                        gestor.eliminarEmpleado(indiceEliminar);
                        System.out.println("Empleado eliminado con éxito.");
                    }
                }
                case 4 -> {
                // mostrar
                    ArrayList<Empleado> empleados = gestor.obtenerEmpleados();
                    if (empleados.isEmpty()) {
                        System.out.println("No hay empleados registrados.");
                    } else {
                        System.out.println("Lista de empleados:");
                        for (int i = 0; i < empleados.size(); i++) {
                            Empleado empleado = empleados.get(i);
                            System.out.println("Índice: " + i);
                            System.out.println("Nombre: " + empleado.nombre);
                            System.out.println("ID: " + empleado.id);
                            System.out.println("Sueldo: " + empleado.calcularSueldo());
                            if (empleado instanceof Impuesto impuestoEmpleado) {
                                System.out.println("Impuesto a pagar: " + impuestoEmpleado.calcularImpuesto());
                            }
                            System.out.println("------------");
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Saliendo");
                    System.exit(0);
                }

                default ->{
                    System.out.println("Opción no válida.");
                }

            }
        }
    }
    static void evaluarMenu(int nroEvaluar) throws ValorInvalido {
        if ((nroEvaluar < 1) || (nroEvaluar > 5)) {
            throw new ValorInvalido("La opcion elegida es incorrecta");
        }
    }
    static void evaluarTipoEmpleados(int nroEvaluar) throws ValorInvalido {
        if ((nroEvaluar < 1) || (nroEvaluar > 3)) {
            throw new ValorInvalido("La opcion elegida es incorrecta. Saliendo...");
        }
    }
}

    class ValorInvalido extends IOException {
        public ValorInvalido() {
        }
        public ValorInvalido(String mensajeError) {
            super(mensajeError);
        }
}
