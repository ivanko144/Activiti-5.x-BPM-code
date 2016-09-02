package com.activiti;

import java.security.Identity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;



public class HistoricData {

	public static void main(String ar[])
	{
	ProcessEngine processEngine = ProcessEngineConfiguration
			.createStandaloneProcessEngineConfiguration()
			.setJdbcDriver("com.mysql.jdbc.Driver")
			.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_book")
			.setJdbcPassword("root").setJdbcUsername("root")
			.buildProcessEngine();
	
	HistoryService history=processEngine.getHistoryService();
	
	List<HistoricActivityInstance>   processinstance=history.createHistoricActivityInstanceQuery().taskAssignee("gonzo").list();

	for(HistoricActivityInstance pi:processinstance)
	{
		System.out.println("Historic Process Instances for kermit:"+pi.getProcessDefinitionId());
		
	}
	
	}
}
