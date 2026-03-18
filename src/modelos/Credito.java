package modelos;

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

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad < valorPrestado - valorRetirado) {
            valorRetirado += cantidad;
            return true;
        }
        return false;
    }

    public double getCuota() {
        double tasaReal = tasaInteres / 100;
        return valorPrestado * (1 + tasaReal) * tasaReal / ((1 + tasaReal) - 1);
    }

    private double getSaldoDeuda() {
        return valorPrestado - getSaldo();
    }

    public void pagar(double cantidad) {
        if (getSaldo() < valorPrestado) {
            var intereses = getSaldoDeuda() * tasaInteres / 100;
            var abonoCapital = cantidad - intereses;
            if (abonoCapital > 0)
                setSaldo(getSaldo() + abonoCapital);
        }
    }

}
