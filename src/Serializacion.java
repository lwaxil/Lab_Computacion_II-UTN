import java.io.*;
import java.util.ArrayList;

abstract class Persona{
    protected String nombre;
    protected String apellido;

    public Persona() {
    }

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
public class Serializacion {
    ArrayList <Persona> personas = new ArrayList<>();
    protected void Serializar (String nombreArchivo){
        try {
            FileOutputStream file = new FileOutputStream(nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(personas);
            out.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Error al serializar");
        }
    }
    protected void Deserializar (String nombreArchivo){
        try {
            FileInputStream file = new FileInputStream(nombreArchivo);
            ObjectInputStream in = new ObjectInputStream(file);
            personas = (ArrayList<Persona>) in.readObject();
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar");
        }
    }

    public static void main(String[] args) {
        //crear el archivo en caso de que no exista
        File archivo = new File("nombre.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
