#include <iostream>

using namespace std;

int main(){
	int t;
	cin >> t;
	for(int x = 0; x < t; x++){
		int n1, n2, n;
		cin >> n1;
		cin >> n2;
		int max1 = -1;
		int max2 = -1;
		for(int y = 0; y < n1; y++){
			cin >> n;
			if(n > max1) max1 = n;
		}
		for(int z = 0; z < n2; z++){
			cin >> n;
			if(n > max2) max2 = n;
		}
		if(max1 >= max2) cout << "Godzilla" << endl;
		else cout << "MechaGodzilla" << endl;
		cout << endl;
	}
}
