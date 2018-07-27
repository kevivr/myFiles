typedef vector<int> VI;

void buildTable(string& w, VI& t){
  t = VI(w.length());  
  int i = 2, j = 0;
  t[0] = -1; t[1] = 0;
  
  while(i < w.length()){ 
   if(w[i-1] == w[j]) { t[i] = j+1; i++; j++; }
    else if(j > 0) j = t[j];
    else { t[i] = 0; i++; }
  }
}

VI KMP(string& s, string& w){
  int m = 0, i = 0;
  VI t;
  VI resultSet;
  buildTable(w, t);  
  while(m+i < s.length()){
   if(w[i] == s[m+i]){
      i++;
      if(i == w.length()){
        resultSet.push_back(m);
        m++;
        i = 0;
      }
    }
    else{
      m += i-t[i];
      if(i > 0) i = t[i];
    }
  }  
  return resultSet();
}
