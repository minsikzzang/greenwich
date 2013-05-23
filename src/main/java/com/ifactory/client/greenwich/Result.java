/**
 * Copyright 2013 iFactory Lab Ltd.
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
	private Main main;
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
		private int windSpeed;
		private int windDegree;
    private Main main;
    
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
		
		public Builder main(Main main) {
		  this.main = main;
		  return this;
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
		windSpeed = builder.windSpeed;
		windDegree = builder.windDegree;		
		weathers = builder.weathers;
		main = builder.main;
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
	
	public int getWindSpeed() {
		return windSpeed;
	}

	public int getWindDegree() {
		return windDegree;
	}	

  public Main getMain() {
    return main;
  }
  
	public List<Weather> getWeathers() {
		return weathers;
	}	
}
