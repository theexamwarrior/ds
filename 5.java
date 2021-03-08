import java.io.*;
import java.sql.*;

public class bulletin {
    static Connection con;
    static Statement st;
    static ResultSet rs;
        
    public static void main(String[] args) throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bulletin","root","");
             
           st = con.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
     //   catch (SQLException ex)
       //{            ex.printStackTrace();        } 
      
        
        if(con!=null){
            System.out.println("Connected to Server...\n");            
            System.out.println("Please Enter your group name:\n");
            try {
                rs = st.executeQuery("Select * from Groups");                
                while(rs.next()){
                    System.out.println(rs.getString("grp"));
                }                
            } catch (SQLException ex) {
                ex.printStackTrace();   }
            String grpname;
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            grpname = br.readLine();            
           
            System.out.print("Enter username: \t");
            String usr = br.readLine();
            System.out.println("\nEnter password: \t");
            String pass = br.readLine();
                try {             
String query = "select * from "+grpname.trim()+"Mem where member='"+usr+"'";
	 System.out.println(query);
	 rs = st.executeQuery(query);
	 while(rs.next()){
                 if(rs.getString("pwd").equals(pass)){
                        System.out.println("Login Successful!!! HAVE A NICE DAY!!!");
             	 while(true){		      
	  	System.out.println("Enter your choice: \n");
		System.out.println("1.Read messages \n");
		System.out.println("2.Write message \n");
		System.out.println("3.Logout \n");	
		
		int option = Integer.parseInt(br.readLine());
		switch(option){
		  case 1:
			query = "select * from "+grpname+"Msg";
		    	System.out.println(query);
		    	rs = st.executeQuery(query);
			while(rs.next())
                   System.out.println(rs.getString("user")+" : "+ rs.getString("Message")+ "\n\n");
                		break;
		  case 2:
			System.out.println("Enter your message:\n");
			String msg = br.readLine();
			query="insert into "+grpname.trim()+"Msg values('"+msg+"','"+usr+"')";
		    	System.out.println(query);
			st.executeUpdate(query);		    	
			break;
		  case 3:
			  System.exit(1);			  
		  default:
			System.out.println("Choose a valid option!!!\n");
			break;
		}	
	          }
	       }
                    else{
                        System.out.println("Please Login again!!\n");
                        System.exit(1);
                    }
                }                    
                } catch (SQLException ex)
   {  ex.printStackTrace();             }                             
            }      
        else   System.out.println("! Not Connected\n"); 
    }    
}
