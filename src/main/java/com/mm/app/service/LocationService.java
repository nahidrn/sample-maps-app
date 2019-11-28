package com.mm.app.service;

import java.util.List;
import com.mm.app.model.Location;

/**
 * @author nahid
 *
 */
public interface LocationService {

	public void createLocation(Location loc);

	public void updateLocation(Location loc);

	public void deleteLocationById(Long id);

	public List<Location> findAllLocations();

	public List<Location> findLocationByType(String type);

	public Location findLocationById(Long id);

	public Location findLocationByName(String name);

	public boolean isLocationNameUnique(Long id, String name);
}
