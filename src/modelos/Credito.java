package modelos;

import java.text.DecimalFormat;

public class Credito extends Cuenta {
    private double valorPrestado;
    private double tasaInteres;
    private int plazo;
    private double valorRetirado;

    public Credito(String titular, String numero,
            double valorPrestado, double tasaInteres, int plazo) {
        super(titular, numero);
        this.valorPrestado = valorPrestado;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
        this.valorRetirado = 0;
    }

    public double getValorPrestado() {
        return valorPrestado;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public int getPlazo() {
        return plazo;
    }

    public double getValorRetirado() {
        return valorRetirado;
    }

    public double getSaldoRetiro() {
        return valorPrestado - valorRetirado;
    }

    public double getCuota() {
        double tasa = tasaInteres / 100;
        double factor = Math.pow(1 + tasa, plazo);
        return valorPrestado * factor * tasa / (factor - 1);
    }

    public double getSaldoDeuda() {
        return valorPrestado - getSaldo();
    }

    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= getSaldoRetiro()) {
            valorRetirado += valor;
            return true;
        }
        return false;
    }

    public boolean pagar(double valor) {
        if (valor > 0 && getSaldoDeuda() > 0) {
            var intereses = getSaldoDeuda() * tasaInteres / 100;
            var abonaCapital = valor - intereses;
            // el abono a capital no puede superar el saldo de la deuda
            if (abonaCapital <= getSaldoDeuda()) {
                return depositar(abonaCapital);
            }
        }
        return false;
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "CRÉDITO",
                getNumero(),
                getTitular(),
                "Valor Préstamo $" + df.format(valorPrestado) + " Tasa Interés " + df.format(tasaInteres)
                        + "% Plazo " + plazo + " Cuota $" + df.format(getCuota()),
                "Saldo Adeudado $ " + df.format(getSaldoDeuda()) + " Saldo Retiro $ " + df.format(getSaldoRetiro())
        };
    }

}
