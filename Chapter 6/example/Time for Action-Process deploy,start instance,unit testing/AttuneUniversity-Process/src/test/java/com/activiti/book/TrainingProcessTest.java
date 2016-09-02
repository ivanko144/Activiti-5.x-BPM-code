package com.activiti.book;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

public class TrainingProcessTest {
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	@Deployment
	public void trainingRequest() {
		
		Date date = new Date();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("customerName", "Irshad");
		variables.put("customerEamil", "irshad.mansuri@attuneinfocom.com");
		variables.put("trainingTopic", "Activiti");
		variables.put("trainingDate", date);
		variables.put("trainerName", "Irshad");
		variables.put("trainerMailId","rehan.pathan@attuneinfocom.com");		
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		runtimeService.startProcessInstanceByKey("traininngProcess",variables);

		TaskService taskService = activitiRule.getTaskService();
		Task task = taskService.createTaskQuery().singleResult();
		
		assertEquals("Business Development Executive", task.getName());

		taskService.complete(task.getId());
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	}
}
