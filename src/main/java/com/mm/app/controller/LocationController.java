package com.mm.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mm.app.model.Location;
import com.mm.app.service.LocationService;

@Controller
public class LocationController {
	
	private LocationService locationService;
	
	@Autowired(required = true)
	@Qualifier(value = "locationService")
	public void setLocationService(LocationService locationService) {
		
		this.locationService = locationService;
	}
	
	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public String getLocationList(Model model) {
		
		model.addAttribute("location", new Location());
		model.addAttribute("getLocationList", this.locationService.getLocationList());
		return "location";
	}

}
