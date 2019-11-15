package com.ibm.homework.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ibm.homework.service.EmployeeRoasterServiceImpl;

/**
 * The main app class for the EmployeeRoasterService.
 *
 */
@ApplicationPath("/api")
public class EmployeeRoasterApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public EmployeeRoasterApplication() {
		singletons.add(new EmployeeRoasterServiceImpl());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
