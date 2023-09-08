package Practica_PrimerParcial.Gestion_Cuentas;

public  class CuentaPersona extends Cuenta{
    private String nombre;
    private String apellido;

    public CuentaPersona(int numeroCuenta, double saldo, String nombre, String apellido) {
        super(numeroCuenta, saldo);
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public double depositar(double cantidad) {
        return saldo+cantidad;
    }

    @Override
    public double retirar(double cantidad) {
        if (saldo-cantidad>=0){
            return saldo-cantidad;
        }else {
            return saldo;
        }
    }

    @Override
    public String mostrarInformacion() {
        return "Nombre: " + nombre + "\t\tApellido: " + apellido + "\nNumero de cuenta: " + numeroCuenta + "\t\tSaldo: " + saldo;
    }
}
