package net.accedegh.studentregistration.models;

import android.content.Context;

import java.util.ArrayList;

public class Student {


    public Student(int studentId, String name, String phone, String email) {
        StudentId = studentId;
        Name = name;
        Phone = phone;
        Email = email;
    }

    public int StudentId;
    public String Name;
    public String Phone;
    public String Email;

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}