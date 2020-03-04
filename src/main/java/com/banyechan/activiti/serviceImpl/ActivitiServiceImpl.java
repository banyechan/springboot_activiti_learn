package com.banyechan.activiti.serviceImpl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyechan.activiti.service.ActivitiService;


@Service
public class ActivitiServiceImpl implements ActivitiService{
	
	Log log = LogFactory.getLog(ActivitiServiceImpl.class);
	
	@Autowired
    ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
   
	@Override
	public void sendMail(String message) {
		String executionId = "sendMail";
		 Map<String ,Object> variables  = runtimeService.getVariables(executionId);
		 log.info(variables);
	}

	@Override
    public String deploy(String name) {
        String bpmn = "bpmn/"+ name +".bpmn";
        String png = "bpmn/"+ name +".png";
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name(name)//添加部署的名称
                .addClasspathResource(bpmn)//从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource(png)//从classpath的资源中加载，一次只能加载一个文件
                .deploy();//完成部署
        log.info("-----部署ID：" + deployment.getId());
        log.info("-----部署名称：" + deployment.getName());

        // 流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();

        log.info("----流程ID：" + processDefinition.getId());
        log.info("----流程.toString---" + processDefinition.toString());


        return deployment.getId();
    }

    @Override
    public String startProcessInstance(String name, Map<String, Object> map) {

        ProcessInstance pi = processEngine.getRuntimeService()
                .startProcessInstanceByKey(name,map);

        log.info("流程实例ID:"+pi.getId());//流程实例ID
        log.info("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID
        log.info("流程.toString"+pi.toString());





        return pi.getId();
    }

    @Override
    public List<Task> findTaskList() {
        List<Task> taskList=processEngine.getTaskService()
                //创建任务查询
                .createTaskQuery()
                .list();
        return taskList;
    }

    @Override
    public void complet(String taskId,Map<String ,Object> map) {
        taskService.complete(taskId,map);
    }

	
	
	
}
