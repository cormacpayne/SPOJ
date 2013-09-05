#include <iostream>
using namespace std;

int main(){
    int num;
    cin >> num;
    for(int x = 0; x < num; x++){
        int n, m;
        cin >> n;
        cin >> m;

        if(m == n){
            if(m % 2 == 0){
                cout << "L" << endl;
            }else{
                cout << "R" << endl;
            }
        }else if(m < n){
            if(m % 2 == 0){
                cout << "U" << endl;
            }else{
                cout << "D" << endl;
            }
        }else{
            if(n % 2 == 0){
                cout << "L" << endl;
            }else{
                cout << "R" << endl;
            }
        }
    }
    return 0;
}
