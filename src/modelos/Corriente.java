package modelos;

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

}
