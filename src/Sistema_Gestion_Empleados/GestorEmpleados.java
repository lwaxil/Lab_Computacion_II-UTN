package Sistema_Gestion_Empleados;

import java.util.ArrayList;

public class GestorEmpleados {
    private ArrayList<Empleado> empleados;

    public GestorEmpleados() {
        empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void modificarEmpleado(int indice, Empleado empleado) {
        empleados.set(indice, empleado);
    }

    public void eliminarEmpleado(int indice) {
        empleados.remove(indice);
    }

    public ArrayList<Empleado> obtenerEmpleados() {
        return empleados;
    }
}
