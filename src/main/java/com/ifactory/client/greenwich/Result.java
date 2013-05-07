/**
 * Copyright 2013 iFactory Ltd.
 * 
 * Min Kim (minsikzzang@gmail.com)
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.ifactory.client.greenwich;

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
	static double KELVIN = 273.15;
	
	static final class Builder {
		
		private List<Weather> weathers = new ArrayList<Weather>();		
		private long id;
		private long timestamp;		
		private double lat;
		private double lng;
		private String name;
		/**
		 * Temperature in Kelvin. Subtracted 273.15 from this figure to convert 
		 * to Celsius.
		 */
		private double temp;
		private int pressure;
		private int humidity;
		private double tempMax;
		private double tempMin;
		private int windSpeed;
		private int windDegree;

		public Builder(long id, long timestamp, String name) {
			this.id = id;
			this.timestamp = timestamp;
			this.name = name;			
		}		
		
		public Builder lng(double lng) {
			this.lng = lng;
			return this;
		}
			
		public Builder lat(double lat) {
			this.lat = lat;
			return this;
		}
		
		public Builder temp(double temp) {
			this.temp = temp;
			return this;
		}
		
		public Builder pressure(int pressure) {
			this.pressure = pressure;
			return this;
		}
		
		public Builder humidity(int humidity) {
			this.humidity = humidity;
			return this;
		}
		
		public Builder tempMax(double tempMax) {
			this.tempMax = tempMax;
			return this;
		}
		
		public Builder tempMin(double tempMin) {
			this.tempMin = tempMin;
			return this;
		}
		
		public Builder windSpeed(int windSpeed) {
			this.windSpeed = windSpeed;
			return this;
		}

		public Builder windDegree(int windDegree) {
			this.windDegree = windDegree;
			return this;
		}
		
		public void addWeather(Weather weather) {
			weathers.add(weather);
		}
		
		public Result build() {
			return new Result(this);
		}
	}
	
	private Result(Builder builder) {
		id = builder.id;
		timestamp = builder.timestamp;
		name = builder.name;
		lng = builder.lng;
		lat = builder.lat;
		temp = builder.temp;
		pressure = builder.pressure;
		humidity = builder.humidity;
		tempMax = builder.tempMax;
		tempMin = builder.tempMin;
		windSpeed = builder.windSpeed;
		windDegree = builder.windDegree;		
		weathers = builder.weathers;
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
		return temp - KELVIN;
	}

	public int getPressure() {
		return pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public double getTempMax() {
		return tempMax - KELVIN;
	}

	public double getTempMin() {
		return tempMin - KELVIN;
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
