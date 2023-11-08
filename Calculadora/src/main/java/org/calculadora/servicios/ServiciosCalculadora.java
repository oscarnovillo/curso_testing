package org.calculadora.servicios;

import org.Error.NosePuedeDivividirException;

public class ServiciosCalculadora {


    public int suma(int a, int b){
        return a+b;
    }

    public int resta(int a, int b){
        return a-b;
    }

    public int multiplicacion(int a, int b){
        return a*b;
    }

    public int division(int a, int b)  {
        if (b== 0){
            throw new NosePuedeDivividirException("/ by zero");
        }
        return a/b;
    }
}
