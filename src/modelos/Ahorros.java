package modelos;

import java.text.DecimalFormat;

public class Ahorros extends Cuenta {

    private double tasaInteres;

    public Ahorros(String titular, String numero, double tasaInteres) {
        super(titular, numero);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void abonarIntereses() {
        setSaldo(getSaldo() * (1 + tasaInteres / 100));
    }

    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "AHORROS",
                getNumero(),
                getTitular(),
                "Tasa de interés " + df.format(tasaInteres) + "%",
                "$ " + df.format(getSaldo())
        };

    }

}
