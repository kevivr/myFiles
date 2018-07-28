struct node{
       int e;
       node *p[10];
           node()
           {
                 for(int i=0;i<10;i++)
                         p[i]=NULL; 
                  e=0;     
           }
       };
       
node root;

bool update(node *point,int pos){
     int t=s[pos]-'0';
     //cout<<pos<<" "<<t<<" "<<point->e<<"\n";
     if(point->e==1)return false;
     if(pos==s.size())
     {
                      point->e=1;
                      return true;
     }
     if(point->p[t]==NULL)point->p[t]=new node;
     return update(point->p[t],pos+1);
     }
     
void clear(node *point){
     for(int i=0;i<10;i++){
             if(point->p[i]!=NULL){
                               point->p[i]=NULL;
                               }
             }
     point->e=0;
     return ;
     }     
