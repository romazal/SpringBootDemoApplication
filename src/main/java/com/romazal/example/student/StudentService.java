package com.romazal.example.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto studentDto) {
        return studentMapper.toStudentResponseDto(
                studentRepository.save(studentMapper.toStudent(studentDto))
        );
    }

    public List<StudentResponseDto> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this.studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto getStudent(Integer studentId) {
        return studentMapper.toStudentResponseDto(studentRepository.findById(studentId).orElse(new Student()));
    }

    public List<StudentResponseDto> getAllStudentsByName(String studentName) {
        return studentRepository.findAllByNameContaining(studentName)
                .stream()
                .map(this.studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

}
