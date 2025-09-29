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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {

    private ProductoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        RecyclerView rv = root.findViewById(R.id.rvProductos);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new ProductoAdapter();
        rv.setAdapter(adapter);
        loadData();
        return root;
    }

    private void loadData() {
        ApiService api = RetrofitProvider.getApiService();
        api.listProductos().enqueue(new Callback<List<ProductoDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductoDto>> call, @NonNull Response<List<ProductoDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setItems(response.body());
                } else {
                    showToast("No se pudieron cargar productos (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductoDto>> call, @NonNull Throwable t) {
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
