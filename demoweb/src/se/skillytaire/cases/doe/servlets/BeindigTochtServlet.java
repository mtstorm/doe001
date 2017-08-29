package se.skillytaire.cases.doe.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.skillytaire.cases.doe.service.TochtEinde;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class BeindigTochtServlet
 */
@WebServlet("/beindigTocht.html")
public class BeindigTochtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PARAM_NAME_EINDE_TOCHT = "eindetocht";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eindetochtNummerString = request.getParameter(PARAM_NAME_EINDE_TOCHT);
		try {
		int bootNr = Integer.parseInt(eindetochtNummerString);
		VerhuurderService service = VerhuurderServiceImpl.getInstance();
		TochtEinde result = service.beeindigTocht(bootNr);
		System.out.println(result.getDuur() +"  " + result.isInspectieNodig());
		}catch(NumberFormatException e) {
			request.setAttribute(Constants.KEY_ERROR_MSG, "Sorry er is geen juist boot nummer ingevuld.");
		}catch(java.lang.IllegalStateException e) {
			//java.lang.IllegalStateException: De tocht is beindigd en kan daarom niet opnieuw worden beindigd
			request.setAttribute(Constants.KEY_ERROR_MSG, e.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
