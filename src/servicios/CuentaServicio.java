package servicios;

import java.util.ArrayList;
import java.util.List;

import modelos.Ahorros;
import modelos.Corriente;
import modelos.Credito;
import modelos.Cuenta;
import modelos.TipoCuenta;

public class CuentaServicio {

    private static String[] encabezados = new String[] { "Tipo", "Número", "Titular", "Parámetros",
            "Saldos" };

    private static List<Cuenta> cuentas = new ArrayList<>();

    public static String[] getEncabezados() {
        return encabezados;
    }

    public static Cuenta agregar(TipoCuenta tipo,
            String titular,
            String numero,
            double tasa,
            double sobregiro,
            double valorPrestado,
            int plazo) {
        Cuenta cuenta = null;
        switch (tipo) {
            case AHORROS:
                cuenta = new Ahorros(titular, numero, tasa);
                break;
            case CORRIENTE:
                cuenta = new Corriente(titular, numero, sobregiro);
                break;
            case CREDITO:
                cuenta = new Credito(titular, numero, valorPrestado, tasa, plazo);
                break;
        }
        if (cuenta != null) {
            cuentas.add(cuenta);
        }
        return cuenta;
    }

    public static String[][] getDatos() {
        String[][] datos = new String[cuentas.size()][encabezados.length];
        int fila = 0;
        for (var cuenta : cuentas) {
            int columna = 0;
            for (var dato : cuenta.getDatos()) {
                if (columna < encabezados.length) {
                    datos[fila][columna] = dato;
                }
                columna++;
            }
            fila++;
        }
        return datos;
    }

    public static boolean eliminar(int posicion) {
        if (posicion >= 0 && posicion < cuentas.size()) {
            cuentas.remove(posicion);
            return true;
        }
        return false;
    }

}
