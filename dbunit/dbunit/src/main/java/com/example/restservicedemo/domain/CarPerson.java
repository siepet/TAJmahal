package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarPerson {
    private long IdPerson;
    private long IdCar;

    public long getIdPerson(){
      return IdPerson;
    }

    public long getIdCar(){
      return IdCar;
    }

    public void setPersonId(Long id){
      this.IdPerson = id;
    }

    public void setCarId(Long id){
      this.IdCar = id;
    }
}
