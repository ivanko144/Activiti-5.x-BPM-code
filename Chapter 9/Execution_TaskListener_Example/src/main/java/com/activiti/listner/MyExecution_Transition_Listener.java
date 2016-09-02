package com.activiti.listner;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class MyExecution_Transition_Listener  implements ExecutionListener{

	@Override
	public void notify(DelegateExecution exection) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("your Request Is Aproved");
	}

}
