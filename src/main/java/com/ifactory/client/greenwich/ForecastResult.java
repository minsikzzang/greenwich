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

public class ForecastResult implements Serializable {
	
	private long id;
	private double lat;
	private double lng;
	private String name;
	private List<DailyForecast> dailyForecasts;
	private List<HourlyForecast> hourlyForecasts;
	
	static final class Builder {
		
		private List<DailyForecast> dailyForecasts = new ArrayList<DailyForecast>();
		private List<HourlyForecast> hourlyForecasts = new ArrayList<HourlyForecast>();		
		private long id;
		private double lat;
		private double lng;
		private String name;
    
		public Builder(long id, String name) {
			this.id = id;
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
										
		public void addDaily(DailyForecast forecast) {
			dailyForecasts.add(forecast);
		}
		
		public void addHourly(HourlyForecast forecast) {
			hourlyForecasts.add(forecast);
		}
				
		public ForecastResult build() {
			return new ForecastResult(this);
		}
	}
	
	private ForecastResult(Builder builder) {
		id = builder.id;
		name = builder.name;
		lng = builder.lng;
		lat = builder.lat;
		hourlyForecasts = builder.hourlyForecasts;
		dailyForecasts = builder.dailyForecasts;
	}	
	
	public long getId() {
		return id;
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
	  
	public List<DailyForecast> getDaily() {
		return dailyForecasts;
	}	
	
	public List<HourlyForecast> getHourly() {
		return hourlyForecasts;
	}
}
