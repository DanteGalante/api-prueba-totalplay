package com.totalplay.empleados.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "personal")
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_seq")
    @SequenceGenerator(name = "personal_seq", sequenceName = "personal_seq", allocationSize = 1)
    private Long id;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Pattern(regexp = "^[A-Z]+$", message = "El nombre solo puede contener letras mayúsculas")
    @Size(max = 35, message = "El nombre no puede tener más de 35 caracteres")
    @Column(name = "nombre", nullable = false, length = 35)
    private String nombre;

    @Pattern(regexp = "^[A-Z]+$", message = "El apellido paterno solo puede contener letras mayúsculas")
    @Size(max = 35, message = "El apellido paterno no puede tener más de 35 caracteres")
    @Column(name = "apellido_paterno", nullable = false, length = 35)
    private String apellidoPaterno;

    @Pattern(regexp = "^[A-Z]+$", message = "El apellido materno solo puede contener letras mayúsculas")
    @Size(max = 35, message = "El apellido materno no puede tener más de 35 caracteres")
    @Column(name = "apellido_materno", nullable = false, length = 35)
    private String apellidoMaterno;

    @Pattern(regexp = "^[0-9]+$", message = "El teléfono solo puede contener números")
    @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @Email(message = "El correo electrónico debe ser válido")
    @Column(name = "correo_electronico", nullable = false, unique = true, length = 50)
    private String correoElectronico;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso", nullable = false)
    private Date fechaIngreso;

    @Column(name = "sueldo", nullable = false)
    private Double sueldo;

    @Column(name = "descripcion_funciones")
    private String descripcionFunciones;

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

    
}
