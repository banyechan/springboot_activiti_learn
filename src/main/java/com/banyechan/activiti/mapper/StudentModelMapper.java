package com.banyechan.activiti.mapper;

import com.banyechan.activiti.entity.StudentModel;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StudentModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StudentModel record);

    int insertSelective(StudentModel record);

    StudentModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentModel record);

    int updateByPrimaryKey(StudentModel record);

    List<StudentModel> listStudent();


}