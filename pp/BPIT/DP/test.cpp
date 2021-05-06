#include <iostream>
#include <vector>

using namespace std;

void fun(int &a, int b) {
    cout << "Inside func a = " <<  a << " b = " << b << endl;
    a = 50;
    b = 100;
    cout << "Inside func a = " << a << " b =" << b << endl;
}

void fun2(vector<int>* arr) {
    cout << "welcome in fun2" << endl;
}

int main(int argc, char** argv) {
    int a = 10;
    int b = 20;
    cout << "Inside main a = " << a << " b = " << b << endl;
    fun(a, b);
    cout << "Inside main a = " << a << " b = " << b << endl;

    // vectoer<int> arr(100000);
    vector<int>* arr = new vector<>(10000);
    fun2(&arr);
}