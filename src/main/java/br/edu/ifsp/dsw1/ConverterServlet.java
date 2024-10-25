package br.edu.ifsp.dsw1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/main.do")
public class ConverterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Conveter converter;
	
	public ConverterServlet() {
		converter = new Conveter();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var out = response.getWriter();
		
		out.println("<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Entrada de Temperatura</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <div class=\"container-sm\">\n"
				+ "        <h1>Conversor de Temperatura</h1>\n"
				+ "        <form action=\"main.do\" method=\"post\">\n"
				+ "            <div class=\"mb-3\">\n"
				+ "            <label for=\"temperatura\" class=\"form-label\">Temperatura</label>\n"
				+ "            <input id=\"temperatura\" name=\"text_temperature\" class=\"form-control\" type=\"text\" placeholder=\"Temperatura a ser convertida\" aria-label=\"Digite temperatura.\">          \n"
				+ "            </div>\n"
				+ "            \n"
				+ "            <div class=\"form-check\">\n"
				+ "                <input class=\"form-check-input\" type=\"radio\" name=\"radio_scale\" id=\"celsius\" value=\"celsius\" >\n"
				+ "                <label class=\"form-check-label\" for=\"celsius\">\n"
				+ "                Celsius\n"
				+ "                </label>\n"
				+ "            </div>\n"
				+ "            <div class=\"form-check\">\n"
				+ "                <input class=\"form-check-input\" type=\"radio\" name=\"radio_scale\" id=\"fahrenheit\" value=\"fahrenheit\" checked>\n"
				+ "                <label class=\"form-check-label\" for=\"fahrenheit\">\n"
				+ "                Fahrenheit\n"
				+ "                </label>\n"
				+ "            </div>\n"
				+ "\n"
				+ "            <button type=\"submit\" class=\"btn btn-primary\">Converter</button>\n"
				+ "        </form>\n"
				+ "      </div>\n"
				+ "</body>\n"
				+ "</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temperature = request.getParameter("text_temperature");
		String scale = request.getParameter("radio_scale");
		
		double temp;
		try {
			temp = Double.parseDouble(temperature);
		} catch (NumberFormatException ex) {
			temp = 0;
		}
		converter.setTemperature(temp);
		
		if (scale.equals("fahrenheit")) {
			converter.setStrategy(new ToFahrenheit());
		} else {
			converter.setStrategy(new ToCelius());
		}
		
		try (var out = response.getWriter()) {
			out.println("<!DOCTYPE html>\n"
					+ "<html lang=\"en\">\n"
					+ "<head>\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\n"
					+ "    <meta charset=\"UTF-8\">\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
					+ "    <title>Entrada de Temperatura</title>\n"
					+ "</head>\n"
					+ "<body>\n"
					+ "    <div class=\"container-sm\">\n"
					+ "        <h1>Conversor de Temperatura</h1>\n"
					+ "        <form action=\"main.do\" method=\"post\">\n"
					+ "            <div class=\"mb-3\">\n"
					+ "            <label for=\"temperatura\" class=\"form-label\">Temperatura</label>\n"
					+ "            <input id=\"temperatura\" name=\"text_temperature\" class=\"form-control\" type=\"text\" placeholder=\"Temperatura a ser convertida\" aria-label=\"Digite temperatura.\">          \n"
					+ "            </div>\n"
					+ "            \n"
					+ "            <div class=\"form-check\">\n"
					+ "                <input class=\"form-check-input\" type=\"radio\" name=\"radio_scale\" id=\"celsius\" value=\"celsius\">\n"
					+ "                <label class=\"form-check-label\" for=\"celsius\">\n"
					+ "                Celsius\n"
					+ "                </label>\n"
					+ "            </div>\n"
					+ "            <div class=\"form-check\">\n"
					+ "                <input class=\"form-check-input\" type=\"radio\" name=\"radio_scale\" id=\"fahrenheit\" value=\"fahrenheit\" checked>\n"
					+ "                <label class=\"form-check-label\" for=\"fahrenheit\">\n"
					+ "                Fahrenheit\n"
					+ "                </label>\n"
					+ "            </div>\n"
					+ "\n"
					+ "            <button type=\"submit\" class=\"btn btn-primary\">Converter</button>\n"
					+ "        </form>\n"
					+ "\n"
					+ "        <div class=\"card\">\n"
					+ "            <div class=\"card-header\">\n"
					+ "              Convertido\n"
					+ "            </div>\n"
					+ "            <div class=\"card-body\">\n"
					+ "              <h5 class=\"card-title\">Conversão realizada</h5>\n"
					+ "              <p class=\"card-text\">"+ String.format("%.2f %s", converter.getTemperature(), converter.getOriginScale()) + " é igual a " + String.format("%.2f %s", converter.conveter(), converter.getResultScale()) + "</p>\n"
					+ "              \n"
					+ "            </div>\n"
					+ "          </div>\n"
					+ "      </div>\n"
					+ "</body>\n"
					+ "</html>");
		}
	}

}