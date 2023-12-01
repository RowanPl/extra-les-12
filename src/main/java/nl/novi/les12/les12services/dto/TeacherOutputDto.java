package nl.novi.les12.les12services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TeacherOutputDto {

    public Long id;
    public String firstName;
    public String lastName;

}
