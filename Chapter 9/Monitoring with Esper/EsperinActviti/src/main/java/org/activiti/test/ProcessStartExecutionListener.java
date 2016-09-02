package org.activiti.test;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.event.LeaveRequestReceivedEvent;

import com.espertech.esper.client.EPServiceProviderManager;

public class ProcessStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		LeaveRequestReceivedEvent event = new LeaveRequestReceivedEvent(
				execution.getId(), 
				new Date().getTime(), 
				(Integer) execution.getVariable("leaveDay"));
		System.out.println(">>> Throwing event: "+event);
		EPServiceProviderManager.getDefaultProvider().getEPRuntime()
				.getEventSender("LeaveRequestReceivedEvent")
				.sendEvent(event);
	}
}
