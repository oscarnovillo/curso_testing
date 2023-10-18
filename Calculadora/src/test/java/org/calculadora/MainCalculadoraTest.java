package org.calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainCalculadoraTest {

    @Test
    @DisplayName("Sumar 1 y 1 para que de 2")
    void sumar_1_y_1_para_que_de_2() {

        //Given
        PrintStream stdout = System.out;
        String entrada = "1\n1\n1\n5\n";

        String salida = getSalidaProceso(entrada);

        System.setOut(stdout);

        //comentario nuevo
        assertTrue(salida.contains("Has elegido sumar")
                    && salida.contains("El resultado es: 2"));
    }

    @Test
    void resta() {
        PrintStream stdout = System.out;
        String entrada = "2\n8\n5\n5\n";

        String salida = getSalidaProceso(entrada);
        System.setOut(stdout);

        assertTrue(salida.contains("Has elegido restar")
                && salida.contains("El resultado es: 3"));
    }

    @Test
    void multiplicar() {
        PrintStream stdout = System.out;
        String entrada = "3\n8\n5\n5\n";

        String salida = getSalidaProceso(entrada);
        System.setOut(stdout);

        assertTrue(salida.contains("Has elegido multiplicar")
                && salida.contains("El resultado es: 40"));
    }

    @Test
    void dividir() {
        PrintStream stdout = System.out;
        String entrada = "4\n8\n5\n5\n";

        String salida = getSalidaProceso(entrada);
        System.setOut(stdout);

        assertTrue(salida.contains("Has elegido dividir")
                && salida.contains("El resultado es: 1"));
    }

    @Test
    @DisplayName("Dividir entre 0")
    void dividirByCero() {
        PrintStream stdout = System.out;
        String entrada = "4\n8\n0\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        //Then
        String salida = byteArrayOutputStream.toString();
        System.setOut(stdout);

        ArithmeticException thrown = assertThrows(ArithmeticException.class, () -> MainCalculadora.main(null));

        System.out.println(thrown.getMessage());
        assertEquals("/ by zero", thrown.getMessage());
    }

    @Test
    void salir() {
        PrintStream stdout = System.out;
        String entrada = "5\n";

        String salida = getSalidaProceso(entrada);
        System.setOut(stdout);

        assertTrue(salida.contains("Has elegido salir"));
    }

    @Test
    void entradaInvalida() {
        PrintStream stdout = System.out;
        String entrada = "7\n5\n";

        String salida = getSalidaProceso(entrada);
        System.setOut(stdout);

        assertTrue(salida.contains("Opción no válida"));
    }

    private static String getSalidaProceso(String entrada) {
        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        //When
        MainCalculadora.main(null);

        //Then
        String salida = byteArrayOutputStream.toString();
        return salida;
    }
}