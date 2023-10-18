package org.calculadora;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainCalculadoraTest {

    @Test
    void sumar() {
        String[][] operaciones = {
                {"1\n1\n1\n5\n",": 2"},
                {"2\n1\n2\n5\n",": -1"},
                {"3\n3\n3\n5\n",": 9"},
                {"4\n10\n3\n5\n",": 3"},
        };
        PrintStream stdout = System.out;
        //String entrada = "1\n1\n1\n5\n";

        for (String[] caso : operaciones) {
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
