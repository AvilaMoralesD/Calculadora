package com.dami.calculadora.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dami.calculadora.data.model.Operacion;
import com.dami.calculadora.data.repository.OperacionRepository;
import com.dami.calculadora.data.result.OperacionResult;

public class OperacionesViewmodel extends ViewModel {
    private Operacion data = null;

    private MutableLiveData<OperacionResult> result = new MutableLiveData<>();

    public void tryToOperate(String numero1, String operationType, String numero2) {
        if (isNumber1NotEmpty(numero1) && isNumber2NotEmpty(numero2) && notTryingToDivideByZero(operationType, numero2)) {
            operate(numero1, operationType, numero2);
        }
    }

    private void operate(String numero1, String operationType, String numero2) {
        data = new Operacion(Float.parseFloat(numero1), operationType, Float.parseFloat(numero2));
        OperacionRepository.getInstance().insert(data);
        result.setValue(OperacionResult.SUCCESS);
    }

    private boolean notTryingToDivideByZero(String operationType, String numero2) {
        if (operationType.equals("/") && Float.parseFloat(numero2) == 0f) {
            result.setValue(OperacionResult.DIVIDIR_POR_CERO);
            return false;
        }
        return true;
    }

    private boolean isNumber1NotEmpty(String numero1) {
        if (!numero1.isEmpty())
            return true;
        result.setValue(OperacionResult.NUM1EMPTY);
        return false;
    }

    private boolean isNumber2NotEmpty(String numero2) {
        if (!numero2.isEmpty())
            return true;
        result.setValue(OperacionResult.NUM2EMPTY);
        return false;
    }

    //Cargar la lista en este fragmento me permite que el indizador sea correcto en el siguiente fragmento.
    public void load() {
        OperacionRepository.getInstance();
    }

    public Operacion getData() {
        return data;
    }

    public MutableLiveData<OperacionResult> getResult() {
        return result;
    }

}
