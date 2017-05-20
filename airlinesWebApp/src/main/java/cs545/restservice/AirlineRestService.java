package cs545.restservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cs545.airline.dao.AirlineDao;
import cs545.airline.dao.AirplaneDao;
import cs545.airline.dao.AirportDao;
import cs545.airline.dao.FlightDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@ApplicationPath("/resources")
@Path("/services")
public class AirlineRestService extends Application{

	
	@Path("/allflights")
	@Produces(MediaType.APPLICATION_XML)
	@GET	
	public List<Flight> getAllFlights(){
		return FlightService.getInstance().findAll();
	}
	
	@Path("/allairlines")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public List<Airline> getAllAirlines(){
		return AirlineService.getInstance().findAll();		
	}
	
	@Path("/allairplanes")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public List<Airplane> getAllAirplanes(){
		return AirplaneService.getInstance().findAll();		
	}
	
	@Path("/allairports")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public List<Airport> getAllAirports(){
		return AirportService.getInstance().findAll();		
	}
	
	@Path("/getairport/{id}")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Airport getAirport(@PathParam("id") long id){
		Airport airport = new Airport();
		airport.setId(id);
		return AirportService.getInstance().find(airport);		
	}
	
	@Path("/getairline/{id}")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Airline getAirLine(@PathParam("id") long id){
		Airline airline = new Airline();
		airline.setId(id);
		return AirlineService.getInstance().find(airline);		
	}
	
	@Path("/getairplane/{id}")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public Airplane getAirplane(@PathParam("id") long id){
		Airplane airplane = new Airplane();
		airplane.setId(id);
		return AirplaneService.getInstance().find(airplane);		
	}
	
	
	
	@Path("/createflight")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@POST
	public void createFlight(Flight flight){
		FlightService.getInstance().create(flight);
	}
	
	@Path("/createairline")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@POST
	public void createAirline(Airline airline){
		AirlineService.getInstance().create(airline);
	}
	
	@Path("/deleteairline/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public void deleteAirline(@PathParam("id") long id){
		Airline temp = new Airline();
		temp.setId(id);		
		AirlineService.getInstance().delete(temp);
	}
	
	@Path("/updateairline")
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public void updateAirline(Airline airline){	
		AirlineService.getInstance().update(airline);
	}
	
	@GET
	@Path("/name")
	public String getName(){
		ArrayList<Airport> airport = (ArrayList<Airport>) AirportService.getInstance().findAll();
		return airport.get(1).getName();
	}
}
