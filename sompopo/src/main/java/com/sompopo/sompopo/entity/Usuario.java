package com.sompopo.sompopo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import jakarta.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreUsuario;

    private String correo;
    private String telefono;

    private String contraseña;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
    @PrePersist
    protected void onCreate() {
        updated_at = created_at;
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public LocalDateTime getCreadoEn() {
        return created_at;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.created_at = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return updated_at;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.updated_at = actualizadoEn;
    }

    public LocalDateTime getEliminadoEn() {
        return deletedAt;
    }

    public void setEliminadoEn(LocalDateTime eliminadoEn) {
        this.deletedAt = eliminadoEn;
    }
}