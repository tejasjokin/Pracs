package tejas;

import java.io.*;

public class Student implements Serializable
{
	String name,rollno,year,email;
	public void AddStudent(String name, String rollno, String year, String email)
	{
		this.name = name;
		this.rollno = rollno;
		this.year = year;
		this.email = email;
	}
	public void DisplayStudent()
	{
		System.out.println(this.name+"\t"+this.rollno+"\t"+this.year+"\t"+this.email);
	}
	public void Display()
	{
		System.out.println(this.name+"\t"+this.rollno+"\t"+this.year+"\t"+this.email);
	}
}
