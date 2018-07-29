from networkx import *
from numpy import *
from pylab import *
set_printoptions(threshold=nan)
def laplacian_matrix(G, nodelist=None, weight='weight'):
    if nodelist is None:
        nodelist=G.nodes()
    	edgelist=G.edges()
    n=len(nodelist)
    m=len(edgelist)
    index=dict( (n,i) for i,n in enumerate(nodelist) )
    L = zeros((n,n))
    for ui,u in enumerate(nodelist):
        totalwt=0.0
        for v,d in G[u].items():
            try:
                vi=index[v]
            except KeyError:
                continue
            wt=d.get(weight,1)
            L[ui,vi]= -wt*(2.0/((2.0*m)-1))
            totalwt+=wt
        L[ui,ui]= totalwt*(2.0/((2.0*m)-1))
    return L
G=read_gml('astro-ph.gml')
Lap_matrix=laplacian_matrix(G)
M=linalg.eigvals(Lap_matrix)
print(sort(M))

L=asarray(Lap_matrix)
eigenvalues,eigenvectors=linalg.eig(L)
index=argsort(eigenvalues)[1:1+1]
k=eigenvectors[:,index]

print k
