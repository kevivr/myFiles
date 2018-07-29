from networkx import *
from numpy import *
from pylab import *
import scipy
import scipy.linalg
import copy
import pprint
set_printoptions(threshold=nan)

def matrixmultiply(a,b):
    '''Multiply two matrices.
    a must be two dimensional
    b can be one or two dimensional.'''
    
    am = len(a)
    bm = len(b)
    an = len(a[0])
    try:
        bn = len(b[0])
    except TypeError:
        bn = 1
    if an != bm:
        raise ValueError, 'matrixmultiply error: array sizes do not match.'
    cm = am
    cn = bn
    if bn == 1:
        c = [0.0]*cm
    else:
        c = []
        for k in range(cm): c.append([0.0]*cn)
    for i in range(cm):
        for j in range(cn):
            for k in range(an):
                if bn == 1:
                    c[i] += a[i][k]*b[k]
                else:
                    c[i][j] += a[i][k]*b[k][j]
    
    return c

#reading the dataset from the file in gml format
G=karate_club_graph() 

#converting the graph (in networkx format) to numpy matrix format
#This is equivalent to obtaining the adjacency matrix of the graph
A=nx.to_numpy_matrix(G)

#obtaining the dictionary of (node_key : degree) of the graph
degree_dict=G.degree()

#obtaining the list of degrees only
degree_list_values=degree_dict.values()

#sum of the degrees of all the nodes in the graph		
two_m=sum(degree_list_values)

#jth element of the ith column in degree_mat stores the product of the degrees of ith node and jth node
degree_mat = np.array([[degree_list_values[i]*degree_list_values[j] for j in range(len(degree_list_values))] for i in range(len(degree_list_values))])


#elements of degree_mat_2m stores the corresponding elements of degree_mat each element divided by 2m (sum of the degree of all nodes)
degree_mat_2m=degree_mat/float(two_m)


modularity_mat = np.array([[A.item((i,j))-degree_mat_2m.item((i,j)) for j in range(A.shape[1])] for i in range(A.shape[0])])
print "Modularity Matrix"
print modularity_mat

M,N=linalg.eigh(modularity_mat)
print(sort(M))
savetxt('test2.txt', N, delimiter=',')

Q, R = scipy.linalg.qr(modularity_mat)

"""print "A:"
pprint.pprint(a)

print "Q:"
pprint.pprint(Q)

print "R:"
pprint.pprint(R)
"""
c = copy.deepcopy(Q)
b = copy.deepcopy(R)

Q1, R1 = scipy.linalg.qr(c)
"""
print "Q1:"
pprint.pprint(Q1)

print "R1:"
pprint.pprint(R1)
"""
D = matrixmultiply(R1,b)
"""print "D:"
pprint.pprint(D)
"""
U2, s, V = np.linalg.svd(D, full_matrices=True)
"""
print "U2"
pprint.pprint(U2)
"""
print "s:"
pprint.pprint(sort(s))

"""print "V:"
pprint.pprint(V)"""

U = matrixmultiply(Q1,U2)

"""print "U:"
pprint.pprint(U)"""

savetxt('test1.txt', U, delimiter=',')


