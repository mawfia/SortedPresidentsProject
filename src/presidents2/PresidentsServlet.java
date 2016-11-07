package presidents2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PresidentsServlet extends HttpServlet {
	President[] presi = new President[44];
	public int currentTerm = 0;
	private final static String presFileName = "WEB-INF/presidents.csv";

	public PresidentsServlet() {
	}

	@Override
	public void init() throws ServletException {
//		ServletContext context = this.getServletContext();
//		ParsePresidents parser = new ParsePresidents();
//		parser.parse(context);
//		List<President> presidentsList = parser.getPresidents();
//		context.setAttribute("presidents", presidentsList.toArray());
		
		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(this.getServletContext().getResourceAsStream(presFileName)));

			String line;

			for (int k = 0; k < 44; k++) {
				if ((line = buf.readLine()) != null) {
					presi[k] = new President();
					String[] words = new String[(line.split(",").length)];
					words = line.split(",");

					presi[k].setTermNumber(Integer.parseInt(words[0]));
					presi[k].setFirstName(words[1].trim());
					presi[k].setMiddleName(words[2].trim());
					presi[k].setLastName(words[3].trim());
					presi[k].setStartDate(Integer.parseInt(words[4].trim().substring(0, 4)));
					presi[k].setEndDate(Integer.parseInt(words[4].trim().substring(5, 9)));
					presi[k].setParty(words[5].trim());
					presi[k].setFunFact((words[6].replaceAll("%", ",")).trim());
					presi[k].setPhoto(words[7].trim());
					ServletContext context = this.getServletContext();
					context.setAttribute("presidents", presi);
					context.setAttribute("currentTerm", this.currentTerm);
					
				}
			}
			buf.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ArrayIndexOutOfBoundsException {
		ServletContext context = this.getServletContext();
		//HttpSession session = request.getSession();

		String operation = request.getParameter("operation");
		int currentTerm = (Integer)context.getAttribute("currentTerm");
		String selectTerm = request.getParameter("browsers");
		String sort = request.getParameter("sort");
		String input = request.getParameter("input");
		String search = request.getParameter("search");
		//System.out.println(input + " " + search);
		
		if(sort != null || input != null){
			
			if(sort != null) new FilterPresidents(Integer.parseInt(sort), null, context);
			else if(search != null) new FilterPresidents(Integer.parseInt(input), search.toUpperCase(), context);
			this.currentTerm = 0;
		}
		
		if (operation == null) {
			if(selectTerm != null) setCurrentTerm(Integer.parseInt(selectTerm)-1);
		} else if (operation.equals("Previous")) {
			setCurrentTerm(--currentTerm);
		} else if (operation.equals("Home")) {
			context.setAttribute("presidents", presi);
			setCurrentTerm(0);
		} else if (operation.equals("Next")) {
			setCurrentTerm(++currentTerm);
		}
		context.setAttribute("currentTerm", this.currentTerm);
		RequestDispatcher dispatcher = context.getRequestDispatcher("/presidents.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

	public int getCurrentTerm() {
		return currentTerm;
	}

	public void setCurrentTerm(int currentTerm) {
		ServletContext context = this.getServletContext();
		Object[] obj = (Object[])context.getAttribute("presidents");
		
		if(currentTerm < 0) this.currentTerm =  obj.length-1;
		else if(currentTerm > obj.length-1) this.currentTerm = 0; 
		else this.currentTerm = currentTerm;
	}

}
