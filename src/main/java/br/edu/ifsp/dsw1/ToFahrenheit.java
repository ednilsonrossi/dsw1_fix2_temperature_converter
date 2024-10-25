package br.edu.ifsp.dsw1;

public class ToFahrenheit implements ConvertionStrategy {

	@Override
	public double conveter(double temperature) {
		return temperature * 1.8 + 32;
	}

	@Override
	public String getResultScale() {
		return "ºF";
	}

	@Override
	public String getOriginScale() {
		return "ºC";
	}
	
	
}
