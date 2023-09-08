package Practica_PrimerParcial.Gestion_Cuentas;

import java.util.ArrayList;

public class GestorCuentas {
    private ArrayList <CuentaPersona> personas = new ArrayList<>();
    private ArrayList <CuentaSociedad> sociedads = new ArrayList<>();

    void agregarCuentaPersona(Cuenta persona){
        personas.add((CuentaPersona) persona);
    }
    void agregarCuentaSociedad(Cuenta sociedad){
        sociedads.add((CuentaSociedad) sociedad);
    }

    void eliminarCuentaPersona(int numeroCuenta){
        for (int i=0; i< personas.size(); i++){
            if (personas.get(i).getNumeroCuenta() == (numeroCuenta)){
                personas.remove(numeroCuenta);
                break;
            }
        }
    }
    void eliminarCuentaSociedad(int numeroCuenta){
        for (int i=0; i< sociedads.size(); i++){
            if (sociedads.get(i).getNumeroCuenta() == (numeroCuenta)){
                sociedads.remove(numeroCuenta);
                break;
            }
        }
    }

    void editarCuentaPersona(int numeroCuenta,double nuevoSaldo){
        for (CuentaPersona persona: personas){
            if (persona.getNumeroCuenta() == numeroCuenta){
                persona.setSaldo(nuevoSaldo);
            }
        }
    }
    void editarCuentaSociedad(int numeroCuenta,double nuevoSaldo){
        for (CuentaSociedad sociedad: sociedads){
            if (sociedad.getNumeroCuenta() == numeroCuenta){
                sociedad.setSaldo(nuevoSaldo);
            }
        }
    }

    void mostrasTodasLasCuentas(){
        System.out.println("Cuentas Personales: ");
        for (CuentaPersona persona: personas){
            System.out.println(persona.mostrarInformacion());
            System.out.println("*************************************************************");
        }
        System.out.println("Cuentas Empresariales: ");
        for (CuentaSociedad sociedad: sociedads){
            System.out.println(sociedad.mostrarInformacion());
            System.out.println("*************************************************************");
        }
    }
}
