package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.CarPersonManager;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;

public class PersonServiceRESTDBTest {

	private static IDatabaseConnection connection ;
	private static IDatabaseTester databaseTester;
	private static Person aPerson = new Person("Ziutek", 2010);


	@BeforeClass
	public static void setUp() throws Exception{
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo";

		Connection jdbcConnection;
		jdbcConnection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		connection = new DatabaseConnection(jdbcConnection);

		databaseTester = new JdbcDatabaseTester(
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(
				new FileInputStream(new File("src/test/resources/fullData.xml")));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Test
	public void addPeson() throws Exception{

		given().contentType("application/json; charset=UTF-16").body(aPerson)
				.when().post("/persons/").then().assertThat().statusCode(201);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("PERSON");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"ID"});

		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/personData.xml"));
		ITable expectedTable = expectedDataSet.getTable("PERSON");

		Assertion.assertEquals(expectedTable, filteredTable);
	}

	@Test
	public void GiveCarToPerson() throws Exception{

		Car aCar = new Car("Fiat","Punto",2004);

		given().contentType("application/json; charset=UTF-16").body(aCar)
		.when().post("/cars/").then().assertThat().statusCode(201);

                given().contentType("application/json; charset=UTF-16").body(aPerson)
                  .when().post("/persons").then().assertThat().statusCode(201);


		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("CarHasPerson");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"IDCAR"});

		System.out.println(actualTable);
		System.out.println(filteredTable);


		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/GivenCars.xml"));
		ITable expectedTable = expectedDataSet.getTable("CarHasPerson");

		Assertion.assertEquals(expectedTable, filteredTable);
	}

	@AfterClass
	public static void tearDown() throws Exception{
		databaseTester.onTearDown();
	}

}
