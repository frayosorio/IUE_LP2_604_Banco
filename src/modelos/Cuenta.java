package modelos;

public abstract class Cuenta {

    private String titular;
    private double saldo;
    private String numero;

    public Cuenta(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = 0;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract boolean retirar(double valor);

    public boolean depositar(double valor) {
        if (valor > 0) {
            setSaldo(saldo + valor);
            return true;
        }
        return false;
    }

}
