
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Random;









public class Doctor extends Persona {
		
	
	private String pass;
	private LocalDate lastLog;
	private String session;
	private ArrayList<Xip> releaseList;
	

	
	public Doctor() {	
		this.releaseList = new ArrayList<>();
	}
	

	public Doctor(String pass, LocalDate lastLog, String session, ArrayList<?> releaseList) {
		super();
		this.pass = pass;
		this.lastLog = lastLog;
		this.session = session;
		this.releaseList = new ArrayList<>();
		

	}

	private String generateCodeSession() {
		Random random = new Random();
		StringBuffer result = new StringBuffer();
		int index = 0;
		while (index <9) {
			result.append(random.nextInt(9));
			index++;
		}
		return result.toString();
	}
	

	
	// getter y setter

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDate getLastLog() {
		return lastLog;
	}

	public void setLastLog(LocalDate lastLog) {
		this.lastLog = lastLog;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public ArrayList<Xip> getReleaseList() {
		return releaseList;
	}

	public void setReleaseList(Xip xipAsociado) {
		this.releaseList.add(xipAsociado);
	}

	// metodos

	public void login(String mail, String pass) {

		String query = "SELECT * FROM doctor WHERE mail='"+mail+"' AND pass='"+pass+"';";		
		
		System.out.println("funciona");
		BBDD bd;
		try {
			bd = new BBDD("localhost", "farmacia", "3306", "root", "");
			ResultSet rSet = bd.executeQuery(query);
			   

			while (rSet.next()) {
				System.out.println(rSet.getString("mail"));
	                // Código de generación de sesión
	                this.session = generateCodeSession();
	                this.lastLog=LocalDate.now();

	                // Actualizar lastLog y session en la base de datos
	             
	                String updateQuery = "UPDATE doctor SET lastLog = ' "+lastLog+ "' , session = '" + session + "' WHERE mail='" + mail + "'";
	                
	                bd.executeUpdate(updateQuery);

	                
	             
	                // Cargar la información del doctor
	                this.load(mail);
	                

	                // Establecer la sesión en el objeto actual
	               
	                this.setSession(session);
	            } 
	            
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
//		
	}

	public boolean isLogged(String mail, String session) {
		
		try {
			String query = "SELECT * FROM doctor WHERE mail='" + mail + "'  AND session='" + session + "'";
			BBDD bd;
			bd = new BBDD("localhost", "farmacia", "3306", "root", "");
			ResultSet rSet = bd.executeQuery(query);
			
			if (rSet.next()) {
	            // Sesión válida, los datos se han cargado correctamente
	            return true;
	            
	        } else {
	            // Sesión inválida, los datos no coinciden
	            return false;
	        }
			
		        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	   

	@Override
	public void load(String id) {

		
		try {
	        BBDD bd = new BBDD("localhost", "farmacia", "3306", "root", "");
	        String query = "SELECT * FROM doctor WHERE mail='" + id + "';";
	        ResultSet rSet = bd.executeQuery(query);
	        
	        while (rSet.next()) {
	        	this.name = rSet.getString("name");
	            this.mail = rSet.getString("mail");
	            this.pass = rSet.getString("pass");
	            this.lastLog = rSet.getDate("lastLog").toLocalDate();
	            this.session = rSet.getString("session");
	            
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		

	}

	public void loadReleaseList() {

		try {
			BBDD bd = new BBDD("localhost", "farmacia", "3306", "root", "");
			String query = "SELECT * FROM xip WHERE doctor_mail='" + mail + "' AND date >= CURDATE()";
			ResultSet rSet = bd.executeQuery(query);

			
					
			while (rSet.next()) {
				// Obtener los datos del chip de cada fila y crear un objeto Xip
				int id1 = rSet.getInt("id");
				Xip xip = new Xip();
				xip.load(id1);
				releaseList.add(xip);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		for (Xip i : releaseList) {
			System.out.println(i.getId());
		}
	}
	

	public String getTable() {
		 StringBuilder table = new StringBuilder();
		    table.append("<table>");
		    table.append("<tr><th>ID</th><th>Medicine</th><th>Patient</th><th>Date</th></tr>");

		    for (Xip xip : releaseList) {
		        if (xip.getDate().isAfter(LocalDate.now())) {
		            table.append("<tr>");
		            table.append("<td>").append(xip.getId()).append("</td>");
		            table.append("<td>").append(xip.getMedicine().getName()).append("</td>");
		            table.append("<td>").append(xip.getPatient().getName()).append("</td>");
		            table.append("<td>").append(xip.getDate()).append("</td>");
		            table.append("</tr>");
		        }
		    }

		    table.append("</table>");
		    return table.toString();
		}
		
		
		
}



