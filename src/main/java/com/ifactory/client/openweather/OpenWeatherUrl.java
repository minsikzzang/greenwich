package com.ifactory.client.openweather;

public class OpenWeatherUrl {
	
	private String url;
	private String version;
	private double lat;
	private double lng;
	private double cnt;
	
	public OpenWeatherUrl(String url) {
		this.url = url;
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
	
	public String toString() {
		return this.url + "/data/" + version + "/find/city?lat=" + this.lat +
				"&lon=" + this.lng + "&cnt=" + this.cnt;
	}
}
