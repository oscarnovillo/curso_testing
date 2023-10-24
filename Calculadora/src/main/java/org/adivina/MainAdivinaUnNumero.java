package org.adivina;

import java.util.Random;
import java.util.Scanner;

public class MainAdivinaUnNumero {

    public static void main(String[] args) {


        Random r = new Random();
        int numeroAleatorio = r.nextInt(100);

        // diez oportunidades para adivinar el numero
        // si acierta, salir del bucle
        // si no acierta, decir si es mayor o menor
        // si no acierta en 10 intentos, decir que ha perdido

        int intentos = 0;
        int numero = 0;
        Scanner sc = new Scanner(System.in);
        while (intentos <10){
            System.out.println("Te quedan " + (10 - intentos) + " intentos");
            System.out.println("Introduce un numero");
            numero = sc.nextInt();
            if (numero == numeroAleatorio){
                System.out.println("Has acertado");
                break;
            } else if (numero > numeroAleatorio){
                System.out.println("El numero es menor");
            } else {
                System.out.println("El numero es mayor");
            }

            intentos ++;
        }

        if (intentos == 10){
            System.out.println("Has perdido");
        }


    }

}
