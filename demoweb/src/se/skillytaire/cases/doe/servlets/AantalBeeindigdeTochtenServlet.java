package se.skillytaire.cases.doe.servlets;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class AantalBeeindigdeTochtenServlet
 */
@WebServlet("/getaantalbeeindigdetochten.html")
public class AantalBeeindigdeTochtenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AantalBeeindigdeTochtenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		try {
			VerhuurderService service = VerhuurderServiceImpl.getInstance();
			int aantalBeeindigdeTochten=service.geefAantalBeeindigdeTochten();
			request.setAttribute("aantalbeeindigdetochten", new Integer(aantalBeeindigdeTochten).toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}catch(NumberFormatException e) {
			request.setAttribute(Constants.KEY_ERROR_MSG, "Sorry er is geen juist boot nummer ingevuld.");
		}catch(java.lang.IllegalStateException e) {
			request.setAttribute(Constants.KEY_ERROR_MSG, e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
