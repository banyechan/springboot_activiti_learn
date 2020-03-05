package com.banyechan.activiti.service;

import java.util.List;

import com.banyechan.activiti.entity.StudentModel;

public interface StudentService {


    StudentModel getByByPrimaryKey(Integer id);

    List<StudentModel> listStudent();


}
