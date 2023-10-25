package org.calculadora.servicios;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ServiciosCalculadoraTest {



    @Test
    void suma() {

        ServiciosCalculadora sc = new ServiciosCalculadora();

        int resultado  =  sc.suma(1,1);



        assertEquals(2, resultado);

    }

    @Test
    void resta() {
        ServiciosCalculadora sc = new ServiciosCalculadora();

    }

    @Test
    void division(){
        ServiciosCalculadora sc = new ServiciosCalculadora();
        Throwable t = catchThrowable(() -> sc.division(1,0));

        assertThat(t)
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("/ by zero");

    }

    @Test
    void divisionBien(){
        ServiciosCalculadora sc = new ServiciosCalculadora();
        final int[] resultado = new int[1];
        Throwable t = catchThrowable(() -> {
            resultado[0] = sc.division(1, 1);});

        assertAll(
                () -> assertThat(t).doesNotThrowAnyException(),
                () -> assertThat(resultado[0]).isEqualTo(1)
        );

    }
}
