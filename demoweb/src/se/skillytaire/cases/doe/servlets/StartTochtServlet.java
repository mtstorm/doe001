package se.skillytaire.cases.doe.servlets;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.skillytaire.cases.doe.common.TochtType;
import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.domain.GeenBootVrijException;
import se.skillytaire.cases.doe.service.StartTochtInformatie;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class StartMeertochServlet
 */
@WebServlet(description = "bla", urlPatterns = { "/starttocht.html" })
public class StartTochtServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public static final String PARAM_NAME_BOOT_TYPE = "bootType";
	public static final String PARAM_NAME_DUUR = "duur";
	public static final String PARAM_NAME_TOCHT_TYPE = "tochtType";
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;
		System.out.println("Start een meertocht in de doPost.");
		VerhuurderService verhuurderService = VerhuurderServiceImpl.getInstance();

		// ??wat is het boottype
		String bootTypeParamValue = request.getParameter(PARAM_NAME_BOOT_TYPE);
		String bootNummerString = request.getParameter("bootNummer");

		String duurStringInUren = request.getParameter(PARAM_NAME_DUUR);
		String tochtTypeParamValue = request.getParameter(PARAM_NAME_TOCHT_TYPE);

		System.out.println("Starten met de boot soort " + bootTypeParamValue);

		TochtType tochtType = TochtType.valueOf(tochtTypeParamValue);
		int uren = Integer.parseInt(duurStringInUren);
		Duration verwachteDuur = Duration.ofHours(uren);

		
		StartTochtInformatie info;
		if (bootNummerString != null) {
			int bootNummer = Integer.parseInt(bootNummerString);
			info = new StartTochtInformatie(bootNummer, tochtType, verwachteDuur);
		} else {
			VoertuigType bootType = VoertuigType.valueOf(bootTypeParamValue);
			info = new StartTochtInformatie(bootType, tochtType, verwachteDuur);
		}
		try {
			
		int bootNummer = verhuurderService.startTocht(info);
		System.out.println("Start meertocht met nummer " + bootNummer);
		request.setAttribute("bootNummer", bootNummer);
		
		
		dispatcher = request.getRequestDispatcher("/tochtbriefje.jsp");
		
		} catch (GeenBootVrijException e) {
			String msg = e.getMessage();
			//FIXME State van het formulier behouden
			request.setAttribute("errorMsg", msg);
		dispatcher = request.getRequestDispatcher("/starttocht.jsp");
			
		} 
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// verwachten een bootnummer -> afgeleid het boot type  bootNummer
		String bootNummerString = request.getParameter("bootNummer");
		if (bootNummerString != null) {
			int bootNummer = Integer.parseInt(bootNummerString);
			request.setAttribute("bootNummer", bootNummerString);
		}
		// starttocht.jsp laten met preselectie, namelijk alleen nog het tochttype
		RequestDispatcher dispatcher = request.getRequestDispatcher("/starttocht.jsp");
		dispatcher.forward(request, response);
	}

}
