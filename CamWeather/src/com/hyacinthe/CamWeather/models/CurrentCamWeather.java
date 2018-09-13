package com.hyacinthe.CamWeather.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CurrentCamWeather {
	private String timezone;
	private long time;
	private double temperature;
	private double humidity;
	private double precipProbability;
	private String summary;

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public long getTime() {
		return time;
	}
	
	public String getFormattedTime() {
		
		// converting unix time to millisecond
		Date date = new Date(time * 1000L);
		//choix du format de la date
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone(timezone));
		String timeString=formatter.format(date);
		
		return timeString;

	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getTemperature() {
		
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPrecipProbability() {
		return precipProbability;
	}

	public void setPrecipProbability(double precipProbability) {
		this.precipProbability = precipProbability;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
