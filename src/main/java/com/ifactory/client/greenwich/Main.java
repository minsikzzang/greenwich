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

import static com.ifactory.client.greenwich.Temp.KELVIN;

public class Main implements Serializable {
  private double temp;
	private int humidity;
	private double pressure;
	private double tempMax;
	private double tempMin;
  private double seaLevel;
	private double groundLevel;
	
	static final class Builder {
    private double temp;
  	private int humidity;
  	private double pressure;
  	private double tempMax;
  	private double tempMin;
    private double seaLevel;
  	private double groundLevel;
		
		public Builder(double temp, double tempMin, double tempMax) {
			this.temp = temp;
			this.tempMin = tempMin;
			this.tempMax = tempMax;
		}

		public Builder pressure(double pressure) {
			this.pressure = pressure;
			return this;
		}
		
		public Builder humidity(int humidity) {
			this.humidity = humidity;
			return this;
		}
		
		public Builder groundLevel(double groundLevel) {
			this.groundLevel = groundLevel;
			return this;
		}
		
		public Builder seaLevel(double seaLevel) {
			this.seaLevel = seaLevel;
			return this;
		}
				
		public Main build() {
			return new Main(this);
		}
	}
	
	private Main(Builder builder) {
		temp = builder.temp;
		tempMin = builder.tempMin;
		tempMax = builder.tempMax;
		humidity = builder.humidity;			
		pressure = builder.pressure;			
		groundLevel = builder.groundLevel;			
		seaLevel = builder.seaLevel;
	}	
	
	public double getTemp() {
		return temp - KELVIN;
	}
	
	public double getTempMax() {
		return tempMax - KELVIN;
	}

	public double getTempMin() {
		return tempMin - KELVIN;
	}
	
	public double getPressure() {
	  return pressure;
	}
	
	public double getHumidity() {
	  return humidity;
	}
	
	public double getGroundLevel() {
		return groundLevel;
	}

	public double getSeaLevel() {
		return seaLevel;
	}
}