package com.romazal.example.student;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        StudentDto dto = new StudentDto(
                "Tom",
                "Meh",
                "tomMeh@gmail.com",
                1
        );

        Student student = new Student(
                "Tom",
                "Meh",
                "tomMeh@gmail.com",
                21
        );

        Student savedStudent = new Student(
                "Tom",
                "Meh",
                "tomMeh@gmail.com",
                21
        );
        savedStudent.setId(1);

        when(studentMapper.toStudent(dto))
                .thenReturn(student);

        when(studentRepository.save(student))
                .thenReturn(savedStudent);

        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                                "Tom",
                                "Meh",
                                "tomMeh@gmail.com"
                        )
                );

        StudentResponseDto responseDto = studentService.saveStudent(dto);


        assertEquals(dto.name(), responseDto.name());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        verify(studentMapper, times(1))
                .toStudent(dto);

        verify(studentRepository, times(1))
                .save(student);

        verify(studentMapper, times(1))
                .toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_successfully_find_all_students() {
        Student student1 = new Student(
                "Tom1",
                "Meh1",
                "tomMeh@gmail.com1",
                21
        );

        Student student2 = new Student(
                "Tom2",
                "Meh2",
                "tomMeh@gmail.com2",
                22
        );

        Student student3 = new Student(
                "Tom3",
                "Meh3",
                "tomMeh@gmail.com3",
                23
        );

        StudentResponseDto studentResponseDto1 = new StudentResponseDto(
                "Tom1",
                "Meh1",
                "tomMeh@gmail.com1"
        );

        StudentResponseDto studentResponseDto2 = new StudentResponseDto(
                "Tom2",
                "Meh2",
                "tomMeh@gmail.com2"
        );

        StudentResponseDto studentResponseDto3 = new StudentResponseDto(
                "Tom3",
                "Meh3",
                "tomMeh@gmail.com3"
        );

        List<Student> students = List.of(student1, student2, student3);

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(student1)).thenReturn(studentResponseDto1);
        when(studentMapper.toStudentResponseDto(student2)).thenReturn(studentResponseDto2);
        when(studentMapper.toStudentResponseDto(student3)).thenReturn(studentResponseDto3);

        List<StudentResponseDto> studentsDto = studentService.getStudents();

        assertEquals(students.size(), studentsDto.size());

        for (int i = 0; i < studentsDto.size(); i++) {
            StudentResponseDto Dto = studentsDto.get(i);
            Student student = students.get(i);
            assertEquals(Dto.name(), student.getName());
            assertEquals(Dto.lastname(), student.getLastname());
            assertEquals(Dto.email(), student.getEmail());
        }

        verify(studentRepository, times(1)).findAll();

    }

    @Test
    public void should_successfully_find_student_by_id() {
        Student student1 = new Student(
                "Tom1",
                "Meh1",
                "tomMeh@gmail.com1",
                21
        );
        student1.setId(1);

        Student student2 = new Student(
                "Tom2",
                "Meh2",
                "tomMeh@gmail.com2",
                22
        );
        student2.setId(2);

        Student student3 = new Student(
                "Tom3",
                "Meh3",
                "tomMeh@gmail.com3",
                23
        );
        student3.setId(3);

        StudentResponseDto studentResponseDto1 = new StudentResponseDto(
                "Tom1",
                "Meh1",
                "tomMeh@gmail.com1"
        );

        StudentResponseDto studentResponseDto2 = new StudentResponseDto(
                "Tom2",
                "Meh2",
                "tomMeh@gmail.com2"
        );

        StudentResponseDto studentResponseDto3 = new StudentResponseDto(
                "Tom3",
                "Meh3",
                "tomMeh@gmail.com3"
        );

        when(studentRepository.findById(student1.getId()))
                .thenReturn(Optional.of(student1));
        when(studentRepository.findById(student2.getId()))
                .thenReturn(Optional.of(student2));
        when(studentRepository.findById(student3.getId()))
                .thenReturn(Optional.of(student3));

        when(studentMapper.toStudentResponseDto(student1))
                .thenReturn(studentResponseDto1);
        when(studentMapper.toStudentResponseDto(student2))
                .thenReturn(studentResponseDto2);
        when(studentMapper.toStudentResponseDto(student3))
                .thenReturn(studentResponseDto3);

        StudentResponseDto studentById1 = studentService.getStudent(1);
        StudentResponseDto studentById2 = studentService.getStudent(2);
        StudentResponseDto studentById3 = studentService.getStudent(3);

        assertEquals(studentById1.name(), student1.getName());
        assertEquals(studentById2.name(), student2.getName());
        assertEquals(studentById3.name(), student3.getName());

        verify(studentRepository, times(1))
                .findById(student1.getId());
        verify(studentRepository, times(1))
                .findById(student2.getId());
        verify(studentRepository, times(1))
                .findById(student3.getId());


    }

    @Test
    public void should_successfully_all_students_by_name() {
        Student student1 = new Student(
                "Tom1",
                "Meh1",
                "tomMeh@gmail.com1",
                21
        );

        Student student2 = new Student(
                "Tom2",
                "Meh2",
                "tomMeh@gmail.com2",
                22
        );

        Student student3 = new Student(
                "Rom3",
                "Meh3",
                "tomMeh@gmail.com3",
                23
        );

        StudentResponseDto studentResponseDto1 = new StudentResponseDto(
                "Tom1",
                "Meh1",
                "tomMeh@gmail.com1"
        );

        StudentResponseDto studentResponseDto2 = new StudentResponseDto(
                "Tom2",
                "Meh2",
                "tomMeh@gmail.com2"
        );

        StudentResponseDto studentResponseDto3 = new StudentResponseDto(
                "Rom3",
                "Meh3",
                "tomMeh@gmail.com3"
        );

        List<Student> students = List.of(student1, student2, student3);

        String name = "Tom";

        when(studentRepository.findAllByNameContaining(name)).thenAnswer(invocation -> {
            List<Student> list = new ArrayList<>();

            students.stream().forEach(student -> {
                if (student.getName().contains(name)) {
                    list.add(student);
                }
            });

            return list;
        });

        when(studentMapper.toStudentResponseDto(student1)).thenReturn(studentResponseDto1);
        when(studentMapper.toStudentResponseDto(student2)).thenReturn(studentResponseDto2);
        when(studentMapper.toStudentResponseDto(student3)).thenReturn(studentResponseDto3);

        List<StudentResponseDto> studentResponseDtos = studentService.getAllStudentsByName(name);

        assertEquals(2, studentResponseDtos.size());

        for (StudentResponseDto studentResponseDto : studentResponseDtos) {
            assertEquals(true, studentResponseDto.name().contains(name));
        }

        verify(studentRepository, times(1)).findAllByNameContaining(name);
    }

    @Test
    public void should_successfully_delete_a_student_by_id() {
        Integer studentId = 2;

        Student student1 = new Student(
                "Tom1",
                "Meh1",
                "tomMeh@gmail.com1",
                21
        );
        student1.setId(1);

        Student student2 = new Student(
                "Tom2",
                "Meh2",
                "tomMeh@gmail.com2",
                22
        );
        student2.setId(2);

        Student student3 = new Student(
                "Rom3",
                "Meh3",
                "tomMeh@gmail.com3",
                23
        );
        student3.setId(3);

        List<Student> students = new ArrayList<>(Arrays.asList(student1, student2, student3));

        int initialSize = students.size();

        doAnswer(invocationOnMock -> {
            students.removeIf(student -> student.getId().equals(studentId));
            return null;
        }).when(studentRepository).deleteById(studentId);


        studentService.deleteStudent(studentId);


        verify(studentRepository, times(1)).deleteById(studentId);

        assertNotEquals(students.size(), initialSize);

        for(Student student : students) {
            assertNotEquals(studentId, student.getId());
        }
    }


}