package org.calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class MainCalculadoraTest {

    @Test
    @DisplayName("Sumar 1 y 1 para que de 2")
    void sumar_1_y_1_para_que_de_2() {

        //Given
        PrintStream stdout = System.out;
        String entrada = "1\n1\n1\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream = new ByteArrayOutputStream(1000);
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
    @DisplayName("Restar 7 y 3 para que de 4")
    void restar_7_y_3_para_que_de_4() {

        //Given
        PrintStream stdout = System.out;
        String entrada = "2\n7\n3\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        //When
        MainCalculadora.main(null);

        //Then
        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        //comentario nuevo
        assertTrue(salida.contains("El resultado es: 4"));
    }

    // User selects option 1 (sum), inputs two numbers, and receives correct sum
    @Test
    public void test_sum_option() {
        PrintStream stdout = System.out;
        String entrada = "1\n2\n3\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        MainCalculadora.main(null);

        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        assertTrue(salida.contains("El resultado es: 5"));
    }

    // User selects option 2 (subtract), inputs two numbers, and receives correct subtraction
    @Test
    public void test_subtract_option() {
        PrintStream stdout = System.out;
        String entrada = "2\n5\n3\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        MainCalculadora.main(null);

        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        assertTrue(salida.contains("El resultado es: 2"));
    }

    // User selects option 3 (multiply), inputs two numbers, and receives correct multiplication
    @Test
    public void test_multiply_option() {
        PrintStream stdout = System.out;
        String entrada = "3\n2\n3\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        MainCalculadora.main(null);

        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        assertTrue(salida.contains("El resultado es: 6"));
    }

    // User inputs non-integer value for menu option, program handles gracefully
    @Test
    public void test_non_integer_menu_option() {
        PrintStream stdout = System.out;
        String entrada = "a\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        assertThrows(InputMismatchException.class, () -> MainCalculadora.main(null));
    }

    // User inputs non-integer value for number input, program handles gracefully
    @Test
    public void test_non_integer_number_input() {
        PrintStream stdout = System.out;
        String entrada = "1\na\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));
        assertThrows(InputMismatchException.class, () -> MainCalculadora.main(null));
    }

    // User selects option 4 (divide) and inputs 0 as second number, program handles gracefully
    @Test
    public void test_divide_option_with_zero() {
        PrintStream stdout = System.out;
        String entrada = "4\n5\n0\n5\n";

        System.setIn(new ByteArrayInputStream(entrada.getBytes()));

        var byteArrayOutputStream = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));

        assertThrows(ArithmeticException.class, () -> MainCalculadora.main(null));
    }

}


