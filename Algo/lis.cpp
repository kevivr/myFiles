set<int> st;
set<int>::iterator it;
...
st.clear();
for(i=0; i<n; i++)
{
st.insert(a[i]); it=st.find(a[i]);
it++; if(it!=st.end()) st.erase(it);
}
cout<<st.size()<<endl;
