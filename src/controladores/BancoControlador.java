package controladores;

import modelos.TipoCuenta;
import servicios.CuentaServicio;
import vistas.BancoVista;

public class BancoControlador {

    private BancoVista vista;

    public BancoControlador(BancoVista vista) {
        this.vista = vista;
        this.vista.setGuardarCuentaClick(evento -> agregarCuenta());
        this.vista.setEliminarCuentaClick(evento -> quitarCuenta());
        mostrarCuentas();
    }

    private void mostrarCuentas() {
        vista.mostrarCuentas(CuentaServicio.getDatos(), CuentaServicio.getEncabezados());
    }

    private void agregarCuenta() {
        var tipo = vista.getTipoCuentaSeleccionada();
        var titular = vista.getTitular();
        var numero = vista.getNumero();
        var tasa = tipo == TipoCuenta.AHORROS || tipo == TipoCuenta.CREDITO
                ? vista.getTasaInteres()
                : 0;
        var sobregiro = tipo == TipoCuenta.CORRIENTE
                ? vista.getValor()
                : 0;
        var valorPrestado = tipo == TipoCuenta.CREDITO
                ? vista.getValor()
                : 0;
        var plazo = tipo == TipoCuenta.CREDITO
                ? vista.getPlazo()
                : 0;

        CuentaServicio.agregar(tipo, titular, numero, tasa, sobregiro, valorPrestado, plazo);

        mostrarCuentas();

        vista.ocultarPanelEditarCuenta();
    }

    private void quitarCuenta(){
        if(vista.getFilaCuentaSeleccionada()>=0){
            if(vista.confirmar("¿Está seguro de eliminar la cuenta?")){
                CuentaServicio.eliminar(vista.getFilaCuentaSeleccionada());
                mostrarCuentas();
            }
        }
        else{
            vista.mostrarMensaje("Debe seleccionar una cuenta");
        }
    }
}
