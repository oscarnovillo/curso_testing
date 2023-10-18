package org.calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainCalculadoraTest {

    @Test
  //  @DisplayName("Sumar 1 y 1 para que de 2")
    @DisplayName("Suma de 1 y uno que da 2")
    void sumar() {
        PrintStream stdout = System.out;
        String entrada = "1\n1\n1\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        MainCalculadora.main(null);

        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);


        assertTrue(salida.contains("El resultado es: 2"));
    }
}
