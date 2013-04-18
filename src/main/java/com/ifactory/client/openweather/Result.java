package com.ifactory.client.openweather;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {
	private long id;
	private long timestamp;	
	private double lat;
	private double lng;
	private String name;
	private double temp;
	private int pressure;
	private int humidity;
	private double tempMax;
	private double tempMin;
	private List<Weather> weathers;
	private int windSpeed;
	private int windDegree;
	
	
	static final class Builder {
		
		private List<Weather> weathers = new ArrayList<Weather>();		
		private long id;
		private long timestamp;		
		private double lat;
		private double lng;
		private String name;
		private double temp;
		private int pressure;
		private int humidity;
		private double tempMax;
		private double tempMin;
		private int windSpeed;
		private int windDegree;
/*
		public Builder(long id, long timestamp, double lat, double lng, 
				String name, double temp, int pressure, int humidity, 
				double tempMax, double tempMin, int windSpeed, int windDegree) {
			this.id = id;
			this.timestamp = timestamp;
			this.lat = lat;
			this.lng = lng;
			this.name = name;
			this.temp = temp;
			this.pressure = pressure;
			this.humidity = humidity;
			this.tempMax = tempMax;
			this.tempMin = tempMin;
			this.windSpeed = windSpeed;
			this.windDegree = windDegree;
		}
		*/
		
		public Builder id(long id) {
			this.id = id;
			return this;
		}
		
		public Builder timestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public void lng(double lng) {
			this.lng = lng;
		}
		
		public void name(String name) {
			this.name = name;
		}
		
		public void lat(double lat) {
			this.lat = lat;
		}
		
		public void temp(double temp) {
			this.temp = temp;
		}
		
		public void pressure(int pressure) {
			this.pressure = pressure;
		}
		
		public void humidity(int humidity) {
			this.humidity = humidity;
		}
		
		public void tempMax(double tempMax) {
			this.tempMax = tempMax;
		}
		
		public void tempMin(double tempMin) {
			this.tempMin = tempMin;
		}
		
		public void windSpeed(int windSpeed) {
			this.windSpeed = windSpeed;
		}

		public void windDegree(int windDegree) {
			this.windDegree = windDegree;
		}
		
		public Result build() {
			return new Result(this);
		}
	}
	
	private Result(Builder builder) {
		// humidity = builder.humidity;
	}

	public long getId() {
		return id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public double getLng() {
		return lng;
	}

	public String getName() {
		return name;
	}

	public double getLat() {
		return lat;
	}
	
	public double getTemp() {
		return temp;
	}

	public int getPressure() {
		return pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public double getTempMax() {
		return tempMax;
	}

	public double getTempMin() {
		return tempMin;
	}

	public int getWindSpeed() {
		return windSpeed;
	}

	public int getWindDegree() {
		return windDegree;
	}	

	public List<Weather> getWeathers() {
		return weathers;
	}	
}
