package com.weather.models;




import lombok.Data;

@Data
public class WeatherData {
	private long dt;
	private Main main;
	private Wind wind;
    private String dt_txt;
}
