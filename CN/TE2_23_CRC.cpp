#include <stdio.h>
#include <iostream>
using namespace std;

int exor(int a, int b)
{
    if(a==b)
    {
        return 0;
    }
    else
    {
        return 1;
    }
}

void receiver_crc(int generator[50], int genelen)
{
    int dataframe[100];
    for(int i = 0; i<100; i++)
    {
        dataframe[i] =0;
    }
    int datalen;
    cout<<"\nEnter the length of the data frame to be checked: ";
    cin>>datalen;
    cout<<"\nEnter the data frame bits:";
    for(int i =0; i<datalen; i++)
    {
        cout<<"\nEnter bit "<<(i+1)<<": ";
        cin>>dataframe[i];
    }
    int temp[genelen], rem[genelen];
    for(int i = 0; i<genelen; i++)
    {
        temp[i] = 0;
        rem[i] = 0;
    }
    for(int i = 0; i<genelen; i++)
    {
        rem[i] = dataframe[i];
    }
    for(int j = genelen; j<=datalen; j++)
    {
        for(int i = 0; i<genelen; i++)
        {
            temp[i] = rem[i];
        }
        if(rem[0]==0)
        {
            for(int i=0; i<genelen-1; i++)
            {
                rem[i] = temp[i+1];
            }
        }
        else
        {
            for(int i = 0; i<genelen-1; i++)
            {
                rem[i] = exor(temp[i+1],generator[i+1]);
            }
        }
        if(j!=datalen)
        {
            rem[genelen-1] = dataframe[j];
        }
    }
    cout<<"\nDataFrame:";
    for(int i=0; i<datalen; i++)
    {
        cout<<dataframe[i];
    }
    cout<<"\nRemainder:";
    for(int i = 0; i<genelen-1; i++)
    {
        cout<<rem[i];
    }
    bool flag = false;
    for(int i = 0; i<genelen-1; i++)
    {
        if(rem[i]==1)
        {
            flag = true;
        }
    }
    if(flag==false)
    {
        cout<<"\nThe recieved frame is correct.";
    }
    else
    {
        cout<<"\nThe recieved frame is corrupt.";
    }
}

void sender_crc(int generator[50], int genelen)
{
    int dataframe[100];
    for(int i = 0; i<100; i++)
    {
        dataframe[i] =0;
    }
    int datalen;
    cout<<"\nEnter the length of the data frame to be sent: ";
    cin>>datalen;
    cout<<"\nEnter the data frame bits:";
    for(int i =0; i<datalen; i++)
    {
        cout<<"\nEnter bit "<<(i+1)<<": ";
        cin>>dataframe[i];
    }
    for(int i = datalen; i<genelen-1; i++)
    {
        dataframe[i] = 0;
    }
    int codelen = datalen + (genelen-1);
    
    int temp[genelen], rem[genelen];
    for(int i = 0; i<genelen; i++)
    {
        temp[i] = 0;
        rem[i] = 0;
    }
    for(int i = 0; i<genelen; i++)
    {
        rem[i] = dataframe[i];
    }
    for(int j = genelen; j<=codelen; j++)
    {
        for(int i = 0; i<genelen; i++)
        {
            temp[i] = rem[i];
        }
        if(rem[0]==0)
        {
            for(int i=0; i<genelen-1; i++)
            {
                rem[i] = temp[i+1];
            }
        }
        else
        {
            for(int i = 0; i<genelen-1; i++)
            {
                rem[i] = exor(temp[i+1],generator[i+1]);
            }
        }
        if(j!=codelen)
        {
            rem[genelen-1] = dataframe[j];
        }
    }
    cout<<"\nDataFrame:";
    for(int i=0; i<datalen; i++)
    {
        cout<<dataframe[i];
    }
    cout<<"\nRedundant Bits:";
    for(int i = 0; i<genelen-1; i++)
    {
        cout<<rem[i];
    }
    cout<<"\nTransmitted DataFrame: ";
    for(int i = 0; i<genelen-1; i++)
    {
        dataframe[datalen+i] = rem[i];
    }
    for(int i = 0; i<codelen; i++)
    {
        cout<<dataframe[i];
    }   
}

int main()
{
    int generator[50], genelen, n;
    do{
        cout<<"\n1.Set Generator(Necessary if not set previously).";
        cout<<"\n2.Generate Transmission Frame.";
        cout<<"\n3.Check received frame.";
        cout<<"\n4.Exit";
        cout<<"\nEnter the index of operation to be performed: ";
        cin>>n;
        switch(n)
        {
            case 1: 
            for(int i = 0; i<50; i++)
            {
                generator[i] =0;
            }
            cout<<"\nEnter the length of the generator: ";
            cin>>genelen;
            cout<<"\nEnter the generator bits: ";
            for(int i = 0; i<genelen; i++)
            {
                cout<<"\nEnter bit "<<(i+1)<<": ";
                cin>>generator[i];
            }
            break;
            case 2: sender_crc(generator, genelen);
            break;
            case 3: receiver_crc(generator, genelen);
            break;
            case 4: cout<<"\nBye";
            break;
            default: cout<<"\nInvalid Entry";
            break;
        }
    }while(n!=4);
    return 0;
}