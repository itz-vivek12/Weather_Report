package com.weather.models;


import java.util.List;


import lombok.Data;

@Data
public class WeatherData {
	private long dt;
	private Main main;
	private List<Weather> weather;
	private Wind wind;
    private String dt_txt;
}
