package com.weather.wethercontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.weather.models.Weather;
import com.weather.models.WeatherData;
import com.weather.models.WeatherDataWrapper;
import com.weather.service.WeatherService;

@RestController
public class WeatherController {
	private final String url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping
	@Scheduled(fixedRate = 1000)
	public ResponseEntity<?> getWeatherData() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<WeatherDataWrapper> weatherDataWrapper = restTemplate.getForEntity(url,
				WeatherDataWrapper.class);
		List<WeatherData> weatherDatas = weatherDataWrapper.getBody().getList();
		List<WeatherData> selectedWeatherDatas;
		
		Scanner scanner = new Scanner(System.in);
		int option;
		String date;
		do {
			System.out.println("1. Get weather");
			System.out.println("2. Get Wind Speed");
			System.out.println("3. Get Pressure");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				System.out.println("Enter date: ");
				date = scanner.nextLine();
				selectedWeatherDatas = new ArrayList<>();
				selectedWeatherDatas = weatherService.getWeatherDataByDate(weatherDatas, date);
				System.out.println();
				System.out.println("Weather Information");
				System.out.println("-------------------");
				for (WeatherData weatherData : selectedWeatherDatas) {
					for (Weather weather : weatherData.getWeather()) {
						System.out.println("  ID:" + weather.getId());
						System.out.println("  MAIN:" + weather.getMain());
						System.out.println("  DESCRIPTION:" + weather.getDescription());
						System.out.println("  ICON:" + weather.getIcon());
						System.out.println();
					}
				}

				System.out.println();
				break;
			case 2:
				System.out.println("Enter date: ");
				date = scanner.nextLine();
				selectedWeatherDatas = new ArrayList<>();
				selectedWeatherDatas = weatherService.getWeatherDataByDate(weatherDatas, date);
				
				System.out.println();
				for (WeatherData weatherData : selectedWeatherDatas) {
					System.out.println("Wind Speed: " + weatherData.getWind().getSpeed());
				}
				System.out.println();
				break;
			case 3:
				System.out.println("Enter date: ");
				date = scanner.nextLine();
				selectedWeatherDatas = new ArrayList<>();
				selectedWeatherDatas = weatherService.getWeatherDataByDate(weatherDatas, date);
				System.out.println();
				for (WeatherData weatherData : selectedWeatherDatas) {
					System.out.println("Pressure: " + weatherData.getMain().getPressure());
				}
				System.out.println();
				break;
			case 0:
				System.out.println();
				System.out.println("Exiting the program. Goodbye!");
				System.out.println();
				break;
			default:
				System.out.println();
				System.out.println("Invalid Option !");
				System.out.println();
			}
		} while (option != 0);
		scanner.close();

		return null;
	}

}
