package br.edu.ifsp.dsw1;

public interface ConvertionStrategy {

	double conveter(double temperature);
	
	String getResultScale();
	
	String getOriginScale();
	
}
