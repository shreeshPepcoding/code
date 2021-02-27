#include <iostream>

using namespace std;

void rec(int n) {
    // if(n == 11000) return;
    cout << n << endl;
    rec(n + 1);
}

int main() {
    rec(1);
}