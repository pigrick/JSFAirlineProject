package cs545.airline.service;

import java.util.List;

import cs545.airline.dao.AirlineDao;
import cs545.airline.dao.AirplaneDao;
import cs545.airline.model.Airplane;
import cs545.airline.model.Flight;

public class AirplaneService {

	// These services should be evaluated to reconsider which methods should be public 
	private static AirplaneService airplaneService = new AirplaneService(new AirplaneDao());
	private AirplaneDao airplaneDao;
	
	public static AirplaneService getInstance(){
		return airplaneService;
	}
	
	public AirplaneService(AirplaneDao airplaneDao) {
		this.airplaneDao = airplaneDao;
	}

	public void create(Airplane airplane) {
		airplaneDao.create(airplane);
	}

	public void delete(Airplane airplane) {
		airplaneDao.delete(airplane);
	}

	public Airplane update(Airplane airplane) {
		return airplaneDao.update(airplane);
	}

	public Airplane find(Airplane airplane) {
		return airplaneDao.findOne(airplane.getId());
	}

	public Airplane findBySrlnr(String serialno) {
		return airplaneDao.findOneBySerialnr(serialno);
	}

	public List<Airplane> findByFlight(Flight flight) {
		return airplaneDao.findByFlight(flight.getId());
	}

	public List<Airplane> findByModel(String model) {
		return airplaneDao.findByModel(model);
	}

	public List<Airplane> findAll() {
		return airplaneDao.findAll();
	}

}
