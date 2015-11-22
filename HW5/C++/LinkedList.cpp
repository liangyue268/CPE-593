#include <iostream>
#include <cstdint>
using namespace std;

class LinkedList{
private:
	class Node{
	public:
		int v;
		Node* next;
	};
	Node* head;
public:
	LinkedList(){
		head = nullptr;
	}
	~LinkedList(){
	}

	void addEnd(int v){
		Node* temp = new Node();
		temp->v = v;
		temp->next = nullptr;
		if (head == nullptr){
			head = temp;
			return;
		}
		//
		Node*  p = head;
		while (p->next != nullptr)
			p = p->next;
		p->next = temp;
	}

	void addStart(int v){
		Node* temp = new Node();
		temp->v = v;
		temp->next = head;
		head = temp;
	}

	void add(int v){
		addStart(v);
	}

	void deleteEnd(){
		Node* temp = head;
		if (head == nullptr)
			return;
		if (head->next == nullptr){
			delete head;
			head = nullptr;
			return;
		}
		while (temp->next->next != nullptr)
			temp = temp->next;
		delete temp->next;
		temp->next = nullptr;
	}

	void deleteStart(){
		if (head == nullptr)
			return;
		Node* temp = head;
		Node* temp2 = head->next;
		head = temp2;
		delete temp;
	}
	int size(){
		
		if (head == nullptr)
			return 0;
		int count = 1;
		Node* temp = head;
		while (temp->next != nullptr){
			temp = temp->next;
			count++;
		}
		return count;
	}

	int get(int i){
		Node* temp = head;
		for (int j = 1; j <= i; j++){
			temp = temp->next;
		}
		return temp->v;
	}

	friend ostream& operator <<(ostream& s, LinkedList& a) {
		s << '[';
		for (int i = 0; i < a.size(); i++)
			s << a.get(i) << ' ';
		s << ']';
		return s;
	}
};

int main(){
	LinkedList a;
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