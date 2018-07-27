vector<int> z_func(string str){
    vector<int> z(str.size());
    int L=0,R=0;
    int n = str.size();
    for(int i=1;i<n;i++){
        if(i>R){
            L=R=i;
            while(R<n and str[R-L]==str[R])R++;
            z[i]=R-L;
            R--;
        }else{
            int k = i-L;
            if(z[k] < R - i + 1)
                z[i] = z[k];
            else{
                L=i;
                while(R<n and str[R-L]==str[R])R++;
                z[i]=R-L;
                R--;
            }
        }
    }
    return z;
}
