package com.romazal.example.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDto schoolDto) {
        School school = new School();

        school.setName(schoolDto.name());

        return school;
    }

    public SchoolResponseDto toSchoolResponseDto(School school) {
        return new SchoolResponseDto(school.getName());
    }
}
