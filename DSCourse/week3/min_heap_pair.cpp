#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#include<assert.h>

using namespace std;

int main(){
	int m,n;
	vector<long long int> arr;
	priority_queue <pair<long long int,int>,vector<pair<long long int,int> >,greater<pair<long long int,int> > > qu;
	cin>>m>>n;
//	assert(n>=m);
	arr.resize(n);
	for(int i=0;i<n;i++)
		cin>>arr[i];
	int count = 0;
	if(m<n){
	while(count < m){
		cout<<count<<" 0"<<"\n";	
		qu.push(make_pair(arr[count],count));
		count++;
	}
	while(count < n){
		pair<long long int,int> temp = qu.top();
		long long int next_start_time = temp.first;
		int free_thread = temp.second;
		cout<<free_thread<<" "<<next_start_time<<"\n";
		qu.pop();	
		qu.push(make_pair(arr[count]+next_start_time,free_thread));
		count++;
	}
	}
	else{
	while(count < n){
		cout<<count<<" 0"<<"\n";
		count++;
	}
	}	
	return 0;
}

