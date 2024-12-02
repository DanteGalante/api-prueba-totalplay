package com.totalplay.empleados.dto;

import org.springframework.web.multipart.MultipartFile;

import com.totalplay.empleados.validation.FileSize;

public class FotoDTO {
    private Long id;

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

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
