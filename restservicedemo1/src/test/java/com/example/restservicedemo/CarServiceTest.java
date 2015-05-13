package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.* ;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

import java.util.ArrayList;
import java.util.List;

import com.example.restservicedemo.service.PersonManager;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

public class CarServiceTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";
	}
	
	
	@Test
	public void getCar(){
		get("/cars/0").then().assertThat().body("model", equalTo("Corsa"));
		
		
		Car aCar = get("/cars/0").as(Car.class);
		assertThat(aCar.getMake(), equalToIgnoringCase("Opel"));
	}
	
	@Test
	public void addCar(){
		
		PersonManager pm = new PersonManager();
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(pm.getPerson(1));
		persons.add(pm.getPerson(2));
		
		Car aCar = new Car(2, "Ford", "Fiesta", 2011 , persons);
		given().
		       contentType("application/json; charset=UTF-16").
		       body(aCar).
		when().	     
		post("/cars/").then().assertThat().statusCode(201).body(containsString("Car saved:"));
	}
	
	@Test
	public void getPersonFromCar(){
		get("/cars/1").then().assertThat().body("person.firstName", hasItems("MARCIN", "NIEMARCIN"));
		
	}
	

}
