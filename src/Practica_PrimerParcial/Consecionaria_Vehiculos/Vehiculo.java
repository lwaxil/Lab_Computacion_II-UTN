package Practica_PrimerParcial.Consecionaria_Vehiculos;

public abstract class Vehiculo {
    protected String marca;
    protected String modelo;
    protected double precio;

    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public abstract double calcularImpuesto();
    public abstract void mostrarInformacion();

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
