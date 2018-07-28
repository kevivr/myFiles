#include <bits/stdc++.h>
#define N 100001
using namespace std;

/*
input: undirected tree
output: tree divided into dominions as per the rules and all different dominions stored in dom_Set with its root
*/

int Visited[N];
vector<int> *AL,*RAL;                                                           //Adjacency list(undirected) and Rebuild Adjacency list(directed)
int *C,*D,*E,*parent,*star;                                                     //star[i] gives whether that node is included
                                                                                //parent[i] gives the parent of a node in a directed tree 
int ROOT,num_nodes;
vector<pair<int,vector<int> > >dom_set;                                         //dom_set stores the root with its leaves in each dominion

void rebuild(int node){                                                         //arguments: a node as an argument
       Visited[node]=1;                                                         //if that node is not visited, visit the node
       for(int i=0;i<(int)AL[node].size();i++)                                  //for all adjacent elements of the present node, if not visited 
                                                                                //they become the children of this node.
               if(!Visited[AL[node][i]])
                    RAL[node].push_back(AL[node][i]),parent[AL[node][i]]=node;
       for(int i=0;i<(int)RAL[node].size();i++)                                 //for all children of the present node, repeat the same by 
                                                                                //the same function again, each children as an argument
               rebuild(RAL[node][i]);
}

void doit(int node){                                                            
       D[node]=1;
       int val=0;
       for(int i=0;i<(int)RAL[node].size();i++){
               doit(RAL[node][i]);
               D[node]+=E[RAL[node][i]];
               val+=C[RAL[node][i]];
       }
       E[node]=min(D[node],val);
       int min_val=D[node];
       for(int i=0;i<(int)RAL[node].size();i++){
               min_val=min(val-C[RAL[node][i]]+D[RAL[node][i]],min_val);
       }
       C[node]=min_val;
       if(C[node]==D[node] and D[node]==E[node]){                               //for the last included node in the dominion
               vector<int> dom;
               int str=0;
               star[node]=1;                                                    //select that as included
               for(int i=0;i<(int)RAL[node].size();i++)                         //for all children of that node which belong to this dominion,
                                                                                //push in to a container 'dom'
                       if(!star[RAL[node][i]])dom.push_back(RAL[node][i]);
               if(parent[node]){                                                //if it has a parent and if all the children of its parent belong 
                                                                                //to some dominion, then select the parent even.
                       for(int i=0;i<(int)RAL[parent[node]].size();i++)
                               if(star[RAL[parent[node]][i]])str++;
                       if(str==(int)RAL[parent[node]].size())
                               dom.push_back(parent[node]),star[parent[node]]=1;
                       dom_set.push_back(make_pair(node,dom));                  //insert into dom_set as root and dom by pairing them.
               }
               if(1){
                       cout<<node<<": ";
                       for(int i=0;i<(int)dom.size();i++)cout<<dom[i]<<" ";
                       cout<<"\n";
               }
               
       }
       return ;
}

int main(){
    int a,b;
    scanf("%d", &num_nodes);
    AL = new vector<int>[num_nodes+1];
    for(int i=0;i<num_nodes-1;i++){
        scanf("%d%d", &a,&b);
        AL[a].push_back(b);
        AL[b].push_back(a);
    }
    C=new int[num_nodes+1];
    D=new int[num_nodes+1];
    E=new int[num_nodes+1];
    parent=new int[num_nodes+1];
    star=new int[num_nodes+1];
    ROOT = 1;
    RAL  = new vector<int>[num_nodes+1];
    memset(Visited,0,sizeof Visited);
    rebuild(ROOT);
    doit(ROOT);
    return 0;
}
