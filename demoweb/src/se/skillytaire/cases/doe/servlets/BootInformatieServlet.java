package se.skillytaire.cases.doe.servlets;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.skillytaire.cases.doe.domain.BootBestaatNietException;
import se.skillytaire.cases.doe.service.BootInformatie;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class Bootinformatie
 */
@WebServlet(description = "Een regel met informatie voor specifieke boot. Nummer meegeven", urlPatterns = {
		"/bootinformatie.html" })
public class BootInformatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_NAME_BOOT_NUMMER = "bootnr";

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bootNummerString = request.getParameter(PARAM_NAME_BOOT_NUMMER);
		try {
			int bootnr = Integer.parseInt(bootNummerString);
			VerhuurderService service = VerhuurderServiceImpl.getInstance();
			BootInformatie bootInfo = service.getBootInformatie(bootnr);
			request.setAttribute("bootInfo", bootInfo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bootinformatie.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute(Constants.KEY_ERROR_MSG, "Sorry er is geen juist boot nummer ingevuld.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (BootBestaatNietException e) {
			request.setAttribute(Constants.KEY_ERROR_MSG, e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
