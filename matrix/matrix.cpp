#include<iostream>
#include<fstream>

using namespace std;

class Matrix{
private:
	int rows;
	int cols;
	double* m;

public:
	Matrix(int r, int c) : rows(r), cols(c)
	{
		m = new double[rows*cols];
	}

	~Matrix()
	{
		delete [] m;
	}

	Matrix(const Matrix& orig) :rows(orig.rows), cols(orig.cols)
	{
		m = new double[rows*cols];
		for (int i = 0; i < orig.rows*orig.cols; i++)
		{
			m[i] = orig.m[i];
		}
	}

	friend Matrix operator +(const Matrix& a, const Matrix& b)
	{
		Matrix c(a.rows, a.cols);
		for (int i = 0; i < a.cols; i++)
			for (int j = 0; j < a.rows; j++)
			{
				c.m[i*c.cols + j] = a.m[i*a.cols + j] + b.m[i*b.cols + j];
			}
		return c;
	}

	friend Matrix add(const Matrix& a, const Matrix& b)
	{
		Matrix c(a.rows, a.cols);
		for (int i = 0; i < a.cols*a.rows; i++)
			c.m[i] = a.m[i] + b.m[i];
		return c;
	}

	friend ostream& operator << (ostream& s, const Matrix& m)
	{
		int c = 0;
		for (int i = 0; i < m.rows; i++)
		{
			for (int j = 0; j < m.cols; j++)
			{
				s << m.m[c++] << " ";
			}
			s << endl;
		}
		return s;
	}

	friend istream& operator >> (istream& s, Matrix& m)
	{
		delete[] m.m;
		s >> m.rows >> m.cols;
		m.m = new double[m.rows * m.cols];
		int c = 0;
		for (int i = 0; i < m.rows; i++)
			for (int j = 0; j < m.cols; j++)
			{
				s >> m.m[c++];
			}
		return s;
	}
};

int main()
{
	ifstream f("hw5a.dat");
	Matrix a(3, 3);
	f >> a;
	Matrix b(3, 3);
	f >> b;
	cout << a;
	Matrix c = a + b;
	cout << c;
	system("pause");
	//Matrix d = a * b;
	//Matrix e = a.transpose();
}
