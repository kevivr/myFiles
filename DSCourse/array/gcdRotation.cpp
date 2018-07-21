#include<iostream>
#include<stdio.h>

using namespace std;

int calculateGcd(int number1, int number2){
        return (number1%number2 == 0 ? number2 : calculateGcd(number2,number1%number2));
}

void rotateArray(int *array, int size, int gcd){
	int count = gcd;
	while(count > 0){
		int ite = gcd-count;
		cout<<"ite "<<ite<<endl;
		int temp = array[ite];
		cout<<temp<<endl;
		for(int i = ite; i < size; i=i+gcd){
			int temp2;
			int k = i+gcd<size ? (i+gcd) : (gcd-count);
			temp2 = temp;
			temp = array[k];
			array[k] = temp2;
			cout<<k<<" "<<array[k]<<endl;
		}
		count --;
	}			
}
int main()
{
	int n,d;
	cin>>n>>d;
	cout<<endl;
	int gcd = calculateGcd(n,d);
	int arr[n];
	for(int i=0;i<n;i++)
		cin>>arr[i];
	rotateArray(arr,n,gcd);	
	for(int i=0;i<n;i++)
		cout<<arr[i]<<" ";
	return 0;
}
