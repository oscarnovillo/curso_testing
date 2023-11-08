package org.calculadora.ui;

import org.calculadora.servicios.ServiciosCalculadora;

import java.util.Scanner;

public class MainCalculadoraUI {


    public static void main(String[] args) {


        // Quiero una calculadora con un menu en un switch con sumar, restar, dividir y multiplicar
        // Quiero que me pida dos numeros y me devuelva el resultado

        // sacar un menu a consola con las opciones de una calculadora

        Scanner scanerc = new Scanner(System.in);
        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        UiMenu uiMenu = new UiMenu();

        uiMenu.menu(scanerc,  new UiSuma(),System.out,serviciosCalculadora);


    }



}
