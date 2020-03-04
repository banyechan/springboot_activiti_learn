package com.banyechan.activiti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banyechan.activiti.service.ActivitiService;
import com.banyechan.activiti.serviceImpl.ActivitiServiceImpl;


@RestController
@RequestMapping("/activiti")
public class ActivitiController {
	
	Log log = LogFactory.getLog(ActivitiController.class);
	

	
	@Autowired
	private ActivitiService activitiService;
	
   
   @RequestMapping("/hello")
   public String sendMail(){
       String message ="这是一个测试邮件";
       return message;
   }
		
   
    @RequestMapping("/deploy")
    public void deploy(){
        String bpmnName = "sendMail";
        String id = activitiService.deploy(bpmnName);
        log.info("部署成功！id=" + id);
    }

    @RequestMapping("/start")
    public void start() {
        String bpmnName = "bandweavermail";

        Map<String ,Object> map = new HashMap<String,Object>();

        //map.put("message","春江潮水连海平，海上明月共潮生。");
        //map.put("content","不知乘月几人归，落月摇情满江树。");
        map.put("user","zhangsan");
        //map.put("users","zhangsan");

        String id = activitiService.startProcessInstance(bpmnName,map);

        log.info("start成功！id=" + id);
    }


    @RequestMapping("/list")
    public List<Task> taskList(){
        return activitiService.findTaskList();
    }

    @RequestMapping("/complet")
    public void complet(String taskId){
    	Map<String ,Object> map = new HashMap<String,Object>();
    	map.put("message", "mail");
    	map.put("content","不知乘月几人归，落月摇情满江树。");
        activitiService.complet(taskId,map);
    }
    
    @RequestMapping("/completShort")
    public void completToShort(String taskId){
    	log.info("----  开始执行 completShort -------");
    	Map<String ,Object> map = new HashMap<String,Object>();
    	map.put("message", "shortmail");
    	map.put("text","春江潮水连海平，海上明月共潮生。");
    	map.put("a","今天是星期一");
    	map.put("content","不知乘月几人归，落月摇情满江树。");
    	map.put("content","不知乘月几人归，落月摇情满江树。");
        activitiService.complet(taskId,map);
        
        log.info("----  执行 completShort end -------");
    }

	

}
