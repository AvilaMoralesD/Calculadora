package com.dami.calculadora.data.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OperacionDao {
    @Insert
    public void insert(Operacion operacion);

    @Delete
    public void delete(Operacion operacion);

    @Query("select * from operation")
    public List<Operacion> selectAll();

    @Query("delete from operation")
    public void deleteAll();

}
