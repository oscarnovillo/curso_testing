package org.calculadora.ui;

import org.calculadora.servicios.ServiciosCalculadora;

import java.io.PrintStream;
import java.util.Scanner;

public class UiMenu {


    public void menu(Scanner scanerc,UiSuma ui, PrintStream out, ServiciosCalculadora serviciosCalculadora)
    {
        int opcion = 0;

        do {
            out.println("Bienvenido a la calculadora");
            out.println("1. Sumar");
            out.println("2. Restar");
            out.println("3. Multiplicar");
            out.println("4. Dividir");
            out.println("5. Salir");
            out.println("Introduce una opcion");
            opcion = scanerc.nextInt();

            switch (opcion) {
                case 1:

                    ui.suma(scanerc, out, serviciosCalculadora);
                    break;
                case 2:
                    resta(scanerc, serviciosCalculadora);
                    break;
                case 3:
                    out.println("Has elegido multiplicar");
                    out.println(Constantes.INTRODUCE_EL_PRIMER_NUMERO);
                    int num5 = scanerc.nextInt();
                    out.println("Introduce el segundo numero");
                    int num6 = scanerc.nextInt();
                    out.println("El resultado es: " + (num5 * num6));
                    break;
                case 4:
                    out.println("Has elegido dividir");
                    out.println(Constantes.INTRODUCE_EL_PRIMER_NUMERO);
                    int num7 = scanerc.nextInt();
                    out.println("Introduce el segundo numero");
                    int num8 = scanerc.nextInt();
                    out.println("El resultado es: " + (num7 / num8));
                    break;
                case 5:
                    out.println("Has elegido salir");
                    break;
                default:
                    out.println("Opcion no valida");
                    break;
            }

        } while (opcion != 5);
    }

    private void resta(Scanner scanerc, ServiciosCalculadora serviciosCalculadora) {
        System.out.println("Has elegido restar");
        System.out.println(Constantes.INTRODUCE_EL_PRIMER_NUMERO);
        int num3 = scanerc.nextInt();
        System.out.println("Introduce el segundo numero");
        int num4 = scanerc.nextInt();
        System.out.println("El resultado es: " + serviciosCalculadora.resta(num3 ,num4));
    }
}
