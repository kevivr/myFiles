#include<iostream>
#include<stdio.h>

using namespace std;

void reverse(int *array, int start_index, int end_index){
	while(start_index < end_index){
		int temp = array[start_index];
		array[start_index] = array[end_index];
		array[end_index] = temp;
		start_index++;
		end_index--;
	}
}
		
int main(){
	int n,d;
	cin>>n>>d;
	int arr[n];
	for(int i=0;i<n;i++)
		cin>>arr[i];
	reverse(arr,0,d-1);
	reverse(arr,d,n-1);
	reverse(arr,0,n-1);
	for(int i=0;i<n;i++)
		cout<<arr[i]<<" ";
	return 0;
}
