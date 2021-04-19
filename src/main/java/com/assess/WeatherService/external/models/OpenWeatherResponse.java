package com.assess.WeatherService.external.models;

import java.util.List;

import lombok.Data;

@Data
public class OpenWeatherResponse {
	    public String cod;
	    public int message;
	    public int cnt;
	    public List<ListOfDays> list;
	    public City city;
}
