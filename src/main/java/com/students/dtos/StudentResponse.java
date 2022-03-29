package com.students.dtos;
import com.students.models.students.Address;

import java.util.Date;

public class StudentResponse {
    public Long id;
    public String firstName;
    public String lastName;
    public String profileImage;
    public String phoneNumber;
    public String email;
    public String gender;
    public Address address;
    public Date createdAt;
    public Date updatedAt;
}

