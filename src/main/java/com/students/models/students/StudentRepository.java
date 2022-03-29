package com.students.models.students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "select * from students s where s.first_name like %:keyword% or s.last_name like %:keyword% or s.email like %:keyword% or s.phone_number like %:keyword%", nativeQuery = true)
    public List<Student> searchStudent(@Param("keyword") String keyword);
}
