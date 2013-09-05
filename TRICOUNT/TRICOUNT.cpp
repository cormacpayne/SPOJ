#include <iostream>
using namespace std;

int main(){
    int n;
    cin >> n;
    for(int x = 0; x < n; x++){
        long long num;
        cin >> num;
        cout << (num * (num + 2) * (2*num + 1))/8 << endl;
    }
    return 0;
}
