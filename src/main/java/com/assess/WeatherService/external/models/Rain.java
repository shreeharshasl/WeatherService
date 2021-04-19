package com.assess.WeatherService.external.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Rain{
    @JsonProperty("3h") 
    public double _3h;
}