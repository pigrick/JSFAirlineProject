package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;
@ApplicationPath("/rs")
@Path("/hello")
public class HelloRest {
	
	// Not the best way of doing it, but it works for this project
	private AirlineService airlineService = new AirlineService(new AirlineDao());
	
	@GET
	public String helloWorld(@DefaultValue("Gorgeous") @QueryParam("name") String name) {
		return "Hello "+name+"!";
	}
	
	
	@Path("/airline")
	@GET
	public String getAirlineTest() {
		String result = "Nil!";
		List<Airline> airlines = airlineService.findAll();
		
		for(Airline airline : airlines) {
			result = "This is an airline: "+airline.getName();
		}
		return result;
	}
	
}
