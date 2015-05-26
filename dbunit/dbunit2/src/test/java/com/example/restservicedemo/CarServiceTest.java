package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.CarManager;
import com.example.restservicedemo.service.CarToPersonManager;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;

public class CarServiceTest {
	
	CarManager cm = new CarManager();
	PersonManager pm = new PersonManager();
	CarToPersonManager ctpm = new CarToPersonManager();
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";
	}
	
	@Test
	public void getCar(){
		
		cm.addCar(new Car(1,"Opel","Corsa",1990));
		
		get("/cars/0").then().assertThat().body("model", equalTo("Corsa"));
		
		
		Car aCar = get("/cars/0").as(Car.class);
		
		assertThat(aCar.getMake(), equalToIgnoringCase("Opel"));
		
	}
	
	@Test
	public void getPersonCars(){
		Car car1 = new Car(1,"Opel","Corsa",1990);
		Car car2 = new Car(2,"Opel","Astra",1991);
		Person Antos = new Person(5,"Antos",1989);
		pm.addPerson(Antos);
		cm.addCar(car1);
		cm.addCar(car2);
		ctpm.addCarToPerson(car1,Antos);
		ctpm.addCarToPerson(car2,Antos);
		Antos.setCars(ctpm.getAllPersonCars(Antos.getId()));
		
		assertThat(get("/persons/"+Antos.getId()).getBody().path("cars[0]"), 
				equalTo(get("/cars/"+car1.getId()).getBody().path(".")));
		assertThat(get("/persons/"+Antos.getId()).getBody().path("cars[1]"), 
				equalTo(get("/cars/"+car2.getId()).getBody().path(".")));
		//Car aCar = get("/cars/0").as(Car.class);
		//assertThat(aCar.getMake(), equalToIgnoringCase("Opel"));
		
	}	
	@AfterClass
	public static void clearUp(){
		CarManager cm = new CarManager();
		CarToPersonManager ctpm = new CarToPersonManager();
		cm.clearCars();
		ctpm.clearCarstoPersons();
	}	

}
