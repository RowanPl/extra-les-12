package nl.novi.les12.les12services.controller;

import jakarta.validation.Valid;
import nl.novi.les12.les12services.Exceptions.ValidationException;
import nl.novi.les12.les12services.dto.TeacherDto;
import nl.novi.les12.les12services.dto.TeacherInputDto;
import nl.novi.les12.les12services.dto.TeacherOutputDto;
import nl.novi.les12.les12services.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static nl.novi.les12.les12services.controller.ControllerHelper.checkForBindingResult;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TeacherOutputDto>> getAllTeachers() {
        return ResponseEntity.ok(service.getTeachers());
    }

    @GetMapping("/{id}")
//
//    @GetMapping("/after")
//    public ResponseEntity<List<Teacher>> getTeachersAfter(@RequestParam LocalDate date) {
//        return ResponseEntity.ok(teacherRepository.findByDobAfter(date));
//    }


    @PostMapping
    public ResponseEntity<TeacherOutputDto> createTeacher(@Valid @RequestBody TeacherInputDto teacherDto, BindingResult br) {

        if (br.hasFieldErrors()) {
           throw new ValidationException(checkForBindingResult(br));
        }
        else {
            TeacherOutputDto teacherOutputDto = service.createTeacher(teacherDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + teacherOutputDto.id).toUriString());

            return ResponseEntity.created(uri).body(teacherOutputDto);
        }
    }
}
