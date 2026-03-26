package modelos;

import java.text.DecimalFormat;

public class Ahorro extends Cuenta {

    private double tasaInteres;

    public Ahorro(String titular, String numero, double tasaInteres) {
        super(titular, numero);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad <= getSaldo()) {
            setSaldo(getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    public void abonarIntereses() {
        setSaldo(getSaldo() * (1 + tasaInteres / 100));
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "Ahorros",
                getNumero(),
                getTitular(),
                df.format(getSaldo()),
                "tasa:" + df.format(tasaInteres)+ " %"

        };
    }

    @Override
    public String toString() {
        return "Ahorros [Numero=" + getNumero() + ", Titular=" + getTitular() + "]";
    }

    @Override
    public boolean procesarTransaccion(TipoTransaccion tipo, double valor) {
        switch (tipo) {
            case DEPOSITAR:
                return depositar(valor);
            case RETIRAR:
                return retirar(valor);
        }
        return false;
    }

}
