#include <map>
#include <set>
#include <string>
#include <vector>

#ifndef MINHEAP_H
#define MINHEAP_H

template <class T> class MinHeap {
public:
  MinHeap(int capacity);

  bool isEmpty();
  int size();
  void put(T key);
  T delMin();

private:
  std::vector<T> pq;
  int n;

  void swim(int k);
  void sink(int k);
  void exch(int i, int j);
};

#endif
