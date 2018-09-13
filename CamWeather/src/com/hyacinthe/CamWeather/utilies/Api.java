package com.hyacinthe.CamWeather.utilies;

public class Api {
	private static final String FORECAST_API_BASE_URL = "https://api.darksky.net/forecast/";
	private static final String FORECAST_API_KEY = "ae945e4413b15dbd23588e147517e1c9";
	
	public static String getForecastUrl(double latitude,double longitude) {
		return FORECAST_API_BASE_URL + FORECAST_API_KEY +"/"+ latitude + "," + longitude;
	}
}
