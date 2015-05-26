package com.example.restservicedemo.rest;

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
import com.example.restservicedemo.service.CarManager;
import com.example.restservicedemo.service.PersonManager;

@Path("cars")
public class CarFakeRESTService {	
	
	private CarManager cm = new CarManager();
	
	@GET
	@Path("/{carId}")
	@Produces("application/json")
	public Car getCar(@PathParam("carId") Long id){
		Car c = cm.getCar(id);
		return c;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Car car) {
		String result = "Car saved: " + car;
		return Response.status(201).entity(result).build(); 
	}

}
