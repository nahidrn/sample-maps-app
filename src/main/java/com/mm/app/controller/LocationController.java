package com.mm.app.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import com.mm.app.constants.LocationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mm.app.model.Location;
import com.mm.app.service.LocationService;

@Controller
@RequestMapping("/l")
public class LocationController {

    @Autowired
	LocationService locationService;

    @Autowired
    MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	/*
     * This method will list all existing locations.
     */
    @RequestMapping(value = { "/listlocations" }, method = RequestMethod.GET)
    public String getLocationList(ModelMap model) {

        List<Location> locations = locationService.findAllLocations();
        model.addAttribute("locations", locations);
		model.addAttribute("loggedinuser", getPrincipal());
        return "locations";
    }

	@RequestMapping(value = { "/searchlocation" }, method = RequestMethod.GET)
	public String getLocationListByType(ModelMap model) {

		List<Location> eventLocations = locationService.findLocationByType(LocationType.EVENT.LocationType());
		List<Location> poiLocations = locationService.findLocationByType(LocationType.POI.LocationType());
		model.addAttribute("eventLocations", eventLocations);
		model.addAttribute("poiLocations", poiLocations);
		model.addAttribute("loggedinuser", getPrincipal());
		return "searchlocation";
	}

    @RequestMapping(value = { "/createlocation" }, method = RequestMethod.GET)
	public String newLocation(ModelMap model) {

		Location location = new Location();
		model.addAttribute("location", location);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createlocation";
	}

	@RequestMapping(value = { "/createlocation" }, method = RequestMethod.POST)
	public String saveLocation(@Valid Location location, BindingResult result,
						   ModelMap model) {

		System.out.println("result : "+result);
		if (result.hasErrors()) {
			return "createlocation";
		}
		if(!locationService.isLocationNameUnique(location.getId(), location.getName())){
			FieldError nameUniqueError =new FieldError("location","name",messageSource.getMessage("non.unique.locationname", new String[]{location.getName()}, Locale.getDefault()));
			result.addError(nameUniqueError);
			return "createlocation";
		}
		locationService.createLocation(location);

		model.addAttribute("success", "Location " + location.getName() + " was created successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-location-{id}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Long id, ModelMap model) {

		Location location = locationService.findLocationById(id);
		model.addAttribute("location", location);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createlocation";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating location in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-location-{id}" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Location location, BindingResult result,
								 ModelMap model, @PathVariable Long id) {

		if (result.hasErrors()) {
			return "createlocation";
		}

		if(!locationService.isLocationNameUnique(location.getId(), location.getName())){
			FieldError nameUniqueError =new FieldError("location","name",messageSource.getMessage("non.unique.locationname", new String[]{location.getName()}, Locale.getDefault()));
			result.addError(nameUniqueError);
			return "createlocation";
		}

		locationService.updateLocation(location);

		model.addAttribute("success", "Location " + location.getName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}


	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-location-{id}" }, method = RequestMethod.GET)
	public String deleteLocation(@PathVariable Long id) {

		locationService.deleteLocationById(id);
		return "redirect:/l/listlocations";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){

		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
