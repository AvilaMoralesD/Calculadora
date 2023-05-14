package com.dami.calculadora.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dami.calculadora.data.model.Operacion;
import com.dami.calculadora.data.model.OperacionDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Operacion.class},version = 4, exportSchema = false)
public abstract class CalculadoraDatabase extends RoomDatabase {

    private static volatile CalculadoraDatabase INSTANCE;
    //Crear los métodos abstractos sin argumentos que devuelva cada DAO
    public abstract OperacionDao operacionDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static CalculadoraDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CalculadoraDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CalculadoraDatabase.class, "Leads").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }


    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (CalculadoraDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CalculadoraDatabase.class, "Leads").fallbackToDestructiveMigration().build();
                    INSTANCE.getOpenHelper().getReadableDatabase(); //Esta linea crea la base de datos en AppInspection
                }
            }
        }
    }

    public static CalculadoraDatabase getDatabase() {
        return INSTANCE;
    }
}
