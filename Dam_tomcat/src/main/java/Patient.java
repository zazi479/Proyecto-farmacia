import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends Persona {
	
	
	public Patient() {
		super();
		
	}

	public Patient(String name, String mail) {
		super(name, mail);
		
	}

	@Override
	public void load(String id) {
		
		try {
			
			BBDD bd  = new BBDD("localhost", "farmacia", "3306","root", "");
			String query = "SELECT * from patient WHERE mail ='"+id+"';" ;
			ResultSet resultSet = bd.executeQuery(query);
			
			if (resultSet.next()){
			
            this.name = resultSet.getString("name");
            this.mail  = resultSet.getString("mail");
            
			} else {
				System.out.println("Paciente no encontrado.");
			}
				
			

		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

