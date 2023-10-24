package org.calculadora;

import java.util.Scanner;

public class MainCalculadora {

    public static final String INTRODUCE_EL_PRIMER_NUMERO = "Introduce el primer numero";

    public static void main(String[] args) {


        // Quiero una calculadora con un menu en un switch con sumar, restar, dividir y multiplicar
        // Quiero que me pida dos numeros y me devuelva el resultado

        // sacar un menu a consola con las opciones de una calculadora

        Scanner scanerc = new Scanner(System.in);
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

            switch (opcion)
            {
                case 1:
                    System.out.println("Has elegido sumar");
                    System.out.println(INTRODUCE_EL_PRIMER_NUMERO);
                    int num1 = scanerc.nextInt();
                    System.out.println("Introduce el segundo numero");
                    int num2 = scanerc.nextInt();
                    System.out.println("El resultado es: " + (num1 + num2));
                    break;
                case 2:
                    System.out.println("Has elegido restar");
                    System.out.println(INTRODUCE_EL_PRIMER_NUMERO);
                    int num3 = scanerc.nextInt();
                    System.out.println("Introduce el segundo numero");
                    int num4 = scanerc.nextInt();
                    System.out.println("El resultado es: " + (num3 - num4));
                    break;
                case 3:
                    System.out.println("Has elegido multiplicar");
                    System.out.println(INTRODUCE_EL_PRIMER_NUMERO);
                    int num5 = scanerc.nextInt();
                    System.out.println("Introduce el segundo numero");
                    int num6 = scanerc.nextInt();
                    System.out.println("El resultado es: " + (num5 * num6));
                    break;
                case 4:
                    System.out.println("Has elegido dividir");
                    System.out.println(INTRODUCE_EL_PRIMER_NUMERO);
                    int num7 = scanerc.nextInt();
                    System.out.println("Introduce el segundo numero");
                    int num8 = scanerc.nextInt();
                    System.out.println("El resultado es: " + (num7 / num8));
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

}
