#include "minheap.h"

template <class T> MinHeap<T>::MinHeap(int capacity) {
  pq.set_capacity(capacity);
}

template <class T> bool MinHeap<T>::isEmpty() { return n == 0; }

template <class T> int MinHeap<T>::size() { return n; }

template <class T> void MinHeap<T>::put(T key) {}

template <class T> T MinHeap<T>::delMin() {
  if (isEmpty())
    throw "Heap vazio!";
  T min = pq[1];
  exch(1, n--);
  sink(1);
  pq[n + 1] = nullptr;
  // if ((n > 0) && (n == (pq.length - 1) / 4))
  //   resize(pq.length / 2);
  return min;
}

template <class T> void MinHeap<T>::swim(int k) {
  while (k > 1 && pq[k / 2] > pq[k]) {
    exch(k, k / 2);
    k = k / 2;
  }
}

template <class T> void MinHeap<T>::sink(int k) {
  while (2 * k <= n) {
    int j = 2 * k;
    if (j < n && pq[j] > pq[j + 1])
      j++;
    if (pq[k] <= pq[j])
      break;
    exch(k, j);
    k = j;
  }
}

template <class T> void MinHeap<T>::exch(int i, int j) {
  T swap = pq[i];
  pq[i] = pq[j];
  pq[j] = swap;
}
