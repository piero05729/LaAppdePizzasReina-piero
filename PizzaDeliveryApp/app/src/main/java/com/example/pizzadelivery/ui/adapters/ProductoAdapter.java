package com.example.pizzadelivery.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzadelivery.R;
import com.example.pizzadelivery.data.model.ProductoDto;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.VH> {

    public interface OnItemClickListener { void onClick(ProductoDto item); }

    private final List<ProductoDto> items = new ArrayList<>();
    private OnItemClickListener listener;

    public void setItems(List<ProductoDto> list) {
        items.clear();
        if (list != null) items.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener l) { this.listener = l; }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        ProductoDto p = items.get(position);
        h.tvNombre.setText(p.nombre != null ? p.nombre : "");
        h.tvDescripcion.setText(p.descripcion != null ? p.descripcion : "");
        String precio = p.precio != null ? ("S/ " + String.format("%.2f", p.precio)) : "S/ 0.00";
        h.tvPrecio.setText(precio);
        h.itemView.setOnClickListener(v -> { if (listener != null) listener.onClick(p); });
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDescripcion, tvPrecio;
        VH(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
