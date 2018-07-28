#include <iostream>
#include <vector>
#include <string.h>
#include <stdio.h>

using namespace std;

const int MAXN = 209; 
const int INF = 1000000000; 

/*
Flow graph:
MAXN = similar as n, set it to maximum number of nodes a flow a can have.
n = number of nodes in the flow (n includes source, sink and extra nodes added for the flow).
s = source.
t = sink.
res_g[a][b] = contains the final flow between node a and b.
add_edge = add edges to the flow graph using this routine ( edge  from a to b with capacity cap).
a[i] = capacity from source to i.
b[i] = capacity from i to sink.

*/

/*
Normal graph:
N = number of normal nodes.
m = number of edges in the normal graph.
*/

struct edge {
    int a, b, cap, flow;
};

int N, m, n1, n2;
int n, s, t, d[MAXN], ptr[MAXN], q[MAXN];
int a[MAXN], b[MAXN];
int res_g[105][105];

vector<edge> e;
vector<int> g[MAXN];


void add_edge (int a, int b, int cap) {
    edge e1 = { a, b, cap, 0 };
    edge e2 = { b, a, 0, 0 };
    g[a].push_back ((int) e.size());
    e.push_back (e1);
    g[b].push_back ((int) e.size());
    e.push_back (e2);
}
 
bool bfs() {
    int qh=0, qt=0;
    q[qt++] = s;
    memset (d, -1, n * sizeof d[0]);
    d[s] = 0;
    while (qh < qt && d[t] == -1) {
        int v = q[qh++];
        for (size_t i=0; i<g[v].size(); ++i) {
            int id = g[v][i],
                to = e[id].b;
            if (d[to] == -1 && e[id].flow < e[id].cap) {
                q[qt++] = to;
                d[to] = d[v] + 1;
            }
        }
    }
    return d[t] != -1;
}
 
int dfs (int v, int flow) {
    if (!flow)  return 0;
    if (v == t)  return flow;
    for (; ptr[v]<(int)g[v].size(); ++ptr[v]) {
        int id = g[v][ptr[v]],
            to = e[id].b;
        if (d[to] != d[v] + 1)  continue;
        int pushed = dfs (to, min (flow, e[id].cap - e[id].flow));
        if (pushed) {
            e[id].flow += pushed;
            e[id^1].flow -= pushed;
            return pushed;
        }
    }
    return 0;
}
void make_flow_graph() { 
    for(int i=0;i<(int)e.size();i++) {
        int a=e[i].a,b=e[i].b,flow=e[i].flow;
        if(a!=s and a!=t and b!=s and b!=t and flow>0){
            if(a>N)a=a-N;
            if(b>N)b=b-N;
            res_g[a][b]=flow;
        }
    }
}

int dinic() {
    int flow = 0;
    for (;;) {
        if (!bfs())  break;
        memset (ptr, 0, n * sizeof ptr[0]);
        while (int pushed = dfs (s, INF))
            flow += pushed;
    }
    make_flow_graph();
    return flow;
}


/*
Input Format:

First line contains: N and m (number of nodes in normal graph and number of edges in normal graph)
Next line contains N space separated integers denoting capacity between source and i
Next line contains N space separated integers denoting capacity between i and sink

*/

int main(){
    int res1=0;
    int res2=0;

    cin>>N>>m;
    
    //assigning flow variables
    n=(2*N)+2,s=0,t=(2*N)+1;

    //input capacities for source
    for(int i=1;i<=N;i++){
        cin>>a[i];
        res1+=a[i];
        add_edge(s,i,a[i]);
        add_edge(i,N+i,INF);
    }
    //input capacities for sink
    for(int i=1;i<=N;i++){
        cin>>b[i];
        res2+=b[i];
        add_edge(N+i,t,b[i]);
    }

    //input edges in the graph
    for(int i=0;i<m;i++){
        cin>>n1>>n2;
        add_edge(n1,N+n2,INF);
        add_edge(n2,N+n1,INF);
    }


    if(res1!=res2){
        cout<<"NO\n";
        return 0;
    }
    int din=dinic();
    if(din!=res1){
        cout<<"NO\n";
        return 0;
    }
    cout<<"YES\n";
    for(int i=1;i<=N;i++){
        for(int j=1;j<=N;j++)
            cout<<res_g[i][j]<<" ";
        cout<<"\n";
    }

    return 0;
}
