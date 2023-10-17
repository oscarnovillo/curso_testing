package org.adivina;

import java.util.Random;
import java.util.Scanner;

public class MainAdivinaUnNumero {

    public static void main(String[] args) {


        final Random random = new Random();
        if (adviniaUnNumero(new Scanner(System.in), random.nextInt(100)))
            System.out.println("Has ganado");
        else
            System.out.println("Has perdido");


    }

    public static boolean adviniaUnNumero(Scanner scanner, int numeroAleatorio) {

        // diez oportunidades para adivinar el numero
        // si acierta, salir del bucle
        // si no acierta, decir si es mayor o menor
        // si no acierta en 10 intentos, decir que ha perdido
        boolean acertado = false;
        int intentos = 0;
        int numero = 0;
        while (intentos <10){
            System.out.println("Te quedan " + (10 - intentos) + " intentos");
            System.out.println("Introduce un numero");
            numero = scanner.nextInt();
            if (numero == numeroAleatorio){

                acertado = true;
                break;
            } else if (numero > numeroAleatorio){
                System.out.println("El numero es menor");
            } else {
                System.out.println("El numero es mayor");
            }

            intentos ++;
        }

        return acertado;
    }

}
