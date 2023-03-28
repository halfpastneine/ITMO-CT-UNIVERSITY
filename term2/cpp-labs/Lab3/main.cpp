#include <cstring>
#include <fstream>
#include <iostream>
#include <string>

#include "phonebook.h"
#include "quicksort.h"
#include "return_codes.h"

using namespace std;

bool check_direction(const string &dir)
{
	return dir == "descending";
}

int main(int argc, char *argv[])
{
	if (argc != 3)
	{
		cerr << "Wrong args" << endl;
		return ERROR_INVALID_DATA;
	}
	ifstream in(argv[1]);
	if (!in)
	{
		cerr << "File not found" << endl;
		return ERROR_NOT_FOUND;
	}
	ofstream out(argv[2]);
	if (!out)
	{
		in.close();
		cerr << "File not found" << endl;
		return ERROR_NOT_FOUND;
	}
	string type, dir;
	int n;
	in >> type >> dir >> n;
	bool flag_dir = check_direction(dir);
	if (type == "int")
	{
		int *arr = (int *)malloc(sizeof(int) * n);
		if (!arr)
		{
			cerr << "Not enough memory" << endl;
			in.close();
			out.close();
			return ERROR_NOT_ENOUGH_MEMORY;
		}
		for (int i = 0; i < n; ++i)
		{
			in >> arr[i];
		}
		in.close();
		if (flag_dir)
		{
			quicksort< int, true >(arr, 0, n - 1, flag_dir);
		}
		else
		{
			quicksort< int, false >(arr, 0, n - 1, flag_dir);
		}
		for (int i = 0; i < n; ++i)
		{
			out << arr[i] << endl;
		}
		out.close();
		free(arr);
	}
	else if (type == "float")
	{
		float *arr = (float *)malloc(sizeof(float) * n);
		if (!arr)
		{
			in.close();
			out.close();
			cerr << "Not enough memory" << endl;
			return ERROR_NOT_ENOUGH_MEMORY;
		}
		for (int i = 0; i < n; ++i)
		{
			in >> arr[i];
		}
		in.close();
		if (flag_dir)
		{
			quicksort< float, true >(arr, 0, n - 1, flag_dir);
		}
		else
		{
			quicksort< float, false >(arr, 0, n - 1, flag_dir);
		}
		for (int i = 0; i < n; ++i)
		{
			out << arr[i] << endl;
		}
		free(arr);
		out.close();
	}
	else
	{
		Phonebook *ph = (Phonebook *)(malloc(sizeof(Phonebook) * n));
		if (!ph)
		{
			in.close();
			out.close();
			cerr << "Not enough memory" << endl;
			return ERROR_NOT_ENOUGH_MEMORY;
		}
		for (int i = 0; i < n; ++i)
		{
			in >> ph[i].name >> ph[i].surname >> ph[i].middle_name >> ph[i].number;
		}
		in.close();
		if (flag_dir)
		{
			quicksort< Phonebook, true >(ph, 0, n - 1, flag_dir);
		}
		else
		{
			quicksort< Phonebook, false >(ph, 0, n - 1, flag_dir);
		}
		for (int i = 0; i < n; ++i)
		{
			out << ph[i].name << " " << ph[i].surname << " " << ph[i].middle_name << " " << ph[i].number << endl;
		}
		out.close();
		free(ph);
	}
	return 0;
}
