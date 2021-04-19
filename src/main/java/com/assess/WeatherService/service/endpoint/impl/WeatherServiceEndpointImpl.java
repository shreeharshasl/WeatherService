package com.assess.WeatherService.service.endpoint.impl;

import java.net.URI;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.assess.WeatherService.external.models.OpenWeatherResponse;
import com.assess.WeatherService.model.WeatherResponse;
import com.assess.WeatherService.service.endpoint.WeatherServiceEndpoint;

@Component
public class WeatherServiceEndpointImpl implements WeatherServiceEndpoint {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${external.weather.api.url}")
	private String apiUrl;
	
	@Value("${external.weather.api.key}")
	private String apiKey;
	
	@Override
	public OpenWeatherResponse getWeatherServiceFromOpenWeather(String city) {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl);
		URI uri = builder.build().toUri();
		uri = UriComponentsBuilder.fromUri(uri)
				.queryParam("q", city)
				.queryParam("appid", apiKey)
				.build().toUri();
		//String jsonData = restTemplate.getForObject(uri, String.class);
		
		OpenWeatherResponse openWeatherResponse = restTemplate.getForObject(uri, OpenWeatherResponse.class);
		
		/*
		 * DocumentContext jsonContext = JsonPath.parse(jsonData);
		 * 
		 * String jsonpathMaxTemp = "$['list'][:day]['main']['temp_max']"; String
		 * jsonpathMinTemp = "$['list'][:day]['main']['temp_min']"; String
		 * jsonpathRainStatus = "$['list'][:day]['weather'][0]['main']";
		 * 
		 * WeatherResponse weatherResponse = new WeatherResponse(); List<DayPrediction>
		 * listDays = new ArrayList<DayPrediction>(); int i=0; while(i<days) {
		 * jsonpathMaxTemp = jsonpathMaxTemp.replace(":day", String.valueOf(i));
		 * jsonpathMinTemp = jsonpathMinTemp.replace(":day", String.valueOf(i));
		 * jsonpathRainStatus = jsonpathRainStatus.replace(":day", String.valueOf(i));
		 * 
		 * DayPrediction dayPrediction = new DayPrediction();
		 * dayPrediction.setDayNumber(i+1);
		 * dayPrediction.setHighTemperature(String.valueOf(jsonContext.read(
		 * jsonpathMaxTemp, Double.class)));
		 * dayPrediction.setLowTemperature(String.valueOf(jsonContext.read(
		 * jsonpathMinTemp, Double.class)));
		 * dayPrediction.setRainStatus(jsonContext.read(jsonpathRainStatus,
		 * String.class)); listDays.add(dayPrediction); i++;
		 * 
		 * } weatherResponse.setListOfDays(listDays);
		 */
		
		return openWeatherResponse;
	}

}
