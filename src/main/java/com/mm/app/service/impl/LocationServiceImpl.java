package com.mm.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.app.dao.LocationDAO;
import com.mm.app.service.LocationService;
import com.mm.app.model.Location;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDAO locationDAO;

	public void setLocationDAO(LocationDAO locationDAO) {
		
		this.locationDAO = locationDAO;
	}

	public void addLocation(Location loc) {
		// TODO Auto-generated method stub
		
	}

	public void updateLocation(Location loc) {
		// TODO Auto-generated method stub
		
	}

	public void removeLocation(Location loc) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public List<Location> getLocationList() {
		
		return this.locationDAO.getLocationList();
	}

	@Transactional
	public List<Location> getLocationListByType(String type) {
		
		return this.locationDAO.getLocationListByType(type);
	}

	@Transactional
	public Location getLocationById(Long id) {
		
		return this.locationDAO.getLocationById(id);
	}
	
	
}
