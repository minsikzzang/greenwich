package com.ifactory.client.openweather;

public class OpenWeatherUrl {
	
	private String url;
	private String version;
	private double lat;
	private double lng;
	private int cnt;
	private boolean weather;
	private boolean forecast;
	
	public OpenWeatherUrl(String url) {
		this.url = url;
		this.weather = false;
		this.forecast = false;
		this.cnt = 0;
	}
	
	public OpenWeatherUrl lat(double lat) {
		this.lat = lat;
		return this;
	}
	
	public OpenWeatherUrl lng(double lng) {
		this.lng = lng;
		return this;
	}
	
	public OpenWeatherUrl cnt(int cnt) {
		this.cnt = cnt;
		return this;
	}
	
	public OpenWeatherUrl version(String version) {
		this.version = version;
		return this;
	}
	
	public OpenWeatherUrl weather() {
		this.weather = true;
		return this;
	}
	
	public OpenWeatherUrl forecast() {
		this.forecast = true;
		return this;
	}

	public String toString() {
		String command = "weather";
		if (this.weather) {
			command = "weather";
		} else if (this.forecast) {
		  command = "forecast/daily";
		}		
		return this.url + "/data/" + version + "/" + command +"?lat=" + 
  			this.lat + "&lon=" + this.lng + "&mode=json" + 
  			(this.cnt > 0 ? ("&cnt=" + Integer.toString(this.cnt)) : "");
	}		
}
