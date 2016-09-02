package com.activiti.listner;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class MyExecuationListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		long approved_amount_limit;
		
		approved_amount_limit=50000;
		System.out.println("******************the Start event Listener is Called************");
		execution.setVariable("approved_amount_limit", approved_amount_limit);
		
		
	}

}
