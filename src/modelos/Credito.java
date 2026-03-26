package modelos;

import java.text.DecimalFormat;

public class Credito extends Cuenta {

    private double valorPrestado;
    private double tasaInteres;
    private int plazo;
    private double valorRetirado;

    public Credito(String titular, String numero, double valorPrestado, double tasaInteres, int plazo) {
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
        return valorPrestado - getValorRetirado();
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= getSaldoRetiro()) {
            valorRetirado += cantidad;
            return true;
        }
        return false;
    }

    public double getCuota() {
        double tasaReal = tasaInteres / 100;
        return valorPrestado * Math.pow(1 + tasaReal, plazo) * tasaReal / (Math.pow(1 + tasaReal, plazo) - 1);
    }

    private double getSaldoDeuda() {
        return valorPrestado - getSaldo();
    }

    public boolean pagar(double cantidad) {
        if (cantidad > 0 && getSaldoDeuda() > 0) {
            var intereses = getSaldoDeuda() * tasaInteres / 100;
            var abonoCapital = cantidad - intereses;
            if (abonoCapital > 0) {
                setSaldo(getSaldo() + abonoCapital);
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "Crédito",
                getNumero(),
                getTitular(),
                "Saldo adeudado: $" + df.format(getSaldoDeuda()),
                "Valor: $" + df.format(valorPrestado) +
                        " plazo:" + plazo +
                        " tasa:" + df.format(tasaInteres) + "%" +
                        " cuota: %" + df.format(getCuota()),
        };
    }

    @Override
    public String toString() {
        return "Crédito [Numero=" + getNumero() + ", Titular=" + getTitular() + "]";
    }

    @Override
    public boolean procesarTransaccion(TipoTransaccion tipo, double valor) {
        switch (tipo) {
            case DEPOSITAR:
                return pagar(valor);
            case RETIRAR:
                return retirar(valor);
        }
        return false;
    }

    @Override
    public double getSaldoPorTransaccion(TipoTransaccion tipo) {
        return (tipo == TipoTransaccion.RETIRAR) ? getSaldoRetiro() : getSaldoDeuda();
    }

}
