package org.calculadora.ui;

import org.calculadora.servicios.ServiciosCalculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainCalculadoraUITest {


    @Mock
    ServiciosCalculadora serviciosCalculadora;
    @Test
    void main() {
    }

    @Test
    void suma() {
        Scanner sc = new Scanner("1\n1");
        serviciosCalculadora = mock(ServiciosCalculadora.class);
        var o  = new ByteArrayOutputStream(1000);
        PrintStream out = new PrintStream(o);
        when(serviciosCalculadora.suma(1,1)).thenReturn(2);

        MainCalculadoraUI.suma(sc,out,serviciosCalculadora);
        String salida = new String(o.toByteArray());

        assertThat(salida).contains("2");
    }
}
