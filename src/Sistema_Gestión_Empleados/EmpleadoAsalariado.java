<<<<<<< HEAD
package Sistema_Gestión_Empleados;

public class EmpleadoAsalariado extends Empleado implements Impuesto{
    public EmpleadoAsalariado() {
    }

    public EmpleadoAsalariado(String nombre, int id, double sueldoBase) {
        super(nombre, id, sueldoBase);
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase;
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo()*0.15;
    }
}
=======
package Sistema_Gestión_Empleados;

public class EmpleadoAsalariado extends Empleado implements Impuesto{
    public EmpleadoAsalariado() {
    }

    public EmpleadoAsalariado(String nombre, int id, double sueldoBase) {
        super(nombre, id, sueldoBase);
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase;
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo()*0.15;
    }
}
>>>>>>> b383b76e70b752beca7f19f3ac29ede24aef4237
