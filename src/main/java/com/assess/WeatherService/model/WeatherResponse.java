package com.assess.WeatherService.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class WeatherResponse {

	List<DayPrediction> listOfDays;
	String ErrorString;
}
