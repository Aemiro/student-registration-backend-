package com.students.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.students.dtos.StudentDto;
import com.students.dtos.StudentResponse;
import com.students.models.students.StudentRepository;
import com.students.models.students.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponse> getStudents(String keyword) {
        List<StudentResponse> responses = new ArrayList<StudentResponse>();
        if (keyword != null) {
            List<Student> result = studentRepository.searchStudent(keyword);

            for (Student s : result) {
                try {
                    responses.add(s.toResponseModel());
                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException("Bad Request");
                }

            }
            return responses;
        }
        List<Student> students = studentRepository.findAll();
        for (Student s : students) {
            try {
                responses.add(s.toResponseModel());
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Bad Request");
            }

        }
        return responses;
    }

    public StudentResponse getStudent(Long id) {
        try {
            Optional<Student> s = studentRepository.findById(id);
            return s.get().toResponseModel();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Bad request");
        }
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public StudentResponse saveStudent(StudentDto studentDto) {
        Student newStudent = new Student(studentDto);
        StudentResponse studentResponse = new StudentResponse();
        try {
            studentResponse = studentRepository.save(newStudent).toResponseModel();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return studentResponse;

    }

    public StudentResponse updateStudent(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse;
        if (student != null) {
            student.setFirstName(studentDto.firstName);
            student.setLastName(studentDto.lastName);
            student.setGender(studentDto.gender);
            student.setEmail(studentDto.email);
            student.setProfileImage(studentDto.profileImage);
            student.setAddressFromJson(studentDto.address);
            student.setPhoneNumber(studentDto.phoneNumber);
            try {
                studentResponse = studentRepository.save(student).toResponseModel();
                return studentResponse;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
       return null;

    }
}
