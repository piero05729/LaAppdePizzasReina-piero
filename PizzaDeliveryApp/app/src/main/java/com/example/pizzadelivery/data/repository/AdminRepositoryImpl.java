package com.example.pizzadelivery.data.repository;

import androidx.annotation.NonNull;

import com.example.pizzadelivery.core.di.RetrofitProvider;
import com.example.pizzadelivery.core.utils.ResultCallback;
import com.example.pizzadelivery.data.datasource.remote.ApiService;
import com.example.pizzadelivery.data.model.AdminDto;
import com.example.pizzadelivery.domain.repository.AdminRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminRepositoryImpl implements AdminRepository {

    private final ApiService api;

    public AdminRepositoryImpl() {
        this.api = RetrofitProvider.getApiService();
    }

    @Override
    public void login(String usuario, String password, final ResultCallback<AdminDto> callback) {
        AdminDto body = new AdminDto(null, usuario, password, null, null);
        api.login(body).enqueue(new Callback<AdminDto>() {
            @Override
            public void onResponse(@NonNull Call<AdminDto> call, @NonNull Response<AdminDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Credenciales inválidas o error del servidor (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AdminDto> call, @NonNull Throwable t) {
                callback.onError(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }

    @Override
    public void register(String usuario, String password, final ResultCallback<AdminDto> callback) {
        // nombres no se pide en UI; usaremos el usuario como nombre por ahora.
        AdminDto body = new AdminDto(null, usuario, password, usuario, "Administrador");
        api.createAdmin(body).enqueue(new Callback<AdminDto>() {
            @Override
            public void onResponse(@NonNull Call<AdminDto> call, @NonNull Response<AdminDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("No se pudo registrar (" + response.code() + ")");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AdminDto> call, @NonNull Throwable t) {
                callback.onError(t.getMessage() != null ? t.getMessage() : "Error de red");
            }
        });
    }
}
