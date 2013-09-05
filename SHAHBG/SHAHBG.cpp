#include <iostream>
using namespace std;
 
int main(){
    int n, i, j, g;
    g = 0;
    cin >> n;
    bool a[20001];
    for(int x = 0; x < 20001; x++){
        a[x] = false;
    }
    for(i = 0; i < n; i++){
        cin >> j;
        a[j] = true;
        if(j == 1){
            if(!a[2]){
                g++;
            }
        }else if(j == 20000){
            if(!a[19999]){
                g++;
            }
        }else{
            if(a[j-1] && a[j+1]){
                g--;
            }else if(!a[j-1] && !a[j+1]){
                g++;
            }
        }
        cout << g << endl;
    }
    cout << "Justice" << endl;
    return 0;
}
