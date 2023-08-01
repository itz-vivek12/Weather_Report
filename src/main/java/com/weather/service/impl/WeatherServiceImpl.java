package com.weather.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.weather.models.WeatherData;
import com.weather.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Override
	public List<WeatherData> getWeatherDataByDate(List<WeatherData> weatherDatas, String date) {
		// TODO Auto-generated method stub
		return weatherDatas.stream().filter(i -> i.getDt_txt().substring(0,10).equals(date)).collect(Collectors.toList());
	}

}
