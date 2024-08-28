package com.romazal.example.school;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolResponseDto createSchool(SchoolDto schoolDto) {
        return schoolMapper.toSchoolResponseDto(schoolRepository.save(schoolMapper.toSchool(schoolDto)));
    }


    public List<SchoolResponseDto> getAllSchools() {
        List<SchoolResponseDto> schools = new ArrayList<>();
        schoolRepository.findAll().forEach(school -> {
            schools.add(new SchoolResponseDto(school.getName()));
        });
        return schools;
    }


}
