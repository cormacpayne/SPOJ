#include <iostream>
using namespace std;

int main(){
    int kids;
    cin >> kids;
    while(kids != -1){
        int numCandies = 0;
        int list[kids];
        for(int i = 0; i < kids; i++){
            int candy;
            cin >> candy;
            numCandies += candy;
            list[i] = candy;
        }
        if(numCandies % kids == 0){
            int average = numCandies/kids;
            int answer = 0;
            for(int y = 0; y < kids; y++){
                if(list[y] < average){
                    answer += average - list[y];
                }
            }
            cout << answer << endl;
        }else{
            cout << "-1" << endl;
        }
        cin >> kids;
    }
    return 0;
}
