
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Release
 */
@WebServlet("/Release")
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Release() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String doctor_mail = request.getParameter("email");
        String session = request.getParameter("session");
        System.out.print(doctor_mail);
        int idXip = Integer.parseInt(request.getParameter("idXip"));
        int idMed = Integer.parseInt(request.getParameter("idMed"));
        String dateLimit = request.getParameter("dateLimit");
        String mailP = request.getParameter("mailP");
        
        try {
            System.out.print("Release:" + session);
            Doctor doctor = new Doctor();
            boolean Logged = doctor.isLogged(doctor_mail, session);
            if (Logged) {
                String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES (?, ?, ?, ?, ?)";
                
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia", "root", "")) {
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setInt(1, idXip);
                    statement.setString(2, doctor_mail);
                    statement.setInt(3, idMed);
                    statement.setString(4, mailP);
                    statement.setString(5, dateLimit);
                    statement.executeUpdate();
                }
                
                            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

