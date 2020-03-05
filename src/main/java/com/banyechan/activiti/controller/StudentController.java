package com.banyechan.activiti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banyechan.activiti.entity.StudentModel;
import com.banyechan.activiti.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/list")
    public List<StudentModel> getStudentList() {

        List<StudentModel> resultList = studentService.listStudent();
        return resultList;
    }

    @RequestMapping("/get")
    public StudentModel getStudent(Integer id) {

        StudentModel result = studentService.getByByPrimaryKey(id);
        return result;
    }


}
