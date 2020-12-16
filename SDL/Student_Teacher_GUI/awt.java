package swing;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*; 
import java.awt.*; 

public class awt {
    int x1=50,x2=150,y=50;
    int stud=0;
 
    Demoswing dss=new Demoswing();
    
    public void Choice(){
    	
    	JFrame f=new JFrame("Student/Teacher login");
    	JLabel ch = new JLabel("MENU");
    	ch.setBounds(75, 10, 100, 30);
    	f.add(ch);
    	JRadioButton r1=new JRadioButton("ADD STUDENT");    
    	JRadioButton r2=new JRadioButton("DELETE STUDENT");    
    	JRadioButton r3=new JRadioButton("DISPLAY STUDENT");
    	JRadioButton r4=new JRadioButton("EXIT");
    	r1.setBounds(75,50,150,30);    
    	r2.setBounds(75,100,150,30); 
    	r3.setBounds(75,150,150,30); 
    	r4.setBounds(75, 200, 150, 30);
    	ButtonGroup bg=new ButtonGroup();    
    	bg.add(r1);bg.add(r2);bg.add(r3);bg.add(r4);    
    	f.add(r1);f.add(r2);f.add(r3);f.add(r4);     
    	f.setSize(300,300);    
    	f.setLayout(null);    
    	f.setVisible(true);  
    	r1.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
               stud=1;
               
            }  
         });  
        r2.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
             
               stud=2;
               
            }  
         });
        r3.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
               stud=3;
               
            }  
         });
        r4.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {               
               stud=4;
               
            }  
         });
        JButton b=new JButton("NEXT");  
        b.setBounds(175,250,150,30);  
        f.add(b);  
        f.setSize(600,400);  
        b.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){  
        	            if(stud==1)
        	            {
        	            	Student();
        	            	f.dispose();
        	            }
        	            else if(stud==2){
            	       	   Studentdel();
            	       	   f.dispose();
        	            }
        	            else if(stud==3){
            	           try {
							Studdis();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        	            }
        	            else if(stud==4)
        	            {
        	            	f.dispose();
        	            	System.exit(0);
        	            }
        	            else{
        	            	JOptionPane.showMessageDialog(f,"Please select one option!!");
        	            }
        	        }  
        	    });  
    }
    
   public void Studentdel() {
	   JFrame f= new JFrame("DELETE STUDENT");  
	   JTextField troll; 
	    JLabel lroll;
	    lroll=new JLabel("ENTER ROLL");  
	    lroll.setBounds(x1,y, 100,30);     
	    troll=new JTextField("");  
	    troll.setBounds(x2,y, 200,30);
	    f.add(lroll);f.add(troll);
	    y+=50;
	    JButton s=new JButton("DELETE");  
        s.setBounds(175,y,100,30);  
        f.add(s);  
        f.setSize(600,400);  
        s.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){  
        	            f.setVisible(false);
        	           
        	            int s2=Integer.parseInt(troll.getText());
        	           
        	            try {
							dss.delstudent(s2);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
        	           
        	            JOptionPane.showMessageDialog(f,"Roll deleted successfully");
        	            Choice();
        	            
        	        }  
        	    });
        f.setSize(500,500);  
	    f.setLayout(null);  
	    f.setVisible(true);  
	    
   }
 public void Studdis() throws Exception {
	int y=50;
	  JFrame f = new JFrame("DISPLAY STUDENT");
			String query="select * from studtech";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mescoe","root","tejas@sql");
			PreparedStatement pst=con.prepareStatement(query);
			ResultSet rs=pst.executeQuery(query);
			while(rs.next())
			{
				JLabel l1,l2,l3;
			    l1=new JLabel("Name:"+rs.getString(1));
		        l1.setBounds(50, y, 100, 30);
		        y+=50;
		        l2=new JLabel("Roll:"+rs.getInt(2));
		        l2.setBounds(50, y, 100, 30);
		        y+=50;
		        l3=new JLabel("Email:"+rs.getString(1));
		        l3.setBounds(50, y, 100, 30);
		        y+=50;
		        f.add(l1);
		        f.add(l2);
		        f.add(l3);
		        
			}
			 f.setSize(500,900);  
		        f.setLayout(null);  
		        f.setVisible(true); 
		
   }

	public void Student(){
		
	 
		  JTextField tf1,tf2;  
		    JButton b1,b2;  
		    JTextArea area;
		    JLabel l1,l2,l3,l4,l5,l6,add;
		    JFrame f;
		    Statement st;
		    PreparedStatement p1,p2;
		    String res,res1;
		  
		
			    f=new JFrame("Student-Teacher-Application");  
		        tf1=new JTextField();  
		        tf1.setBounds(200,50,150,30); 
		        l1=new JLabel("Name:");
		        l1.setBounds(50, 50, 100, 30);
		        
		        
		        add=new JLabel("Roll:");  
		        add.setBounds(50,100,100,30); 
		        area=new JTextArea();
		        area.setBounds(200,100,100,30); 
		        
		        
		        l2=new JLabel("Phone:");
		        l2.setBounds(50, 150, 100, 30);
		        tf2=new JTextField();
		        tf2.setBounds(200, 150, 150, 30);
		     
		       
		        b1=new JButton("Submit");  
		        b1.setBounds(80,550,90,30);  
		        b2=new JButton("Reset");  
		        b2.setBounds(200 ,550,90,30);  
		       
		        f.add(tf1); f.add(tf2);f.add(l1);f.add(l2);f.add(add);f.add(area);
		        f.add(b1);
		        f.add(b2);
		        f.setSize(500,900);  
		        f.setLayout(null);  
		        f.setVisible(true);
		        b1.addActionListener(new ActionListener(){  
		        	public void actionPerformed(ActionEvent e){  
		        	           
		        		 String s1=tf1.getText();
				            int s2=Integer.parseInt(area.getText());
				            String s3=tf2.getText();
				           
				            if(e.getSource() == b1) {
				            	 
				            	  int flag=0;
				            	  if(s1.length()==0) {
				            		JOptionPane.showMessageDialog(f,"Please enter your name..");
				            		flag=1;
				            	  }
				            	  if(s2==0) {
				            		  flag=1;
				            		  JOptionPane.showMessageDialog(f,"Please enter your roll no" );
				            	  }
				            	 
				            	  if(s3.length()!=10) {
				            		  flag=1;
				            		  JOptionPane.showMessageDialog(f,"Please enter your 10 digit phone no" );
				            	  }
				            	  
				           
				            	  if(flag==0) {
				            		  JOptionPane.showMessageDialog(f,"Data Submitted!!");
				            		  f.dispose();
				            		try {
										dss.addstudent1(s1, s2, s3);
										 Choice();
									} catch (Exception e1) {
										e1.printStackTrace();
									}
				            	  }
								  tf1.setText("");
				            		  tf2.setText("");
				            		  area.setText("");
				            	
				            	  }
				            
		        	}
				            });
		        	   
		        b2.addActionListener(new ActionListener(){  
		        	public void actionPerformed(ActionEvent e){  
		        		 
		         		  tf1.setText("");
		         		  tf2.setText("");
		         		  area.setText("");
		         		 JOptionPane.showMessageDialog(f,"Reset Succesful!!");
		         		 Choice();
		        	            
		        	        }  
		        	    });  
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		awt a = new awt();
		a.Choice();
	}

}