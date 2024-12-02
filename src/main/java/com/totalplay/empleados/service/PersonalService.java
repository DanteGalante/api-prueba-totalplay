package com.totalplay.empleados.service;

import com.totalplay.empleados.dto.FotoDTO;
import com.totalplay.empleados.dto.PersonalDTO;
import com.totalplay.empleados.dto.UpdatePersonalDTO;
import com.totalplay.empleados.exception.EmpleadoDuplicadoException;
import com.totalplay.empleados.exception.RecursoNoEncontradoException;
import com.totalplay.empleados.mapper.PersonalMapper;
import com.totalplay.empleados.model.Personal;
import com.totalplay.empleados.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalService {
    @Autowired
    private PersonalRepository personalRepository;

    public List<PersonalDTO> findAll() throws Exception {
        return personalRepository.findAll().stream()
                .map(PersonalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonalDTO findById(Long id) throws RecursoNoEncontradoException {
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empleado no encontrado"));
        return PersonalMapper.toDTO(personal);
    }

    public void saveFoto(FotoDTO fotoDTO) {
        personalRepository.subir_foto(fotoDTO.getId(), fotoDTO.getFotoUrl());
    }

    public PersonalDTO save(PersonalDTO personalDTO) throws EmpleadoDuplicadoException, Exception {
        // Verificar duplicados
        if (personalRepository.existsByNombreAndApellidoPaternoAndApellidoMaterno(
                personalDTO.getNombre(), personalDTO.getApellidoPaterno(), personalDTO.getApellidoMaterno())) {
            throw new EmpleadoDuplicadoException("Empleado duplicado");
        }

        // Generar correo electrónico
        String correoElectronico = personalDTO.getNombre().toLowerCase() + "." +
                personalDTO.getApellidoPaterno().toLowerCase() + "@totalplay.com.mx";
        personalDTO.setCorreoElectronico(correoElectronico);

        Personal personal = PersonalMapper.toEntity(personalDTO);
        Personal savedPersonal = personalRepository.save(personal);
        return PersonalMapper.toDTO(savedPersonal);
    }

    public PersonalDTO update(long id, UpdatePersonalDTO personalDTO) {
        Personal personal = personalRepository.findById(id).orElseThrow(
            () -> new RecursoNoEncontradoException("No se encontro el personal con el id especificado")
        );
        personal = PersonalMapper.getMergedEntity(personal, personalDTO);
        personal = personalRepository.save(personal);
        return PersonalMapper.toDTO(personal);
    }

    public void deleteById(Long id) {
        if (!personalRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Empleado no encontrado");
        }
        personalRepository.deleteById(id);
    }
}
