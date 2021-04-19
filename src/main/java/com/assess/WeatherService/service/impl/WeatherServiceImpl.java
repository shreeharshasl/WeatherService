package com.assess.WeatherService.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.assess.WeatherService.external.models.OpenWeatherResponse;
import com.assess.WeatherService.model.DayPrediction;
import com.assess.WeatherService.model.WeatherResponse;
import com.assess.WeatherService.service.WeatherService;
import com.assess.WeatherService.service.endpoint.WeatherServiceEndpoint;

@Service
public class WeatherServiceImpl implements WeatherService{

	@Autowired
	private WeatherServiceEndpoint weatherServiceEndpoint;
	
	@Value("${weatherservice.temperature.maxtollerable}")
	private Double maxTollerableTemp;
	
	@Value("${weatherservice.temperature.rainstatus}")
	private String rainStatus;
	
	@Override
	public WeatherResponse fetchWeatherDataForCity(String city, Integer days) {
		WeatherResponse weatherResponse = new WeatherResponse();
		
		OpenWeatherResponse openWeatherResponse = weatherServiceEndpoint.getWeatherServiceFromOpenWeather(city);
		
		List<DayPrediction> listOfdDayPredictions = new ArrayList<DayPrediction>();
		
		for(int i=0;i<days;i++) {
			DayPrediction dayPrediction = new DayPrediction();
			dayPrediction.setDayNumber(i+1);
			dayPrediction.setHighTemperature(openWeatherResponse.getList().get(i) != null?openWeatherResponse.getList().get(i).getMain().getTemp_max():0);
			dayPrediction.setLowTemperature(openWeatherResponse.getList().get(i)!=null?openWeatherResponse.getList().get(i).getMain().getTemp_min():0);
			dayPrediction.setRainStatus(openWeatherResponse.getList().get(i)!=null?openWeatherResponse.getList().get(i).getWeather().get(0).getMain():"");
			
			if(dayPrediction.getHighTemperature()>maxTollerableTemp)
				dayPrediction.setSuggestion("Use Sunscreen lotion");
			else if(dayPrediction.getRainStatus().equalsIgnoreCase(rainStatus))
				dayPrediction.setSuggestion("Carry Umbrella");
			
			listOfdDayPredictions.add(dayPrediction);
		}
		
		weatherResponse.setListOfDays(listOfdDayPredictions);
		return weatherResponse;
	}

	
}
