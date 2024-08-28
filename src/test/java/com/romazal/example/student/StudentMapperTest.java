package com.romazal.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto(
                "Tom",
                "Meh",
                "tomMeh@gmail.com",
                1
        );

        Student student = mapper.toStudent(dto);

        assertEquals(dto.name(), student.getName());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());

    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){
        var exc = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals(exc.getMessage(), "studentDto cannot be null");
    }

    @Test
    public void shouldMapStudentToStudentDto() {
            Student student = new Student(
                    "Roman",
                    "Zal",
                    "zal@gmail.com",
                    21
            );

            StudentResponseDto dto = mapper.toStudentResponseDto(student);

            assertEquals(dto.name(), student.getName());
            assertEquals(dto.lastname(), student.getLastname());
            assertEquals(dto.email(), student.getEmail());
            //assertNotNull(student.getSchool());



    }
}