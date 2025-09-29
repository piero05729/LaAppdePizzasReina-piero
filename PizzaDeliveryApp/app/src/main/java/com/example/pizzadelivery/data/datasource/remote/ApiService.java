package com.example.pizzadelivery.data.datasource.remote;

import com.example.pizzadelivery.data.model.AdminDto;
import com.example.pizzadelivery.data.model.OfertaDto;
import com.example.pizzadelivery.data.model.ProductoDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.DELETE;

public interface ApiService {

    @GET("api/admins")
    Call<List<AdminDto>> listAdmins();

    @GET("api/admins/{id}")
    Call<AdminDto> getAdmin(@Path("id") Long id);

    @POST("api/admins")
    Call<AdminDto> createAdmin(@Body AdminDto dto);

    @POST("api/admins/login")
    Call<AdminDto> login(@Body AdminDto credentials);

    // Ofertas
    @GET("api/ofertas/activas")
    Call<List<OfertaDto>> listOfertasActivas();

    @GET("api/ofertas")
    Call<List<OfertaDto>> listOfertas();

    // Productos (pizzas predeterminadas y CRUD)
    @GET("api/productos")
    Call<List<ProductoDto>> listProductos();

    @POST("api/productos")
    Call<ProductoDto> createProducto(@Body ProductoDto dto);

    @PUT("api/productos/{id}")
    Call<ProductoDto> updateProducto(@Path("id") Long id, @Body ProductoDto dto);

    @DELETE("api/productos/{id}")
    Call<Void> deleteProducto(@Path("id") Long id);
}
