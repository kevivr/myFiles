#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>

using namespace std;

class disjoint_set{
	vector<int> rank;
	vector<int> parent;
	int m;
	int n;
	vector <pair<int,int> >merge;
	priority_queue<int> find_max;	
	public:
	void getData(){
		cin>>n>>m;
		parent.resize(n);
		rank.resize(n);
		for(int i=0;i<n;i++){
			parent[i] = i;
			cin>>rank[i];
			find_max.push(rank[i]);
		}
		for(int i=0;i<m;i++){
			int x,y;
			cin>>x>>y;
			merge.push_back(make_pair(x,y));
		}
	}
	int find(int x){
		int root,y;
		int old = x;
		while(parent[x] != x){
			x = parent[x];
		}
		root = x;
		x = old;
		y = x;
		while(parent[y] != root){
			y = parent[x];
			parent[x] = root;
			x = y;
		}
		return root;
	}
	void union_merge(int x,int y){
		int a = find(x);
		int b = find(y);
		if(a == b){
			return;
		}
		if(rank[a] > rank[b]){
			parent[b] = a;
			rank[a] = rank[a]+rank[b];
			rank[b] = 1;
			find_max.push(rank[a]);
		}
		else{
			parent[a] = b;
			rank[b] = rank[b]+rank[a];
			rank[a] = 1;
			find_max.push(rank[b]);
		}
	}	
	void process_input(){
		for(int i=0;i<m;i++){
                      int x,y;
                      x = merge[i].first;
            	      y = merge[i].second;
                      union_merge(x-1,y-1);
		      cout<<find_max.top()<<endl;
		}
        }
	
				
};

int main(){
	disjoint_set d1;
	d1.getData();
	d1.process_input();
	return 0;
}	

