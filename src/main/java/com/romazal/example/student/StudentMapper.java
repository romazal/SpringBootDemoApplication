package com.romazal.example.student;

import com.romazal.example.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto) {
        if(dto == null) {
            throw new NullPointerException("studentDto cannot be null");
        }
        var student = new Student();
        student.setName(dto.name());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getName(), student.getLastname(), student.getEmail());
    }

}
