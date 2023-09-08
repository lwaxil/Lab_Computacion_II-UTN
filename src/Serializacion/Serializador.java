package Serializacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializador {
    public void serializar(String texto) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream("texto.txt");
            ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida);
            flujoSalida.writeObject(texto);
            flujoSalida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String deserializar() {
        String bloc =" ";
        try {
            FileInputStream archivoEntrada = new FileInputStream("texto.txt");
            ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada);
            bloc = (String) flujoEntrada.readObject();
            flujoEntrada.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bloc;
    }
}
