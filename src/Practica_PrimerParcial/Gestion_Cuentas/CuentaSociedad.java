package Practica_PrimerParcial.Gestion_Cuentas;

public class CuentaSociedad extends Cuenta{
    private String nombreEmpresa;
    private String tipoEmpresa;

    public CuentaSociedad(int numeroCuenta, double saldo, String nombreEmpresa, String tipoEmpresa) {
        super(numeroCuenta, saldo);
        this.nombreEmpresa = nombreEmpresa;
        this.tipoEmpresa = tipoEmpresa;
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
        return "Numero de cuenta: " + numeroCuenta +"\t\tNombre de la empresa: " + nombreEmpresa + "\nTipo de empresa: " + tipoEmpresa +  "\t\tSaldo: " + saldo;
    }
}
