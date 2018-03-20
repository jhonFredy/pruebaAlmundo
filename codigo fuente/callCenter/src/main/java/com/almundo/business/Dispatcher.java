/**
 * 
 */
package com.almundo.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.almundo.model.Employed;
import com.almundo.model.Role;
import com.almundo.util.Constants;
import com.almundo.util.RandomTime;

/**
 * Class that answers calls to the call center
 * @author jhon.galeano
 *@version 23/03/2018
 */
public class Dispatcher {
	
	static List<Employed> employees; 
	private ExecutorService executor;
	static boolean availableEmployees;
	
	
	
	/**
	 * class constructor Dispatcher
	 */
	public Dispatcher() {
		loadEmployees(Constants.CANT_OPERATOR, Constants.CANT_SUPERVISOR, Constants.CANT_DIRECTOR);
		executor = Executors.newFixedThreadPool(Constants.AMOUNT_CALL);
		availableEmployees = true;
	}
	
	/**
	 * method that receives a call and assigns an employee to this
	 * @return true if the call was successfully answered
	 */
	public synchronized boolean dispatchCall() {

		if (employees.isEmpty()) {
			availableEmployees = false;
		}
		//wait for an available employee
		while (!availableEmployees) {
			try {
				Thread.sleep(Constants.TIME_WAIT);
				System.out.println(" On hold!!!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Employed employeeCall = AssignEmployeeToCall();
		employeeCall.setTimeCall(RandomTime.generateTImeCall());
		executor.execute(employeeCall);

		return true;

	}
	
	/**
	 * loads the number of call center employees by assigning them a role
	 * @param cantOPerator number of operator a create
	 * @param cantSupervisor number of supervisor a create
	 * @param cantDirector number of director a create
	 */
	public void loadEmployees(int cantOPerator, int cantSupervisor, int cantDirector) {

		employees = new ArrayList<>();

		for (int i = 0; i < cantOPerator; i++) {
			employees.add(new Employed(i + "", "operator " + (i + 1), Role.OPERATOR));
		}

		for (int j = 0; j < cantSupervisor; j++) {
			Employed employedF = new Employed(j + cantOPerator + "", "Superovisor" + (j + 1), Role.SUPERVISOR);
			employees.add(employedF);
		}

		for (int k = 0; k < cantDirector; k++) {
			Employed employedI = new Employed(k + cantOPerator + cantSupervisor + "", "Director" + (k + 1),
					Role.DIRECTOR);
			employees.add(employedI);
		}
	}
	
	/**
	 * Assigns an available employee, in the order they must attend
	 * @return the employee to attend the next call
	 */
	private Employed AssignEmployeeToCall() {

		Optional<Employed> avaliableEmployee = null;
		avaliableEmployee = employees.stream()
				.filter(employed -> employed.getType().name().equals(Role.OPERATOR.name())).findFirst();

		if (avaliableEmployee.isPresent()) {
			employees.remove(avaliableEmployee.get());
			return avaliableEmployee.get();
		}

		avaliableEmployee = employees.stream()
				.filter(employed -> employed.getType().name().equals(Role.SUPERVISOR.name())).findFirst();

		if (avaliableEmployee.isPresent()) {
			employees.remove(avaliableEmployee.get());
			return avaliableEmployee.get();
		}

		avaliableEmployee = employees.stream()
				.filter(employed -> employed.getType().name().equals(Role.DIRECTOR.name())).findFirst();
		
		if (avaliableEmployee.isPresent()) {
			employees.remove(avaliableEmployee.get());
			return avaliableEmployee.get();
		}

		else {
			return null;
		}
 
	}
	
	/**
	 * load a employee when ends a call
	 * @param employedNew the employee what ends the call
	 */
	public static synchronized void loadEmployed(Employed employedNew) {
		employees.add(employedNew);
		availableEmployees = true;
	}

	/**
	 * return the list of employees available
	 * @return the listo of employees
	 */
	public static List<Employed> getEmployees() {
		return employees;
	}

	/**
	 * set a list of employees
	 * @param employees to set
	 */
	public static void setEmployees(List<Employed> employees) {
		Dispatcher.employees = employees;
	}
	
	
	
	

}
