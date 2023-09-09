package Serializacion;

import java.io.*;

public class Serializador {
    public void serializar(Block texto) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream("texto.txt");
            ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida);
            flujoSalida.writeObject(texto);
            flujoSalida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Block deserializar() {
        Block texto = null;
        try {
            File archivo = new File("texto.txt");

            if (archivo.exists() && archivo.length() > 0) {
                FileInputStream archivoEntrada = new FileInputStream(archivo);
                ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada);
                texto = (Block) flujoEntrada.readObject();
                flujoEntrada.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }
}
