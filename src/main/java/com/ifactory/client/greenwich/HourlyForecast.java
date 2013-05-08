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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class HourlyForecast implements Serializable {

	private List<Weather> weathers;
	private long timestamp;
	private Main main;
	
	static final class Builder {
  	private List<Weather> weathers = new ArrayList<Weather>();	
  	private long timestamp;
  	private Main main;
		
		public Builder(long timestamp) {
			this.timestamp = timestamp;			
		}

		public void addWeather(Weather weather) {
			weathers.add(weather);
		}
		
		public Builder main(Main main) {
		  this.main = main;
		  return this;
		}
    
		public HourlyForecast build() {
			return new HourlyForecast(this);
		}
	}
	
	private HourlyForecast(Builder builder) {
		timestamp = builder.timestamp;
		main = builder.main;
		weathers = builder.weathers;
	}	
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public List<Weather> getWeathers() {
		return weathers;
	}
	
	public Main getMain() {
		return main;
	}
}
