#include <iostream>
#include "unionfind.h"

using namespace std;

int main()
{
    UnionFind uf;
    uf.addSet("1");
    uf.addSet("2");
    uf.addSet("3");
    uf.addSet("4");
    uf.addSet("5");
    uf.addSet("6");

    cout << "start" << endl;

    cout << "conj. 5:" << uf.find("5") << endl;
    cout << endl;
    cout << "Unindo 1-3, 1-5, e 5-6" << endl;
    uf.join("1", "3");
    uf.join("1", "5");
    uf.join("5", "6");
    cout << "conj. 3: " << uf.find("3") << endl;
    cout << "conj. 5: " << uf.find("5") << endl;
    cout << "conj. 6: " << uf.find("6") << endl;
}
