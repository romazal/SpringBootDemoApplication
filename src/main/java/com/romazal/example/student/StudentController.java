package com.romazal.example.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController()
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }


    @GetMapping("/students")
    public List<StudentResponseDto> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto getStudent(@PathVariable("student-id") Integer studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> getAllStudentsByName(@PathVariable("student-name") String studentName) {
        return studentService.getAllStudentsByName(studentName);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student-id") Integer studentId) {
        studentService.deleteStudent(studentId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ){
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
