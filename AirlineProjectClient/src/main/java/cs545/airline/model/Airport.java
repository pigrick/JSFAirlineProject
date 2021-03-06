package cs545.airline.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Airport {

	private long id;
	private String airportcode;
	private String name;
	private String city;
	private String country;

	private List<Flight> arrivals;

	private List<Flight> departures;

	/* Constructors */
	public Airport() {
	}

	public Airport(String airportcode, String name, String city, String country) {
		this.airportcode = airportcode;
		this.name = name;
		this.city = city;
		this.country = country;
	}

	/* Getters & Setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAirportcode() {
		return airportcode;
	}

	public void setAirportcode(String airportcode) {
		this.airportcode = airportcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Flight> getArrivals() {
		return Collections.unmodifiableList(arrivals);
	}

	public List<Flight> getDepartures() {
		return Collections.unmodifiableList(departures);
	}

	/* Collection methods */
	public boolean addArrival(Flight flight) {
		boolean success =  (!arrivals.contains(flight)) && (arrivals.add(flight));
		if (success) {
			flight.setDestination(this);
		}
		return success;
	}

	public boolean removeArrival(Flight flight) {
		boolean success = false;
		if (arrivals.remove(flight)) {
			flight.setDestination(null);
			success = true;
		}
		return success;
	}

	public boolean addDeparture(Flight flight) {
		boolean success =  (!departures.contains(flight)) && (departures.add(flight));
		if (success) {
			flight.setOrigin(this);
		}
		return success;
	}

	public boolean removeDeparture(Flight flight) {
		boolean success = false;
		if (departures.remove(flight)) {
			flight.setOrigin(null);
			success = true;
		}
		return success;
	}
}
