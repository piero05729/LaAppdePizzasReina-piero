package com.example.pizzadelivery.core.utils;

public interface ResultCallback<T> {
    void onSuccess(T data);
    void onError(String message);
}
