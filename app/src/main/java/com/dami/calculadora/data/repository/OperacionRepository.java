package com.dami.calculadora.data.repository;

import com.dami.calculadora.data.database.CalculadoraDatabase;
import com.dami.calculadora.data.model.Operacion;
import com.dami.calculadora.data.model.OperacionDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class OperacionRepository {
    private static OperacionRepository instance = new OperacionRepository();
    private OperacionDao operacionDao;

    private OperacionRepository() {
        operacionDao = CalculadoraDatabase.getDatabase().operacionDao();
    }

    public static OperacionRepository getInstance() {
        return instance;
    }

    public void insert(Operacion op) {
        CalculadoraDatabase.databaseWriteExecutor.execute(() -> operacionDao.insert(op));
    }

    public void delete(Operacion op) {
        CalculadoraDatabase.databaseWriteExecutor.execute(() -> operacionDao.delete(op));
    }

    public void deleteAll() {
        CalculadoraDatabase.databaseWriteExecutor.execute(() -> operacionDao.deleteAll());
    }

    public List<Operacion> selectAll() {
        try {
            return CalculadoraDatabase.databaseWriteExecutor.submit(() -> operacionDao.selectAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}


