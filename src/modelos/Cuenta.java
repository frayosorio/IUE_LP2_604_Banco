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

    // Metodo accesible solo por las clases HIJAs
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Metodos que deben implementar las clases HIJAs
    public abstract boolean retirar(double valor);

    public abstract String[] getDatos();

    public boolean depositar(double valor) {
        if (valor > 0) {
            setSaldo(saldo + valor);
            return true;
        }
        return false;
    }

}
