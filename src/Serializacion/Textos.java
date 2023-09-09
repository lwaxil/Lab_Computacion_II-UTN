package Serializacion;

import java.io.Serializable;

public class Textos implements Serializable {
    protected String textos;

    public Textos(String textos) {
        this.textos = textos;
    }

    @Override
    public String toString() {
        return textos;
    }
}
