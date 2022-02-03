/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aplicacionbanco;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw1
 */
public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;

    /**
     *
     * @param nombre
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        cuentas=new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    /**
     *
     * @param codigo
     * @param titular
     * @param saldo
     * @return
     */
    public boolean abrirCuenta(String codigo, String titular,float saldo){
        boolean salida=false;
        
        if(buscarCuenta(codigo)==-1){
            salida=cuentas.add(new Cuenta (codigo,titular,saldo));
        }
        return salida;
    }
    
    /**
     *
     * @param codigo
     * @return
     */
    public Cuenta getCuenta(String codigo){
        Cuenta c=null;
        int posicion;
        
        posicion=buscarCuenta(codigo);
        if(posicion!=-1){
            c=cuentas.get(posicion);
        }
        return c;
    }
    
    /**
     *
     * @param codigo
     * @return
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
     *
     * @return
     */
    public float getTotalDepositos(){
        float acumulador=0;
        for(Cuenta c : cuentas){
            acumulador+=c.getSaldo();
        }
        return acumulador;
    }
    
    private int buscarCuenta(String codigo){
       int posicion=-1;
       
       for(int i=0;i<cuentas.size() && posicion==-1;i++){
           if(cuentas.get(i).getCodigo().equals(codigo)){
               posicion=i;
           }
       }
       return posicion; 
    }
    
    
}
