package com.banyechan.activiti.config;

import java.io.Serializable;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banyechan.activiti.entity.StudentModel;
import com.banyechan.activiti.service.StudentService;

@Component("delegateExpression")
public class DelegateExpression4ServiceTask implements Serializable, JavaDelegate {


    private static final long serialVersionUID = 8868317417484046639L;

    private Log log = LogFactory.getLog(DelegateExpression4ServiceTask.class);

    //@Autowired
    //private StudentService studentService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("------ 进入 delegateExecution 配置模式   start  ---------");
        StudentService studentService = SpringUtill.getBean(StudentService.class);
        List<StudentModel> list = studentService.listStudent();
        for (StudentModel tem : list) {
            log.info("--- name=" + tem.getName() + ",age=" + tem.getAge() + "  ---------");
        }

        log.info("------  delegateExecution 配置模式   end  --------");
    }

}
