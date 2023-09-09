package Practica_PrimerParcial.Consecionaria_Vehiculos;

public abstract class Vehiculo {
    protected String marca;
    protected int modelo;
    protected double precio;

    public abstract double calcularImpuesto();
    public abstract String mostrarInformacion();
}
