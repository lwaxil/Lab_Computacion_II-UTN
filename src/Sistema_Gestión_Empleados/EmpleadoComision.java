<<<<<<< HEAD
package Sistema_Gestión_Empleados;

public class EmpleadoComision extends Empleado implements Impuesto{
    private double ventasRealizadas;

    public EmpleadoComision() {
    }

    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase + (0.3*ventasRealizadas);
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo()*0.20;
    }
}
=======
package Sistema_Gestión_Empleados;

public class EmpleadoComision extends Empleado implements Impuesto{
    private double ventasRealizadas;

    public EmpleadoComision() {
    }

    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase + (0.3*ventasRealizadas);
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo()*0.20;
    }
}
>>>>>>> b383b76e70b752beca7f19f3ac29ede24aef4237
