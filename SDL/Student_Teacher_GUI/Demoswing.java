package swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Demoswing {

	
	public void addstudent1(String s1,int s2,String s3) throws Exception
	{
		String query="insert into studtech values(?,?,?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mescoe","root","tejas@sql");
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, s1);
		pst.setInt(2, s2);
		pst.setString(3, s3);
		pst.executeUpdate();
		
	}
	public void delstudent(int rollno) throws Exception
	{
		String query="delete from studtech where roll=?";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mescoe","root","tejas@sql");
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, rollno);
		pst.executeUpdate();

	}
}