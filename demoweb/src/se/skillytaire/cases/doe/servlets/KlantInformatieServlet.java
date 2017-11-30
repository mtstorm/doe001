package se.skillytaire.cases.doe.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.skillytaire.cases.doe.service.KlantInformatie;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class KlantInformatieServlet
 */
@WebServlet(description = "Geeft informatie voor een ingegeven klantnummer. Tabel met een regel", urlPatterns = { "/klantinformatie.html" })
public class KlantInformatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_NAME_KLANT_NUMMER = "klantnr";
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String klantNummerString = request.getParameter(PARAM_NAME_KLANT_NUMMER);
		try {
		int klantnr = Integer.parseInt(klantNummerString);
		VerhuurderService service = VerhuurderServiceImpl.getInstance();
		KlantInformatie klantInfo = service.getKlantInformatie(klantnr);
		request.setAttribute("klantInfo", klantInfo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/klantinformatie.jsp");
		dispatcher.forward(request, response);
		} catch(NumberFormatException e) {
			request.setAttribute(Constants.KEY_ERROR_MSG, "Sorry er is geen juist klant nummer ingevuld.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} 
	}

}
