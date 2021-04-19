package com.assess.WeatherService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.assess.WeatherService.model.WeatherResponse;
import com.assess.WeatherService.service.WeatherService;

@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;

	@GetMapping(path = "/weather/{city}/{days}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WeatherResponse> getWeatherForCity(@PathVariable("city") String city, @PathVariable("days") Integer days){
		
		WeatherResponse response = weatherService.fetchWeatherDataForCity(city,days);
		
		return new ResponseEntity<WeatherResponse>(response, HttpStatus.OK);
	}
}
