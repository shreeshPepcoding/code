#include <iostream>
using namespace std;

void fun(int n) {
    cout << n << endl;
    fun(n + 1);
}

int main(int argc, char** argv) {
    int n = 1;
    fun(n);
}