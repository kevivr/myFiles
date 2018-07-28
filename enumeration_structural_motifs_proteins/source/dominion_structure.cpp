#include <bits/stdc++.h>

using namespace std;

class node
{
   bool color;

   public:

     node()
     {
         color=true;
     }

     node( bool f )
     {
           color=f;
      }
};

class edge
{
     int color;

     public:

            edge()
            {
                color=0;
            }

            edge( int a)
            {
                 color=a;
            }

};

class dominion_edge
{
     int color;
     int type;
     int dominion;
     public:

            dominion_edge()
            {
                color=0;
                type=-1;
                dominion=-1;
            }

            dominion_edge( int a,int b,int c )
            {
                 color=a;
                 type=b;
                 dominion=c;
            }

};

class dominion
{
    int degree_of_root;
    vector<dominion_edge> connecting_edges;

   public:

       dominion()
       {
           degree_of_root=0;
       }

       dominion(int a)
       {
           degree_of_root=a;
       }

       dominion(dominion_edge b)
       {
           connecting_edges.push_back(b);
       }

};


int main()
{
    return 0;
}
