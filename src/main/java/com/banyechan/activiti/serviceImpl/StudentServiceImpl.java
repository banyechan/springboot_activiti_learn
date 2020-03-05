package com.banyechan.activiti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyechan.activiti.entity.StudentModel;
import com.banyechan.activiti.mapper.StudentModelMapper;
import com.banyechan.activiti.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentModelMapper studentMapper;

    @Override
    public StudentModel getByByPrimaryKey(Integer id) {

        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StudentModel> listStudent() {

        return studentMapper.listStudent();
    }


}
