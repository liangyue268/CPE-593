#include <cstdint> // C++11 Required
#include <iostream>
#include<string>
using namespace std;

class HashMap {
private:
	uint32_t size;
	uint32_t used;
	struct Node {
		string key;
		int    val;
		//    Node() : key() {}
	};
	Node* table;
	void grow() {
	}
	size_t hash(const string& s) {
		uint32_t sum = 0;
		for (int i = 0; i < s.size(); i++)
			sum = sum + (sum << 13) + (sum >> 17) + s[i];
		return sum & (size - 1);
	}
public:
	HashMap(size_t initialSize)
		: size(initialSize), used(0) {
		table = new Node[size];
	}
	HashMap(const HashMap& orig);
	HashMap& operator =(const HashMap& orig);
	~HashMap() {
		delete[] table;
	}

	// if key is already there replace the value
	void add(const string& key, int val) {
		uint32_t i = hash(key);
		while (table[i].key.length() != 0) {
			if (table[i].key == key) {
				table[i].val = val;
				return;
			}
			i = (i + 1) & (size - 1);
		}
		table[i].key = key;
		table[i].val = val;
	}
	int* get(const string& key) {
		uint32_t i = hash(key);
		while (table[i].key.length() != 0) {
			if (table[i].key == key) {
				return &table[i].val;
			}
			i = (i + 1) & (size - 1);
		}
		return nullptr;
	}

	class Iterator {
	private:
		Node* current; // if the hashmap grows, this is GARBAGE
		size_t size;
	public:
		Iterator(HashMap& m) {
			current = &m.table[-1];
			size = m.size;
		}

		bool hasNext() {
			current++;
			size--;
			while (current->key.length() == 0) {
				if (size == 0)
					return false;
				current++;
				size--;
			}
			return true;
		}
		string getKey() {
			return current->key;
		}
		int getValue() {
			return current->val;
		}
	};
	friend Iterator;

};

int main() {
	HashMap m(4);
	m.add("IBM", 106);
	m.add("BABA", 88);
	int* val = m.get("IBM");
	if (val != nullptr) {
		cout << *val << endl;
	}

	HashMap::Iterator i(m);
	while (i.hasNext()) {
		cout << i.getKey() << "==>" << i.getValue() << '\n';
	}
	system("pause");
}