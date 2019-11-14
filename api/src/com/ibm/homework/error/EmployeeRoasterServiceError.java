package com.ibm.homework.error;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import com.ibm.homework.model.GenericResponse;
import com.ibm.homework.model.Role;

public class EmployeeRoasterServiceError {
        
    public Response getNonExistentEmployeeErrorResponse() {
	GenericResponse response = new GenericResponse();
	response.setStatus(false);
	response.setMessage("Employee does not exist. Please verify the ID!");
	response.setErrorCode("NONEXISTENT_EMPLOYEE_ERROR");
	return Response.status(404).entity(response).build();
    }
    
    public Response getDuplicateCEOErrorResponse() {
	GenericResponse response = new GenericResponse();
	response.setStatus(false);
	response.setMessage("CEO can't be more than one! Please assign a dfferent role.");
	response.setErrorCode("EXISTING_CEO_ERROR");
	return Response.status(422).entity(response).build();
    }
    
    public Response getInvalidHireDateErrorResponse() {
	GenericResponse response = new GenericResponse();
	response.setStatus(false);
	response.setMessage("Hire should be in the past and in the yyyy-MM-dd format only.");
	response.setErrorCode("INVALID_HIRE_DATE_ERROR");
	return Response.status(422).entity(response).build();
    }
    
    public Response getExistingEmployeeErrorResponse() {
	GenericResponse response = new GenericResponse();
	response.setStatus(false);
	response.setMessage("Employee Already Exists");
	response.setErrorCode("EXISTING_EMPLOYEE_ERROR");
	return Response.status(422).entity(response).build();
    }
    
    public Response getInvalidRoleErrorResponse() {
	GenericResponse response = new GenericResponse();
	response.setStatus(false);
	response.setMessage("Role must be one of: " + Arrays.toString(Role.values()));
	response.setErrorCode("INVALID_ROLE_ERROR");
	return Response.status(422).entity(response).build();
    }
}
