package com.totalplay.empleados.dto;

import jakarta.validation.constraints.*;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.totalplay.empleados.validation.FileSize;

public class PersonalDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[A-Z]+$", message = "El nombre solo puede contener letras mayúsculas")
    @Size(max = 35, message = "El nombre no puede tener más de 35 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Pattern(regexp = "^[A-Z]+$", message = "El apellido paterno solo puede contener letras mayúsculas")
    @Size(max = 35, message = "El apellido paterno no puede tener más de 35 caracteres")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Pattern(regexp = "^[A-Z]+$", message = "El apellido materno solo puede contener letras mayúsculas")
    @Size(max = 35, message = "El apellido materno no puede tener más de 35 caracteres")
    private String apellidoMaterno;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono solo puede contener números")
    @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    private String telefono;

    @Email(message = "El correo electrónico debe ser válido")
    private String correoElectronico;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private Date fechaIngreso;

    @NotNull(message = "El sueldo es obligatorio")
    private Double sueldo;

    private String descripcionFunciones;

    private String fotoUrl;

    @FileSize(max = 4096, message = "El tamaño de la imagen no puede exceder los 4KB")
    private MultipartFile foto;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getDescripcionFunciones() {
        return descripcionFunciones;
    }

    public void setDescripcionFunciones(String descripcionFunciones) {
        this.descripcionFunciones = descripcionFunciones;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}