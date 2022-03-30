
package com.students.controllers;

import com.students.dtos.StudentDto;
import com.students.dtos.StudentResponse;
import com.students.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.saveStudent(studentDto));
    }
    @PutMapping("update-student/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDto));
    }
    @GetMapping("get-students")
    public ResponseEntity<List<StudentResponse>> getStudents( @Param("keyword") String keyword) {
        return ResponseEntity.ok(studentService.getStudents(keyword));
    }
    @GetMapping("get-student/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable("id") Long id) {
        StudentResponse s=studentService.getStudent(id);
        System.out.println(s.toString());
        if(s!=null){
            return ResponseEntity.ok(s);
        }
       return ResponseEntity.notFound().build();
    }
    @DeleteMapping("delete-student/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") Long id) {
         studentService.deleteStudent(id);
         return ResponseEntity.ok(true);
    }
}
