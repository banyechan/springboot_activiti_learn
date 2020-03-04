package com.banyechan.activiti.service;


import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;

public interface ActivitiService {
	
	 	void sendMail(String message);

	    String deploy(String name);

	    String startProcessInstance(String name,Map<String ,Object> map);

	    List<Task> findTaskList();

	    void complet(String taskId,Map<String ,Object> map);

}
