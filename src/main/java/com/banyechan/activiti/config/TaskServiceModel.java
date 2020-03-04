package com.banyechan.activiti.config;

import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class TaskServiceModel implements JavaDelegate{
	
	Log log = LogFactory.getLog(TaskServiceModel.class);
	
	private Expression text1;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("serviceTask已经执行已经执行！");
		
		//Object object = text1.getValue(execution);
		//log.info("------object = " + object.toString() + "--------");
		Map<String,Object> variables = execution.getVariables();
		if(variables != null && !variables.isEmpty()) {
			Set<String> keySet = variables.keySet();
			for(String key : keySet) {
				log.info("------key = " + key + ",value = " + variables.get(key)+ "-------");
			}
		}
		
		
		 
		
	}

}
