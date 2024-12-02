package com.totalplay.empleados.mapper;

import com.totalplay.empleados.dto.FotoDTO;
import com.totalplay.empleados.dto.PersonalDTO;
import com.totalplay.empleados.dto.UpdatePersonalDTO;
import com.totalplay.empleados.model.Personal;

public class PersonalMapper {
    public static PersonalDTO toDTO(Personal personal) {
        PersonalDTO dto = new PersonalDTO();
        dto.setId(personal.getId());
        dto.setNombre(personal.getNombre());
        dto.setApellidoPaterno(personal.getApellidoPaterno());
        dto.setApellidoMaterno(personal.getApellidoMaterno());
        dto.setTelefono(personal.getTelefono());
        dto.setCorreoElectronico(personal.getCorreoElectronico());
        dto.setFechaIngreso(personal.getFechaIngreso());
        dto.setSueldo(personal.getSueldo());
        dto.setDescripcionFunciones(personal.getDescripcionFunciones());
        return dto;
    }

    public static FotoDTO toDto(Personal personal) {
        FotoDTO fotoDto = new FotoDTO();
        fotoDto.setId(personal.getId());
        fotoDto.setFotoUrl(personal.getFotoUrl());
        return fotoDto;
    }

    public static Personal toEntity(PersonalDTO dto) {
        Personal personal = new Personal();
        personal.setId(dto.getId());
        personal.setNombre(dto.getNombre());
        personal.setFotoUrl(dto.getFotoUrl());
        personal.setApellidoPaterno(dto.getApellidoPaterno());
        personal.setApellidoMaterno(dto.getApellidoMaterno());
        personal.setTelefono(dto.getTelefono());
        personal.setCorreoElectronico(dto.getCorreoElectronico());
        personal.setFechaIngreso(dto.getFechaIngreso());
        personal.setSueldo(dto.getSueldo());
        personal.setDescripcionFunciones(dto.getDescripcionFunciones());
        return personal;
    }

    public static Personal toEntity(FotoDTO fotoDTO) {
        Personal personal = new Personal();
        personal.setId(fotoDTO.getId());
        personal.setFotoUrl(fotoDTO.getFotoUrl());
        return personal;
    }

    public static Personal getMergedEntity(Personal entity, UpdatePersonalDTO dto) {
        entity.setNombre(dto.getNombre() != null ? dto.getNombre() : entity.getNombre());
        entity.setFotoUrl(dto.getFotoUrl() != null ? dto.getFotoUrl() : entity.getFotoUrl());
        entity.setApellidoPaterno(dto.getApellidoPaterno() != null ? dto.getApellidoPaterno() : entity.getApellidoPaterno());
        entity.setApellidoMaterno(dto.getApellidoMaterno() != null ? dto.getApellidoMaterno() : entity.getApellidoMaterno());
        entity.setTelefono(dto.getTelefono() != null ? dto.getTelefono() : entity.getTelefono());
        entity.setCorreoElectronico(dto.getCorreoElectronico() != null ? dto.getCorreoElectronico() : entity.getCorreoElectronico());
        entity.setFechaIngreso(dto.getFechaIngreso() != null ? dto.getFechaIngreso() : entity.getFechaIngreso());
        entity.setSueldo(dto.getSueldo() != null ? dto.getSueldo() : entity.getSueldo());
        entity.setDescripcionFunciones(dto.getDescripcionFunciones() != null ? dto.getDescripcionFunciones() : entity.getDescripcionFunciones());
        return entity;
    }
}
