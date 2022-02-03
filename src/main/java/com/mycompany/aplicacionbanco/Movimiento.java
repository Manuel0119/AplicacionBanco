/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aplicacionbanco;

import java.time.LocalDate;

/**
 *
 * @author daw1
 */
public class Movimiento {
    private LocalDate fecha;
    private char tipo;
    private float cantidad;
    private float saldo;

    /**
     *
     * @param fecha
     * @param tipo
     * @param cantidad
     * @param saldo
     */
    public Movimiento(LocalDate fecha, char tipo, float cantidad, float saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
    }

    /**
     *
     * @return
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     *
     * @return
     */
    public char getTipo() {
        return tipo;
    }

    /**
     *
     * @return
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     *
     * @return
     */
    public float getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return fecha + "," + tipo + "," + cantidad + "," + saldo;
    }
    
    
}
