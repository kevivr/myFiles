#include<iostream>
#include<stdio.h>

using namespace std;

int gcd2(int number1, int number2){
	return (number1%number2 == 0 ? number2 : gcd2(number2,number1%number2));
}

int gcd(int number1, int number2){
	int remainder = number1%number2;
	if(remainder == 0)
		return number2;
	else
		return gcd(number2, remainder);
}

int main(){
	int number1, number2;
	cout<<"Enter 2 numbers\n";
	cin>>number1>>number2;
	int result;
	result = number1 > number2 ? gcd2(number1,number2) : gcd2(number2,number1);
	cout<<"GCD of the two numbers is "<<result<<endl;	
	return 0;
}
