package com.banyechan.activiti.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.banyechan.activiti.entity.StudentModel;
import com.banyechan.activiti.service.StudentService;



public class TaskServiceModel implements JavaDelegate{
	
	Log log = LogFactory.getLog(TaskServiceModel.class);
	
	private Expression text1;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

        log.info("------ 进入 java class 配置模式   start  ---------");
		
		//Object object = text1.getValue(execution);
		//log.info("------object = " + object.toString() + "--------");
		Map<String,Object> variables = execution.getVariables();
		if(variables != null && !variables.isEmpty()) {
			Set<String> keySet = variables.keySet();
			for(String key : keySet) {
				log.info("------key = " + key + ",value = " + variables.get(key)+ "-------");
			}
		}
        StudentService studentService = SpringUtill.getBean(StudentService.class);
        List<StudentModel> list = studentService.listStudent();
        for (StudentModel tem : list) {
            log.info("--- name=" + tem.getName() + ",age=" + tem.getAge() + "  ---------");
        }


        log.info("------  java class 配置模式   end  ---------");
	}

}
