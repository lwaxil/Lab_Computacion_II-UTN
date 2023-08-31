<<<<<<< HEAD
package Sistema_Gestión_Empleados;

public class EmpleadoPorHoras extends Empleado implements Impuesto{
    private int horasTrabajadas;

    public EmpleadoPorHoras() {
    }

    public EmpleadoPorHoras(String nombre, int id, double sueldoBase, int horasTrabajadas) {
        super(nombre, id, sueldoBase);
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase *horasTrabajadas;
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo()*0.1;
    }
}
=======
package Sistema_Gestión_Empleados;

public class EmpleadoPorHoras extends Empleado implements Impuesto{
    private int horasTrabajadas;

    public EmpleadoPorHoras() {
    }

    public EmpleadoPorHoras(String nombre, int id, double sueldoBase, int horasTrabajadas) {
        super(nombre, id, sueldoBase);
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase *horasTrabajadas;
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo()*0.1;
    }
}
>>>>>>> b383b76e70b752beca7f19f3ac29ede24aef4237
