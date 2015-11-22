#include<iostream>
#include<fstream>
#include<string>
#include<cstdint>
using namespace std;

int kmp(const string& search, const string& target){
	uint32_t offset[256];
	uint32_t m = target.length();
	for (int i = 0; i < 256; i++){
		offset[i] = m;
	}
	for (int i = 0; i < target.length(); i++){
		offset[(unsigned char)target[i]] = m - (i + 1);
	}
	for (int i = 0; i < 256; i++){
		if (offset[i] != m){
			cout << i << ":" << offset[i] << '\n';
		}
	}
	uint32_t pos = m;
	int jump = offset[search[pos]];
	if (jump == 0){
		cout << "possible found";
	}
	else{
		pos = pos + jump;
	}
	return -1;
}

int main(){
	ifstream f("aaa.txt");
	string target = "peace";
	string line;
	while (!f.eof()){
		getline(f, line);
		int pos = kmp(line, target);
	}
}