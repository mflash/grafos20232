#include <iostream>
#include "minheap.h"

using namespace std;

int main()
{
  MinHeap<string> h(10);
  h.put("A");
  h.put("X");
  h.put("G");
  h.put("O");
  h.put("F");
  h.put("L");

  while (h.size() > 0)
  {
    cout << h.delMin() << endl;
  }
}