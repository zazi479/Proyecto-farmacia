import java.sql.*;

public class BBDD {
    private Connection conn;
    private Statement st;
    private ResultSet rSet;
    private String host;
    private String dbname;
    private String port;
    private String password;
    private String username;

    public BBDD(String host, String dbname, String port,String username, String password)
            throws ClassNotFoundException {
        this.host = host;
        this.dbname = dbname;
        this.port = port;
        this.password = password;
        this.username = username;
        
        
        

        try {
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, this.username, this.password);

            
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public BBDD()throws ClassNotFoundException{
    	        this.host = "localhost";
    	        this.dbname = "farmacia";
    	        this.port = "3306";
    	        this.password = "";
    	        this.username = "root";
    	        
    	        
    	        

    	        try {
    	            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname;
    	            Class.forName("com.mysql.cj.jdbc.Driver");
    	            conn = DriverManager.getConnection(url, this.username, this.password);

    	            
    	            System.out.println("Connected");
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }

    	    
    	   
	}
	
	public void executeUpdate(String query){

        try{
            st = conn.createStatement();
            st.executeUpdate(query);
            System.out.println("Completed");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public ResultSet executeQuery(String query) {
        try{
            st = conn.createStatement();
            rSet = st.executeQuery(query);
            System.out.println("Completed: "+query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rSet;
    }
	
    
//    
//    public static void main(String[] args) {
//		
//    	try {
//			BBDD conn  = new BBDD("localhost", "farmacia", "3306","root", "");
//			conn.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	
//	}
}
	    
	    
	    
	    
	    
	  
	
