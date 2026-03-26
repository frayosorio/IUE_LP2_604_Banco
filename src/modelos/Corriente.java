package modelos;

import java.text.DecimalFormat;

public class Corriente extends Cuenta {

    private double sobregiro;

    public Corriente(String titular, String numero, double sobregiro) {
        super(titular, numero);
        this.sobregiro = sobregiro;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad <= getSaldo() + sobregiro) {
            setSaldo(getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "Corriente",
                getNumero(),
                getTitular(),
                df.format(getSaldo()),
                "sobregiro: $" + df.format(sobregiro)

        };
    }

    @Override
    public String toString() {
        return "Corriente [Numero=" + getNumero() + ", Titular=" + getTitular() + "]";
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
