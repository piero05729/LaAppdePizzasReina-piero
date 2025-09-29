package com.example.pizzadelivery.ui.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzadelivery.R;
import com.example.pizzadelivery.core.di.RetrofitProvider;
import com.example.pizzadelivery.data.datasource.remote.ApiService;
import com.example.pizzadelivery.data.model.ProductoDto;
import com.example.pizzadelivery.ui.adapters.ProductoAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PizzaPersonalizadaFragment extends Fragment {

    private ProductoAdapter adapter;
    private TextInputEditText etNombre, etDescripcion, etPrecio, etCategoriaId;
    private Long selectedId = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pizza_personalizada, container, false);

        etNombre = root.findViewById(R.id.etNombre);
        etDescripcion = root.findViewById(R.id.etDescripcion);
        etPrecio = root.findViewById(R.id.etPrecio);
        etCategoriaId = root.findViewById(R.id.etCategoriaId);

        MaterialButton btnCrear = root.findViewById(R.id.btnCrear);
        MaterialButton btnActualizar = root.findViewById(R.id.btnActualizar);
        MaterialButton btnEliminar = root.findViewById(R.id.btnEliminar);

        RecyclerView rv = root.findViewById(R.id.rvPersonalizadas);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new ProductoAdapter();
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(item -> {
            selectedId = item.idProducto;
            if (etNombre != null) etNombre.setText(item.nombre);
            if (etDescripcion != null) etDescripcion.setText(item.descripcion);
            if (etPrecio != null && item.precio != null) etPrecio.setText(String.valueOf(item.precio));
            if (etCategoriaId != null && item.categoria != null) etCategoriaId.setText(String.valueOf(item.categoria.idCategoria));
        });

        btnCrear.setOnClickListener(v -> createProducto());
        btnActualizar.setOnClickListener(v -> updateProducto());
        btnEliminar.setOnClickListener(v -> deleteProducto());

        loadList();
        return root;
    }

    private void loadList() {
        ApiService api = RetrofitProvider.getApiService();
        api.listProductos().enqueue(new Callback<List<ProductoDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductoDto>> call, @NonNull Response<List<ProductoDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setItems(response.body());
                } else {
                    showToast("No se pudieron cargar pizzas (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductoDto>> call, @NonNull Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    private ProductoDto readForm() {
        ProductoDto dto = new ProductoDto();
        dto.nombre = etNombre != null && etNombre.getText() != null ? etNombre.getText().toString().trim() : null;
        dto.descripcion = etDescripcion != null && etDescripcion.getText() != null ? etDescripcion.getText().toString().trim() : null;
        try {
            dto.precio = etPrecio != null && etPrecio.getText() != null && etPrecio.getText().length() > 0
                    ? Double.parseDouble(etPrecio.getText().toString()) : null;
        } catch (NumberFormatException ex) { dto.precio = null; }
        try {
            Long catId = etCategoriaId != null && etCategoriaId.getText() != null && etCategoriaId.getText().length() > 0
                    ? Long.parseLong(etCategoriaId.getText().toString()) : null;
            if (catId != null) dto.categoria = new ProductoDto.CategoriaRef(catId);
        } catch (NumberFormatException ignored) {}
        return dto;
    }

    private void createProducto() {
        ApiService api = RetrofitProvider.getApiService();
        ProductoDto body = readForm();
        api.createProducto(body).enqueue(new Callback<ProductoDto>() {
            @Override
            public void onResponse(@NonNull Call<ProductoDto> call, @NonNull Response<ProductoDto> response) {
                if (response.isSuccessful()) {
                    showToast("Creado");
                    clearSelection();
                    loadList();
                } else {
                    showToast("Error al crear (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductoDto> call, @NonNull Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    private void updateProducto() {
        if (selectedId == null) { showToast("Seleccione un producto"); return; }
        ApiService api = RetrofitProvider.getApiService();
        ProductoDto body = readForm();
        api.updateProducto(selectedId, body).enqueue(new Callback<ProductoDto>() {
            @Override
            public void onResponse(@NonNull Call<ProductoDto> call, @NonNull Response<ProductoDto> response) {
                if (response.isSuccessful()) {
                    showToast("Actualizado");
                    clearSelection();
                    loadList();
                } else {
                    showToast("Error al actualizar (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductoDto> call, @NonNull Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    private void deleteProducto() {
        if (selectedId == null) { showToast("Seleccione un producto"); return; }
        ApiService api = RetrofitProvider.getApiService();
        api.deleteProducto(selectedId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    showToast("Eliminado");
                    clearSelection();
                    loadList();
                } else {
                    showToast("Error al eliminar (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    private void clearSelection() {
        selectedId = null;
        if (etNombre != null) etNombre.setText("");
        if (etDescripcion != null) etDescripcion.setText("");
        if (etPrecio != null) etPrecio.setText("");
        if (etCategoriaId != null) etCategoriaId.setText("");
    }

    private void showToast(String msg) {
        if (getContext() != null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
