package se.skillytaire.cases.doe.servlets;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.skillytaire.cases.doe.service.BootInformatie;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

/**
 * Servlet implementation class OverzichtVrijeBoten
 */
@WebServlet("/getvrijeboten.html")
public class OverzichtVrijeBoten extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_SORT = "sortColumn";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Vrije botenoverzicht.");
		VerhuurderService service = VerhuurderServiceImpl.getInstance();
		LocalDateTime tijdstip = LocalDateTime.now();
		Duration duur = Duration.ofHours(2);
		List<BootInformatie> result = service.geefVrijeBoten(tijdstip, duur);

		HttpSession session = req.getSession();
		String sortColumn = req.getParameter(PARAM_SORT);
		if(sortColumn != null && !sortColumn.isEmpty()) {
			Comparator<BootInformatie> comparator =  getComparator(session, sortColumn);
			Collections.sort(result,comparator);
		}
		
//		
//		for (BootInformatie bootInformatie : result) {
//			System.out.println(bootInformatie);
//		}
		// hoe van servlet naar jsp
		req.setAttribute("boten", result);
		String selectedbootnummer = req.getParameter("selectedbootnummer");
		if(selectedbootnummer != null) {
			req.setAttribute("selectedbootnummer", selectedbootnummer);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/botenoverzicht.jsp");
		dispatcher.forward(req, resp);

	}
	private Comparator<BootInformatie> getComparatorForSortColumn(String sortColumn){
		Comparator<BootInformatie> comparator = null;
		switch (sortColumn) {
			case "nr": {
				comparator = BootInformatie.comparatorByNumber();
			}break;
			case "naam": {
				comparator = BootInformatie.comparatorByName();
			}break;	
			case "type": {
				comparator = BootInformatie.comparatorByType();
			}break;	
			case "ap": {
				comparator = BootInformatie.comparatorByAantalPersonen();
			}break;	
			case "inspectie": {
				comparator = BootInformatie.comparatorByInspectie();
			}break;				
		}
		return comparator;
	}
	
	private Comparator<BootInformatie> getComparator(HttpSession session, String sortColumn){
		Comparator<BootInformatie> sorter;
		Comparator<BootInformatie> voorlaatste = null;
		Object laatstGesorteerdeKolomComparator = session.getAttribute("laatsteCompare");
		if(laatstGesorteerdeKolomComparator == null) {
			//--> zoek je juiste comparator op voor de kolom
			sorter = getComparatorForSortColumn(sortColumn);
		} else {
			// is de voolaatste sortering hetzelfde als deze
			Object laatstGesorteerdeKolom = session.getAttribute(PARAM_SORT);
			
			boolean isReversed = sortColumn.equals(laatstGesorteerdeKolom);
			if(isReversed) {
				//Ja dan reversed
				Comparator<BootInformatie> voorlaatsteSorter = (Comparator<BootInformatie>) laatstGesorteerdeKolomComparator;
				sorter = voorlaatsteSorter.reversed();
			} else {
				//Nee dan zoek de juiste comparatopr voor de kolom
			
				sorter = getComparatorForSortColumn(sortColumn);
			}
		}
		
		session.setAttribute("laatsteCompare", sorter);
		session.setAttribute(PARAM_SORT, sortColumn);
		return sorter;
	}

}
