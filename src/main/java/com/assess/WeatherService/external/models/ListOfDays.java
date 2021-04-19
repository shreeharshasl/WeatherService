package com.assess.WeatherService.external.models;

import java.util.List;

import lombok.Data;

@Data
public class ListOfDays{
    public int dt;
    public Main main;
    public List<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public int visibility;
    public double pop;
    public Rain rain;
    public Sys sys;
    public String dt_txt;
}