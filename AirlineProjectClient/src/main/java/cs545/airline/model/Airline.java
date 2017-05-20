package cs545.airline.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Named("airline")
@SessionScoped
public class Airline implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private List<Flight> flights;

	/* Constructors */
	public Airline() {
	}

	public Airline(String name) {
		this.name = name;
	}

	/* Getters & Setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Flight> getFlights() {
		return Collections.unmodifiableList(flights);
	}
	
	/* Collections Methods */
	public boolean addFlight(Flight flight) {
		boolean success =  (!flights.contains(flight)) && (flights.add(flight));
		if (success) {
			flight.setAirline(this);
		}
		return success;
	}

	public boolean removeFlight(Flight flight) {
		boolean success = false;
		if (flights.remove(flight)) {
			flight.setAirline(null);
			success = true;
		}
		return success;
	}
}
