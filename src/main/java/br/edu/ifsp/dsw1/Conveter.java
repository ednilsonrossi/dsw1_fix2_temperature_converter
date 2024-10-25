package br.edu.ifsp.dsw1;

public class Conveter {
	private double temperature;
	private ConvertionStrategy strategy;
	
	public Conveter() {
		temperature = 0;
		strategy = new ToFahrenheit();
	}
	
	public Conveter(double temperature, ConvertionStrategy strategy) {
		this();
		this.temperature = temperature;
		if (strategy != null) {
			this.strategy = strategy;
		}
	}
	
	public double conveter() {
		return strategy.conveter(this.temperature);
	}

	public String getOriginScale() {
		return strategy.getOriginScale();
	}
	
	public String getResultScale() {
		return strategy.getResultScale();
	}
	
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public ConvertionStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(ConvertionStrategy strategy) {
		if (strategy != null) {
			this.strategy = strategy;
		}
	}
	
}
