package com.banyechan.activiti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banyechan.activiti.config.DelegateExpression4ServiceTask;
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
       String message = "陋室空堂，当年笏满床；衰草枯杨，曾为歌舞场。" +
               "蛛丝儿结满雕梁，绿纱今又糊在蓬窗上。说甚么脂正浓、粉正香，如何两鬓又成霜" +
               "昨日黄土陇头送白骨，今宵红灯帐底卧鸳鸯。" +
               "金满箱，银满箱，展眼乞丐人皆谤。正叹他人命不长，那知自己归来丧！" +
               "训有方，保不定日后作强梁；择膏粱，谁承望流落在烟花巷！" +
               "因嫌纱帽小，致使锁枷扛；昨怜破袄寒，今嫌紫蟒长。" +
               "乱烘烘，你方唱罢我登场，反认他乡是故乡；甚荒唐，到头来都是为他人作嫁衣裳！";
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
        map.put("user","zhangsan");

        String id = activitiService.startProcessInstance(bpmnName,map);

        log.info("start成功！id=" + id);
    }


    @RequestMapping("/list")
    public List<Task> taskList(){
        return activitiService.findTaskList();
    }

    /**
     * activiti MailTask流程
     * 自动发送邮件
     */
    @RequestMapping("/complet")
    public void complet(String taskId){
        Map<String ,Object> map = new HashMap<String,Object>();
        map.put("message", "mail");
        map.put("content","不知乘月几人归，落月摇情满江树。");
        activitiService.complet(taskId,map);
    }
    
    /**      activiti ServiceTask流程
     *  i 实现方式有三种：
     *      1.通过 Java Class方式，实现类需实现 JavaDelegate 接口，并重写其execute()方法，在execute()方法中
     *         	 写自己的业务逻辑。但是不需要实现序列化接口
     *      2.通过 delegateExpression方式，实现JavaDelegate接口，使用其中的execute方法 由于要放入流程定义中，
     *      	所以要实现可序列话接口
     *      3.通过 expression方式 ，不需要实现javadelegate，但是由于要放入到流程定义中，所以需要实现可序列话接口
     *
     */

    @RequestMapping("/complet2Java")
    public void completToJavaClass(String taskId) {
        log.info("----  开始执行 complet2Java -------");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "tips");
        map.put("text", "春江潮水连海平，海上明月共潮生。");
        map.put("content", "不知乘月几人归，落月摇情满江树。");
        activitiService.complet(taskId, map);

        log.info("----  执行 complet2Java end -------");
    }

    @RequestMapping("/complet2Express")
    public void completToExpress(String taskId) {
        log.info("----  开始执行 complet2Express -------");
        Map<String, Object> map = new HashMap<String,Object>();
    	map.put("message", "expression");
        map.put("text", "春江潮水连海平，海上明月共潮生。");
    	map.put("a","今天是星期一");
    	map.put("content","不知乘月几人归，落月摇情满江树。");
    	map.put("content","不知乘月几人归，落月摇情满江树。");
        activitiService.complet(taskId,map);
        
        log.info("----  执行 complet2Express end -------");
    }

    @RequestMapping("/complet2Delegate")
    public void completToDelegateExpress(String taskId) {
        log.info("----  开始执行 complet2Delegate -------");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "delegate");
        map.put("delegateExpression", new DelegateExpression4ServiceTask());

        activitiService.complet(taskId, map);

        log.info("----  执行 complet2Delegate end -------");
    }


}
