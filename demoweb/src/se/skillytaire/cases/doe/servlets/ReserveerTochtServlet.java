package se.skillytaire.cases.doe.servlets;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import javafx.util.converter.LocalDateTimeStringConverter;
import se.skillytaire.cases.doe.common.TochtType;
import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.domain.GeenBootVrijException;
import se.skillytaire.cases.doe.service.StartTochtInformatie;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class ReserveerTochtServlet
 */
@WebServlet(description = "Hiermee reserveer je een tocht", urlPatterns = { "/reserveertocht.html" })
public class ReserveerTochtServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	public static final String PARAM_NAME_BOOT_TYPE = "bootType";
	public static final String PARAM_NAME_DUUR = "duur";
	public static final String PARAM_NAME_TOCHT_TYPE = "tochtType";
	public static final String PARAM_NAME_STARTTIJD = "starttijd";
	public static final String PARAM_NAME_DATUM = "datum";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;
		VerhuurderService verhuurderService = VerhuurderServiceImpl.getInstance();

		String bootTypeParamValue = request.getParameter(PARAM_NAME_BOOT_TYPE);
		String duurStringInUren = request.getParameter(PARAM_NAME_DUUR);
		String tochtTypeParamValue = request.getParameter(PARAM_NAME_TOCHT_TYPE);
		String starttijdStringInUren = request.getParameter(PARAM_NAME_STARTTIJD);
		String datumString = request.getParameter(PARAM_NAME_DATUM);

		int uren = Integer.parseInt(duurStringInUren);
		Duration verwachteDuur = Duration.ofHours(uren);
		String datumTijdString = new StringBuilder().append(datumString).append(" " + starttijdStringInUren).toString();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		try {
			LocalDateTime datumTijd = LocalDateTime.parse(datumTijdString, formatter);
			boolean gereserveerd = verhuurderService.reserveren(tochtTypeParamValue, bootTypeParamValue, datumTijd,
					verwachteDuur);
			System.out.println("Reserveer tocht succesvol = " + gereserveerd);
			dispatcher = request.getRequestDispatcher("/reserveertocht.jsp");
		} catch (GeenBootVrijException e) {
			String msg = e.getMessage();
			// FIXME State van het formulier behouden
			request.setAttribute("errorMsg", msg);
			dispatcher = request.getRequestDispatcher("/reserveertocht.jsp");
		} catch (DateTimeParseException e) {
			request.setAttribute(Constants.KEY_ERROR_MSG, "Sorry er is geen juiste datum ingevuld.");
			dispatcher = request.getRequestDispatcher("/reserveertocht.jsp");
		}
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/reserveertocht.jsp");
		dispatcher.forward(request, response);

	}
}