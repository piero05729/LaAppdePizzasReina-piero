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
import com.example.pizzadelivery.data.model.OfertaDto;
import com.example.pizzadelivery.ui.adapters.OfertaAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfertaCrudFragment extends Fragment {

    private OfertaAdapter adapter;
    private TextInputEditText etNombre, etDescripcion, etTipoDescuento, etValorDescuento, etFechaInicio, etFechaFin;
    private Long selectedId = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_oferta_crud, container, false);

        etNombre = root.findViewById(R.id.etNombre);
        etDescripcion = root.findViewById(R.id.etDescripcion);
        etTipoDescuento = root.findViewById(R.id.etTipoDescuento);
        etValorDescuento = root.findViewById(R.id.etValorDescuento);
        etFechaInicio = root.findViewById(R.id.etFechaInicio);
        etFechaFin = root.findViewById(R.id.etFechaFin);

        MaterialButton btnCrear = root.findViewById(R.id.btnCrear);
        MaterialButton btnActualizar = root.findViewById(R.id.btnActualizar);
        MaterialButton btnEliminar = root.findViewById(R.id.btnEliminar);
        MaterialButton btnActivar = root.findViewById(R.id.btnActivar);

        RecyclerView rv = root.findViewById(R.id.rvOfertas);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new OfertaAdapter();
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(item -> {
            selectedId = item.id;
            if (etNombre != null) etNombre.setText(item.nombre);
            if (etDescripcion != null) etDescripcion.setText(item.descripcion);
            if (etTipoDescuento != null) etTipoDescuento.setText(item.tipoDescuento);
            if (etValorDescuento != null && item.valorDescuento != null) etValorDescuento.setText(String.valueOf(item.valorDescuento));
            if (etFechaInicio != null) etFechaInicio.setText(item.fechaInicio);
            if (etFechaFin != null) etFechaFin.setText(item.fechaFin);
        });

        btnCrear.setOnClickListener(v -> createOferta());
        btnActualizar.setOnClickListener(v -> updateOferta());
        btnEliminar.setOnClickListener(v -> deleteOferta());
        btnActivar.setOnClickListener(v -> toggleActiva());

        loadList();
        return root;
    }

    private void loadList() {
        ApiService api = RetrofitProvider.getApiService();
        api.listOfertas().enqueue(new Callback<List<OfertaDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<OfertaDto>> call, @NonNull Response<List<OfertaDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setItems(response.body());
                } else {
                    showToast("No se pudieron cargar ofertas (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<OfertaDto>> call, @NonNull Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    private OfertaDto readForm() {
        OfertaDto dto = new OfertaDto();
        dto.nombre = etNombre != null && etNombre.getText() != null ? etNombre.getText().toString().trim() : null;
        dto.descripcion = etDescripcion != null && etDescripcion.getText() != null ? etDescripcion.getText().toString().trim() : null;
        dto.tipoDescuento = etTipoDescuento != null && etTipoDescuento.getText() != null ? etTipoDescuento.getText().toString().trim() : null;
        try {
            dto.valorDescuento = etValorDescuento != null && etValorDescuento.getText() != null && etValorDescuento.getText().length() > 0
                    ? Double.parseDouble(etValorDescuento.getText().toString()) : null;
        } catch (NumberFormatException ignored) { dto.valorDescuento = null; }
        dto.fechaInicio = etFechaInicio != null && etFechaInicio.getText() != null ? etFechaInicio.getText().toString().trim() : null;
        dto.fechaFin = etFechaFin != null && etFechaFin.getText() != null ? etFechaFin.getText().toString().trim() : null;
        dto.activo = true;
        return dto;
    }

    private void createOferta() {
        ApiService api = RetrofitProvider.getApiService();
        OfertaDto body = readForm();
        api.createOferta(body).enqueue(new Callback<OfertaDto>() {
            @Override
            public void onResponse(@NonNull Call<OfertaDto> call, @NonNull Response<OfertaDto> response) {
                if (response.isSuccessful()) {
                    showToast("Oferta creada");
                    clearSelection();
                    loadList();
                } else {
                    showToast("Error al crear (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<OfertaDto> call, @NonNull Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    private void updateOferta() {
        if (selectedId == null) { showToast("Seleccione una oferta"); return; }
        ApiService api = RetrofitProvider.getApiService();
        OfertaDto body = readForm();
        api.updateOferta(selectedId, body).enqueue(new Callback<OfertaDto>() {
            @Override
            public void onResponse(@NonNull Call<OfertaDto> call, @NonNull Response<OfertaDto> response) {
                if (response.isSuccessful()) {
                    showToast("Oferta actualizada");
                    clearSelection();
                    loadList();
                } else {
                    showToast("Error al actualizar (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<OfertaDto> call, @NonNull Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    private void deleteOferta() {
        if (selectedId == null) { showToast("Seleccione una oferta"); return; }
        ApiService api = RetrofitProvider.getApiService();
        api.deleteOferta(selectedId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    showToast("Oferta eliminada");
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

    private void toggleActiva() {
        if (selectedId == null) { showToast("Seleccione una oferta"); return; }
        ApiService api = RetrofitProvider.getApiService();
        // Para simplicidad, si hay fechaFin nula asumimos activar; si no, desactivar.
        api.activarOferta(selectedId).enqueue(new Callback<OfertaDto>() {
            @Override
            public void onResponse(@NonNull Call<OfertaDto> call, @NonNull Response<OfertaDto> response) {
                if (response.isSuccessful()) {
                    showToast("Estado actualizado");
                    clearSelection();
                    loadList();
                } else {
                    showToast("Error al cambiar estado (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<OfertaDto> call, @NonNull Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    private void clearSelection() {
        selectedId = null;
        if (etNombre != null) etNombre.setText("");
        if (etDescripcion != null) etDescripcion.setText("");
        if (etTipoDescuento != null) etTipoDescuento.setText("");
        if (etValorDescuento != null) etValorDescuento.setText("");
        if (etFechaInicio != null) etFechaInicio.setText("");
        if (etFechaFin != null) etFechaFin.setText("");
    }

    private void showToast(String msg) {
        if (getContext() != null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
