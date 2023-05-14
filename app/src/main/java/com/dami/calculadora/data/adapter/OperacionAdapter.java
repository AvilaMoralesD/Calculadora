package com.dami.calculadora.data.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dami.calculadora.R;
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
        ItemOperacionBinding itemBinding = ItemOperacionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (lista.get(position).getTipoOperacion()){
            case "+":
                holder.itemBinding.tvOper.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
                break;
            case "-":
                holder.itemBinding.tvOper.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red));
                break;
            case "*":
                holder.itemBinding.tvOper.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.purple));
                break;
            case "/":
                holder.itemBinding.tvOper.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.mustard));
                break;
        }
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

    public void ordenarPorID() {
        Collections.sort(lista);
        notifyDataSetChanged();
    }

    public void ordenarPorTipo() {
        Collections.sort(lista, new OperationComparatorByID());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemOperacionBinding itemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = ItemOperacionBinding.bind(itemView);
        }

        public void bind(Operacion operacion) {
            itemBinding.tvOper.setText(operacion.toString());
            itemBinding.tvID.setText(String.valueOf(operacion.getIdOrden()));
            itemView.setOnClickListener(view -> listener.onShowOperacion(operacion));
        }
    }
}
