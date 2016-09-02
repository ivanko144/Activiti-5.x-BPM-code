package org.activiti.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LeaveRequestReceivedEvent")
@XmlAccessorType(XmlAccessType.FIELD)
public class LeaveRequestReceivedEvent {

	// For correlating the events.
	@XmlElement
	private final String processInstanceId;
	@XmlElement
	private final long receiveTime;
	@XmlElement
	private final int requestedDay;
	
	@SuppressWarnings("unused")
	private LeaveRequestReceivedEvent() {
		processInstanceId = null;
		receiveTime = 0L;
		requestedDay = 0;
	}
	
	public LeaveRequestReceivedEvent(String processInstanceId, long receiveTime, int requestedDay) {
		this.processInstanceId = processInstanceId;
		this.receiveTime = receiveTime;
		this.requestedDay = requestedDay;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public long getReceiveTime() {
		return receiveTime;
	}

	public int getRequestedDay() {
		return requestedDay;
	}

	@Override
	public String toString() {
		return "LeaveRequestReceivedEvent{processInstanceId="+processInstanceId+",receiveTime="+receiveTime+",requestedDay="+requestedDay+"}";
	}
}
