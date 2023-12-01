package nl.novi.les12.les12services.service;

import nl.novi.les12.les12services.dto.TeacherDto;
import nl.novi.les12.les12services.dto.TeacherInputDto;
import nl.novi.les12.les12services.dto.TeacherOutputDto;
import nl.novi.les12.les12services.model.Teacher;
import nl.novi.les12.les12services.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }

    public TeacherOutputDto createTeacher(TeacherInputDto teacherDto) {
          Teacher t = tdtoToTeacher(teacherDto);
        return teacherToTeacherOutputDto(t);
    }


    public List<TeacherOutputDto> getTeachers(){
        List<Teacher> teachers = repos.findAll();
        List<TeacherOutputDto> todto = new ArrayList<>();
        for(Teacher t : teachers){
            todto.add(teacherToTeacherOutputDto(t));
        }
        return todto;
    }

    public Teacher tdtoToTeacher(TeacherInputDto tidto){
        Teacher t = new Teacher();
        t.setFirstName(tidto.firstName);
        t.setLastName(tidto.lastName);
        t.setDob(tidto.dob);
        repos.save(t);
        return t;
    }

    public TeacherOutputDto teacherToTeacherOutputDto(Teacher t){
        TeacherOutputDto todto = new TeacherOutputDto();
        todto.firstName = t.getFirstName();
        todto.lastName = t.getLastName();
        todto.id = t.getId();
        return todto;
    }

}
