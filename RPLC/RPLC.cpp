#include <iostream>
using namespace std;

int main(){
    int num, bottles, n;
    cin >> num;
    for(int x = 1; x <= num; x++){
        cin >> bottles;
        long long int life = 1;
        long long int total = 0;
        for(int y = 0; y < bottles; y++){
            cin >> n;
            total += n;
            if(total < 0){
                life += total * -1;
                total = 0;
            }
        }
        cout << "Scenario #" << x << ": " << life << endl;
        cout << endl;
    }
    return 0;
}
