int BG=1000000000;
// to avoid overflow in addition, do not use 2^31-1
A[1][i][j] = r[i][j]; p[1][i][j]=j;
for(t=2; t<=n; t++)
for(i=0; i<n; i++) for(j=0; j<n; j++)
{
A[t][i][j]=BG; p[t][i][j]=-1;
for(k=0; k<n; k++) if(A[1][i][k]<BG && A[t-1][k][j]<BG)
if(A[1][i][k]+A[t-1][k][j] < A[t][i][j])
{
A[t][i][j] = A[1][i][k]+A[t-1][k][j];
p[t][i][j] = k;
}
}
