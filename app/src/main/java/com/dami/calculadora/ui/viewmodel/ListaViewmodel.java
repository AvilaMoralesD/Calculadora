package com.dami.calculadora.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dami.calculadora.data.model.Operacion;
import com.dami.calculadora.data.repository.OperacionRepository;
import com.dami.calculadora.data.result.OperacionListResult;

import java.util.ArrayList;

public class ListaViewmodel extends ViewModel {

    private MutableLiveData<ArrayList<Operacion>> data = new MutableLiveData<>();
    private MutableLiveData<OperacionListResult> result = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Operacion>> getData() {
        return data;
    }

    public MutableLiveData<OperacionListResult> getResult() {
        return result;
    }

    /**
     * Este metodo devuelve los datos de Operacion de la base de datos si los hubiera.
     * Le dice a la interfaz que no hay datos si no los hay
     */
    public void load() {
        data.setValue((ArrayList<Operacion>) OperacionRepository.getInstance().selectAll());
        if (data.getValue().isEmpty())
            result.setValue(OperacionListResult.NODATA);
        else {
            result.setValue(OperacionListResult.SUCCESS);
        }
    }

    public void delete(Operacion operacion) {
        OperacionRepository.getInstance().delete(operacion);

        ArrayList<Operacion> listaActual = data.getValue();
        listaActual.remove(operacion);
        data.setValue(listaActual);

        result.setValue(OperacionListResult.DELETESUCCESS);
    }
    //data.getValue().remove(operacion);
    //data.setValue(data.getValue());
}
