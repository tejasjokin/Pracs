import java.util.Scanner;
import java.lang.Math;

public class IpAddress
{
	Scanner sc = new Scanner(System.in);
	String ip,netID = "",broadcastID = "",firstIP="",lastIP="",ipClass,subnetMask;
	String[] ip_octet;
	String[] mask_octet;
	int oct,x,y,z;
	int oct2[] = new int[8];
	IpAddress()
	{
		System.out.println("\nEnter the IP Address to be analysed: ");
		ip = sc.next();
		ip_octet = ip.split("\\.");
		oct = Integer.parseInt(ip_octet[0]);
		if(oct>=0 && oct<=127)
		{
			ipClass = "Class A";
			
			subnetMask = "255.0.0.0";
			mask_octet = subnetMask.split("\\.");
		}
		else if(oct>=128 && oct<=191)
		{
			ipClass = "Class B";
			subnetMask = "255.255.0.0";
			mask_octet = subnetMask.split("\\.");
		}
		else if(oct>=192 && oct<=223)
		{
			ipClass = "Class C";
			subnetMask = "255.255.255.0";
			mask_octet = subnetMask.split("\\.");
		}
		for(int i=0,j=0; i<mask_octet.length; i++,j++)
		{
			x = Integer.parseInt(ip_octet[i]);
			y = Integer.parseInt(mask_octet[j]);
			z = x&y;
			netID = netID + Integer.toString(z)+".";
		}
		netID = netID.substring(0,netID.length()-1);
		for(int i=0; i<mask_octet.length; i++)
		{
			if(i!=(mask_octet.length-1))
			{
				x = Integer.parseInt(ip_octet[i]);
				y = Integer.parseInt(mask_octet[i]);
				z = x&y;
				firstIP = firstIP + Integer.toString(z)+".";
			}
			else
			{
				x = Integer.parseInt(ip_octet[i]);
				y = Integer.parseInt(mask_octet[i]);
				z = (x&y)+1;
				firstIP = firstIP + Integer.toString(z);	
			}
		}
		ip_octet = netID.split("\\.");
		for (int i=0; i<mask_octet.length; i++) {
			x = Integer.parseInt(ip_octet[i]);
			y = Integer.parseInt(mask_octet[i]);
			toBinary(y, oct2);
			for (int j=0; j<oct2.length; j++) {
				if(oct2[j]==0)
				{
					oct2[j]=1;
				}
				else if(oct2[j]==1)
				{
					oct2[j]=0;
				}
			}
			y = toDecimal(oct2);
			z = x|y;
			broadcastID = broadcastID + Integer.toString(z)+".";
		}
		broadcastID = broadcastID.substring(0,broadcastID.length()-1);
		for (int i=0; i<mask_octet.length; i++) {
			if(i!=mask_octet.length-1)
			{
				x = Integer.parseInt(ip_octet[i]);
				y = Integer.parseInt(mask_octet[i]);
				toBinary(y, oct2);
				for (int j=0; j<oct2.length; j++) {
					if(oct2[j]==0)
					{
						oct2[j]=1;
					}
					else if(oct2[j]==1)
					{
						oct2[j]=0;
					}
				}
				y = toDecimal(oct2);
				z = x|y;
				lastIP = lastIP + Integer.toString(z)+".";	
			}
			else
			{
				x = Integer.parseInt(ip_octet[i]);
				y = Integer.parseInt(mask_octet[i]);
				toBinary(y, oct2);
				for (int j=0; j<oct2.length; j++) {
					if(oct2[j]==0)
					{
						oct2[j]=1;
					}
					else if(oct2[j]==1)
					{
						oct2[j]=0;
					}
				}
				y = toDecimal(oct2);
				z = x|y;
				z = z-1;
				lastIP = lastIP + Integer.toString(z);
			}
		}
		System.out.println("\nIP Class: "+ipClass+"\nSubnet Mask: "+subnetMask+"\nNetwork ID: "+netID+"\nFirst IP Address: "+firstIP+"\nLast IP Address: "+lastIP+"\nBroadcast ID: "+broadcastID);
	}

	public static void toBinary(int decimal, int[] oct)
	{        
		int index = 0;
		if(decimal==0)
		{
			for (int i = 0; i<oct.length; i++) {
				oct[i]=0;
			}
		}
		else
		{
			while(decimal > 0)
			{    
				oct[index++] = decimal%2;    
				decimal = decimal/2;    
    		}
		}
    }    

    public static int toDecimal(int[] oct)
    {
    	int temp = 0;
    	int two = 1;
    	for (int i=0; i<oct.length; i++) {
    		if(oct[i]==1)
    		{
    			two =1;
    			for (int j=0; j<i; j++) {
    				two = two*2;
    			}
    			temp = temp+two;
    		}
    	}
    	return(temp);
    }

	public static void main(String[] args) {
		IpAddress obj = new IpAddress();
	}
}