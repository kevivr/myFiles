import pprint
import scipy
import scipy.linalg 
from numpy import *
from pylab import *
from networkx import *
import copy

set_printoptions(threshold=nan)

   
def svd(a):
    '''Compute the singular value decomposition of array.'''

    # Golub and Reinsch state that eps should not be smaller than the
    # machine precision, ie the smallest number
    # for which 1+e>1.  tol should be beta/e where beta is the smallest
    # positive number representable in the computer.
    eps = 1.e-15  # assumes double precision
    tol = 1.e-64/eps
    assert 1.0+eps > 1.0 # if this fails, make eps bigger
    assert tol > 0.0     # if this fails, make tol bigger
    itmax = 50
    u = copy.deepcopy(a)
    m = len(a)
    n = len(a[0])
    #if __debug__: print 'a is ',m,' by ',n

    if m < n:
        if __debug__: print 'Error: m is less than n'
        raise ValueError,'SVD Error: m is less than n.'

    e = [0.0]*n  # allocate arrays
    q = [0.0]*n
    v = []
    for k in range(n): v.append([0.0]*n)
 
    # Householder's reduction to bidiagonal form

    g = 0.0
    x = 0.0

    for i in range(n):
        e[i] = g
        s = 0.0
        l = i+1
        for j in range(i,m): s += (u[j][i]*u[j][i])
        if s <= tol:
            g = 0.0
        else:
            f = u[i][i]
            if f < 0.0:
                g = math.sqrt(s)
            else:
                g = -math.sqrt(s)
            h = f*g-s
            u[i][i] = f-g
            for j in range(l,n):
                s = 0.0
                for k in range(i,m): s += u[k][i]*u[k][j]
                f = s/h
                for k in range(i,m): u[k][j] = u[k][j] + f*u[k][i]
        q[i] = g
        s = 0.0
        for j in range(l,n): s = s + u[i][j]*u[i][j]
        if s <= tol:
            g = 0.0
        else:
            f = u[i][i+1]
            if f < 0.0:
                g = math.sqrt(s)
            else:
                g = -math.sqrt(s)
            h = f*g - s
            u[i][i+1] = f-g
            for j in range(l,n): e[j] = u[i][j]/h
            for j in range(l,m):
                s=0.0
                for k in range(l,n): s = s+(u[j][k]*u[i][k])
                for k in range(l,n): u[j][k] = u[j][k]+(s*e[k])
        y = abs(q[i])+abs(e[i])
        if y>x: x=y
    # accumulation of right hand gtransformations
    for i in range(n-1,-1,-1):
        if g != 0.0:
            h = g*u[i][i+1]
            for j in range(l,n): v[j][i] = u[i][j]/h
            for j in range(l,n):
                s=0.0
                for k in range(l,n): s += (u[i][k]*v[k][j])
                for k in range(l,n): v[k][j] += (s*v[k][i])
        for j in range(l,n):
            v[i][j] = 0.0
            v[j][i] = 0.0
        v[i][i] = 1.0
        g = e[i]
        l = i
    #accumulation of left hand transformations
    for i in range(n-1,-1,-1):
        l = i+1
        g = q[i]
        for j in range(l,n): u[i][j] = 0.0
        if g != 0.0:
            h = u[i][i]*g
            for j in range(l,n):
                s=0.0
                for k in range(l,m): s += (u[k][i]*u[k][j])
                f = s/h
                for k in range(i,m): u[k][j] += (f*u[k][i])
            for j in range(i,m): u[j][i] = u[j][i]/g
        else:
            for j in range(i,m): u[j][i] = 0.0
        u[i][i] += 1.0
    #diagonalization of the bidiagonal form
    eps = eps*x
    for k in range(n-1,-1,-1):
        for iteration in range(itmax):
            # test f splitting
            for l in range(k,-1,-1):
                goto_test_f_convergence = False
                if abs(e[l]) <= eps:
                    # goto test f convergence
                    goto_test_f_convergence = True
                    break  # break out of l loop
                if abs(q[l-1]) <= eps:
                    # goto cancellation
                    break  # break out of l loop
            if not goto_test_f_convergence:
                #cancellation of e[l] if l>0
                c = 0.0
                s = 1.0
                l1 = l-1
                for i in range(l,k+1):
                    f = s*e[i]
                    e[i] = c*e[i]
                    if abs(f) <= eps:
                        #goto test f convergence
                        break
                    g = q[i]
                    h = pythag(f,g)
                    q[i] = h
                    c = g/h
                    s = -f/h
                    for j in range(m):
                        y = u[j][l1]
                        z = u[j][i]
                        u[j][l1] = y*c+z*s
                        u[j][i] = -y*s+z*c
            # test f convergence
            z = q[k]
            if l == k:
                # convergence
                if z<0.0:
                    #q[k] is made non-negative
                    q[k] = -z
                    for j in range(n):
                        v[j][k] = -v[j][k]
                break  # break out of iteration loop and move on to next k value
            if iteration >= itmax-1:
                if __debug__: print 'Error: no convergence.'
                # should this move on the the next k or exit with error??
                #raise ValueError,'SVD Error: No convergence.'  # exit the program with error
                break  # break out of iteration loop and move on to next k
            # shift from bottom 2x2 minor
            x = q[l]
            y = q[k-1]
            g = e[k-1]
            h = e[k]
            f = ((y-z)*(y+z)+(g-h)*(g+h))/(2.0*h*y)
            g = pythag(f,1.0)
            if f < 0:
                f = ((x-z)*(x+z)+h*(y/(f-g)-h))/x
            else:
                f = ((x-z)*(x+z)+h*(y/(f+g)-h))/x
            # next QR transformation
            c = 1.0
            s = 1.0
            for i in range(l+1,k+1):
                g = e[i]
                y = q[i]
                h = s*g
                g = c*g
                z = pythag(f,h)
                e[i-1] = z
                c = f/z
                s = h/z
                f = x*c+g*s
                g = -x*s+g*c
                h = y*s
                y = y*c
                for j in range(n):
                    x = v[j][i-1]
                    z = v[j][i]
                    v[j][i-1] = x*c+z*s
                    v[j][i] = -x*s+z*c
                z = pythag(f,h)
                q[i-1] = z
                c = f/z
                s = h/z
                f = c*g+s*y
                x = -s*g+c*y
                for j in range(m):
                    y = u[j][i-1]
                    z = u[j][i]
                    u[j][i-1] = y*c+z*s
                    u[j][i] = -y*s+z*c
            e[l] = 0.0
            e[k] = f
            q[k] = x
            # goto test f splitting
        
            
    #vt = transpose(v)
    #return (u,q,vt)
    return (u,q,v)

def pythag(a,b):
    absa = abs(a)
    absb = abs(b)
    if absa > absb: return absa*math.sqrt(1.0+(absb/absa)**2)
    else:
        if absb == 0.0: return 0.0
        else: return absb*math.sqrt(1.0+(absa/absb)**2)

def transpose(a):
    '''Compute the transpose of a matrix.'''
    m = len(a)
    n = len(a[0])
    at = []
    for i in range(n): at.append([0.0]*m)
    for i in range(m):
        for j in range(n):
            at[j][i]=a[i][j]
    return at

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

def mult_matrix(M, N):
    """Multiply square matrices of same dimension M and N"""
    # Converts N into a list of tuples of columns                                                                     
    tuple_N = zip(*N)

    # Nested list comprehension to calculate matrix multiplication                                                    
    return [[sum(el_m * el_n for el_m, el_n in zip(row_m, col_n)) for col_n in tuple_N] for row_m in M]

def trans_matrix(M):
    """Take the transpose of a matrix."""
    n = len(M)
    return [[ M[i][j] for i in range(n)] for j in range(n)]

def norm(x):
    """Return the Euclidean norm of the vector x."""
    return sqrt(sum([x_i**2 for x_i in x]))

def Q_i(Q_min, i, j, k):
    """Construct the Q_t matrix by left-top padding the matrix Q                                                      
    with elements from the identity matrix."""
    if i < k or j < k:
        return float(i == j)
    else:
        return Q_min[i-k][j-k]

def qr(A):
    """Performs a Householder Reflections based QR Decomposition of the                                               
    matrix A. The function returns Q, an orthogonal matrix and R, an                                                  
    upper triangular matrix such that A = QR."""
    n = len(A)

    # Set R equal to A, and create Q as a zero matrix of the same size
    R = A
    Q = [[0.0] * n for i in xrange(n)]

    # The Householder procedure
    for k in range(n-1):  # We don't perform the procedure on a 1x1 matrix, so we reduce the index by 1
        # Create identity matrix of same size as A                                                                    
        I = [[float(i == j) for i in xrange(n)] for j in xrange(n)]

        # Create the vectors x, e and the scalar alpha
        # Python does not have a sgn function, so we use cmp instead
        x = [row[k] for row in R[k:]]
        e = [row[k] for row in I[k:]]
        alpha = -cmp(x[0],0) * norm(x)

        # Using anonymous functions, we create u and v
        u = map(lambda p,q: p + alpha * q, x, e)
        norm_u = norm(u)
        v = map(lambda p: p/norm_u, u)

        # Create the Q minor matrix
        Q_min = [ [float(i==j) - 2.0 * v[i] * v[j] for i in xrange(n-k)] for j in xrange(n-k) ]

        # "Pad out" the Q minor matrix with elements from the identity
        Q_t = [[ Q_i(Q_min,i,j,k) for i in xrange(n)] for j in xrange(n)]

        # If this is the first run through, right multiply by A,
        # else right multiply by Q
        if k == 0:
            Q = Q_t
            R = mult_matrix(Q_t,A)
        else:
            Q = mult_matrix(Q_t,Q)
            R = mult_matrix(Q_t,R)

    # Since Q is defined as the product of transposes of Q_t,
    # we need to take the transpose upon returning it
    return trans_matrix(Q), R
    
a =  [[22.,10., 2.,  3., 7.],
      [14., 7.,10.,  0., 8.],
      [-1.,13.,-1.,-11., 3.],
      [-3.,-2.,13., -2., 4.],
      [ 9., 8., 1., -2., 4.],
      [ 9., 1.,-7.,  5.,-1.],
      [ 2.,-6., 6.,  5., 1.],
      [ 4., 5., 0., -2., 2.]]
      
Q, R = qr(a)

print "A:"
pprint.pprint(a)

print "Q:"
pprint.pprint(Q)

print "R:"
pprint.pprint(R)

c = copy.deepcopy(Q)
b = copy.deepcopy(R)

Q1, R1 = scipy.linalg.qr(c)

print "Q1:"
pprint.pprint(Q1)

print "R1:"
pprint.pprint(R1)

D = matrixmultiply(R1,b)
print "D:"
pprint.pprint(D)

U2, s, V = np.linalg.svd(D, full_matrices=True)

print "U2"
pprint.pprint(U2)

print "s:"
pprint.pprint(s)

print "V:"
pprint.pprint(V)

U = matrixmultiply(Q1,U2)

print "U:"
pprint.pprint(U)

savetxt('test.txt', U, delimiter=',')

