

public abstract class Persona {

	protected String name;
	protected String mail;
	  
	  
	public Persona() {
		
	}
	
	public Persona(String name, String mail) {
		super();
		this.name = name;
		this.mail = mail;
	}
	
	 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public abstract void load (String id);
	 
	 
	
	
	  

	  
	  
	  
	 
	 
	 
	
}
