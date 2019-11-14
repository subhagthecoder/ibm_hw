package com.ibm.homework.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.homework.error.EmployeeRoasterServiceError;
import com.ibm.homework.external.JokeTriviaFetcher;
import com.ibm.homework.external.QuoteTriviaFetcher;
import com.ibm.homework.model.Employee;
import com.ibm.homework.model.GenericResponse;
import com.ibm.homework.model.Role;

@Path("/employees")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class EmployeeRoasterServiceImpl implements EmployeeRoasterService {

    private Map<String, Employee> emps;
    private final DateFormat dateFormat;
    private final EmployeeRoasterServiceError error;
    private final HashSet<String> roles;
    private static Employee ceo;
    private final JokeTriviaFetcher jokeFetcher;
    private final QuoteTriviaFetcher quoteFetcher;

    public EmployeeRoasterServiceImpl() {
	emps = new HashMap<String, Employee>();
	dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	error = new EmployeeRoasterServiceError();
	roles = new HashSet<String>();
	for (Role role : Role.values()) {
	    roles.add(role.name());
	}
	ceo = null;
	jokeFetcher = new JokeTriviaFetcher();
	quoteFetcher = new QuoteTriviaFetcher();
    }
    @Override
    @POST
    public Response addEmployee(Employee e) {
	GenericResponse response = new GenericResponse();
	if(emps.get(e.getId()) != null){
	    return error.getExistingEmployeeErrorResponse();
	}
	if (Role.CEO.name().equalsIgnoreCase(e.getRole())) {
	    if (ceo != null) {
		return error.getDuplicateCEOErrorResponse();
	    } else {
		ceo = e;
	    }
	}
	try {
	    if(e.getHireDate() != null && new Date().before(dateFormat.parse(e.getHireDate()))) {
		return error.getInvalidHireDateErrorResponse();
	    }
	} catch (ParseException ex) {
	    return error.getInvalidHireDateErrorResponse();
	}
	if(e.getRole() != null) {
	    String role = e.getRole().toUpperCase();
	    if(!roles.contains(role))
		return error.getInvalidRoleErrorResponse();
	    else
		e.setRole(role);
	}
	
	e.setId(e.generateId());
	e.setFavoriteJoke(jokeFetcher.getTrivia());
	e.setFavoriteQuote(quoteFetcher.getTrivia());
	
	emps.put(e.getId(), e);
	response.setEmployee(e);
	response.setStatus(true);
	response.setMessage("Employee created successfully with ID: " + e.getId());
	return Response.ok(response).build();
    }

    @Override
    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") String id, Employee e) {
	GenericResponse response = new GenericResponse();
	Employee existingE = emps.get(id);
	if(existingE == null){
	    return error.getNonExistentEmployeeErrorResponse();
	}
	// Check if trying to make another CEO in the update operation.
	if (Role.CEO.name().equalsIgnoreCase(e.getRole())) {
	    if (ceo != null && id != ceo.getId()) {
		return error.getDuplicateCEOErrorResponse();
	    } else {
		ceo = e;
	    }
	}
	try {
	    if(e.getHireDate() != null && new Date().before(dateFormat.parse(e.getHireDate()))) {
		return error.getInvalidHireDateErrorResponse();
	    }
	} catch (ParseException ex) {
	    return error.getInvalidHireDateErrorResponse();
	}
	if(e.getRole() != null) {
	    String role = e.getRole().toUpperCase();
	    if(!roles.contains(role))
		return error.getInvalidRoleErrorResponse();
	    else
		e.setRole(role);
	}
	updateNonNullValues(existingE, e);
	response.setEmployee(existingE);
	response.setStatus(true);
	response.setMessage("Employee updated successfully!");
	return Response.ok(response).build();
    }

    @Override
    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") String id) {
	GenericResponse response = new GenericResponse();
	if(emps.get(id) == null){
	    return error.getNonExistentEmployeeErrorResponse();
	}
	Employee e = emps.remove(id);
	// Reopen the CEO position if CEO left
	if (Role.CEO.name().equalsIgnoreCase(e.getRole())) {
	    ceo = null;
	}
	response.setStatus(true);
	response.setMessage("Employee deleted successfully!");
	return Response.ok(response).build();
    }

    @Override
    @GET
    @Path("/{id}/get")
    public Response getEmployee(@PathParam("id") String id) {
	GenericResponse response = new GenericResponse();
	if(emps.get(id) == null){
	    return error.getNonExistentEmployeeErrorResponse();
	}
	response.setStatus(true);
	response.setMessage("Employee retrived successfully!");
	response.setEmployee(emps.get(id));
	return Response.ok(response).build();
    }

    @Override
    @GET
    public Employee[] getAllEmployees() {
	Set<String> ids = emps.keySet();
	Employee[] e = new Employee[ids.size()];
	int i=0;
	for(String id : ids){
	    e[i] = emps.get(id);
	    i++;
	}
	return e;
    }

    private void updateNonNullValues(Employee oldE, Employee newE) {
	String favoriteJoke = newE.getFavoriteJoke();
	if (null != favoriteJoke)	
	    oldE.setFavoriteJoke(favoriteJoke);
	String favoriteQuote = newE.getFavoriteQuote();
	if (null != favoriteQuote)	
	    oldE.setFavoriteQuote(favoriteQuote);
	String firstName = newE.getFirstName();
	if (null != firstName)	
	    oldE.setFirstName(firstName);
	String lastName = newE.getLastName();
	if (null != lastName)	
	    oldE.setLastName(lastName);
	String hireDate = newE.getHireDate();
	if (null != hireDate)	
	    oldE.setHireDate(hireDate);
	String role = newE.getRole();
	if (null != role)	
	    oldE.setRole(role);
    }

}
