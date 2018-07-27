	int n,w,m,wc[505][505],mc[505][505],men[505]={0},women[505]={0},check[505]={0};
        n = scanInt();
        for(int i = 0; i < n; i ++) {
            w = scanInt();
            int c = 1;
            for(int j = 1; j <= n; j++){
                m = scanInt();
                wc[w][m]=c++;
            }
        }
        for(int i = 0; i < n; i ++){
            m = scanInt();
            for(int j = 1; j <= n; j++){
                w = scanInt();
                mc[m][j]=w;
            }
        }
        int unmat = 1;
        int g;
        int flag = 1;
        while(1){
            int i;
            for(i = 1; i <= n; i ++){
                w = mc[unmat][i];
                m = women[w];
                if(!m){
                    men[unmat] = w;
                    women[w] = unmat;
                    check[unmat] = 1;
                    break;
                }
                if(wc[w][m] < wc[w][unmat])
                continue;
                else if(wc[w][m] == wc[w][unmat]){
                    check[unmat] = 1;
                    break;
                }
                if(wc[w][m] > wc[w][unmat]){
                    check[unmat] = 1;
                    int w_swap = men[unmat];
                    women[w] = unmat;
                    men[unmat] = w;
                    men[m] = 0;
                    women[w_swap] = 0;
                    unmat = m;
                    flag = 1;
                    check[unmat] = 0;
                    break;
                }
            }
            if(i == n+1 && !flag)
            check[unmat] = 1;
        if(!(unmat = check_valid(n,check))) break;
    }
        for(int i = 1; i <= n; i ++)
        	printf("%d %d\n",i,men[i]);

