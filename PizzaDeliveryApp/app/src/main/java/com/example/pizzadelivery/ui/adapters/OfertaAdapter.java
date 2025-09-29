package com.example.pizzadelivery.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzadelivery.R;
import com.example.pizzadelivery.data.model.OfertaDto;

import java.util.ArrayList;
import java.util.List;

public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.VH> {

    public interface OnItemClickListener { void onClick(OfertaDto item); }

    private final List<OfertaDto> items = new ArrayList<>();
    private OnItemClickListener listener;

    public void setItems(List<OfertaDto> list) {
        items.clear();
        if (list != null) items.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener l) { this.listener = l; }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oferta, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        OfertaDto o = items.get(position);
        h.tvNombre.setText(o.nombre != null ? o.nombre : "");
        h.tvDescripcion.setText(o.descripcion != null ? o.descripcion : "");
        String desc = (o.tipoDescuento != null ? o.tipoDescuento : "") + ": " + (o.valorDescuento != null ? o.valorDescuento : 0);
        h.tvDescuento.setText(desc);
        String vig = (o.fechaInicio != null ? o.fechaInicio : "") + (o.fechaFin != null ? " - " + o.fechaFin : "");
        h.tvVigencia.setText(vig);
        h.itemView.setOnClickListener(v -> { if (listener != null) listener.onClick(o); });
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDescripcion, tvDescuento, tvVigencia;
        VH(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvDescuento = itemView.findViewById(R.id.tvDescuento);
            tvVigencia = itemView.findViewById(R.id.tvVigencia);
        }
    }
}
