/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aplicacionbanco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author daw1
 */
public class Banco {
    private String nombre;
    private Set<Cuenta> cuentas;

    /**
     * Método que inicializa el nombre de la cuenta. Además guarda el nombre en un Hashset.
     * @param nombre Nombre de la cuenta.
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        cuentas=new HashSet<>();
    }

    /**
     * Método que devuleve el nombre de la cuenta solicitada.
     * @return Nombre de la cuenta solicitada.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que guarda las cuentas creadas en una lista (ArrayList).
     * @return Lista de cuentas creadas.
     */
    public List<Cuenta> getCuentas() {
        List<Cuenta> cuenta;
        cuenta =new ArrayList<>(cuentas);
        return cuenta;
    }

    /**
     * Método que modifica el nombre de la cuenta solicitada.
     * @param nombre Nombre de la cuenta.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    /**
     * Método que nos permite abrir una cuenta, estableciendole un código, un titular y un saldo.
     * @param codigo Codigo de la cuenta.
     * @param titular Titular de la cuenta.
     * @param saldo Saldo de la cuenta.
     * @return Verdadero si la cuenta ha sido creada con éxito.
     */
    public boolean abrirCuenta(String codigo, String titular,float saldo){
        return cuentas.add(new Cuenta (codigo,titular,saldo));
    }
    
    /**
     * Método que develve la cuenta en funcion del código que se le ha solicitado.
     * @param codigo Codigo de la cuenta.
     * @return Cuenta en funcion del codigo solicitado.
     */
    public Cuenta getCuenta(String codigo){
        Cuenta c=null,aux;
        Iterator<Cuenta> iterador=cuentas.iterator();
        
        for(Cuenta c1: cuentas){
           if(c1.getCodigo().equals(codigo)){
               c=c1;
               break;
           } 
        }
        return c;
    }
    
    /**
     * Método que permite cancelar una cuenta pasándole un codigo de cuenta concreto.
     * @param codigo Codigo de la cuenta.
     * @return Verdadero si la cuenta ha sido cancelada con éxito.
     */
    public boolean cancelarCuenta(String codigo){
        boolean salida=false;
        Cuenta c=getCuenta(codigo);
        
        if(c!=null){
            salida=cuentas.remove(c);
        }
        return salida;
        
    }
    
    /**
     * Método que devuelve el total de depositos de las cuentas creadas.
     * @return Total de depositos de las cuentas creadas.
     */
    public float getTotalDepositos(){
        float acumulador=0;
        for(Cuenta c : cuentas){
            acumulador+=c.getSaldo();
        }
        return acumulador;
    }
    
    
    
}
