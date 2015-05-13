package com.example.restservicedemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.service.PersonManager;
import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;;

@Path("cars")
public class CarFakeRESTService {	
	
	private PersonManager pm = new PersonManager();
	
	@GET
	@Path("/{carId}")
	@Produces("application/json")
	public Car getCar(@PathParam("carId") Long id){
		List<Person> persons2 = new ArrayList<Person>();
		persons2.add(pm.getPerson(1));
		persons2.add(pm.getPerson(2));
		
		return new Car(1L, "Opel", "Corsa", 2005 , persons2);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Car car) {
 
		String result = "Car saved: " + car;
		return Response.status(201).entity(result).build(); 
	}

}
