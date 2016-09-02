package com.activiti.listner;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyTaskListener implements TaskListener{

	@Override
	public void notify(DelegateTask task) {
		// TODO Auto-generated method stub
		
		task.setAssignee("kermit");
		
		System.out.println("Task Listener is called");
	}

}
