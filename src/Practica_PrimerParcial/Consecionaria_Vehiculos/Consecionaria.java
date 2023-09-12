package Practica_PrimerParcial.Consecionaria_Vehiculos;

import java.io.*;
import java.util.LinkedList;

public class Consecionaria implements Serializable {
    transient LinkedList<Vehiculo> vehiculos = new LinkedList<>();

    public void agregarVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(String marca, String modelo){
        for (Vehiculo vehiculo: vehiculos){
            if(vehiculo.getMarca().equals(marca) && vehiculo.getModelo().equals(modelo)) {
                vehiculos.remove(vehiculo);
                System.out.println("Vehiculo removido con exito");
            }else {
                System.out.println("No se encontro el vehiculo");
            }
        }
    }
    public void editarPrecio(String marca, String modelo, double nuevoPrecio){
        for (Vehiculo vehiculo: vehiculos){
            if(vehiculo.getMarca().equals(marca) && vehiculo.getModelo().equals(modelo)){
                vehiculo.setPrecio(nuevoPrecio);
                System.out.println("Vehiculo editado con exito");
            }else {
                System.out.println("No se encontro el vehiculo");
            }
        }
    }
    public void mostrarInventario(){
        for (Vehiculo vehiculo: vehiculos){
            vehiculo.mostrarInformacion();
        }
    }
    // Agregar métodos para serializar y deserializar estudiantes
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Llama al método predeterminado de serialización de la superclase
        out.writeObject(vehiculos); // Escribe la lista de estudiantes en el flujo de salida
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Llama al método predeterminado de deserialización de la superclase
        vehiculos = (LinkedList<Vehiculo>)  in.readObject(); // Lee la lista de estudiantes desde el flujo de entrada
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vehículos en la concesionaria:\n");
        for (Vehiculo vehiculo : vehiculos) {
            builder.append(vehiculo.toString()).append("\n");
        }
        return builder.toString();
    }

}
