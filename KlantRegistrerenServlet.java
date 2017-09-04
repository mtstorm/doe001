package se.skillytaire.cases.doe.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.domain.GeenBootVrijException;
import se.skillytaire.cases.doe.service.KlantInformatie;
import se.skillytaire.cases.doe.service.StartTochtInformatie;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class KlantRegistrerenServlet
 */
@WebServlet("/klantregistreren.html")
public class KlantRegistrerenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PARAM_NAME_KLANT_NAAM = "naam";
	public static final String PARAM_NAME_KLANT_ADRES = "adres";
	public static final String PARAM_NAME_KLANT_WOONPLAATS = "woonplaats";
	public static final String PARAM_NAME_KLANT_TELEFOONNUMMER = "telefoonnummer";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		VerhuurderService verhuurderService = VerhuurderServiceImpl.getInstance();

		String naamStringParamValue = request.getParameter(PARAM_NAME_KLANT_NAAM);
		String adresStringParamValue = request.getParameter(PARAM_NAME_KLANT_ADRES);
		String woonplaatsStringParamValue = request.getParameter(PARAM_NAME_KLANT_WOONPLAATS);
		String telefoonnummerStringParamValue = request.getParameter(PARAM_NAME_KLANT_TELEFOONNUMMER);
		
		KlantInformatie info = new KlantInformatie(naamStringParamValue, adresStringParamValue, woonplaatsStringParamValue, telefoonnummerStringParamValue);
		int klantNummer = verhuurderService.registrerenKlant(info);
	
		request.setAttribute("klantNummer", klantNummer);
		dispatcher = request.getRequestDispatcher("/geregistreerd.jsp");
		dispatcher.forward(request, response);

}
}
