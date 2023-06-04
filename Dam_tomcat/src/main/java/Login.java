

import java.io.IOException;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	

   
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor. 
     */
    public Login() {
        super();
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail = request.getParameter("email");
		String pass = request.getParameter("pass");
		System.out.println("Mail recibido del frontend: "+mail);
		
		Doctor doctor = new Doctor();
		doctor.login(mail, pass);
		
		boolean loggedIn = doctor.isLogged(mail, doctor.getSession());
		System.out.println(doctor.getName());

	    if (loggedIn) {
	        String session = doctor.getSession();
	        System.out.println("Respuesta enviada: "+session);
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setContentType("text/plain");
	        response.getWriter().write(session);
	    } else {
	    	System.out.println("Error: No se pudo iniciar sesión");
	        response.setContentType("text/plain");
	        response.getWriter().write("Error: No se pudo iniciar sesión");
	        
	    }
	    
	}
	
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Esto es un post");
		doGet(request, response);
	}

}
