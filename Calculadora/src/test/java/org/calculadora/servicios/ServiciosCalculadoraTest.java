package org.calculadora.servicios;

import org.Error.NosePuedeDivividirException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ServiciosCalculadoraTest {


    ServiciosCalculadora sc;

    @BeforeEach
    void setUp() {
        sc = new ServiciosCalculadora();
    }



    @Test
    void suma() {


        int resultado  =  sc.suma(1,1);

        assertThat(2).isEqualTo(resultado);
    }

    @Test
    void resta() {

        int resultado  =  sc.resta(1,1);

        assertEquals(0, resultado);
    }

    @Test
    void multiplicacion() {

        int resultado  =  sc.multiplicacion(2,2);

        assertEquals(4, resultado);
    }


    @Test
    void division(){
        //Given


        //when
        Throwable t = catchThrowable(() -> sc.division(1,0));



        //then
        assertThat(t)
                .isInstanceOf(NosePuedeDivividirException.class)
                .hasMessageContaining("/ by zero");

    }

    @Test
    void divisionBien(){

        final int[] resultado = new int[1];


        Throwable t = catchThrowable(() -> {
            resultado[0] = sc.division(1, 1);
        });

        assertThat(t).doesNotThrowAnyException();
        assertThat(resultado[0]).isEqualTo(1);

        assertAll(
                () -> assertThat(t).doesNotThrowAnyException(),
                () -> assertThat(resultado[0]).isEqualTo(1)
        );

    }
}
