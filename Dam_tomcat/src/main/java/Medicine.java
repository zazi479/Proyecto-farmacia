import java.sql.ResultSet;
import java.sql.SQLException;

public class Medicine {

	private int id;
	private String name;
	private Float Tmax;
	private Float Tmin;
	
	
	public Medicine() {
	
	}


	public Medicine(int id, String name, Float tmax, Float tmin) {
		this.id = id;
		this.name = name;
		this.Tmax = tmax;
		this.Tmin = tmin;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Float getTmax() {
		return Tmax;
	}


	public void setTmax(Float tmax) {
		Tmax = tmax;
	}


	public Float getTmin() {
		return Tmin;
	}


	public void setTmin(Float tmin) {
		Tmin = tmin;
	}
	

	public void load(int id) {
		
		try {
			BBDD bd = new BBDD("localhost", "farmacia", "3306", "root", "");
			String query = ("SELECT * FROM medicine WHERE id = "+id+";");
            ResultSet resultSet = bd.executeQuery(query);
			
			if (resultSet.next()) { 
			this.id = id;
            this.name = resultSet.getString("name");
            this.Tmax = resultSet.getFloat("tmax");
            this.Tmin = resultSet.getFloat("tmin");
            
            System.out.println("Carga de datos exitosa.");
            
			}else {
				System.out.println("Paciente no encontrado.");
			}
			
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	
	

	
}
