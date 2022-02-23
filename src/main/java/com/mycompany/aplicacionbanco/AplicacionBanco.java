/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aplicacionbanco;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

/**
 *
 * @author daw1
 */
public class AplicacionBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco banco=new Banco("Banco Sauces");
        int opcion=0,opcion2=0;
        Scanner teclado = new Scanner(System.in);
        String codigo;
        String titular;
        List<Cuenta> listado;
        float saldo;
        float cantidad;
        Cuenta cuenta1,cuenta2;
        boolean correcto;
        
        do{
            System.out.println("1. Abrir cuenta");
            System.out.println("2. Operar con cuenta");
            System.out.println("3. Cancelar cuenta");
            System.out.println("4. Listar cuentas");
            System.out.println("5. Consultar total depositos");
            System.out.println("0. Salir");
            System.out.println("Introduzca una opcion: ");
            try {
                opcion = teclado.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println(ime.getMessage());
            } finally{
                teclado.nextLine();
            }
            switch(opcion){
                case 1:
                    System.out.println("Abrir cuenta");
                    System.out.println("Introduzca el codigo de la cuenta: ");
                    codigo = teclado.nextLine();
                    System.out.println("Introduzca el titular de la cuenta: ");
                    titular = teclado.nextLine();
                    System.out.println("Introduca el saldo de la cuenta: ");
                    saldo = teclado.nextFloat();
                    teclado.nextLine();
                        try {
                            if (banco.abrirCuenta(codigo, titular, saldo)) {
                                System.out.println("Cuenta creada con éxito");
                            } else {
                                System.out.println("No se ha posido crear la cuenta");
                            }
                        } catch (SaldoException ex) {
                            System.out.println(ex.getMessage());
                        }
                    break;
                case 2:
                    System.out.println("Operar cuenta");
                    System.out.println("Introduzca el codigo de la cuenta: ");
                    codigo=teclado.nextLine();
                    cuenta1=banco.getCuenta(codigo);
                if(cuenta1!=null){
                    do{
                        System.out.println("Eliga una opción");
                        System.out.println("1. Ingresar dinero");
                        System.out.println("2. Retirar dinero");
                        System.out.println("3. Consultar saldo");
                        System.out.println("4. Realizar transferencia");
                        System.out.println("5. Consultar movimientos");
                        System.out.println("0. Salir");
                        System.out.println("Introduzca una opcion: ");
                        
                        do{
                            correcto=true;
                            try{
                                opcion2=teclado.nextInt();
                            }catch(InputMismatchException ime){
                                System.out.println("Entero");
                                correcto=false;
                            } finally{
                                teclado.nextLine();
                            }
                        }while(!correcto);
                        switch(opcion2){
                            case 1:
                                System.out.println("INGRESO DE DINERO");
                                System.out.println("Introduca la cantidad a ingresar");
                                cantidad=teclado.nextFloat();
                                teclado.nextLine();
                                try{
                                cuenta1.ingresar(cantidad);
                                System.out.println("Saldo actual: "+cuenta1.getSaldo());
                                }catch(IllegalArgumentException iae){
                                    System.out.println(iae.getMessage());
                                }
                                break;
                            case 2:
                                System.out.println("RETIRADA DE DINERO");
                                System.out.println("Introduzca la cantidad a retirar");
                                cantidad=teclado.nextFloat();
                                teclado.nextLine();
                                try {
                                    cuenta1.reintegrar(cantidad);
                                    System.out.printf("Saldo: &d\n",cuenta1.getSaldo());
                                } catch (SaldoException | IllegalArgumentException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                            case 3: 
                                System.out.println("CONSULTAR SALDO");
                                System.out.println("Saldo: "+cuenta1.getSaldo());
                                break;
                            case 4: 
                                System.out.println("REALIZAR TRANSFERENCIA");
                                System.out.println("Codigo de la cuenta a la que quieres transferir dinero: ");
                                codigo=teclado.nextLine();
                                System.out.println("Introduce la cantidad a transferrir: ");
                                cantidad=teclado.nextFloat();
                                teclado.nextLine();
                                cuenta2=banco.getCuenta(codigo);
                                if(cuenta2!=null && !cuenta1.equals(cuenta2)){
                                    try {
                                        cuenta1.realizarTransferencia(cuenta2, cantidad);
                                    } catch (SaldoException | IllegalArgumentException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                        System.out.println("Saldo de la cuenta actual: "+cuenta1.getSaldo());
                                        teclado.nextLine();  
                                }
                                else{
                                     System.out.println("No existe la cuenta de destino");       
                                    }
                                break;
                            case 5:
                                System.out.println("CONSULTAR MOVIMIENTOS");
                                System.out.println("Movimientos: "+cuenta1.listarMovimientos());
                                break;
                            case 0: 
                                break;
                        }
                        }while(opcion2!=0);
                }
                else{
                    System.out.println("NO existe una cuenta con ese código");
                }
                    break;
                        
                case 3:
                    System.out.println("Cancelar cuenta");
                    System.out.println("Introduzca el codigo de la cuenta: ");
                    codigo = teclado.nextLine();
                    if (banco.cancelarCuenta(codigo)) {
                        System.out.println("Cuanta cancelada con éxito");
                    } else {
                        System.out.println("No se ha podido cancelar cuenta");
                    }
                    break;
                case 4:
                    System.out.println("Listar cuentas");
                    listado = banco.getCuentas();
                    for(Cuenta c : listado){
                        System.out.println(c);
                    }
                    break;  
                case 5:
                    System.out.println("Consultar total depositos");
                    System.out.println(banco.getTotalDepositos());
                    break;
                case 0:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opcion incorrecta");             
            }
        }while(opcion!=0);
    }
}
