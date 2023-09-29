package Practica_PrimerParcial.Sistema_Gestion_Empleados;

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
