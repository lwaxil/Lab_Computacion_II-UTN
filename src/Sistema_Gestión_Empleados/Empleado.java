package Sistema_Gestión_Empleados;

public abstract class  Empleado {
    protected String nombre;
    protected int id;
    protected double sueldoBase;

    public Empleado() {
    }

    public Empleado(String nombre, int id, double sueldoBase) {
        this.nombre = nombre;
        this.id = id;
        this.sueldoBase = sueldoBase;
    }

    public abstract double calcularSueldo();

}
