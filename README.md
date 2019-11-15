**My Notes**

After discussing with Bart Vashaw, we decided to use Postman plugin from Chrome to provide an interface for the service APIs. The APIs is purely implemented in Java.

Below are some sample inputs:
* POST: http://localhost:8080/api/employees  

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<employee>
	<firstName>Subhag</firstName>
	<lastName>Singh</lastName>
	<hireDate>2019-11-11</hireDate>
	<role>ceO</role>
</employee>
```

* PUT: http://localhost:8080/api/employees/3613791231

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<employee>
	<lastName>Ragi</lastName>
	<role>vP</role>
</employee>
```

*Testing Done through Postman*
1. Created employee A with role ceo.  
1. Created employee B with role ceo and saw that a message was given that having more than one CEOs not allowed.
1. Created employee B with role vP and the did a get for the same to see the role was stored as VP.
1. Tried to employee A's role to an invalid role - xyz and noticed not allowed.
1. Deleted employee A and was able to add a new employee C as role CEO.
1. Called get all API and saw the employee list had A and C in there.
1. Tried to set a hire date in future and saw that it is not allowed.  
1. Updated last name of the employee A.
1. Tried to update a record that does not exist and saw that it is not allowed.
1. Tried to delete a record that does not exist and saw that it is not allowed.
1. Tried to get an employee that does not exist (with invalid ID).



**TASK**

- Create a node app that implements a set of REST APIs allowing CRUD functionality for an employee resource.
- Add a front end component of your choosing. The front end component should:
  - Show a list of the existing employees
  - Include a way to create a new employee using the POST API

**Expected Time**

This exercise is expected to take about 4-5 hours total, over the course of 2-3 days.

**Submission Instructions**

Post your solution to a public repository on [Github](https://github.com/). Send the repository URL back to the same person who sent you these instructions.

**Additional Guidance**

Persistent storage is not necessary, just use an in memory object to track employee records.

Use any npm modules you find useful.

**Expected Endpoints**

POST http://localhost:3000/api/employees

- Create a new record using a randomly generated value as the unique identifier (i.e. _id field).  Validate that the following fields are included in the POST body and have the right type/format as posted below:
    - firstName (String)
    - lastName (String)
    - hireDate (YYYY-MM-DD format must be in the past)
    - role (String) - must be one of the following (case-insensitive):
        - CEO (can only be one of these)
        - VP
        - MANAGER
        - LACKEY

    - In addition to the fields included in the POST body, include two fields in each new record that are populated by different external APIs.  For example, a favorite joke and a favorite quote, or a favorite joke and a second favorite joke.  As long as the two external APIs are different.
        - Possible API endpoints:

            https://ron-swanson-quotes.herokuapp.com/v2/quotes

            https://icanhazdadjoke.com

            https://quotes.rest/qod

PUT http://localhost:3000/api/employees/:id

- Replace the record corresponding to :id with the contents of the PUT body


GET http://localhost:3000/api/employees/:id

- Return the record corresponding to the id parameter


GET http://localhost:3000/api/employees

- Return all current records


DELETE http://localhost:3000/api/employees/:id

- delete the record corresponding to the id parameter


