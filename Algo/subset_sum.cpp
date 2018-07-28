int m[M+10];
for(i=0; i<M+10; i++) m[i]=0;
m[0]=1;
for(i=0; i<n; i++)
(single supply)for(j=M; j>=a[i]; j--) or (multiple supply)for(j=a[i];j<=K; j++)
m[j] |= m[j-a[i]];
