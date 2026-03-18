package modelos;

public class Transaccion {

    private Cuenta cuenta;
    private TipoCuenta tipo;
    private double valor;
    private double saldo;
    
    public Transaccion(Cuenta cuenta, TipoCuenta tipo, double valor, double saldo) {
        this.cuenta = cuenta;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldo() {
        return saldo;
    }

    

}
