package com.test.rules;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

import com.book.activiti.LaptopOrder;

public class BusinessRulesMain {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule(
			"activiti.cfg-mem-rules.xml");

	@Test
	@Deployment(resources = { "laptopOrderHumanProcess.bpmn20.xml",
			"payment.drl" })
	public void testLaptopProcess() {

		Map<String, Object> variableMap = new HashMap<String, Object>();
		Map<String, Object> completeOrder = new HashMap<String, Object>();

		LaptopOrder laptopOrder = new LaptopOrder();
		laptopOrder.setCustomerName("Irshad");
		laptopOrder.setLaptopModelNo(1234);
		laptopOrder.setLaptopName("Dell");
		laptopOrder.setLaptopQuantity(2);
		laptopOrder.setPaymentMode("creditCard");
		laptopOrder.setLaptopAmount(30000);
		

		variableMap.put("laptopOrder", laptopOrder);

		ProcessInstance processInstance = activitiRule.getRuntimeService()
				.startProcessInstanceByKey("laptopHumanProcess", variableMap);
		assertNotNull(processInstance);

		TaskService taskService = activitiRule.getTaskService();

		Task task = taskService.createTaskQuery().singleResult();
		completeOrder.put("acceptOrder", "true");
		taskService.complete(task.getId(), completeOrder);
		
		task = taskService.createTaskQuery().singleResult();		
		taskService.complete(task.getId(), variableMap);

	}
}