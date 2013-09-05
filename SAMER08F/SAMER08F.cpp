#include <iostream>
using namespace std;

int main(){
    int num;
    cin >> num;
    while(num != 0){
        int total = 0;
        for(int x = num; x >= 1; x--){
            total += x * x;
        }
        cout << total << endl;
        cin >> num;
    }
    return 0;
}
