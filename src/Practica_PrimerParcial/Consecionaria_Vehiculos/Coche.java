package Practica_PrimerParcial.Consecionaria_Vehiculos;

import java.io.Serializable;

public class Coche extends Vehiculo implements Serializable {

    public Coche() {
    }

    public Coche(String marca, String modelo, double precio) {
        super(marca, modelo, precio);
    }



    @Override
    public double calcularImpuesto() {
        return 0;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Coches: \nMarca: " + marca + "\nModelo: " + modelo + "\nPrecio: " + precio);
        System.out.println("***************************************************");
    }
    @Override
    public String toString() {
        return "Coche:\nMarca: " + marca + "\nModelo: " + modelo + "\nPrecio: " + precio;
    }

}
