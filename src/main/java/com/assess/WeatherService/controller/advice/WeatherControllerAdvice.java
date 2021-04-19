package com.assess.WeatherService.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assess.WeatherService.model.WeatherResponse;

@RestControllerAdvice
public class WeatherControllerAdvice {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<WeatherResponse> handleRuntimeException(RuntimeException e){
		WeatherResponse response = new WeatherResponse() {{
			setErrorString(e.getMessage() + e.getStackTrace().toString());
		}};
		return new ResponseEntity<WeatherResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
