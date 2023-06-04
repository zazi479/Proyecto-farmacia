
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServeXips
 */
@WebServlet("/ServeXips")
public class ServeXips extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServeXips() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("email");
	    String session = request.getParameter("session");
	    System.out.println(session);
	    
	    Doctor doctor = new Doctor();
	    boolean isLogged = doctor.isLogged(mail, session);
	    System.out.println(session);
	    
	    if (isLogged) {
	        doctor.load(mail);
	        doctor.loadReleaseList();
	        String table = doctor.getTable();
	        
	        
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
            out.println(table);
            
	    } else {
	    	response.setContentType("text/plain");
	    	PrintWriter out = response.getWriter();
            out.println("No está autenticado.");
	        
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
