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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UiSumaTest {

    //class under test
    UiSuma ui  = new UiSuma();

    @Mock
    ServiciosCalculadora serviciosCalculadora;
    @Test
    void main() {
    }



    @Test
    void suma() {
        Scanner sc = new Scanner("1\n1");

        var o  = new ByteArrayOutputStream(1000);
        PrintStream out = new PrintStream(o);
        when(serviciosCalculadora.suma(1,1)).thenReturn(2);

        ui.suma(sc,out,serviciosCalculadora);
        String salida = new String(o.toByteArray());

        assertThat(salida).contains("2");
    }
    @Test
    void sumaConError(){
        Scanner sc = new Scanner("1\n1");

        var o  = new ByteArrayOutputStream(1000);
        PrintStream out = new PrintStream(o);
        when(serviciosCalculadora.suma(1,1)).thenReturn(2);

        ui.suma(sc,out,serviciosCalculadora);
        String salida = new String(o.toByteArray());

        assertThat(salida).contains("2");
    }
}
