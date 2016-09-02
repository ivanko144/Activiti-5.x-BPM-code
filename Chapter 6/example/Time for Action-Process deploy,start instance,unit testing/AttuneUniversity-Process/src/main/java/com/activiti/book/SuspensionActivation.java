package com.activiti.book;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;

public class SuspensionActivation {

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

		repositoryService.suspendProcessDefinitionByKey("traininngProcess");
		repositoryService.activateProcessDefinitionByKey("traininngProcess");
	}
}
