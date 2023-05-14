package com.dami.calculadora.data.model;

import java.util.Comparator;

public class OperationComparatorByID implements Comparator<Operacion> {
    @Override
    public int compare(Operacion operacion, Operacion t1) {
        int orden = operacion.getTipoOperacion().compareTo(t1.getTipoOperacion());
        if (orden == 0)
            return operacion.compareTo(t1);
        else return orden;
    }
}
