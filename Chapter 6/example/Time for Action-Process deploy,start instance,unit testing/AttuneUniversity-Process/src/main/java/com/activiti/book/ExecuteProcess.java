package com.activiti.book;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.IdentityServiceImpl;
import org.activiti.engine.impl.util.ReflectUtil;
import org.activiti.engine.runtime.ProcessInstance;

public class ExecuteProcess {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ProcessEngine processEngine = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration()
				.setJdbcDriver("com.mysql.jdbc.Driver")
				.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_book")
				.setJdbcPassword("root").setJdbcUsername("root")
				.buildProcessEngine();

		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		// Deploying The process into the repository

		repositoryService
				.createDeployment()
				.addInputStream(
						"trainingRequest.bpmn20.xml",
						ReflectUtil
								.getResourceAsStream("diagrams/trainingRequest.bpmn"))
				.deploy();

		// Starting the Deployed Process

		Date date = new Date();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("customerName", "Irshad");
		variables.put("customerEamil", "irshad.mansuri@attuneinfocom.com");
		variables.put("trainingTopic", "Activiti");
		variables.put("trainingDate", date);

		RuntimeService runtimeService = processEngine.getRuntimeService();

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("traininngProcess", variables);
	}
}