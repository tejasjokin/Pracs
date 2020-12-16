#include <iostream>
#include <cmath>
using namespace std;
 int parity(int final[],int len,int pos){
       int flag=0;
       for(int i=pos;i<=len;i+=(2*pos))
       {
           cout<<"\n value at pos"<<i<<final[i];
           int j=i;
           for(int q=pos;q>0;q--)
           {
               if(j<=len)
               {
               cout<<"\n value at j "<<j<< final[j];
               if(final[j++]==1)
               flag++;
               }
           }
       }
       cout<<"\n for pos"<<pos<<" flag is"<<flag;
       if(flag%2==0)
       return 0;
      else
      return 1;
   }
int main()
{
   int given[10],i,j,a,b,r=0,bit,flag1,R[10];
  
   cout<<"\nenter the size of dataframe: ";
   cin>>b;
   cout<<"\nenter data = ";
   for(i=0;i<b;i++)
   {
       cout<<"\nEnter the bit "<<i+1<<": ";
       cin>>given[i];
   }
   
   cout<<"\nno of redundant bits = ";
 
   while(pow(2,r)<b+r+1)
   {
      r++;
   }
  cout<<r;
  a=b+r;
   int final[a];
   for(int i=0;i<a;i++)
   {
       if(i<r)
       {      int s=pow(2,i);
           final[s]=-1;
       }
   }
  
   
    i=1,j=b-1;int k=0;
//padding
while(i<=a && j>=0)
   {
       if(final[i]==-1)
       {
           i++;
           k++;
       }
       else
       {
           final[i]=given[j];
           i++;
           j--;
       }
   }
   cout<<"\nafter redundant bits     ";
   for(int p=1;p<=a;p++)
{
  cout<<final[p];
 
}
//calculating parity
for(int s=1;s<=a;s++)
{
  if(final[s]==-1)
  {
      cout<<"\n pos is"<<s;
     final[s]=parity(final,a,s); 
  }
 
}
 cout<<"\nafter parity bits     ";
   for(int p=1;p<=a;p++)
{
  cout<<final[p];
 
}


 cout<<"\n-----------------------------------------------------------------------------";  
    
    
    int ans;
    cout<<"\nTHE RECEIVER SIDE ";
    cout<<"\ntransmit the same data or make changes in data (1 for same else any number)";
    cin>>ans;
    
    if(ans==1)
    {
        cout<<"\ndata recieved is correct";
        for(i=1;i<=a;i++)
        {
            cout<<final[i];
        }
    }
    else
    {
         flag1=1;
        cout<<"\nenter the bit you want to change";
        cin>>bit;
      
        if(final[bit]==1)
        {
            final[bit]=0;
        }
        else
        {final[bit]=1;}
        
        cout<<"\ndata transmitted is";
         for(i=1;i<=a;i++)
        {
            cout<<final[i];
        }
      
    }
k=0;
    
for(int s=1;s<=a;s++)
{
  if(s==pow(2,k))
  {
     R[k]=parity(final,a,s);
      k++;
  }

}
cout<<"\nvalue of k is"<<k;
 cout<<"\nthe changed bits are     ";
   for(int p=k-1;p>=0;p--)
{
  cout<<R[p];
 
}


   return 0;

}