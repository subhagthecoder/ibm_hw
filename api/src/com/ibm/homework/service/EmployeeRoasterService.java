package com.ibm.homework.service;

import javax.ws.rs.core.Response;

import com.ibm.homework.model.Employee;

/**
 * An interface for the EmployeeRoasterService.
 */
public interface EmployeeRoasterService {

    public Response addEmployee(Employee e);
    
    public Response updateEmployee(String id, Employee e);

    public Response deleteEmployee(String id);

    public Response getEmployee(String id);

    public Employee[] getAllEmployees();

}
