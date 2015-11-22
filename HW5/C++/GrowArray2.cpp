#include <iostream>
#include <cstdint>
using namespace std;

class GrowArray2{
private:
	int* p;
	uint32_t size_;
	uint32_t used_;
public:
	GrowArray2(){
		p = new int[10];
		size_ = 10;
		used_ = 0;
	}
	~GrowArray2(){
		delete[] p;
	}
	void grow(){
		size_ = 2 * size_;
		int* temp = p;
		p = new int[size_];
		for (int i = 0; i < size_ / 2; i++)
			p[i] = temp[i];
	}

	//copy constructor
	GrowArray2(const GrowArray2& orig){
	}
	// operator =
	GrowArray2& operator =(const GrowArray2& orig){
		return *this;
	}
	//move constructor
	GrowArray2(const GrowArray2&& orig){
	}

	void addStart(int v){
		if (used_ == size_)
			grow();
		used_++;
		for (int i = used_ - 1; i > 0; i--){
			p[i] = p[i - 1];
		}	
		p[0] = v;
	}

	void addEnd(int v){
		if (used_ == size_)
			grow();
		p[used_++] = v;
	}

	void deleteStart(){
		int* temp = p;
		used_--;
		p = new int[used_];
		for (int i = 0; i < used_; i++)
			p[i] = temp[i + 1];
		delete[] temp;
	}

	void deleteEnd(){
		used_--;
	}

	uint32_t size() const{
		return used_;
	}

	int get(int i) const{
		return p[i];
	}

	void set(int i, int v){
		p[i] = v;
	}

	friend ostream& operator <<(ostream& s, const GrowArray2& a){
		s << '[';
		for (int i = 0; i < a.size(); i++)
			s << a.p[i] << ' ';
		s << ']';
		return s;
	}
};

int main(){
	GrowArray2 a;
	cout << a << '\n';
	a.addEnd(5);
	a.addStart(1);
	a.addStart(4);
	a.addStart(1);
	a.addStart(3);
	cout << a << '\n';
	a.deleteEnd();
	cout << a << '\n';
	a.deleteStart();
	cout << a << '\n';
	int sum = 0;
	for (int i = 0; i < a.size(); i++)
		sum += a.get(i);
	cout << "sum=" << sum << '\n';
	system("pause");
}