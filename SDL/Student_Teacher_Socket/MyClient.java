import java.net.*;
import java.io.*;
import java.util.Scanner;
class Student implements Serializable
{
	String name,year,branch,div,batch,rollno;
	public void AddStudent(String name, String year, String branch, String batch, String div, String rollno)
	{
		this.name = name;
		this.year = year;
		this.branch = branch;
		this.batch = batch;
		this.div = div;
		this.rollno = rollno;
	}
	public void DisplayStudent()
	{
		System.out.println(this.name+"\t"+this.year+"\t"+this.branch+"\t"+this.batch+"\t"+this.div+"\t"+this.rollno);
	}
	public void Display()
	{
		System.out.println(this.name+"\t"+this.year+"\t"+this.branch+"\t"+this.batch+"\t"+this.div+"\t"+this.rollno);
	}
}
public class MyClient
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n,con,size,choice;
		String name,year,branch,div,batch,rollno,result,con1;
		try
		{
			Socket s = new Socket("127.0.0.1",5000);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			DataInputStream din = new DataInputStream(s.getInputStream());
			do
			{
				String str = (String)din.readUTF();
				System.out.println(str);
				n = sc.nextInt();
				dout.writeInt(n);
				dout.flush();
				switch(n)
				{
					case 1:
						System.out.println("Enter the name of the student to be added: ");
						name = sc.next();
						System.out.println("Enter the year of the student to be added: ");
						year = sc.next();
						System.out.println("Enter the branch of the student to be added: ");
						branch = sc.next();
						System.out.println("Enter the batch of the student to be added: ");
						batch = sc.next();
						System.out.println("Enter the Division of the student to be added: ");
						div = sc.next();
						System.out.println("Enter the roll no of the student to be added: ");
						rollno = sc.next();
						Student obj = new Student();
						obj.AddStudent(name,year,branch,batch,div,rollno);
						ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
						out.writeObject(obj);
						result = (String)din.readUTF();
						System.out.println(result);
						break;
					case 2:
						result = (String)din.readUTF();
						System.out.println(result);
						size = din.readInt();
						for(int i = 0; i<size; i++)
						{
							Student obj1 = new Student();
							ObjectInputStream in = new ObjectInputStream(s.getInputStream());
							obj1 = (Student)in.readObject();
							obj1.Display();
						}
						break;
					case 3:
						System.out.println("\nEnter the roll no of the student to be updated: ");
						rollno = sc.next();
						dout.writeUTF(rollno);
						dout.flush();
						result = (String)din.readUTF();
						if(result.equals("Success"))
						{
							do{
								result = (String)din.readUTF();
								System.out.println(result);
								choice = sc.nextInt();
								dout.writeInt(choice);
								dout.flush();
								result = (String)din.readUTF();
								System.out.println(result);
								name = sc.next();
								dout.writeUTF(name);
								dout.flush();
								result = (String)din.readUTF();
								System.out.println(result);
								con1 = sc.next();
								dout.writeUTF(con1);
								dout.flush();
							}while(con1.equals("Y")||con1.equals("y"));
						}
						else
						{
							System.out.println("\nThe student is not found.");
						}
						break;
						case 4:
							System.out.println("\nEnter the rollno of the student to be deleted: ");
							rollno = sc.next();
							dout.writeUTF(rollno);
							dout.flush();
							result = (String)din.readUTF();
							System.out.println(result);
							break;
				}
				con = din.readInt();
			}while(con!=0);
			dout.close();
			din.close();
			s.close();
		}
		catch(Exception e){System.out.println(e);}
	}
}