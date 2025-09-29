package com.example.back_pizza.dto;

public class AdminDTO {
    private Long idAdmin;
    private String usuario;
    private String nombres;
    private String rol;

    public Long getIdAdmin() { return idAdmin; }
    public void setIdAdmin(Long idAdmin) { this.idAdmin = idAdmin; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
