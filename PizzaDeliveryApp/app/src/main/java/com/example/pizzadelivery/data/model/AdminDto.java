package com.example.pizzadelivery.data.model;

public class AdminDto {
    private Long id;
    private String usuario;
    private String password;
    private String nombres;
    private String rol;

    public AdminDto() {}

    public AdminDto(Long id, String usuario, String password, String nombres, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.nombres = nombres;
        this.rol = rol;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
