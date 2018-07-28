#include<bits/stdc++.h>

using namespace std;


/* 
input: For a given graph, we try to find all possible copies of a node for a given degree
output: Store all copies of a node of certain degree
*/

int degree;                                     //degree required for a node
vector<int> *AL;                                //Adjacency list of a graph
vector<vector<int> >dom_copy;                   //copies for a dominion
int node;                               
int num_nodes,num_edges;                        //number of nodes,number of edges

void print(vector<int> copy){
        for(int element=0;element<(int)copy.size();element++)cout<<copy[element]<<" ";
        cout<<"\n";
        return ;
}

void comb(vector<int> copy,int pos){            //arguments (copy found till the previous position and present position
        if((int)copy.size()==degree){           //if copy size equals to certain degree
                dom_copy.push_back(copy);       //storing the copy and returning back
                //print(copy);      
                return;
        }
        if(pos==(int)AL[node].size())return ;   //if out of limit return back
        comb(copy,pos+1);                       //skipping the element in the present position
        copy.push_back(AL[node][pos]);          //selecting the element in present position and sending the updated copy
        comb(copy,pos+1);                       
        return ;
}


int main(){
        cin>>degree;
        cin>>num_nodes>>num_edges;
        int a,b;
        AL=new vector<int> [num_nodes+2];
        for(int edges=0;edges<num_edges;edges++){
                cin>>a>>b;
                AL[a].push_back(b);             //in the list of node 'a', insert node 'b'
                AL[b].push_back(a);             //in the list of node 'b', insert node 'a'
        }
        for(node=1;node<=num_nodes;node++){
                if((int)AL[node].size()>=degree){//only if the degree of present node is sufficient to find a valid copy do
                        vector<int>copy;
                        comb(copy,0);           //starting from first element in the list of the node
                }
        }
        return 0;
}
