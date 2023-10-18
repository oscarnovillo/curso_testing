package org.calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainCalculadoraTest {

    @Test
    @DisplayName("Sumar 0 y 0 para que de 0")
    void sumar_1_y_1_para_que_de_2() {

        //Given
        PrintStream stdout = System.out;
        String entrada = "1\n0\n0\n5";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        //When
        MainCalculadora.main(null);

        //Then
        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);


        assertTrue(salida.contains("El resultado es: 0"));
    }
}
