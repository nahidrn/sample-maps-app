package com.mm.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mm.app.dao.LocationDAO;
import com.mm.app.service.LocationService;
import com.mm.app.model.Location;
import com.mm.app.model.User;

@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService {


    @Autowired
    private LocationDAO dao;
    
	@Override
	public void createLocation(Location loc) {
		
		dao.createLocation(loc);
	}

	@Override
	public void updateLocation(Location loc) {
		
		Location entity = dao.findLocationById(loc.getId());
        if(entity!=null){
            entity.setName(loc.getName());
            entity.setAddress(loc.getAddress());
            entity.setType(loc.getType());
        }
	}

	@Override
	public void deleteLocationById(Long id) {
		
		dao.deleteLocationById(id);
	}

	@Override
	public List<Location> findAllLocations() {
		
		return dao.findAllLocations();
	}

	@Override
	public List<Location> findLocationByType(String type) {
		
		return dao.findLocationsByType(type);
	}

	@Override
	public Location findLocationById(Long id) {
		
		return dao.findLocationById(id);
	}


	@Override
	public Location findLocationByName(String name) {

		return dao.findLocationByName(name);
	}

	@Override
	public boolean isLocationNameUnique(Long id, String name) {

		Location location = findLocationByName(name);
		return ( location == null || ((id != null) && (location.getId() == id)));
	}
}
