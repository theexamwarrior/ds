import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

class server1 {
 public static void main(String args[])  {
  ServerSocket sock;
  Socket client;
  DataInputStream input;
  PrintStream ps;
  String url,s,u;
  Connection con;
  Statement stmt;
  ResultSet rs;
  try   {
   s=u="\0";
  // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  // con=DriverManager.getConnection("jdbc:odbc:NameServers");
  Class.forName("com.mysql.jdbc.Driver");
     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/be2k2186","cse","a");
  
  

   stmt=con.createStatement();

   sock=new ServerSocket(5123);
   while(true)   {
    client= sock.accept();
    input=new DataInputStream(client.getInputStream());
    ps=new PrintStream(client.getOutputStream());
    url=input.readLine();

    StringTokenizer st=new StringTokenizer(url,".");
    while(st.countTokens()>1)
     s=s+st.nextToken()+ ".";
    s=s.substring(0,s.length()-1).trim();
    u=st.nextToken();

    rs=stmt.executeQuery("select port,ipadd from Root where name='" + u +"'");
    if(rs.next())    {
     ps.println(rs.getString(1));
     ps.println(rs.getString(2));
     ps.println(s);
    }
    else
     ps.println("Illegal Address please check the spelling again");
     con.close();
  }
 }
 catch(Exception e)
 {  System.err.println(e); }
 }
   }
