#include <iostream>
#include <vector>
#include <algorithm>
using std::vector;
using std::cin;
using std::cout;
using std::swap;
using std::pair;
using std::make_pair;
using namespace std;
class HeapBuilder {
 private:
  vector<int> data_;
  vector< pair<int, int> > swaps_;
  void WriteResponse() const {
    cout << swaps_.size() << "\n";
    for (int i = 0; i < swaps_.size(); ++i) {
      cout << swaps_[i].first << " " << swaps_[i].second << "\n";
    }
  }
  void ReadData() {
    int n;
    cin >> n;
    data_.resize(n);
    for(int i = 0; i < n; ++i)
      cin >> data_[i];
  }

  void GenerateSwaps() {
    swaps_.clear();
    // The following naive implementation just sorts 
    // the given sequence using selection sort algorithm
    // and saves the resulting sequence of swaps.
    // This turns the given array into a heap, 
    // but in the worst case gives a quadratic number of swaps.
    //
    // TODO: replace by a more efficient implementation
    for (int i = 0; i < data_.size(); ++i)
      for (int j = i + 1; j < data_.size(); ++j) {
        if (data_[i] > data_[j]) {
          swap(data_[i], data_[j]);
          swaps_.push_back(make_pair(i, j));
        }
      }
  }

  int parent(int i){
    return (i/2);
  }

  int leftChild(int i){
    return ((2*i) +1);
  }

  int rightChild(int i){
    return ((2*i) +2);
  }

  void ShiftDown(int i){
    int x = i;
    int n = data_.size();
    int l = leftChild(i);
    if(l <= n-1 && data_[l] < data_[x]){
      x = l;
    }
    int r = rightChild(i);
    if(r <= n-1 && data_[r] < data_[x]){
      x = r;
    }
    if(x != i){
      swap(data_[x], data_[i]);
      swaps_.push_back(make_pair(i, x));
      ShiftDown(x);
    }
  } 

  void GenerateSwaps2() {
    int i =  data_.size()/2;
    while(i >= 0){
      ShiftDown(i);   
      i--;
    }
  }

 public:
  void Solve() {
    ReadData();
    swaps_.clear();
    GenerateSwaps2();
    WriteResponse();
  }
};

int main() {
  std::ios_base::sync_with_stdio(false);
  HeapBuilder heap_builder;
  heap_builder.Solve();
  return 0;
}
