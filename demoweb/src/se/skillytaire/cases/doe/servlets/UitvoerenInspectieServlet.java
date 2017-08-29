package se.skillytaire.cases.doe.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.skillytaire.cases.doe.domain.Voertuig;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class UitvoerenInspectieServlet
 */
@WebServlet(description = "De servlet voor het uitvoeren van de bootinspectie", urlPatterns = { "/UitvoerenInspectie.html" })
public class UitvoerenInspectieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PARAM_NAME_INSPECTIE = "inspectie";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inspectieNummerString = request.getParameter(PARAM_NAME_INSPECTIE);
		try {			
			int bootNr = Integer.parseInt(inspectieNummerString);
			VerhuurderService service = VerhuurderServiceImpl.getInstance();
			service.uitvoerenInspectie(bootNr);
			System.out.println("Boot met nummer " + bootNr + " is geïnspecteerd");
			}catch(NumberFormatException e) {
				request.setAttribute(Constants.KEY_ERROR_MSG, "Sorry er is geen juist boot nummer ingevuld.");
			}catch(java.lang.IllegalStateException e) {
				//java.lang.IllegalStateException: De boot heeft geen inspectie nodig
				request.setAttribute(Constants.KEY_ERROR_MSG, e.getMessage());
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			
			
	}

}
