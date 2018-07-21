#include <iostream>
#include <stack>
#include <string>

using namespace std;

struct Bracket {
    Bracket(char type, int position):
        type(type),
        position(position)
    {}

    bool Matchc(char c) {
        if (type == '[' && c == ']')
            return true;
        if (type == '{' && c == '}')
            return true;
        if (type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
};

int main() {
    std::string text;
    getline(std::cin, text);

    std::stack <Bracket> opening_brackets_stack;
    for (int position = 0; position < text.length(); ++position) {
        char next = text[position];
	Bracket x(next,position);
        if (next == '(' || next == '[' || next == '{') {
		opening_brackets_stack.push(x);
        }

        if (next == ')' || next == ']' || next == '}') {
	     if(opening_brackets_stack.empty()!=true){
		if(opening_brackets_stack.top().Matchc(next))
		{
			opening_brackets_stack.pop();
		}
		else{
			cout<<position+1<<"\n";
			return 0;
		}
	     }
	      else{
			cout<<position+1<<"\n";
			return 0;
	    }
        }
	
    }

      if(!opening_brackets_stack.empty()){
	cout<<opening_brackets_stack.top().position+1<<"\n";
      }
      else{
	cout<<"Success\n";
      }
/*    while(!opening_brackets_stack.empty()){
	cout<<" "<<opening_brackets_stack.top().type;
	opening_brackets_stack.pop();
    }
 */
   return 0;
}
