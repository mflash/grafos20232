#include <map>
#include <set>
#include <string>
#include <vector>
#include <iostream>

#ifndef MINHEAP_H
#define MINHEAP_H

template <class T>
class MinHeap
{
public:
  MinHeap(int capacity);

  bool isEmpty();
  int size();
  void put(T key);
  T delMin();
  void show();

private:
  std::vector<T> pq;
  size_t n;

  void swim(size_t k);
  void sink(size_t k);
  void exch(size_t i, size_t j);
};

template <class T>
MinHeap<T>::MinHeap(int capacity)
{
  pq.resize(capacity);
}

template <class T>
bool MinHeap<T>::isEmpty() { return n == 0; }

template <class T>
int MinHeap<T>::size() { return n; }

template <class T>
void MinHeap<T>::show()
{
  for (size_t i = 1; i <= n; i++)
    std::cout << pq[i] << " ";
  std::cout << std::endl;
}

template <class T>
void MinHeap<T>::put(T key)
{
  // duplica o tamanho do array se necessário
  if (n == pq.size() - 1)
    pq.resize(2 * pq.size());

  // adiciona key e faz swim para manter propriedade de ordem parcial
  pq[++n] = key;
  swim(n);
}

template <class T>
T MinHeap<T>::delMin()
{
  if (isEmpty())
    throw "Heap vazio!";
  T min = pq[1];
  exch(1, n--);
  sink(1);
  if ((n > 0) && (n == (pq.size() - 1) / 4))
    pq.resize(pq.size() / 2);
  return min;
}

template <class T>
void MinHeap<T>::swim(size_t k)
{
  while (k > 1 && pq[k / 2] > pq[k])
  {
    exch(k, k / 2);
    k = k / 2;
  }
}

template <class T>
void MinHeap<T>::sink(size_t k)
{
  while (2 * k <= n)
  {
    size_t j = 2 * k;
    if (j < n && pq[j] > pq[j + 1])
      j++;
    if (pq[k] <= pq[j])
      break;
    exch(k, j);
    k = j;
  }
}

template <class T>
void MinHeap<T>::exch(size_t i, size_t j)
{
  T swap = pq[i];
  pq[i] = pq[j];
  pq[j] = swap;
}

#endif
