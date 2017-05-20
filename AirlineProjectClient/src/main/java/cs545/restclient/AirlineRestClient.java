package cs545.restclient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.model.FlightFilter;


@Named
@ApplicationScoped
public class AirlineRestClient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public AirlineRestClient(){
		
	}
	
	public List<Flight> getAllFlights(){
		
		Client client = ClientBuilder.newClient();
		List<Flight> flights = client.target("http://localhost:8080/airlinesWebApp/resources/services/allflights").request(MediaType.APPLICATION_XML).get(new GenericType<List<Flight>>(){});
		return flights;
	}
	
	public List<Flight> getAllFlights(FlightFilter details){
		Client client = ClientBuilder.newClient();
		List<Flight> flights = client.target("http://localhost:8080/airlinesWebApp/resources/services/allflights").request(MediaType.APPLICATION_XML).get(new GenericType<List<Flight>>(){});
		List<Flight> temp = new ArrayList<Flight>();
		
		for(Flight fs : flights){
			if(fs.getAirline().getName().toLowerCase().contains(details.getAirline().toLowerCase()) &&
					fs.getOrigin().getName().toLowerCase().contains(details.getOrigin().toLowerCase()) &&
					fs.getDestination().getName().toLowerCase().contains(details.getDestination().toLowerCase()) &&
					fs.getDepartureDate().contains(details.getDepartureDate()) &&
					fs.getDepartureTime().contains(details.getDepartureTime())){				
				temp.add(fs);				
			}
		}
		return temp;
	}
	
	public String createFlight(Flight flight){
		
		System.out.println("helo ---------------------------------------------------------");
		System.out.println(flight.getId());
		Airline airline = getAirline(flight.getAirline().getId());
		Airport origin = getAirport(flight.getOrigin().getId());
		Airport destination = getAirport(flight.getDestination().getId());
		Airplane airplane = getAirplane(flight.getAirplane().getId());
		Flight newFlight = new Flight(flight.getFlightnr(), flight.getDepartureDate(), 
				flight.getDepartureTime(), flight.getArrivalDate(), flight.getArrivalTime(),
				airline, origin, destination, airplane);
		System.out.println("helo ---------------------------------------------------------");
		System.out.println(airline.getName());
		System.out.println(origin.getName());
		System.out.println(destination.getName());
		System.out.println(airplane.getModel());
		flight.setAirline(airline);
		flight.setOrigin(origin);
		flight.setDestination(destination);
		flight.setAirplane(airplane);
		Client client = ClientBuilder.newClient();
		client.target("http://localhost:8080/airlinesWebApp/resources/services/createflight").request(MediaType.APPLICATION_XML).post(Entity.xml(newFlight));
		return "success";
	}
	
	public List<Airline> getAllAirlines(){
		Client client = ClientBuilder.newClient();
		List<Airline> airlines = client.target("http://localhost:8080/airlinesWebApp/resources/services/allairlines").request(MediaType.APPLICATION_XML).get(new GenericType<List<Airline>>(){});
		return airlines;
	}
	
	public List<Airplane> getAllAirplanes(){
		Client client = ClientBuilder.newClient();
		List<Airplane> airplanes = client.target("http://localhost:8080/airlinesWebApp/resources/services/allairplanes").request(MediaType.APPLICATION_XML).get(new GenericType<List<Airplane>>(){});
		return airplanes;
	}
	
	
	public List<Airport> getAllAirports(){
		Client client = ClientBuilder.newClient();
		List<Airport> airports = client.target("http://localhost:8080/airlinesWebApp/resources/services/allairports").request(MediaType.APPLICATION_XML).get(new GenericType<List<Airport>>(){});
		return airports;
	}
	
	public Airport getAirport(long id){
		Client client = ClientBuilder.newClient();
		Airport airport = client.target("http://localhost:8080/airlinesWebApp/resources/services/getairport/" + id).request(MediaType.APPLICATION_XML).get(Airport.class);
		return airport;
	}
	public Airline getAirline(long id){
		Client client = ClientBuilder.newClient();
		Airline airline = client.target("http://localhost:8080/airlinesWebApp/resources/services/getairline/" + id).request(MediaType.APPLICATION_XML).get(Airline.class);
		return airline;
	}
	public Airplane getAirplane(long id){
		Client client = ClientBuilder.newClient();
		Airplane airplane = client.target("http://localhost:8080/airlinesWebApp/resources/services/getairplane/" + id).request(MediaType.APPLICATION_XML).get(Airplane.class);
		return airplane;
	}
	
	public String createAirline(String name){
		Airline airline = new Airline(name);
		Client client = ClientBuilder.newClient();
		client.target("http://localhost:8080/airlinesWebApp/resources/services/createairline").request(MediaType.APPLICATION_XML).post(Entity.xml(airline));
		return "airlinelist";
	}
	
	public void deleteAirline(long id){
		Client client = ClientBuilder.newClient();
		client.target("http://localhost:8080/airlinesWebApp/resources/services/deleteairline/" + id).request(MediaType.APPLICATION_XML).delete();
	}
	
	public String updateAirline(long id, String name){
		System.out.println(id + "==================================================");
		Airline airline = new Airline(name);
		airline.setId(id);
		Client client = ClientBuilder.newClient();
		client.target("http://localhost:8080/airlinesWebApp/resources/services/updateairline").request(MediaType.APPLICATION_XML).put(Entity.xml(airline));
		return "airlinelist";
	}
	
	public String call(){
		Client client = ClientBuilder.newClient();		
		String name = client.target("http://localhost:8080/airlinesWebApp/resources/services/name").request().get(String.class);
	    System.out.println(name);
		return "order";
	}
}
