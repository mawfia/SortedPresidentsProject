package presidents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class ParsePresidents {
	private static final String INPUT_FILE = "WEB-INF/presidents.csv";
	private List<President> presidents = new ArrayList<>();
	private ServletContext servletContext;
	
	public ParsePresidents(ServletContext context) throws ServletException {
		servletContext = context;
		InputStream is = servletContext.getResourceAsStream(INPUT_FILE);
		try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) 
		{

			String line;
			while ((line = buf.readLine()) != null) 
			{
				President p = buildPresident(line);
				presidents.add(p);
			}

		} 
		catch (IOException e) {
			System.err.println(e);
		}
		finally{
			try {
				is.close();
			} catch (IOException e) {
				System.err.println(e);;
			}
		}
	}

	private President buildPresident(String line) {
		String[] tokens = line.split(",");
	    int number = Integer.parseInt(tokens[0].trim());
	    String fname = tokens[1].trim();
	    String mname = tokens[2].trim();
	    String lname = tokens[3].trim();
	    int[] termArray = getTerms(tokens[4].trim());
	    String party = tokens[5].trim();
	    String funFact = tokens[6].replaceAll("%", ",").trim();
	    String photo = tokens[7].trim();

		return new President(number, fname, mname, lname, termArray[0], termArray[1], party, funFact, photo);
	}

	private int[] getTerms(String terms) {
		// The terms are separated by a dash
		String[] termTokens = terms.split("-");
		int startTerm = Integer.parseInt(termTokens[0]);
		int endTerm = Integer.parseInt(termTokens[1]);
		return new int[] { startTerm, endTerm };
	}

	public List<President> getPresidents() {
		return presidents;
	}
}
