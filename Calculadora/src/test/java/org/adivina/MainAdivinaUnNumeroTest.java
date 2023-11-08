package org.adivina;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MainAdivinaUnNumeroTest {

    @Test
    @DisplayName("acierto despues de numero menor numero mayor")
    void probandoAciertas() {
        //Given
        PrintStream stdout = System.out;

        Scanner sc = new Scanner("10\n90\n80\n");


        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));


        //When
        MainAdivinaUnNumero.adivinaUnNumero(sc,80);

        //then
        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        assertThat(salida)
                .containsIgnoringCase("Has acertado")
                .contains("El numero es menor")
                .contains("El numero es mayor")
                .doesNotContainIgnoringCase("te quedan 7 intentos");



    }

    @Test
    @DisplayName("no acierto despues de 10 intentos")
    void probandoNoAcertado() {
        //Given
        PrintStream stdout = System.out;

        Scanner sc = new Scanner("10\n90\n80\n10\n90\n80\n10\n90\n80\n80\n");

        var byteArrayOutputStream  = new ByteArrayOutputStream(1000);
        System.setOut(new PrintStream(byteArrayOutputStream));


        //When
        MainAdivinaUnNumero.adivinaUnNumero(sc,56);

        //then
        String salida = byteArrayOutputStream.toString();

        System.setOut(stdout);

        assertThat(salida)
                .contains("Has perdido")
                .containsIgnoringCase("te quedan 1 intentos");



    }


}
