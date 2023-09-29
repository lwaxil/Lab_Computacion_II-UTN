package Practica_PrimerParcial.Sistema_Gestion_Empleados;

public abstract class  Empleado {
    public String nombre;
    public int id;
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
