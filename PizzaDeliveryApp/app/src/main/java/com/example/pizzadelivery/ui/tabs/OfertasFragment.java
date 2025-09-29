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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfertasFragment extends Fragment {

    private OfertaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ofertas, container, false);
        RecyclerView rv = root.findViewById(R.id.rvOfertas);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()))
        ;
        adapter = new OfertaAdapter();
        rv.setAdapter(adapter);
        loadData();
        return root;
    }

    private void loadData() {
        ApiService api = RetrofitProvider.getApiService();
        api.listOfertasActivas().enqueue(new Callback<List<OfertaDto>>() {
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

    private void showToast(String msg) {
        if (getContext() != null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
