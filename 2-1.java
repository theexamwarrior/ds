import java.io.*; 
import java.net.*; 
import java.sql.*; 
import java.util.*;

class client {
public static void main(String args[]) { Socket clisock;
DataInputStream input; PrintStream ps;
String url,ip,s,u,p,str; int pno=5123; Connection con; Statement stmt; ResultSet rs; Boolean status=true; try {
ip=s=p=u="\0";
System.out.println("Enter the name to resolve:");
BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); url=br.readLine();
Class.forName("com.mysql.jdbc.Driver");
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/be2k21XX","cse","a");
stmt=con.createStatement();
while(status) { s="\0";
StringTokenizer st=new StringTokenizer(url,"."); if(st.countTokens()==1) { status=false; } while(st.countTokens()>1)
s=s+st.nextToken()+ "."; s=s.substring(0,s.length()-1).trim(); u=st.nextToken();
rs=stmt.executeQuery("select port,ipadd from Client where name='"+u+"'"); if(rs.next())	{
p=rs.getString(1); pno=Integer.parseInt(p); str=rs.getString(2); url=s;
ip=str + "." + ip;
}
else	{
clisock=new Socket("127.0.0.1",pno);
input=new DataInputStream(clisock.getInputStream()); ps=new PrintStream(clisock.getOutputStream()); ps.println(url);
p=input.readLine(); pno=Integer.parseInt(p); str=input.readLine(); url=input.readLine(); ip=str + "." + ip;
stmt.executeUpdate("insert into Client values('" + u +"','" + str +"','" + p +"')");
}
ip=ip.substring(0,ip.length()-1).trim();
}
System.out.println("IP Address is: " + ip); con.close();
}
catch(Exception e)
{ System.err.println(e); }
}
}
