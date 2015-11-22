#include <iostream>
#include <cstdint>
using namespace std;

class GrowArray{
private:
	int* p;
	uint32_t size_;
public:
	GrowArray(){
		size_ = 0;
		p = nullptr;
	}
	~GrowArray(){
		delete[] p;
	}
	//copy constructor
	GrowArray(const GrowArray& orig){
	}
	// operator =
	GrowArray& operator =(const GrowArray& orig){
		return *this;
	}
	//move constructor
	GrowArray(const GrowArray&& orig){
	}

	void addStart(int v){
		int* temp = p;
		size_++;
		p = new int[size_];
		p[0] = v;
		for (int i = 1; i < size_; i++)
			p[i] = temp[i - 1];
		delete[] temp;
	}

	void addEnd(int v){
		int* temp = p;
		size_++;
		p = new int[size_];
		for (int i = 0; i < size_-1; i++)
			p[i] = temp[i];
		p[size_ - 1] = v;
		delete[] temp;
	}

	void deleteStart(){
		int* temp = p;
		size_--;
		p = new int[size_];
		for (int i = 0; i < size_; i++)
			p[i] = temp[i + 1];
		delete[] temp;
	}

	void deleteEnd(){
		int* temp = p;
		size_--;
		p = new int[size_];
		for (int i = 0; i < size_; i++)
			p[i] = temp[i];
		delete[] temp;
	}
	
	uint32_t size() const{
		return size_;
	}

	int get(int i) const{
		return p[i];
	}

	void set(int i, int v){
		p[i] = v;
	}

	friend ostream& operator <<(ostream& s, const GrowArray& a){
		s << '[';
		for (int i = 0; i < a.size(); i++)
			s << a.p[i] << ' ';
		s << ']';
		return s;
	}
};

int main(){
	GrowArray a;
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