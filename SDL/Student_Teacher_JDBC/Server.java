package tejas;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server
{	
	public static void main(String args[]) throws Exception
	{
		String url = "jdbc:mysql://localhost:3306/mescoe";
		String user = "root";
		String pass = "tejas@sql";
		String insert = "insert into student values (?,?,?,?)";
		String count1 = "Select count(rollno)from student";
		String display = "Select * from student";
		String name_update = "update student set name = ? where rollno = ?";
		String rollno_update = "update student set roll_no = ? where rollno = ?";
		String year_update = "update student set year = ? where rollno = ?";
		String email_update = "update student set email = ? where rollno = ?";
		String delete = "Delete from student where rollno = ?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,user,pass);
		PreparedStatement st;
		Statement st1 = con.createStatement();
		ResultSet rs;
		try
		{
			ServerSocket ss =  new ServerSocket(5000);
			System.out.println("\nServer Running.");
			Socket s = ss.accept();
			System.out.println("\nConnection Established.");
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			int n,choice,count,index,flag;
			String rollno,name,year,email,change;
			do{
				dout.writeUTF("\n1.Add Student.\n2.Display Student Table\n3.Update Student.\n4.Delete Student.\n5.Exit.\nEnter index as choice: ");
				dout.flush();
				n = din.readInt();
				switch(n)
				{
					case 1:
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());
						st = con.prepareStatement(insert);
						Student obj = new Student();
						obj = (Student)in.readObject();
						name = obj.name;
						rollno = obj.rollno;
						year = obj.year;
						email = obj.email;
						st.setString(1,name);
						st.setString(2,rollno);
						st.setString(3,year);
						st.setString(4,email);
						count = st.executeUpdate();
						System.out.println(count+" entries added.");
						System.out.println("New student "+obj.name+" was added.");
						dout.writeUTF("The new student was added.");
						dout.flush();
						dout.writeInt(1);
						dout.flush();
						break;
					case 2:
						System.out.println("\nThe user wants to display student table.");
						dout.writeUTF("\n--------------------------------------------------------\nName|\t|Roll No|\t|Year|\t|Email|\n--------------------------------------------------------");
						dout.flush();
						rs = st1.executeQuery(count1);
						rs.next();
						int size = rs.getInt(1); 
						dout.writeInt(size);
						Student obj1 = new Student();
						rs = st1.executeQuery(display);
						while(rs.next())
						{
							name = rs.getString(1);
							rollno = rs.getString(2);
							year = rs.getString(3);
							email = rs.getString(4);
							obj1.AddStudent(name, rollno, year, email);
							ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
							out.writeObject(obj1);
						}
						dout.writeInt(1);
						dout.flush();
						break;
					case 3:
						index = 0;flag = 0;
						rollno = (String)din.readUTF();
						do
						{
							dout.writeUTF("\nEnter the attribute to modify:\n1.Name\n2.Roll No\n3.Year\n4.Email\nEnter the index as choice: ");
							choice = din.readInt();
							switch(choice)
							{
								case 1:
									System.out.println("\nThe user wants to update the name.");
									dout.writeUTF("\nEnter the new name of the student: ");
									dout.flush();
									name = (String)din.readUTF();
									st = con.prepareStatement(name_update);
									st.setString(1, name);
									st.setString(2, rollno);
									st.executeUpdate();
									System.out.println("\nName changed.");
									break;
								case 2:
									System.out.println("\nThe user wants to update the rollno.");
									dout.writeUTF("\nEnter the new rollno of the student: ");
									dout.flush();
									name = (String)din.readUTF();
									st = con.prepareStatement(rollno_update);
									st.setString(1, name);
									st.setString(2, rollno);
									st.executeUpdate();
									System.out.println("\nRollno changed.");
									break;
								case 3:
									System.out.println("\nThe user wants to update the year.");
									dout.writeUTF("\nEnter the new year of the student: ");
									dout.flush();
									year = (String)din.readUTF();
									st = con.prepareStatement(year_update);
									st.setString(1, year);
									st.setString(2, rollno);
									st.executeUpdate();
									System.out.println("\nYear changed.");
									break;
								case 4:
									System.out.println("\nThe user wants to update the email.");
									dout.writeUTF("\nEnter the new email of the student: ");
									dout.flush();
									email = (String)din.readUTF();
									st = con.prepareStatement(email_update);
									st.setString(1, email);
									st.setString(2, rollno);
									st.executeUpdate();
									System.out.println("\nEmail changed.");
									break;
							}
							dout.writeUTF("\nDo you want to make more changes(Y/N): ");
							change = (String)din.readUTF();
						}while(change.equals("Y")||change.equals("y"));
						dout.writeInt(1);
						dout.flush();
						break;
					case 4: 
						index = -1;
						System.out.println("\nThe user wants to delete student.");
						rollno = (String)din.readUTF();
						st = con.prepareStatement(delete);
						st.setString(1, rollno);
						st.executeUpdate();
						System.out.println("\nStudent Deleted.");
						dout.writeUTF("\nThe student was deleted.");
						dout.flush();
						dout.writeInt(1);
						dout.flush();
						break;
					case 5:
						System.out.println("\nBye.");
						dout.writeInt(0);
						dout.flush();
						break;
				}
			}while(n!=5);
			dout.close();
			din.close();
			s.close();
			ss.close();
		}
		catch(Exception e){System.out.println();}
		con.close();
	}
}

