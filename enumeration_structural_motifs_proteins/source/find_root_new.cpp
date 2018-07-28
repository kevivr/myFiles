#include <bits/stdc++.h>
#define N 101
using namespace std;
 
int Visited[100001];
int max0[100001];
int max1[100001];
vector<int> *AL,*RAL;
int ROOT,n;

void compute(int v){
	int i,j,r;
	vector <int> & k = AL[v];
	r=0;
	Visited[v] = 1;
	for(i=0;i<(int)k.size();i++){
		j = k[i];
		if(Visited[j]==0){ 
			compute(j);		
			r=max(max1[j],max0[j])+1;
			if(max0[v]<r){
				max1[v]=max0[v];
				max0[v]=r;
			}
			else if(max1[v]<r)
				max1[v]=r;
		}
	}
	return ;
}
 
int find_root(){
	int max_sum=0;
	int root;
	int root_degree=0;
	int root_diff=INT_MAX;
	for(int i=1;i<=n;i++){
	        RAL = new vector<int>[n+1];
	        rebuild(i);
	        compute(i);
	        memset(Visited,0,sizeof Visited);
	        delete []RAL;
                int s=(max1[i]+max0[i]);
                int diff=abs(max1[i]-max0[i]);
                if(s>max_sum or (s==max_sum and (diff<root_diff or (diff==root_diff and root_degree<(int)AL[i].size())))){
                        root=i;
                        root_degree=AL[i].size();
                        max_sum=s;
                        root_diff=abs(max1[i]-max0[i]);
                }       
	}
        return root;
}
 
 
void rebuild(int node){
        Visited[node]=1;
        for(int i=0;i<(int)AL[node].size();i++){
                if(!Visited[AL[node][i]])RAL[node].push_back(AL[node][i]);
        }
        for(int i=0;i<(int)RAL[node].size();i++){
                rebuild(RAL[node][i]);
        }
}

void debug(){
       	cout<<"Root: "<<ROOT<<"\n";
        for(int i=1;i<=n;i++){
	        cout<<i<<"-->  ";
	        for(int j=0;j<(int)RAL[i].size();j++){
	                cout<<RAL[i][j]<<" ";
	        }
	        cout<<"\n";
	}
}

int main(){
	int a,b;
	scanf("%d", &n);
	AL = new vector<int>[n+1];
	for(int i=0;i<n-1;i++){
		scanf("%d%d", &a,&b);
		AL[a].push_back(b);
		AL[b].push_back(a);
	}	
	ROOT=find_root();
	cout<<ROOT<<"\n";
	debug();
	delete []AL;
	return 0;
}
