package Serializacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Block implements Serializable {
    public transient LinkedList<Textos> textos;
    String nombre;

    public Block() {
        this.textos = new LinkedList<>();
        this.nombre = nombre;
    }

    public void agregarTexto(Textos texto){
        textos.add(texto);
    }


    // Agregar métodos para serializar y deserializar estudiantes
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Llama al método predeterminado de serialización de la superclase
        out.writeObject(textos); // Escribe la lista de estudiantes en el flujo de salida
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Llama al método predeterminado de deserialización de la superclase
        textos = (LinkedList<Textos>) in.readObject(); // Lee la lista de estudiantes desde el flujo de entrada
    }
}
