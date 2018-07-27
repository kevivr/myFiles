int mancher(){
    int mx = 0, id, i, ans = 0;
    memset(p, 0, sizeof(p));
    for (i = 1; i < m; i++){
        if (mx > i){
	    p[i] = min(p[2 * id - i], mx - i);
	} else {
	    p[i] = 1;
	}
	for ( ; str2[i - p[i]] == str2[i + p[i]]; p[i]++);
	if (i + p[i] > mx){
	    mx = i + p[i];
	    id = i;
	}
    }
    for (i = 0; i < m; i++){
	ans = max(ans, p[i]);
    }
    ans--;
    return ans;
}
