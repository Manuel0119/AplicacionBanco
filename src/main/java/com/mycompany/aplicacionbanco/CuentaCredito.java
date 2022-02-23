/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aplicacionbanco;

/**
 *
 * @author daw1
 */
public class CuentaCredito extends Cuenta {
    private float limitecredito;

    /**
     * Construye una nueva cuenta vacía reutilizando el constructor de la clase padre que es Cuenta, inicilizando el código, el titular, el saldo y el límite de credito.
     * @param codigo Codigo de la cuenta.
     * @param titular Titular de la cuenta.
     * @param saldo Saldo de la cuenta.
     * @param limitecredito Limite de credito.
     */
    public CuentaCredito(String codigo, String titular, float saldo, float limitecredito) throws SaldoException {
        super(codigo, titular, saldo);
        this.limitecredito = limitecredito;
    }

    /**
     * Método que devuelve el límite de credito de una cuenta.
     * @return Limite de credito.
     */
    public float getLimitecredito() {
        return limitecredito;
    }

    /**
     * Método que permite establecer el límite de credito de una cuenta.
     * @param limitecredito Limite credito.
     */
    public void setLimitecredito(float limitecredito) {
        this.limitecredito = limitecredito;
    }

    /**
     * Método que reutiliza el metodo reintegrar, incluyendo el limite de credito y un nuevo saldo.
     * @param cantidad Cantidad a reintegrar en la cuenta.
     */
    @Override
    public void reintegrar(float cantidad) throws SaldoException {
        float nuevoSaldo;
        if(cantidad>0){        
            nuevoSaldo=getSaldo()-cantidad;
            if(nuevoSaldo>=limitecredito){
                setSaldo(nuevoSaldo);
            }
        } 
    }

    @Override
    public String toString() {
        return super.toString()+","+limitecredito;
    }
 
}