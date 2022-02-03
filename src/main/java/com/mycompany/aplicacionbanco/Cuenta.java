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
public class Cuenta {
   private String codigo;
   private String titular;
   private float saldo;
   List<Movimiento> movimientos;

    /**
     * 
     * @param codigo
     * @param titular
     * @param saldo
     */
    public Cuenta(String codigo, String titular, float saldo) {
        this.codigo = codigo;
        this.titular = titular;
        if(saldo>=0){
            this.saldo = saldo;
        }
        movimientos=new ArrayList<>();
        movimientos.add(new Movimiento(LocalDate.now(),'I',saldo,saldo));
    }

    /**
     *
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @return
     */
    public String getTitular() {
        return titular;
    }

    /**
     *
     * @return
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     *
     * @return
     */
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * 
     * @param desde
     * @param hasta
     * @return
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
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @param titular
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     *
     * @param saldo
     */
    public void setSaldo(float saldo) {
        if(saldo>=0){
            this.saldo = saldo;
        }
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
     * Método que permite ingresar dinero en la cuenta
     * @param cantidad cantidad a ingresar en la cuenta
     */
    public void ingresar(float cantidad){
        if(cantidad>0){
            saldo+=cantidad;
            movimientos.add(new Movimiento(LocalDate.now(),'I',cantidad,saldo));
        }
        else{
            System.out.println("No se ha podido ingresar");
        }
    }
    
    /**
     *
     * @param cantidad
     */
    public void reintegrar(float cantidad){
        if(cantidad>0 && cantidad<=saldo){
            saldo-=cantidad;
            movimientos.add(new Movimiento(LocalDate.now(),'R',-cantidad,saldo));
        }
        else{
            System.out.println("No se ha podido ingresar");
        }
    }
    
    /**
     *
     * @param destino
     * @param cantidad
     */
    public void realizarTransferencia(Cuenta destino,float cantidad){
        if(cantidad>0 && cantidad<=saldo){
            saldo-=cantidad;
            destino.saldo+=cantidad;
            movimientos.add(new Movimiento(LocalDate.now(),'T',-cantidad,saldo));
            destino.movimientos.add(new Movimiento(LocalDate.now(),'T',cantidad,destino.saldo));
        }
    }
    
    /**
     *
     * @return
     */
    public String listarMovimientos(){
        StringBuilder sb=new StringBuilder();
        
        for(Movimiento m : movimientos){
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }
    
    
}
