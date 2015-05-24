package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.* ;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

public class PersonTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";
	}
	
	@Test
	public void getPerson(){
		get("/persons/1").then().assertThat().body("firstName", equalTo("MARCIN"));
		get("/persons/2").then().assertThat().body("firstName", equalTo("NIEMARCIN"));
		
		Person aPerson = get("/persons/1").as(Person.class);
		assertThat(aPerson.getFirstName(), equalToIgnoringCase("MARCIN"));
	}
	
	@Test
	public void addPerson(){
		InputStream schema = Thread.currentThread().getContextClassLoader().getResourceAsStream("PersonSchemaJSON.json");
		Person aPerson = new Person(4, "Kuba", 1410);
		given().
		       contentType("application/json; charset=UTF-16").
		       body(aPerson).
		when().	     
		post("/persons/").then().assertThat().statusCode(201).body(containsString("Person saved:"));
	}
	
	@Test
	public void getAllPerson(){
		
		get("/persons/all").then().assertThat().body("person.firstName", hasItems("MARCIN", "NIEMARCIN"));
	}
	
    @Test 
    public void json_schema_test() {
    	
    	InputStream schema = Thread.currentThread().getContextClassLoader().getResourceAsStream("PersonSchemaJSON.json");
    	when().
                get("/persons/3").
        then().
                body(matchesJsonSchema(schema)).
                body("firstName", equalTo("BOLEKLOLEK")). 
                body("id", equalTo("3")).
                body("yob", equalTo("1993"));   
    }
	

}
