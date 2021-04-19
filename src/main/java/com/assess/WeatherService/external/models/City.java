package com.assess.WeatherService.external.models;

import lombok.Data;

@Data
public class City{
    public int id;
    public String name;
    public Coord coord;
    public String country;
    public int timezone;
    public int sunrise;
    public int sunset;
}