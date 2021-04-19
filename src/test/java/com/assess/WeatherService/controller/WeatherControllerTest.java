package com.assess.WeatherService.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assess.WeatherService.model.DayPrediction;
import com.assess.WeatherService.model.WeatherResponse;
import com.assess.WeatherService.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@WebMvcTest(value = { WeatherController.class })
class WeatherControllerTest {

	@MockBean
	private WeatherService weatherService;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	private WeatherResponse weatherResponse;

	@BeforeEach
	void setUp() throws Exception {

		weatherResponse = new WeatherResponse();
		List<DayPrediction> listOfDays = new ArrayList<DayPrediction>();
		DayPrediction day = new DayPrediction() {
			{
				setDayNumber(1);
				setHighTemperature(280.1);
				setLowTemperature(210.4);
				setRainStatus("rain");
				setSuggestion("carry Umbrella");
			}
		};
		listOfDays.add(day);
		weatherResponse.setListOfDays(listOfDays);
	}

	@Test
	void testGetWeatherForCity() {
		Mockito.when(weatherService.fetchWeatherDataForCity("London", 3)).thenReturn(weatherResponse);
		try {
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/weather/London/3"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			String responseString = mvcResult.getResponse().getContentAsString();
			WeatherResponse actualResponse = objectMapper.readValue(responseString, WeatherResponse.class);
			
			assertEquals(weatherResponse.getListOfDays().get(0).getHighTemperature(), actualResponse.getListOfDays().get(0).getHighTemperature());
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	void testGetWeatherForCityBadRequest() {
		Mockito.when(weatherService.fetchWeatherDataForCity("London", 3)).thenReturn(weatherResponse);
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/weathe"))
					.andExpect(MockMvcResultMatchers.status().isBadRequest());
		} catch (Exception e) {
			assertFalse(true);
		}
	}

}
