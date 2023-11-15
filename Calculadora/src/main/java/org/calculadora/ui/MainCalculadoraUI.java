package org.calculadora.ui;

import org.calculadora.servicios.ServiciosCalculadora;

import java.io.PrintStream;
import java.util.Scanner;

public class MainCalculadoraUI {

    public static final String INTRODUCE_EL_PRIMER_NUMERO = "Introduce el primer numero";

    public static void main(String[] args) {


        // Quiero una calculadora con un menu en un switch con sumar, restar, dividir y multiplicar
        // Quiero que me pida dos numeros y me devuelva el resultado

        // sacar un menu a consola con las opciones de una calculadora

        Scanner scanerc = new Scanner(System.in);
        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        int opcion = 0;

        do {
            System.out.println("Bienvenido a la calculadora");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.println("Introduce una opcion");
            opcion = scanerc.nextInt();

            switch (opcion) {
                case 1:
                    suma(scanerc, System.out, serviciosCalculadora);
                    break;
                case 2:
                    resta(scanerc, System.out, serviciosCalculadora);
                    break;
                case 3:
                    multiplicación(scanerc,System.out,serviciosCalculadora);
                    break;
                case 4:
                    división(scanerc,System.out,serviciosCalculadora);
                    break;
                case 5:
                    System.out.println("Has elegido salir");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (opcion != 5);


    }

    private static void división(Scanner scanerc, PrintStream out, ServiciosCalculadora serviciosCalculadora) {
        out.println("Has elegido dividir");
        out.println(INTRODUCE_EL_PRIMER_NUMERO);
        int num7 = scanerc.nextInt();
        out.println("Introduce el segundo numero");
        int num8 = scanerc.nextInt();
        out.println("El resultado es: " + serviciosCalculadora.division(num7 , num8));
    }
    private static void multiplicación(Scanner scanerc, PrintStream out, ServiciosCalculadora serviciosCalculadora) {
        out.println("Has elegido multiplicar");
        out.println(INTRODUCE_EL_PRIMER_NUMERO);
        int num5 = scanerc.nextInt();
        out.println("Introduce el segundo numero");
        int num6 = scanerc.nextInt();
        out.println("El resultado es: " + serviciosCalculadora.multiplicacion(num5 , num6));
    }
    private static void resta(Scanner scanerc, PrintStream out, ServiciosCalculadora serviciosCalculadora) {
        out.println("Has elegido restar");
        out.println(INTRODUCE_EL_PRIMER_NUMERO);
        int num3 = scanerc.nextInt();
        out.println("Introduce el segundo numero");
        int num4 = scanerc.nextInt();
        out.println("El resultado es: " + serviciosCalculadora.resta(num3 ,num4));
    }

    public static void suma(Scanner scanerc, PrintStream out, ServiciosCalculadora serviciosCalculadora) {
        out.println("Has elegido sumar");
        out.println(INTRODUCE_EL_PRIMER_NUMERO);
        int num1 = scanerc.nextInt();
        out.println("Introduce el segundo numero");
        int num2 = scanerc.nextInt();
        out.println("El resultado es: " + serviciosCalculadora.suma(num1, num2));
    }

}
