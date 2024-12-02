package com.totalplay.empleados.controller;

import com.totalplay.empleados.dto.ApiResponse;
import com.totalplay.empleados.dto.FotoDTO;
import com.totalplay.empleados.dto.PersonalDTO;
import com.totalplay.empleados.dto.UpdatePersonalDTO;
import com.totalplay.empleados.exception.EmpleadoDuplicadoException;
import com.totalplay.empleados.exception.RecursoNoEncontradoException;
import com.totalplay.empleados.service.PersonalService;
import com.totalplay.empleados.service.S3Service;
import com.totalplay.empleados.validation.FileSize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {
    @Autowired
    private PersonalService personalService;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    public HashMap<String, Object> getAllPersonal() {
        HashMap<String, Object> res = new HashMap<>();

        try {
            List<PersonalDTO> data = personalService.findAll();
            res.put("data", data);
        } catch (Exception e) {
            res.put("error", e.getMessage());
        }

        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonalDTO>> getPersonalById(
            @PathVariable Long id) {
        PersonalDTO personal = personalService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, personal));
    }

    @PostMapping("/foto")
    public ResponseEntity<ApiResponse<String>> uploadFoto(@RequestParam String id,
            @RequestParam @FileSize(max = 4096, message = "El tamaño de la imagen no puede exceder los 4KB") MultipartFile foto) {
        String fileUrl = "";
        ResponseEntity<ApiResponse<String>> res = new ResponseEntity<>(
                HttpStatusCode.valueOf(500));
        try {
            // Guardar la imagen en un archivo temporal
            Path tempFile = Files.createTempFile("temp", foto.getOriginalFilename());
            foto.transferTo(tempFile.toFile());

            // Subir la imagen a S3
            s3Service.uploadFile(foto.getOriginalFilename(), tempFile.toFile());

            // Obtener la URL pública del archivo
            fileUrl = s3Service.getFileUrl(foto.getOriginalFilename());

            // Eliminar el archivo temporal
            Files.delete(tempFile);

            FotoDTO fotoDTO = new FotoDTO();
            fotoDTO.setId(Long.parseLong(id));
            fotoDTO.setFotoUrl(fileUrl);
            personalService.saveFoto(fotoDTO);
            res = ResponseEntity.ok().body(new ApiResponse<>(200, fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
            res = ResponseEntity.status(500).body(new ApiResponse<>(500, e.getMessage()));
        }

        return res;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PersonalDTO>> createPersonal(
            @Valid @RequestBody PersonalDTO personalDTO) {
        ResponseEntity<ApiResponse<PersonalDTO>> res = new ResponseEntity<>(
                HttpStatusCode.valueOf(500));
        try {
            PersonalDTO createdPersonal = personalService.save(personalDTO);
            res = ResponseEntity.ok(new ApiResponse<>(200, createdPersonal));
        } catch (IOException e) {
            res = ResponseEntity.status(500)
                    .body(new ApiResponse<>(500, e.getMessage(), null));
        } catch (EmpleadoDuplicadoException e) {
            res = ResponseEntity.status(500)
                    .body(new ApiResponse<>(500, e.getMessage(), null));
        } catch (Exception e) {
            res = ResponseEntity.badRequest().body(new ApiResponse<>(
                    HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonalDTO>> updatePersonal(@PathVariable Long id,
            @Valid @RequestBody UpdatePersonalDTO personalDTO) {
        ResponseEntity<ApiResponse<PersonalDTO>> res = new ResponseEntity<>(
                HttpStatusCode.valueOf(500));
        try {
            PersonalDTO updatedPersonal = personalService.update(id, personalDTO);
            res = ResponseEntity.ok(new ApiResponse<>(200, updatedPersonal));
        } catch (RecursoNoEncontradoException e) {
            res = ResponseEntity.status(500)
                    .body(new ApiResponse<>(500, e.getMessage(), null));
        } catch (EmpleadoDuplicadoException e) {
            res = ResponseEntity.badRequest()
                    .body(new ApiResponse<>(500, e.getMessage(), null));
        } catch (Exception e) {
            res = ResponseEntity.status(500)
                    .body(new ApiResponse<>(500, e.getMessage(), null));
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePersonal(@PathVariable Long id) {
        personalService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse<>(200,
                String.format("Personal: %s eliminado", id), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Error de validación", errors));
    }
}
