package com.assess.WeatherService.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.assess.WeatherService.external.models.ListOfDays;
import com.assess.WeatherService.external.models.Main;
import com.assess.WeatherService.external.models.OpenWeatherResponse;
import com.assess.WeatherService.external.models.Weather;
import com.assess.WeatherService.model.WeatherResponse;
import com.assess.WeatherService.service.endpoint.WeatherServiceEndpoint;

class WeatherServiceImplTest {

	@InjectMocks WeatherServiceImpl weatherServiceImpl;
	@Mock WeatherServiceEndpoint weatherServiceEndpoint;
	
	private OpenWeatherResponse openWeatherResponse;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		openWeatherResponse = new OpenWeatherResponse();
		ListOfDays listOfDays = new ListOfDays();
		Main main = new Main();
		main.setTemp_max(322.2);
		main.setTemp_min(233.3);
		main.setHumidity(22);
		
		Weather weather = new Weather();
		weather.setMain("Clear");
		weather.setId(1);
		
		listOfDays.setMain(main);
		listOfDays.setWeather(new ArrayList<Weather>() {{
			add(weather);
		}});
		
		openWeatherResponse.setList(new ArrayList<ListOfDays>() {{
			add(listOfDays);
		}});
	}

	@Test
	void testFetchWeatherDataForCity() {
		
		Mockito.when(weatherServiceEndpoint.getWeatherServiceFromOpenWeather("London")).thenReturn(openWeatherResponse);
		WeatherResponse weatherResponse = weatherServiceImpl.fetchWeatherDataForCity("London", 1);
		
		assertEquals(openWeatherResponse.getList().get(0).getMain().getTemp_max(),weatherResponse.getListOfDays().get(0).getHighTemperature());
	}

}
