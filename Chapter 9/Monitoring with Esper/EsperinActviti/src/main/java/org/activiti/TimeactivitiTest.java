package org.activiti;

import java.util.LinkedList;
import java.util.Queue;

import junit.framework.Assert;

import org.activiti.event.LeaveRequestProcessedEvent;
import org.activiti.event.LeaveRequestReceivedEvent;
import org.junit.Before;
import org.junit.Test;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.client.time.CurrentTimeEvent;
import com.espertech.esper.client.time.TimerControlEvent;
import com.espertech.esper.client.time.TimerControlEvent.ClockType;

public class TimeactivitiTest {
	
	private EPRuntime epRuntime;
	private EPAdministrator epAdmin;

	@Before
	public void startEsper() {
		Configuration configuration = new Configuration();
		configuration.addEventTypeAutoName("org.activiti.event");
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(configuration);
		epRuntime = epService.getEPRuntime();
		epAdmin = epService.getEPAdministrator();
	}
	
	private Queue<Long> numLeavesQueue = new LinkedList<Long>();
	private Queue<Integer> sumLeaveedAmountQueue = new LinkedList<Integer>();
	
	@Test
	public void monitorLeaveedAmount() {
		System.out.println("---------- Start monitoring Leave Days ----------");
		
		epRuntime.sendEvent(new TimerControlEvent(ClockType.CLOCK_EXTERNAL));
		
		EPStatement epStatement = epAdmin.createEPL(
			"select count(*) as numLeaves, sum(requestedDay) as sumLeaveedAmount from LeaveRequestProcessedEvent(requestApproved=true).win:time(1 sec)");
		
		epStatement.addListener(new UpdateListener () {
			public void update(EventBean[] newEvents, EventBean[] oldEvents) {
				Assert.assertEquals(1, newEvents.length);
				Assert.assertNull(oldEvents);
				Long numLeaves = (Long) newEvents[0].get("numLeaves");
				Integer sumLeaveedAmount = (Integer) newEvents[0].get("sumLeaveedAmount");
				System.out.println("=>numLeaves="+numLeaves+", sumLeaveedAmount="+sumLeaveedAmount);
				numLeavesQueue.add(numLeaves);
				sumLeaveedAmountQueue.add(sumLeaveedAmount);
			}
		} );
		
		sendLeaveRequestProcessedEvent(1000, "1", true, 100);
		assertMonitoredLeaves(1L, 100);
		sendLeaveRequestProcessedEvent(1300, "2", true, 200);
		assertMonitoredLeaves(2L, 300);
		sendLeaveRequestProcessedEvent(1600, "3", false, 1000);
		assertMonitoredLeaves(null, null);
		sendLeaveRequestProcessedEvent(1900, "4", true, 300);
		assertMonitoredLeaves(3L, 600);
		sendLeaveRequestProcessedEvent(2200, "5", true, 400);
		assertMonitoredLeaves(2L, 500);
		assertMonitoredLeaves(3L, 900);
		sendLeaveRequestProcessedEvent(2400, "6", false, 900);
		assertMonitoredLeaves(2L, 700);
		assertMonitoredLeaves(null, null);
		
		epStatement.destroy();
	}

	
	private void assertMonitoredLeaves(Long numLeaves, Integer sumLeaveedAmount) {
		Assert.assertEquals(numLeaves, numLeavesQueue.poll());
		Assert.assertEquals(sumLeaveedAmount, sumLeaveedAmountQueue.poll());
	}

	private Queue<Double> avgProcessDurationQueue = new LinkedList<Double>();
	private Queue<Long> maxProcessDurationQueue = new LinkedList<Long>();
	
	@Test
	public void monitorProcessDuration() {
		System.out.println("---------- Start monitoring process duration ----------");
		
		epRuntime.sendEvent(new TimerControlEvent(ClockType.CLOCK_EXTERNAL));

		EPStatement epStatement = epAdmin.createEPL(new StringBuffer()
				.append("select avg(endEvent.processedTime - beginEvent.receiveTime) as avgProcessDuration, ")
				.append("max(endEvent.processedTime - beginEvent.receiveTime) as maxProcessDuration ")
				.append("from pattern [every beginEvent=LeaveRequestReceivedEvent -> endEvent=LeaveRequestProcessedEvent(processInstanceId=beginEvent.processInstanceId)].win:time(5 sec)")
				.toString());
		
		epStatement.addListener(new UpdateListener () {
			public void update(EventBean[] newEvents, EventBean[] oldEvents) {
				Assert.assertEquals(1, newEvents.length);
				Assert.assertNull(oldEvents);
				Double avgProcessDuration = (Double) newEvents[0].get("avgProcessDuration");
				Long maxProcessDuration = (Long) newEvents[0].get("maxProcessDuration");
				System.out.println("avgProcessDuration="+avgProcessDuration+", maxProcessDuration="+maxProcessDuration);
				avgProcessDurationQueue.add(avgProcessDuration);
				maxProcessDurationQueue.add(maxProcessDuration);
			}
		} );
		
		sendLeaveRequestReceivedEvent (   0, "1", 100);
		assertMonitoredProcessDuration(null, null);
		
		sendLeaveRequestReceivedEvent ( 300, "2", 200);
		assertMonitoredProcessDuration(null, null);
		
		sendLeaveRequestProcessedEvent( 400, "2", true, 200);
		assertMonitoredProcessDuration(100.0, 100L);
		
		sendLeaveRequestProcessedEvent( 600, "1", true, 100);
		assertMonitoredProcessDuration(350.0, 600L);
		
		sendLeaveRequestReceivedEvent (1100, "3", 300);
		assertMonitoredProcessDuration(null, null);
		
		sendLeaveRequestProcessedEvent(1600, "3", true, 300);
		assertMonitoredProcessDuration(400.0, 600L);

		epStatement.destroy();
	}

	
	private void assertMonitoredProcessDuration(Double avgProcessDuration, Long maxProcessDuration) {
		Assert.assertEquals(avgProcessDuration, avgProcessDurationQueue.poll());
		Assert.assertEquals(maxProcessDuration, maxProcessDurationQueue.poll());
	}

	private void sendLeaveRequestReceivedEvent(long time, String processInstanceId, int requestedAmount) {
		sendEvent(time, new LeaveRequestReceivedEvent(processInstanceId, time, requestedAmount));
	}

	private void sendLeaveRequestProcessedEvent(long time, String processInstanceId, boolean requestApproved, int LeaveedAmount) {
		sendEvent(time, new LeaveRequestProcessedEvent(processInstanceId, time, requestApproved, LeaveedAmount));
	}

	private void sendEvent(long time, Object event) {
		System.out.printf(" %1$4d : %2$s\n", time, event);
		epRuntime.sendEvent(new CurrentTimeEvent(time));
		epRuntime.sendEvent(event);
	}
}
