package Practica_PrimerParcial.Consecionaria_Vehiculos;

import java.io.*;

public class Serializador {
    public void guardar(Consecionaria consecionaria) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream("consecionaria.txt");
            ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida);
            flujoSalida.writeObject(consecionaria);
            flujoSalida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Consecionaria cargar() {
        Consecionaria texto = null;
        try {
            File archivo = new File("consecionaria.txt");

            if (archivo.exists() && archivo.length() > 0) {
                FileInputStream archivoEntrada = new FileInputStream(archivo);
                ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada);
                texto = (Consecionaria) flujoEntrada.readObject();
                flujoEntrada.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }
}
