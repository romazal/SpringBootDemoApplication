package com.romazal.example.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(

        @NotEmpty(message = "Firstname mustn't be empty")
        String name,

        @NotEmpty(message = "Lastname mustn't be empty")
        String lastname,

        String email,

        Integer schoolId
) {
}
