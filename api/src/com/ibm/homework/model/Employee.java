package com.ibm.homework.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class Employee {
    private String firstName;
    private String lastName;
    private String hireDate;
    private String role;
    private String id;
    private String favoriteJoke;
    private String favoriteQuote;

    private static final long LIMIT = 10000000000L;
    private static long last = 0;

    public String getFirstName() {
	return firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getHireDate() {
	return hireDate;
    }

    public void setHireDate(String hireDate) {
	this.hireDate = hireDate;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getFavoriteJoke() {
	return favoriteJoke;
    }

    public void setFavoriteJoke(String favoriteJoke) {
	this.favoriteJoke = favoriteJoke;
    }

    public String getFavoriteQuote() {
	return favoriteQuote;
    }

    public void setFavoriteQuote(String favoriteQuote) {
	this.favoriteQuote = favoriteQuote;
    }

    public String generateId() {
	// 10 digits.
	long newId = System.currentTimeMillis() % LIMIT;
	if (newId <= last) {
	    newId = (last + 1) % LIMIT;
	}
	last = newId;
	return String.valueOf(newId);
    }

    @Override
    public String toString() {
	return id + "::" + firstName + "::" + lastName + "::" + role;
    }
}
