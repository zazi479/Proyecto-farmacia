import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;



public class Xip  {
	
	private int id;
	private String doctor_mail;
	private Medicine medicine;
	private Patient patient;
	LocalDate date;
	
	
	
	public Xip() {

	}


	public Xip(int id, Medicine medicine, Patient patient, Date data, String doctor_mail, LocalDate date) {
		super();
		this.id = id;
		this.doctor_mail= doctor_mail;
		this.medicine = medicine;
		this.patient = patient;
		this.date = date;
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public Medicine getMedicine() {
		return medicine;
	}


	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	

	public String getDoctor_mail() {
		return doctor_mail;
	}


	public void setDoctor_mail(String doctor_mail) {
		this.doctor_mail = doctor_mail;
	}
	


	public void load(int id) throws ClassNotFoundException {
		
		
		try {
		    BBDD bd = new BBDD("localhost", "farmacia", "3306", "root", "");
		    String query = "SELECT * FROM xip WHERE id = " + id;
		    
		    ResultSet resultSet = bd.executeQuery(query);
		    
		    
		    

		    if (resultSet.next()) {
		         int xipid = resultSet.getInt("id");
		         int id_medicine = resultSet.getInt("id_medicine");
		         String id_patient = resultSet.getString("id_patient");
		         LocalDate date = resultSet.getDate("date").toLocalDate();
		    
		        Medicine medicine = new Medicine();
            	Patient patient = new Patient();
            	
            	medicine.load(id_medicine);
            	patient.load(id_patient);
            	
            	setId(xipid);
            	setMedicine(medicine);
            	setPatient(patient);
            	setDate(date);

            	   System.out.println("Carga de datos exitosa para el Xip con ID: " + id);
            } else {
                System.out.println("Xip no encontrado con ID: " + id);
            }
		}	catch (SQLException e) {
                e.printStackTrace();
         }
        
		}
}
