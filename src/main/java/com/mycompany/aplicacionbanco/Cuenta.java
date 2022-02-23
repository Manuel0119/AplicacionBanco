/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aplicacionbanco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author daw1
 */
public class Cuenta implements Comparable<Cuenta>{
   private String codigo;
   private String titular;
   private float saldo;
   List<Movimiento> movimientos;

    /**
     * Construye una nueva cuenta vacía, inicilizando el código, el titular y el saldo.
     * Además, genera una lista (ArrayList) de movimientos que guarda la fecha del movimiento percibido y el saldo actual de la cuenta.
     * @param codigo Codigo de la cuenta.
     * @param titular Titular de la cuenta.
     * @param saldo Saldo de la cuenta.
     */
   public Cuenta(String codigo, String titular, float saldo) throws SaldoException {
        if (saldo < 0) {
            throw new SaldoException("Error en el saldo");
        }
            this.codigo = codigo;
            this.titular = titular;
            this.saldo = saldo;
        movimientos = new ArrayList<>();
        movimientos.add(new Movimiento(LocalDate.now(), 'I', saldo, saldo));
        }

    /**
     * Método que devuelve el código de la cuenta solicitada.
     * @return El código de la cuenta a buscar.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Método que devuelve el titular de la cuenta solicitada.
     * @return El titular de la cuenta.
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Método que devuelve el saldo de la cuenta solicitada.
     * @return El saldo de la cuenta.
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Método que devuelve una lista con los movimentos realizados en la cuenta.
     * @return Una lista con los movimentos realizados en la cuenta.
     */
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * Método que permite listar los movimientos que se han realizado, añadiendo la fecha a la cual se realizó.
     * @param desde Fecha desde que se realizó el primer movimiento.
     * @param hasta Fecha del último movimiento.
     * @return Una lista con los movimientos que se han realizado.
     */
    public List<Movimiento> getMovimientos(LocalDate desde, LocalDate hasta){
        List<Movimiento> salida=new ArrayList<>();
        Iterator<Movimiento> li=movimientos.iterator();
        Movimiento m;
        
        while(li.hasNext()){
            m=li.next();
            if(m.getFecha().isAfter(desde) && m.getFecha().isBefore(hasta)){
                
            }
        }
        return salida;
    }
    
    /**
     * Método que nos permite modificar el codigo de una cuenta en particular.
     * @param codigo Código de la cuenta.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Método que nos permite modificar el titular de una cuenta.
     * @param titular Titular de la cuenta.
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * Método nos permite modificar un saldo de una cuenta en concreto.
     * @param saldo Saldo de la cuenta.
     */
    public void setSaldo(float saldo) throws SaldoException {
    if (saldo >= 0) {
        throw new SaldoException("Error en el saldo");
    }
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + "," + titular + "," + saldo;
    }
    
    /**
     * Método que permite ingresar dinero en la cuenta.
     * @param cantidad Cantidad a ingresar en la cuenta.
     */
    public void ingresar(float cantidad){
        if(cantidad<0){
           throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
        }
        saldo+=cantidad;
        movimientos.add(new Movimiento(LocalDate.now(),'I',cantidad,saldo));
    }
    
    /**
     * Método que permite reintegrar dinero en la cuenta.
     * @param cantidad Cantidad a reintegrar en la cuenta.
     */
    public void reintegrar(float cantidad)throws SaldoException{
        if(cantidad>saldo){
           throw new SaldoException("Saldo Insuficiente");
        }
        if(cantidad<0){
           throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
        }
        saldo-=cantidad;
        movimientos.add(new Movimiento(LocalDate.now(),'R',-cantidad,saldo));
        
    }
    
    /**
     * Metodo que nos permite realizar una transferencia de una cantidad de dinero determinadad, desde una cuenta origen a una cuenta destino.
     * Además, permite conocer la fecha a la cual se hizo la transferencia, añadiendo un movimiento a la cuenta a ambas cuentas.
     * @param destino Cuenta de destino al que se le realiza la transferencia.
     * @param cantidad Cantidad de dinero que se quiere transferir.
     */
    public void realizarTransferencia(Cuenta destino,float cantidad) throws SaldoException{
        if(cantidad>saldo){
           throw new SaldoException("Saldo Insuficiente");
        }
        if(cantidad<0){
           throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
        }
        saldo-=cantidad;
        destino.saldo+=cantidad;
        movimientos.add(new Movimiento(LocalDate.now(),'T',-cantidad,saldo));
        destino.movimientos.add(new Movimiento(LocalDate.now(),'T',cantidad,destino.saldo));
    }
    
    /**
     * Método que permite listar los movimientos de una cuenta concreta.
     * @return Movimientos que se han realizado en la cuenta, incluyendo la fecha a la que se realizó el movimiento.
     */
    public String listarMovimientos(){
        StringBuilder sb=new StringBuilder();
        
        for(Movimiento m : movimientos){
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Cuenta o) {
        return this.codigo.compareTo(o.codigo);
    }
     
}