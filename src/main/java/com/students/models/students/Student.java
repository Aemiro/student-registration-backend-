package com.students.models.students;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.students.dtos.StudentDto;
import com.students.dtos.StudentResponse;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotNull()
    private String firstName;
    @NotNull()
    private String lastName;
    private String profileImage;
    private String phoneNumber;
    private String email;
    @NotNull()
    private String gender;
    @Nullable()

    private String address;
    @Column(nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
    @Column(nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Student() {
    }

    public Student(StudentDto studentDto) {
        this.firstName = studentDto.firstName;
        this.lastName = studentDto.lastName;
        this.gender = studentDto.gender;
        this.profileImage = studentDto.profileImage;
        this.phoneNumber = studentDto.phoneNumber;
        this.email = studentDto.email;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(studentDto.address);
            System.out.println("ResultingJSONstring = " + json);
            this.address = json;
            //System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.updatedAt = new Date();
    }

    public Student(Long id, String firstName, String lastName, String gender, String profileImage, String phoneNumber, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
       this.address=address;
    }
public void setAddressFromJson(Address address){
    ObjectMapper mapper = new ObjectMapper();
    try {
        String json = mapper.writeValueAsString(address);
        System.out.println("Update = " + json);
        this.address = json;
        //System.out.println(json);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }
}
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public StudentResponse toResponseModel() throws JsonProcessingException {
        StudentResponse response=new StudentResponse();
        response.id = this.id;
        response.firstName = this.firstName;
        response.lastName = this.lastName;
        response.gender = this.gender;
        response.profileImage = this.profileImage;
        response.phoneNumber = this.phoneNumber;
        response.email = this.email;
        ObjectMapper mapper = new ObjectMapper();

        response.address=mapper.readValue(this.address, Address.class);
        response.createdAt = this.createdAt;
        response.updatedAt = this.updatedAt;
        return response;
    }
}
