package com.example.restservicedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.CarhasPerson;
import com.example.restservicedemo.domain.Person;

public class CarToPersonManager {

	private Connection connection;

	private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	private static final String CREATE_TABLE_CAR_TO_PERSON = "CREATE TABLE CarHasPerson(idcar bigint, idperson bigint)";

	private PreparedStatement addCarToPersonStmt;
	private PreparedStatement deleteAllStmt;
	private PreparedStatement getAllCarsToPersonStmt;
	private PreparedStatement getCarPersonsStmt;
	private PreparedStatement getPersonCarsByIdStmt;
	private PreparedStatement getCarByIdStmt;
	private PreparedStatement getPersonByIdStmt;
	
	
	private Statement statement;

	public CarToPersonManager() {
		try {
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("CarHasPerson".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(CREATE_TABLE_CAR_TO_PERSON);

			addCarToPersonStmt = connection
					.prepareStatement("INSERT INTO CarHasPerson (idcar,idperson) VALUES (?, ?)");
			deleteAllStmt = connection
					.prepareStatement("DELETE FROM CarHasPerson");
			getAllCarsToPersonStmt = connection
					.prepareStatement("SELECT idcar,idperson FROM CarHasPerson");
			getCarPersonsStmt = connection
					.prepareStatement("SELECT idcar, idperson FROM CarHasPerson where idcar = ?");
			getPersonCarsByIdStmt = connection
					.prepareStatement("SELECT idcar, idperson FROM CarHasPerson where idperson = ?");
			getPersonByIdStmt = connection
					.prepareStatement("SELECT id, name, yob FROM Person where id = ?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	public void clearCarstoPersons() {
		try {
			deleteAllStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addCarToPerson(Car Car,Person person) {
		int count = 0;
		try {
			addCarToPersonStmt.setLong(1, Car.getId());
			addCarToPersonStmt.setLong(2, person.getId());
			
			count = addCarToPersonStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<CarhasPerson> getAllCars() {
		List<CarhasPerson> Stmt = new ArrayList<CarhasPerson>();

		try {
			ResultSet rs = getAllCarsToPersonStmt.executeQuery();

			while (rs.next()) {
				CarhasPerson p = new CarhasPerson();
				p.setIdCar(rs.getInt("idcar"));
				p.setIdPerson(rs.getInt("idperson"));
				Stmt.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Stmt;
	}

	public List<Car> getAllPersonCars(Long id) {
		List<Car> Stmt = new ArrayList<Car>();
		CarManager cm = new CarManager();
		try {
			getPersonCarsByIdStmt.setLong(1, id);
			ResultSet rs = getPersonCarsByIdStmt.executeQuery();
			while (rs.next()) {
				Stmt.add(cm.getCar(rs.getLong("idcar")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Stmt;
	}
	
	public List<Person> getAllCarPersons(Long id) {
		List<Person> Stmt = new ArrayList<Person>();
		try {
			getCarPersonsStmt.setLong(1, id);
			ResultSet rs = getCarPersonsStmt.executeQuery();

			while (rs.next()) {
				getPersonByIdStmt.setLong(1, rs.getInt("idperson"));
				ResultSet rsc = getCarByIdStmt.executeQuery();
				while (rsc.next()) {
					Person p = new Person();
					p.setId(rsc.getLong("id"));
					p.setFirstName(rsc.getString("name"));
					p.setYob(rsc.getInt("yob"));
					Stmt.add(p);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Stmt;
	}

}
