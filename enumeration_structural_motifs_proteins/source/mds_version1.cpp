#include <bits/stdc++.h>
#define N 100001
using namespace std;

int Visited[N];
vector<int> *AL,*RAL;
int *C,*D,*E;
int ROOT,n;
int min_dom=INT_MAX;
int min_root=1;

void rebuild(int node){
       Visited[node]=1;
       for(int i=0;i<(int)AL[node].size();i++)
               if(!Visited[AL[node][i]])
                    RAL[node].push_back(AL[node][i]);
       for(int i=0;i<(int)RAL[node].size();i++)
               rebuild(RAL[node][i]);
}

void debug(){
    cout<<"Root: "<<ROOT<<"\n";
    for(int i=1;i<=n;i++){
       cout<<i<<"-->\n";
       for(int j=0;j<(int)RAL[i].size();j++){
               cout<<RAL[i][j]<<"\n";
       }
       cout<<"---------------------------\n";
    }
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
       return ;
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
    C=new int[n+1];
    D=new int[n+1];
    E=new int[n+1];
    for(int i=1;i<=n;i++){
           ROOT = i;
           RAL  = new vector<int>[n+1];
           memset(Visited,0,sizeof Visited);
           rebuild(ROOT);
           doit(ROOT);
           cout<<ROOT<<" "<<C[ROOT]<<"\n";
           if(min_dom>C[ROOT]){
                   min_dom=C[ROOT];
                   min_root=ROOT;
           }
           delete []RAL;
    }
    return 0;
}
