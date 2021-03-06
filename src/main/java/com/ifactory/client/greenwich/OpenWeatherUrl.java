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

public class OpenWeatherUrl {
	
	private String url;
	private String version;
	private String apiKey;
	private double lat;
	private double lng;
	private int cnt;
	private boolean weather;
	private boolean forecast;
	private boolean hourly;
	
	static final class Builder {
    private String url;
  	private String version;
  	private String apiKey;
  	private double lat;
  	private double lng;
  	private int cnt;
  	private boolean weather;
  	private boolean forecast;
  	private boolean hourly;
		
		public Builder(String url, String apiKey) {
			this.url = url;
  		this.weather = false;
  		this.forecast = false;
  		this.cnt = 0;
  		this.hourly = false;
  		this.apiKey = apiKey;
		}

		public Builder lat(double lat) {
  		this.lat = lat;
  		return this;
  	}

  	public Builder lng(double lng) {
  		this.lng = lng;
  		return this;
  	}

  	public Builder cnt(int cnt) {
  		this.cnt = cnt;
  		return this;
  	}

  	public Builder version(String version) {
  		this.version = version;
  		return this;
  	}

  	public Builder weather() {
  		this.weather = true;
  		return this;
  	}

  	public Builder forecast() {
  		this.forecast = true;
  		return this;
  	}

  	public Builder hourly(boolean hourly) {
  	  this.hourly = hourly;
  	  return this;
  	}
				
		public OpenWeatherUrl build() {
			return new OpenWeatherUrl(this);
		}
	}
	
	private OpenWeatherUrl(Builder builder) {
		url = builder.url;
  	version = builder.version;
  	lat = builder.lat;
  	lng = builder.lng;
  	cnt = builder.cnt;
  	weather = builder.weather;
  	forecast = builder.forecast;
  	hourly = builder.hourly;
  	apiKey = builder.apiKey;
	}		

  public boolean getHourly() {
    return this.hourly;
  }
  
	public String toString() {
		StringBuilder builder = new StringBuilder(this.url).append("/data/")
		  .append(version).append("/");
		if (this.weather) {
			builder.append("weather");
		} else if (this.forecast) {
		  builder.append("forecast")
		    .append(this.hourly != true ? "/daily" : "");
		}		
		return builder.append("?lat=").append(this.lat).append("&lon=")
		  .append(this.lng).append("&mode=json")
		    .append((this.cnt > 0 ? ("&cnt=" + Integer.toString(this.cnt)) : ""))
		      .append("&APPID=").append(this.apiKey).toString();
	}		
}
