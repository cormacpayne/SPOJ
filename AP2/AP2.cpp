#include <iostream>
#include <string>

using namespace std;

int main(){
	int t;
	long long int a3, an2, sum, n, d, num;
	cin >> t;
	for(int x = 0; x < t; x++){
		cin >> a3;
		cin >> an2;
		cin >> sum;
		n = (2*sum)/(a3+an2);
		d = (2*sum-2*a3*n)/(n*(n-5));
		num = a3-2*d;
		cout << n << endl;
		cout << num;
		for(int y = 1; y < (int)n; y++){
			num += d;
			cout << " " << num;
		}
		cout << endl;
	}
	return 0;
}
