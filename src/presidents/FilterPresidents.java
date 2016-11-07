package presidents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class FilterPresidents {

	public FilterPresidents(int selection, String search, ServletContext context) throws ServletException {
		ParsePresidents parser = new ParsePresidents(context);

		List<President> presidentsList = parser.getPresidents();
		List<President> filteredList = null;
		
		switch (selection) {
		case 0: 
			Collections.sort(presidentsList);
			context.setAttribute("presidents", presidentsList.toArray());
			//printPresidents(presidentsList);
			break;
		case 1:
			Collections.sort(presidentsList, (p1, p2) -> -(p1.getLastName().compareToIgnoreCase(p2.getLastName())));
			context.setAttribute("presidents", presidentsList.toArray());
			//printPresidents(presidentsList);	// For debugging purposes	
			break;
		case 2:
			filteredList = filterPresidents(presidentsList, (p) -> p.getEndDate()-p.getStartDate() <= 4);
			context.setAttribute("presidents", filteredList.toArray());
			//printPresidents(filteredList);
			break;
		case 3:
			filteredList = filterPresidents(presidentsList, (p) -> p.getEndDate()-p.getStartDate() > 4);
			context.setAttribute("presidents", filteredList.toArray());
			//printPresidents(filteredList);			
			break;
		case 4:
			filteredList = filterPresidents(presidentsList, (p) -> p.getFirstName().toUpperCase().startsWith(search));
			context.setAttribute("presidents", filteredList.toArray());
			//printPresidents(filteredList);									
			break;
		case 5:
			filteredList = filterPresidents(presidentsList, (p) -> p.getLastName().toUpperCase().startsWith(search));
			context.setAttribute("presidents", filteredList.toArray());
			//printPresidents(filteredList);												
			break;
		case 6:
			filteredList = filterPresidents(presidentsList, (p) -> p.getParty().equalsIgnoreCase(search));
			context.setAttribute("presidents", filteredList.toArray());
			//printPresidents(filteredList);
			break;
		case 7:
			filteredList = filterPresidents(presidentsList, (p) -> p.getFunFact().toUpperCase().contains(search));
			context.setAttribute("presidents", filteredList.toArray());
			//printPresidents(filteredList);									
			break;
		default:
			break;
		}
	}
	
	public List<President> filterPresidents(List<President> presidents, Predicate<President> predicate) {
		
		List<President> tempList = new ArrayList<>();
		
		for (President president : presidents)
			if (predicate.test(president))
				tempList.add(president);
		
		return tempList;
	}

	public void printPresidents(List<President> presidentsList) {
		for (President president : presidentsList) {
			System.out.println(president);
		}

	}

}