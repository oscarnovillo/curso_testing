package org.calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainCalculadoraTest {

    @Test
    @DisplayName("Pruebas de operaciones sobre la calculadora")
    // matriz donde cada fila se compone de dos String: el texto de las operaciones a mandar
    // a la calculadora y el texto a buscar en el resutlado para considerarlo correcto
    void pruebasOperacionesCalculadora() {
        String[][] entradasOperacionesYResultadosDeSalida = {
                {"1\n1\n1\n5\n",": 2"},
                {"2\n1\n2\n5\n",": -1"},
                {"3\n3\n3\n5\n",": 9"},
                {"4\n10\n3\n5\n",": 3"},
        };
        PrintStream stdout = System.out;
        //String entrada = "1\n1\n1\n5\n";

        for (String[] caso : entradasOperacionesYResultadosDeSalida) {
            System.setIn(new ByteArrayInputStream(caso[0].getBytes()));

            var byteArrayOutputStream = new ByteArrayOutputStream(1000);
            System.setOut(new PrintStream(byteArrayOutputStream));

            MainCalculadora.main(null);

            String salida = byteArrayOutputStream.toString();

            System.setOut(stdout);

            //assertEquals("El resultado es: 2\nHas elegido salir\n",salida);
            assertTrue(salida.contains(caso[1]));
        } // for :
    }
}
