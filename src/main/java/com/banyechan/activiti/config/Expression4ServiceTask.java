package com.banyechan.activiti.config;

import java.io.Serializable;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyechan.activiti.entity.StudentModel;
import com.banyechan.activiti.service.StudentService;


@Service("expression4ServiceTask")
public class Expression4ServiceTask implements Serializable {

    private static final long serialVersionUID = 8557578413039382107L;

    private Log log = LogFactory.getLog(Expression4ServiceTask.class);

    @Autowired
    private StudentService studentService;

    public void expressionTest(DelegateExecution execution) {

        log.info("------ 进入 expression 配置模式   start  ---------");

        List<StudentModel> list = studentService.listStudent();
        for (StudentModel tem : list) {
            log.info(tem.toString());
        }

        log.info("------  expression 配置模式   end  --------");

    }


}
