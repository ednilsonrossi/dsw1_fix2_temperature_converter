package br.edu.ifsp.dsw1;

public class ToCelius implements ConvertionStrategy{

	@Override
	public double conveter(double temperature) {
		return ( (temperature - 32) * 5 ) / 9;
	}

	@Override
	public String getResultScale() {
		return "ºC";
	}

	@Override
	public String getOriginScale() {
		return "ºF";
	}
}
