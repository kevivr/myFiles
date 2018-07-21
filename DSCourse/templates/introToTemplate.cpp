#include<iostream>

using namespace std;

template <typename T>

T square(T x){
	return x*x;
}

template <typename T>
class myVector{
  	T arr[1000];
	int size;
	public:
		myVector():size(-1){}
		void push(T x){arr[++size] = x;}
		T get(int i){return arr[i];}
		int getSize(){return size;}
		void print(){for(int i=0; i<=size; i++){cout<<arr[i]<<endl;}}	
};

template <typename T>
myVector<T> operator *(myVector<T>& a, myVector<T>& b){
	myVector<T> c;
	for(int i=0; i<=a.getSize(); i++){
		c.push(a.get(i)*b.get(i));
	}
	return c;
}

int main(){
	cout<<square(5)<<" ";
	cout<<square(5.5)<<" ";
	myVector<int> x;
	x.push(12);
	x.push(3);
	x.push(5);
	x.print();
	
	x = square(x);
	x.print();
	return 0;
}
