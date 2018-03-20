package com.almundo.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.almundo.business.Dispatcher;
import com.almundo.model.Employed;
import com.almundo.model.Role;

/**
 * class test of the dispatcher
 * @author jhon.galeano
 *@version 23/03/2018
 */
class DispatcherTest {
	
	private Dispatcher dispatcher;
	int cantOPerator ;
	int cantSupervisor;
	int cantDirector;
	
	@BeforeEach
	/**
	 * initialize the test parameters
	 */
	void init() {
		 dispatcher = new Dispatcher(); 
		 cantOPerator =3;
		 cantSupervisor= 4;
		 cantDirector = 2;
	}

	@Test
	/**
	 * test of the dispatch function with 10 calls
	 */
	void testDispatchCall() {	
		int succesfulCalls =0;
		
		for (int i = 0; i < 10; i++) {
			if (dispatcher.dispatchCall()) {
				succesfulCalls ++;
			}
		}
		
		assertEquals(10, succesfulCalls);
		
	}

	@Test
	/**
	 * test that load employees operator, director and supervisor
	 */
	void testLoadEmployees() {
		int resultOPerator = 0;
		int resultDirector = 0;
		int resultSupervisor = 0;

		dispatcher.loadEmployees(cantOPerator, cantSupervisor, cantDirector);
		java.util.List<Employed> employees = Dispatcher.getEmployees();

		for (Employed employed : employees) {
			if (Role.OPERATOR.name().equals(employed.getType().name())) {
				resultOPerator++;
			} else if (Role.SUPERVISOR.name().equals(employed.getType().name())) {
				resultSupervisor++;
			} else if (Role.DIRECTOR.name().equals(employed.getType().name())) {
				resultDirector++;
			}

		}

		assertEquals(cantOPerator, resultOPerator);

		assertEquals(cantSupervisor, resultSupervisor);

		assertEquals(cantDirector, resultDirector);

	}

}
