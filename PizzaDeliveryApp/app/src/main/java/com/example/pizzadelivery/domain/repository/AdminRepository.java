package com.example.pizzadelivery.domain.repository;

import com.example.pizzadelivery.core.utils.ResultCallback;
import com.example.pizzadelivery.data.model.AdminDto;

public interface AdminRepository {
    void login(String usuario, String password, ResultCallback<AdminDto> callback);
    void register(String usuario, String password, ResultCallback<AdminDto> callback);
}
