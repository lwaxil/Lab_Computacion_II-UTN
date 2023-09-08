package Practica_PrimerParcial.Gestion_Cuentas;

public abstract class Cuenta {
    protected int numeroCuenta;
    protected double saldo;

    public Cuenta(int numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public double getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract double depositar(double cantidad);
    public abstract  double retirar (double cantidad);
    public abstract  String mostrarInformacion();
}
