package com.weather.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		Date parsedDate = parseDate(date);
		if (parsedDate == null) {
			throw new IllegalArgumentException("Invalid date format. Expected 'yyyy-MM-dd' or 'dd-MM-yyyy'.");
		}
//		return weatherDatas.stream().filter(i -> i.getDt_txt().substring(0,10).equals(date)).collect(Collectors.toList());
		return weatherDatas.stream().filter(i -> {
			Date dtTxtDate = parseDate(i.getDt_txt());
			return dtTxtDate != null && dtTxtDate.equals(parsedDate);
		}).collect(Collectors.toList());
	}

	private Date parseDate(String dateStr) {
		Date date = null;
		String[] patterns = { "yyyy-MM-dd", "dd-MM-yyyy" };

		for (String pattern : patterns) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				sdf.setLenient(false);
				date = sdf.parse(dateStr);
				if (date != null) {
					break;
				}
			} catch (ParseException e) {
				
			}
		}

		return date;
	}
}
