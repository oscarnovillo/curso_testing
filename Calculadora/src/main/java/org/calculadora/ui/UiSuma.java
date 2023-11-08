package org.calculadora.ui;

import org.calculadora.servicios.ServiciosCalculadora;

import java.io.PrintStream;
import java.util.Scanner;

public class UiSuma {
    public void suma(Scanner scanerc, PrintStream out, ServiciosCalculadora serviciosCalculadora) {
        out.println("Has elegido sumar");
        out.println(Constantes.INTRODUCE_EL_PRIMER_NUMERO);
        int num1 = scanerc.nextInt();
        out.println("Introduce el segundo numero");
        int num2 = scanerc.nextInt();
        out.println("El resultado es: " + serviciosCalculadora.suma(num1, num2));
    }
}
