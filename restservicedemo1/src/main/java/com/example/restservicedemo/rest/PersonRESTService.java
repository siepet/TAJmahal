package com.example.restservicedemo.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

@Path("persons")
public class PersonRESTService {	
	
	private PersonManager pm = new PersonManager();
	
	@GET
	@Path("/{personId}")
	@Produces("application/json")
	public Person getPerson(@PathParam("personId") int id){
		Person p = pm.getPerson(id);
		return p;
	}
	
	@GET
	@Path("/test")
	@Produces("text/html")
	public String test(){
		return "REST Service is running";
	}
	
	@GET
	@Path("/test1")
	@Produces("text/html")
	public String test1(){
		return "REST Service is running";
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Person> getAll(@PathParam("personId") Long id){
		List<Person> p = pm.getAllPersons();
		return p;
	}
	
	@GET
	@Path("/name/{personId}")
	@Produces("application/json")
	public String getPersonName(@PathParam("personId") int id){
		String p = pm.getPersonName(id);
		return p;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Person person) {
 
		String result = "Person saved: " + person;
		return Response.status(201).entity(result).build(); 
	}

}
