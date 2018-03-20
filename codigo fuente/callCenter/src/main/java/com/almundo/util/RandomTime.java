package com.almundo.util; 

/**
 * class Random  that generates the time of a call
 * @author jhon.galeano
 *@version 23/03/2018
 */
public class RandomTime {
	
	/**
	 * generate the time of a call with a parameters 
	 * @return the time of the call
	 */
	public static int generateTImeCall() {

		return (int) Math.floor(
				Math.random() * (Constants.MAX_TIME_CALL - Constants.MIN_TIME_CALL + 1) + Constants.MIN_TIME_CALL);

	}

}
