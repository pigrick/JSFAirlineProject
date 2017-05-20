package cs545.airline.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("flightFilter")
@ViewScoped
public class FlightFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String search;
	private String origin;
	private String destination;
	private String airline;
	private String departureDate;
	private String departureTime;
	
	public FlightFilter(){
		search = "normal";
		origin = "";
		destination = "";
		airline = "";
		departureDate = "";
		departureTime = "";
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	
	
	
	
}
