package com.assess.WeatherService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DayPrediction {

	private Integer DayNumber;
	private String summary;
	private Double highTemperature;
	private Double lowTemperature;
	private String rainStatus;
	private String suggestion;
}
