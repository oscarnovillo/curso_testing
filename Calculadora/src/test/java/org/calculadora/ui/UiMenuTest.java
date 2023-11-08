package org.calculadora.ui;

import org.calculadora.servicios.ServiciosCalculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UiMenuTest {

    @Test
    void menu() {
        //Given
        UiMenu uiMenu = new UiMenu();
        Scanner sc = new Scanner("1\n1\n1\n5\n");
        UiSuma uiSuma = mock(UiSuma.class);
        ServiciosCalculadora serviciosCalculadora = new ServiciosCalculadora();
        var o  = new ByteArrayOutputStream(1000);
        PrintStream out = new PrintStream(o);
       // when(uiSuma.suma(any(),any(),any())).thenReturn(2);

        //When
        uiMenu.menu(sc,uiSuma,out,serviciosCalculadora);

        //Then
        String salida = new String(o.toByteArray());

        assertThat(salida).contains("2");


    }
}
