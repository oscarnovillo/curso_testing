package org.calculadora;

import java.util.Scanner;

public class MainCalculadora {
public static final String BIENVENIDO="Bienvenido a la calculadora";
    public static final String OPCION_NO_VALIDA = "Opcion no valida";
    public static final String HAS_ELEGIDO_SALIR = "Has elegido salir";

    public static void main(String[] args) {


        // Quiero una calculadora con un menu en un switch con sumar, restar, dividir y multiplicar
        // Quiero que me pida dos numeros y me devuelva el resultado
    	
        // sacar un menu a consola con las opciones de una calculadora
    	
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println(BIENVENIDO);
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.println("Introduce una opcion");
            opcion = sc.nextInt();

            switch (opcion)
            {
                case 1:
                    System.out.println("Has elegido sumar");
                    System.out.println("Introduce el primer numero");
                    int num1 = sc.nextInt();
                    System.out.println("Introduce el segundo numero");
                    int num2 = sc.nextInt();
                    System.out.println("El resultado es: " + (num1 + num2));
                    break;
                case 2:
                    System.out.println("Has elegido restar");
                    System.out.println("Introduce el primer numero");
                    int num3 = sc.nextInt();
                    System.out.println("Introduce el segundo numero");
                    int num4 = sc.nextInt();
                    System.out.println("El resultado es: " + (num3 - num4));
                    break;
                case 3:
                    System.out.println("Has elegido multiplicar");
                    System.out.println("Introduce el primer numero");
                    int num5 = sc.nextInt();
                    System.out.println("Introduce el segundo numero");
                    int num6 = sc.nextInt();
                    System.out.println("El resultado es: " + (num5 * num6));
                    break;
                case 4:
                    System.out.println("Has elegido dividir");
                    System.out.println("Introduce el primer numero");
                    int num7 = sc.nextInt();
                    System.out.println("Introduce el segundo numero");
                    int num8 = sc.nextInt();
                    System.out.println("El resultado es: " + (num7 / num8));
                    break;
                case 5:
                    System.out.println(HAS_ELEGIDO_SALIR);
                    break;
                default:
                    System.out.println(OPCION_NO_VALIDA);
                    break;
            }

        } while (opcion != 5);


    }

}
