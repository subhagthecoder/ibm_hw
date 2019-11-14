package com.ibm.homework.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class GenericResponse {

    private boolean status;
    private String message;
    private Employee employee;
    private String errorCode;

    public boolean isStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Employee getEmployee() {
	return employee;
    }

    public void setEmployee(Employee employee) {
	this.employee = employee;
    }

    public String getErrorCode() {
	return errorCode;
    }

    public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
    }

    @Override
    public String toString() {
	return status + "|" + message + "|" + errorCode;
    }
}
