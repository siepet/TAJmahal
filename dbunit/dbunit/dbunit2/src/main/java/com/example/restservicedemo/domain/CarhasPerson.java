package com.example.restservicedemo.domain;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarhasPerson {
	private long idPerson;
	private long idCar;
	
	public long getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(long idPerson) {
		this.idPerson = idPerson;
	}
	public long getIdCar() {
		return idCar;
	}
	public void setIdCar(long idCar) {
		this.idCar = idCar;
	}
}
