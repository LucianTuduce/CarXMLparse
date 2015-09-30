package com.fortech.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.fortech.jaxbmodel.PersonJAXB;
import com.fortech.model.Person;

@Path("/model")
public class CarRestController {

	@GET
	@Path("/info")
	public String getInfo() {
		return "I am here";
	}

	@POST
	@Path("/person")
	@Produces("application/xml")
	public List<PersonJAXB> getObjectList() {
		List<PersonJAXB> personJAXBs = new ArrayList<PersonJAXB>();
		PersonJAXB p1 = new PersonJAXB();
		p1.setFirstname("Lucian");
		p1.setLastname("Tuduce");
		p1.setPersonid(1);
		PersonJAXB p2 = new PersonJAXB();
		p2.setFirstname("Paul");
		p2.setLastname("Marina");
		p2.setPersonid(2);
		personJAXBs.add(p1);
		personJAXBs.add(p2);
		return personJAXBs;
	}

	@POST
	@Path("/list")
	@Consumes("application/xml")
	@Produces("application/json")
	public List<Person> getSimplePersonList(final List<PersonJAXB> personJAXBs){
		Person person = null;
		List<Person> persons = new ArrayList<Person>();
		for(PersonJAXB p: personJAXBs){
			System.out.println(p.getFirstname()+" "+p.getLastname());
			person = new Person();
			person.setFirstname(p.getFirstname());
			person.setLastname(p.getLastname());
			person.setPersonid(p.getPersonid());
			persons.add(person);
		}
		
		return persons;
	}
	
	@GET
	@Path("/json/list")
	@Produces("application/json")
	public List<Person> getJSONPersonList(){
		List<Person> persons = new ArrayList<Person>();
		Person p1 = new Person();
		p1.setFirstname("Lucian");
		p1.setLastname("Tuduce");
		p1.setPersonid(1);
		Person p2 = new Person();
		p2.setFirstname("Paul");
		p2.setLastname("Marina");
		p2.setPersonid(2);
		persons.add(p1);
		persons.add(p2);
		return persons;
	}
	
	@POST
	@Path("/jsonlist")
	@Produces("application/json")
	public List<Person> getListWithJSON(final List<Person> pList){
		return pList;
	}
}
