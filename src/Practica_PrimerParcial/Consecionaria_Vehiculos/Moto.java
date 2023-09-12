package Practica_PrimerParcial.Consecionaria_Vehiculos;

public class Moto extends Vehiculo {

    public Moto(String marca, String modelo, double precio) {
        super(marca, modelo, precio);
    }

    @Override
    public double calcularImpuesto() {
        return 0;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Motos: \nMarca: " + marca + "\nModelo: " + modelo + "\nPrecio: " + precio);
        System.out.println("***************************************************");
    }
}
