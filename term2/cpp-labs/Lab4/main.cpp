#include "LN.h"
#include "return_codes.h"

#include <fstream>
#include <iostream>
#include <stack>
#include <string>
using namespace std;

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
	string str;
	stack< string > stack;
	while (in >> str)
	{
		if (str == "+")
		{
			try
			{
				LN a(stack.top());
				stack.pop();
				LN b(stack.top());
				stack.pop();
				LN c = b + a;
				string s = make_reverse_string(c);
				stack.push(s);
			} catch (std::bad_alloc)
			{
				in.close();
				out.close();
				cerr << "NOT ENOUGH MEMORY";
				return ERROR_NOT_ENOUGH_MEMORY;
			} catch (...)
			{
				in.close();
				out.close();
				return ERROR_UNKNOWN;
			}
		}
		else if (str == "-")
		{
			try
			{
				LN a(stack.top());
				stack.pop();
				LN b(stack.top());
				stack.pop();
				LN c = b - a;
				string s = make_reverse_string(c);
				stack.push(s);
			} catch (std::bad_alloc)
			{
				in.close();
				out.close();
				cerr << "NOT ENOUGH MEMORY";
				return ERROR_NOT_ENOUGH_MEMORY;
			} catch (...)
			{
				in.close();
				out.close();
				cerr << "UNKNOWN ERROR";
				return ERROR_UNKNOWN;
			}
		}
		else if (str == "*")
		{
			try
			{
				LN a(stack.top());
				stack.pop();
				LN b(stack.top());
				stack.pop();
				LN c = b * a;
				string s = make_reverse_string(c);
				stack.push(s);
			} catch (std::bad_alloc)
			{
				in.close();
				out.close();
				cerr << "NOT ENOUGH MEMORY";
				return ERROR_NOT_ENOUGH_MEMORY;
			} catch (...)
			{
				in.close();
				out.close();
				cerr << "UNKNOWN ERROR";
				return ERROR_UNKNOWN;
			}
		}
		else if (str == "/")
		{
			try
			{
				LN a(stack.top());
				stack.pop();
				LN b(stack.top());
				stack.pop();
				LN c = b / a;
				string s = make_reverse_string(c);
				stack.push(s);
			} catch (std::bad_alloc)
			{
				in.close();
				out.close();
				cerr << "NOT ENOUGH MEMORY";
				return ERROR_NOT_ENOUGH_MEMORY;
			} catch (...)
			{
				in.close();
				out.close();
				cerr << "UNKNOWN ERROR";
				return ERROR_UNKNOWN;
			}
		}
		else if (str == "%")
		{
			try
			{
				LN a(stack.top());
				stack.pop();
				LN b(stack.top());
				stack.pop();
				LN c = b % a;
				string s = make_reverse_string(c);
				stack.push(s);
			} catch (std::bad_alloc)
			{
				in.close();
				out.close();
				cerr << "NOT ENOUGH MEMORY";
				return ERROR_NOT_ENOUGH_MEMORY;
			} catch (...)
			{
				in.close();
				out.close();
				cerr << "UNKNOWN ERROR";
				return ERROR_UNKNOWN;
			}
		}
		else if (str == "~")
		{
			try
			{
				LN a(stack.top());
				stack.pop();
				LN c = ~a;
				string s = make_reverse_string(c);
				stack.push(s);
			} catch (std::bad_alloc)
			{
				in.close();
				out.close();
				cerr << "NOT ENOUGH MEMORY";
				return ERROR_NOT_ENOUGH_MEMORY;
			} catch (...)
			{
				in.close();
				out.close();
				cerr << "UNKNOWN ERROR";
				return ERROR_UNKNOWN;
			}
		}
		else if (str == "_")
		{
			try
			{
				LN a(stack.top());
				stack.pop();
				LN c = -a;
				string s = make_reverse_string(c);
				stack.push(s);
			} catch (std::bad_alloc)
			{
				in.close();
				out.close();
				cerr << "NOT ENOUGH MEMORY";
				return ERROR_NOT_ENOUGH_MEMORY;
			} catch (...)
			{
				in.close();
				out.close();
				cerr << "UNKNOWN ERROR";
				return ERROR_UNKNOWN;
			}
		}
		else if (str == "==")
		{
			LN a(stack.top());
			stack.pop();
			LN b(stack.top());
			stack.pop();
			int c = b == a;
			string s = to_string(c);
			stack.push(s);
		}
		else if (str == ">=")
		{
			LN a(stack.top());
			stack.pop();
			LN b(stack.top());
			stack.pop();
			int c = b >= a;
			string s = to_string(c);
			stack.push(s);
		}
		else if (str == "<=")
		{
			LN a(stack.top());
			stack.pop();
			LN b(stack.top());
			stack.pop();
			int c = b <= a;
			string s = to_string(c);
			stack.push(s);
		}
		else if (str == "!=")
		{
			LN a(stack.top());
			stack.pop();
			LN b(stack.top());
			stack.pop();
			int c = b != a;
			string s = to_string(c);
			stack.push(s);
		}
		else if (str == ">")
		{
			LN a(stack.top());
			stack.pop();
			LN b(stack.top());
			stack.pop();
			int c = b > a;
			string s = to_string(c);
			stack.push(s);
		}
		else if (str == "<")
		{
			LN a(stack.top());
			stack.pop();
			LN b(stack.top());
			stack.pop();
			int c = b < a;
			string s = to_string(c);
			stack.push(s);
		}
		else
		{
			stack.push(str);
		}
	}
	while (stack.size() != 0)
	{
		out << stack.top() << endl;
		stack.pop();
	}
	in.close();
	out.close();
}
