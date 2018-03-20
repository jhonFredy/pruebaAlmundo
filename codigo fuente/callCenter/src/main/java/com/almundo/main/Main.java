package com.almundo.main;

import com.almundo.business.Dispatcher;

/**
 * Main of application
 * @author jhon.galeano
 *@version 23/03/2018
 */
public class Main {

	/**
	 * load the call center with tens calls
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dispatcher dispatcher = new Dispatcher();
		
		for (int i = 0; i < 11; i++) {
			dispatcher.dispatchCall();
		}
	}

}
