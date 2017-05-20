package cs545.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import cs545.airline.model.Airport;
import cs545.restclient.AirlineRestClient;

@FacesConverter(forClass=Airport.class, value="airportConverter")
public class AirportConverter implements Converter, Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private AirlineRestClient airlineRestClient = new AirlineRestClient();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		System.out.println("lol -------------------------------------------- " + value);
		long id = Long.parseLong(value);
		Airport airport = airlineRestClient.getAirport(id);
		return airport;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		Airport airport = (Airport) value;
		System.out.println("get string-------------------------------------------------------" + airport);
		return Long.toString(airport.getId());
	}

}
