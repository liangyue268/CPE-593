#include <iostream>
#include <cstdint>
#include <time.h>
using namespace std;

/**
* sieve of Eratosthenes algorithm
* Created by yue on 1/26/2015.
*/
class Bitvec {
private:
	uint64_t* bits;
	uint64_t  size;
public:
	Bitvec(uint64_t numBits) : size((numBits + 1 + 63) / 64) {
		bits = new uint64_t[size];
		for (uint64_t i = 0; i < size; i++)
			bits[i] = (uint64_t)~0ULL;
	}
	~Bitvec() {
		delete[] bits;
	}

	void set(uint64_t pos) {
		bits[pos >> 6] |= ((uint64_t)1ULL << (pos & 0x3F));
	}

	void clear(uint64_t pos) {
		bits[pos >> 6] &= ~((uint64_t)1ULL << (pos & 0x3F));
	}
	bool test(uint64_t pos) {
		return (bits[pos >> 6] & ((uint64_t)1ULL << (pos & 0x3F))) != 0;
	}
};

int main() {
	//set numbers
	uint64_t a, b;		
	cout << "Input a:";
	cin >> a;
	cout << "Input b:";
	cin >> b;

	clock_t start, end;
	start = clock();//start time

	//compute prime
	Bitvec prime(b);
	prime.clear(0);//0 is not a prime number
	prime.clear(1);//1 is not a prime number
	uint64_t range = (uint64_t)sqrt(b);
	for (uint64_t i = 4; i <= b; i += 2)
		prime.clear(i);
	for (uint64_t i = 3; i <= range; i++){
		if (prime.test(i)){
			for (uint64_t j = i*i; j <= b; j += 2 * i){
				//It is multiple of i
				prime.clear(j);
			}
		}
	}

	//count prime number
	uint64_t sum = 0;
	for (uint64_t i = a; i <= b; i++){
		if (prime.test(i)){
			sum += i;
		}
	}	
	cout << "Total prime numbers between " << a << " and " << b << " are " << sum << endl;	

	end = clock();
	cout << "Total Time: " << (double)(end - start) / CLOCKS_PER_SEC << "sec"<<endl;
	system("pause");
}