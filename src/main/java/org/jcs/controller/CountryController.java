package org.jcs.controller;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import org.jcs.bean.Country;
import org.jcs.service.CountryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

	CountryService countryService = new CountryService();

	@RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, headers = "Accept=application/json")
	public ArrayList<Country> getCountries() {
		ArrayList<Country> listOfCountries = countryService.getAllCountries();
		return listOfCountries;
	}

	@RequestMapping(value = "/getCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Country getCountryById(@PathVariable int id) {
		return countryService.getCountry(id);
	}

	@RequestMapping(value = "/addCountry", method = RequestMethod.POST, headers = "Accept=application/json")
	public Country addCountry(@RequestBody Country country) {
		return countryService.addCountry(country);
	}

	@RequestMapping(value = "/updateCountry", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Country updateCountry(@RequestBody Country country) {
		return countryService.updateCountry(country);
	}

	@RequestMapping(value = "/deleteCountry/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id) {
		countryService.deleteCountry(id);
	}	
	
	@RequestMapping(value = "/getPopTotal", method = RequestMethod.GET, headers = "Accept=application/json")
	public Long popTotal() {
		return countryService.getPopTotal();
	} 
	
	@RequestMapping(value = "/getPopAvg", method = RequestMethod.GET, headers = "Accept=application/json")
	public Float popAvg() {
		return countryService.getPopAvg();
	} 
	
	@RequestMapping(value = "/getMethods", method = RequestMethod.GET, headers = "Accept=application/json")
	public ArrayList<String> Options() {
		return countryService.getMethods();
	} 
}
