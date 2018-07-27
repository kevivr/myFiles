long long C(int n, int r){
    Matrix<long long> M(r+1,r+1);
    for (int i=0;i<(r+1)*(r+1);i++)
        M.data[i]=0;
    M.data[0]=1;
    for (int i=1;i<r+1;i++){
        M.data[i*(r+1)+i-1]=1;
        M.data[i*(r+1)+i]=1;
    }
    return (M^n).data[r*(r+1)];
}
