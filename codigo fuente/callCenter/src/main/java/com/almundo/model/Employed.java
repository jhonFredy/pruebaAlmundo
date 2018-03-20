package com.almundo.model;

import com.almundo.business.Dispatcher;

/**
 * class Employed
 * @author jhon.galeano
 *@version 23/03/2018
 */
public class Employed implements Runnable{

	String id;	
	String name;
	Role type;
	int timeCall;
	
	/**
	 * Constructor of employed
	 * @param id id of employed
	 * @param name name of employed
	 * @param type rol of employed
	 */
	public Employed(String id, String name, Role type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	/**
	 * get the id of employed
	 * @return id of employed
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * set id of employed
	 * @param id of employed
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * get the name of employed
	 * @return name of employed
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set name of employed
	 * @param name of employed
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get the Role of employed
	 * @return Role of employed
	 */
	public Role getType() {
		return type;
	}
	
	/**
	 * set type of employed
	 * @param type of employed
	 */
	public void setType(Role type) {
		this.type = type;
	}
	
	/**
	 * get time of call
	 * @return the time of call
	 */
	public int getTimeCall() {
		return timeCall;
	}
	
	/**
	 * set time of call
	 * @param timeCall time of call
	 */
	public void setTimeCall(int timeCall) {
		this.timeCall = timeCall;
	}

	@Override
	/**
	 * answer a call at a specific time
	 */
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("processing call:::: " + "Operator: " + name + " Rol: " + type.name());

		try {
			Thread.sleep(timeCall * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		System.out.println("Operator: " + name + "  call completed!!");
		Dispatcher.loadEmployed(this);
	}
	
	
	
}
