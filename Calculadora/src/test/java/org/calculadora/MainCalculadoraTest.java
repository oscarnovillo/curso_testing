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

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        //When
        MainCalculadora.main(null);

        //Then
        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        //comentario nuevo
        assertTrue(salida.contains("El resultado es: 2"));
    }
    @Test
    @DisplayName("Restar 1 y 1 para que de 1")
    void restar_1_y_1_para_que_de_0() {

        //Given
        PrintStream stdout = System.out;
        String entrada = "2\n1\n1\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        //When
        MainCalculadora.main(null);

        //Then
        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        //comentario nuevo
        assertTrue(salida.contains("El resultado es: 0"));
    }
    @Test
    @DisplayName("Multiplicar 3 y 3 para que de 9")
    void multiplicar_3_y_3_para_que_de_9() {

        //Given
        PrintStream stdout = System.out;
        String entrada = "3\n3\n3\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        //When
        MainCalculadora.main(null);

        //Then
        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        //comentario nuevo
        assertTrue(salida.contains("El resultado es: 9"));
    }
    @Test
    @DisplayName("Dividir 10 y 2 para que de 5")
    void dividir_10_y_5_para_que_de_2() {

        //Given
        PrintStream stdout = System.out;
        String entrada = "4\n10\n2\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        //When
        MainCalculadora.main(null);

        //Then
        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        //comentario nuevo
        assertTrue(salida.contains("El resultado es: 5"));
    }


}
