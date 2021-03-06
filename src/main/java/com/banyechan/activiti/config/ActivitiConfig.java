package com.banyechan.activiti.config;

import javax.sql.DataSource;

import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
//import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ActivitiConfig {
	
	/**
     * 配置分为以下几步骤
     * 1. 创建ActivitiConfig
     * 2. 使用ActivitiConfig创建ProcessEngineFactoryBean
     * 3. 使用ProcessEngineFactoryBean创建ProcessEngine对象
     * 4. 使用ProcessEngine对象创建需要的服务对象
     * */

    private final DataSource dataSource;
    private final PlatformTransactionManager platformTransactionManager;

    @Autowired
    public ActivitiConfig(DataSource dataSource,PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.platformTransactionManager = platformTransactionManager;
    }

    //流程配置，与spring整合采用SpringProcessEngineConfiguration这个实现
    @Bean
    public ProcessEngineConfiguration processEngineConfiguration(){
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setTransactionManager(platformTransactionManager);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");//设置创建数据库的方式
        processEngineConfiguration.setDatabaseType("mysql");//设置数据库类型

        //流程图字体
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");

        processEngineConfiguration.setMailServerHost("smtp.mxhichina.com");
        processEngineConfiguration.setMailServerPort(465);
        processEngineConfiguration.setMailServerUsername("lutao.lv@bandweaver.cn");
        processEngineConfiguration.setMailServerPassword("bohui@banyechan1218");
        processEngineConfiguration.setMailServerUseSSL(true);

        processEngineConfiguration.setJobExecutorActivate(false);//JobExecutor是管理几个线程计时器的组成部分,JobExecutor对多线程的处理较为笨重缓慢
        processEngineConfiguration.setAsyncExecutorEnabled(false);//定义为true，使用AsyncExecutor代替默认的JobExecutor;
        processEngineConfiguration.setAsyncExecutorActivate(false);//定义为true，工作流引擎在启动时就建立启动AsyncExecutor线程
        
        

        return processEngineConfiguration;
    }

    //流程引擎，与spring整合使用factoryBean
    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(){
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration());
        return processEngineFactoryBean;
    }


    @Bean
    public ProcessEngine processEngine() throws Exception {
        return processEngineFactoryBean().getObject();
    }



    //八大接口
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }

    @Bean
    public FormService formService(ProcessEngine processEngine){
        return processEngine.getFormService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine){
        return processEngine.getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine){
        return processEngine.getDynamicBpmnService();
    }

    //八大接口 end
	
}
