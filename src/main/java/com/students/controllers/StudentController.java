
package com.students.controllers;

import com.students.dtos.StudentDto;
import com.students.dtos.StudentResponse;
import com.students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping("create-student")
    public StudentResponse createStudent(@RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }
    @PutMapping("update-student/{id}")
    public StudentResponse updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }
    @GetMapping("get-students")
    public List<StudentResponse> getStudents( @Param("keyword") String keyword) {
        return studentService.getStudents(keyword);
    }
    @GetMapping("get-student/{id}")
    public StudentResponse getStudent(@PathVariable("id") Long id) {
        StudentResponse s=studentService.getStudent(id);
        System.out.println(s.toString());
        if(s!=null){
            return s;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }
    @DeleteMapping("delete-student/{id}")
    public boolean deleteStudent(@PathVariable("id") Long id) {
         studentService.deleteStudent(id);
         return true;
    }
}
