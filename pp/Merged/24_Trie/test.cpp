#include <iostream>

using namespace std;

void fun(int a, int b) {
    cout << a << " " << b << endl;
    a = 15;
    b = 105;
    cout << a << " " << b << endl;
}

int main(int arc, char** argv) {
    int a = 10;
    int b = 100;

    cout << a << " " << b << endl;
    fun(a, b);
    cout << a << " " << b << endl;
}