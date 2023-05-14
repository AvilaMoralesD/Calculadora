package com.dami.calculadora.data.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dami.calculadora.data.model.Operacion;
import com.dami.calculadora.data.model.OperationComparatorByID;
import com.dami.calculadora.databinding.ItemOperacionBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OperacionAdapter extends RecyclerView.Adapter<OperacionAdapter.ViewHolder> {
    private ArrayList<Operacion> lista;
    private final OnManageListener listener;
    private ItemOperacionBinding itemBinding;

    public OperacionAdapter(OnManageListener listener) {
        this.listener = listener;
        this.lista = new ArrayList<>();
    }

    public interface OnManageListener {
        void onShowOperacion(Operacion operacion);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemBinding = ItemOperacionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void update(List<Operacion> lista) {
        this.lista.clear();
        this.lista.addAll(lista);
        notifyDataSetChanged();
    }

    //No lo llamo porque no tengo boton para ordenar
    public void ordenarPorID() {
        Collections.sort(lista);
        notifyDataSetChanged();
    }

    public void ordenarPorTipo() {
        Collections.sort(lista, new OperationComparatorByID());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder() {
            super(itemBinding.getRoot());
        }

        public void bind(Operacion operacion) {
            itemBinding.tvOper.setText(operacion.toString());
            itemBinding.tvID.setText(operacion.getIdOrden());
            itemView.setOnClickListener(view -> listener.onShowOperacion(operacion));
        }
    }
}
