package com.dami.calculadora.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity(tableName = "operation")
public class Operacion implements Parcelable, Comparable<Operacion> {
    private static int autoID;

    @PrimaryKey
    @NonNull
    private int idOrden;
    @NonNull
    private float numero1;
    @NonNull
    private String tipoOperacion;
    @NonNull
    private float numero2;
    private float resultado;

    @Ignore
    public Operacion(float numero1, String tipoOperacion, float numero2) {
        this.numero1 = numero1;
        this.tipoOperacion = tipoOperacion;
        this.numero2 = numero2;
        idOrden = ++autoID;
        operar(numero1, tipoOperacion, numero2);
    }

    public Operacion(int idOrden, float numero1, String tipoOperacion, float numero2, float resultado) {
        this.numero1 = numero1;
        this.tipoOperacion = tipoOperacion;
        this.numero2 = numero2;
        this.idOrden = idOrden;
        this.resultado = resultado;
        autoID = idOrden;
    }

    @Ignore
    protected Operacion(Parcel in) {
        idOrden = in.readInt();
        numero1 = in.readInt();
        tipoOperacion = in.readString();
        numero2 = in.readInt();
        resultado = in.readInt();
    }

    public static final Creator<Operacion> CREATOR = new Creator<Operacion>() {
        @Override
        public Operacion createFromParcel(Parcel in) {
            return new Operacion(in);
        }

        @Override
        public Operacion[] newArray(int size) {
            return new Operacion[size];
        }
    };

    public static int getAutoID() {
        return autoID;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public float getNumero1() {
        return numero1;
    }

    @NonNull
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public float getNumero2() {
        return numero2;
    }

    public float getResultado() {
        return resultado;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    private void operar(float numero1, String tipoOperacion, float numero2) {
        switch (tipoOperacion) {
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "*":
                resultado = numero1 * numero2;
                break;
            case "/":
                resultado = numero1 / numero2;
                break;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operacion operacion = (Operacion) o;

        return idOrden == operacion.idOrden;
    }

    @Override
    public int hashCode() {
        return idOrden;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return numero1 + " " + tipoOperacion + " " + numero2 + " = " + df.format(resultado);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(idOrden);
        parcel.writeFloat(numero1);
        parcel.writeString(tipoOperacion);
        parcel.writeFloat(numero2);
        parcel.writeFloat(resultado);
    }

    @Override
    public int compareTo(Operacion operacion) {
        return Integer.compare(this.idOrden, operacion.idOrden);
    }
}
