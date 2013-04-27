package com.ifactory.client.openweather;

public class OpenWeatherUrl {
	
	private String url;
	private String version;
	private double lat;
	private double lng;
	private double cnt;
	private boolean searchWeather;
	
	public OpenWeatherUrl(String url) {
		this.url = url;
		this.searchWeather = false;
	}
	
	public OpenWeatherUrl lat(double lat) {
		this.lat = lat;
		return this;
	}
	
	public OpenWeatherUrl lng(double lng) {
		this.lng = lng;
		return this;
	}
	
	public OpenWeatherUrl cnt(double cnt) {
		this.cnt = cnt;
		return this;
	}
	
	public OpenWeatherUrl version(String version) {
		this.version = version;
		return this;
	}
	
	public void searchWeather() {
		this.searchWeather = true;
	}
	
	public String toString() {
		String command = "weather";
		if (this.searchWeather) {
			command = "weather";
		}
		return this.url + "/data/" + version + "/" + command +"?lat=" + 
			this.lat + "&lon=" + this.lng;
	}		
}
