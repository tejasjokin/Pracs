import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;


class Student implements Serializable {
	String name,year,branch,batch,div,rollno,choice;
	public void AddStudent(String name, String year, String branch, String batch, String div, String rollno)
	{
		this.name = name;
		this.year = year;
		this.branch = branch;
		this.batch = batch;
		this.div = div;
		this.rollno = rollno;
	}
}
public class MyServer
{	
	public static void main(String args[])
	{
		ArrayList<Student> student = new ArrayList<Student>();
		try
		{
			ServerSocket ss =  new ServerSocket(5000);
			System.out.println("\nServer Running.");
			Socket s = ss.accept();
			System.out.println("\nConnection Established.");
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			int n,choice,index,flag;
			String rollno,name,change;
			do{
				dout.writeUTF("\n1.Add Student.\n2.Display Student Table\n3.Update Student.\n4.Delete Student.\n5.Exit.\nEnter index as choice: ");
				dout.flush();
				n = din.readInt();
				switch(n)
				{
					case 1:
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());
						Student obj = new Student();
						obj = (Student)in.readObject();
						student.add(obj);
						System.out.println("New student "+obj.name+" was added.");
						dout.writeUTF("The new student was added.");
						dout.flush();
						dout.writeInt(1);
						dout.flush();
						break;
					case 2:
						System.out.println("\nThe user wants to display student table.");
						dout.writeUTF("\n--------------------------------------------------------\nName|\tYear|\tBranch|\tBatch|\tDivision|Roll No|\n--------------------------------------------------------");
						dout.flush();
						int size = student.size();
						dout.writeInt(size);
						for (int i = 0; i<student.size(); i++) {
							ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
							out.writeObject(student.get(i));
						}
						dout.writeInt(1);
						dout.flush();
						break;
					case 3:
						index = 0;flag = 0;
						rollno = (String)din.readUTF();
						for (int i = 0; i<student.size(); i++) {
							Student o = new Student();
							o = student.get(i);
							if(o.rollno.equals(rollno))
							{
								index = i;
								flag = 1;
								break;
							}
						}
						if(flag==1)
						{
							dout.writeUTF("Success");
							dout.flush();
							do
							{
								dout.writeUTF("\nEnter the attribute to modify:\n1.Name\n2.Year\n3.Branch\n4.Batch\n5.Division\nEnter the index as choice: ");
								choice = din.readInt();
								switch(choice)
								{
									case 1:
										System.out.println("\nThe user wants to update the name.");
										dout.writeUTF("\nEnter the new name of the student: ");
										dout.flush();
										name = (String)din.readUTF();
										student.get(index).name = name;
										System.out.println("\nName changed.");
										break;
									case 2:
										System.out.println("\nThe user wants to update the year.");
										dout.writeUTF("\nEnter the new year of the student: ");
										dout.flush();
										name = (String)din.readUTF();
										student.get(index).year = name;
										System.out.println("\nYear changed.");
										break;
									case 3:
										System.out.println("\nThe user wants to update the branch.");
										dout.writeUTF("\nEnter the new branch of the student: ");
										dout.flush();
										name = (String)din.readUTF();
										student.get(index).branch = name;
										System.out.println("\nBranch changed.");
										break;
									case 4:
										System.out.println("\nThe user wants to update the batch.");
										dout.writeUTF("\nEnter the new batch of the student: ");
										dout.flush();
										name = (String)din.readUTF();
										student.get(index).batch = name;
										System.out.println("\nBatch changed.");
										break;
									case 5:
										System.out.println("\nThe user wants to update the division.");
										dout.writeUTF("\nEnter the new division of the student: ");
										dout.flush();
										name = (String)din.readUTF();
										student.get(index).div = name;
										System.out.println("\nDivision changed.");
										break;
								}
								dout.writeUTF("\nDo you want to make more changes(Y/N): ");
								change = (String)din.readUTF();
							}while(change.equals("Y")||change.equals("y"));
						}
						else
						{
							dout.writeUTF("Fail");
						}
						dout.writeInt(1);
						dout.flush();
						break;
					case 4: 
						index = -1;
						System.out.println("\nThe user wants to delete student.");
						rollno = (String)din.readUTF();
						for (int i = 0; i<student.size(); i++) {
							if(student.get(i).rollno.equals(rollno))
							{
								index = i;
							}
						}
						if(index!=-1)
						{
							student.remove(index);
							System.out.println("\nStudent Deleted.");
							dout.writeUTF("\nThe student was deleted.");
							dout.flush();
							dout.writeInt(1);
							dout.flush();
						}
						else
						{
							dout.writeUTF("\nStudent not found.");
							dout.flush();
							dout.writeInt(1);
							dout.flush();
						}
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
	}
}
