package com.weather.service;

import java.util.List;

import com.weather.models.WeatherData;

public interface WeatherService {
	List<WeatherData> getWeatherDataByDate(List<WeatherData> weatherDatas,String date);
}
