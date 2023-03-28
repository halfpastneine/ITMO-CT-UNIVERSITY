#include "LN.h"

#include <cmath>
#include <cstring>

// operators

LN operator+(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		try
		{
			LN b;
			b.isNaN = true;
			b.ch = 'N';
			b.digit = ln1.digit;
			return b;
		} catch (...)
		{
			throw;
		}
	}
	if (ln1.ch != ln2.ch)
	{
		if (ln1.ch == '-')
		{
			LN ln;
			ln.ch = '+';
			ln.digit = ln1.digit;
			if (ln > ln2)
			{
				try
				{
					LN a = ln - ln2;
					LN b('-', a.digit);
					return b;
				} catch (...)
				{
					throw;
				}
			}
			else
			{
				try
				{
					LN a = ln2 - ln;
					LN b('+', a.digit);
					return b;
				} catch (...)
				{
					throw;
				}
			}
		}
		else
		{
			LN ln;
			ln.ch = '+';
			ln.digit = ln2.digit;
			//			ln2.ch = '+';
			if (ln > ln1)
			{
				try
				{
					LN a = ln - ln1;
					LN b('-', a.digit);
					return b;
				} catch (...)
				{
					throw;
				}
			}
			else
			{
				try
				{
					LN a = ln1 - ln;
					LN b('+', a.digit);
					return b;
				} catch (...)
				{
					throw;
				}
			}
		}
	}
	else
	{
		Vector digit;
		int bit_per = 0;
		for (int i = 0; i < min(ln1.digit.length(), ln2.digit.length()); i++)
		{
			int num = ln1.digit[i] + ln2.digit[i] + bit_per;
			bit_per != 0 ? bit_per-- : bit_per;
			if (num >= 10)
			{
				num -= 10;
				bit_per++;
			}
			try
			{
				digit.push_back(num);
			} catch (...)
			{
				throw;
			}
		}
		for (int i = min(ln1.digit.length(), ln2.digit.length()); i < max(ln1.digit.length(), ln2.digit.length()); i++)
		{
			int num;
			ln1.digit.length() > ln2.digit.length() ? num = bit_per + ln1.digit[i] : num = bit_per + ln2.digit[i];
			bit_per = 0;
			if (num >= 10)
			{
				num -= 10;
				bit_per++;
			}
			try
			{
				digit.push_back(num);
			} catch (...)
			{
				throw;
			}
		}
		if (bit_per != 0)
		{
			try
			{
				digit.push_back(bit_per);
			} catch (...)
			{
				throw;
			}
		}
		int s = digit.length() - 1;
		while (digit[s] == 0)
		{
			if (s == 0)
			{
				break;
			}
			digit.pop_back();
			s--;
		}
		try
		{
			LN b(ln1.ch, digit);
			return b;
		} catch (...)
		{
			throw;
		}
	}
}

LN operator-(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		try
		{
			LN b;
			b.isNaN = true;
			b.ch = 'N';
			b.digit = ln1.digit;
			return b;
		} catch (...)
		{
			throw;
		}
	}
	if (ln1.ch == '+' && ln2.ch == '-')
	{
		LN ln;
		ln.ch = '+';
		ln.digit = ln2.digit;
		try
		{
			LN a = ln + ln1;
			LN b('+', a.digit);
			return b;
		} catch (...)
		{
			throw;
		}
	}
	else if (ln1.ch == '-' && ln2.ch == '+')
	{
		LN ln;
		ln.ch = '+';
		ln.digit = ln1.digit;
		try
		{
			LN a = ln + ln2;
			LN b('-', a.digit);
			return b;
		} catch (...)
		{
			throw;
		}
	}
	else if (ln1.ch == '-' && ln2.ch == '-')
	{
		LN ln;
		LN ln0;
		ln.ch = '+';
		ln.digit = ln1.digit;
		ln0.ch = '+';
		ln0.digit = ln2.digit;
		if (ln > ln0)
		{
			try
			{
				LN a = ln - ln0;
				LN b('-', a.digit);
				return b;
			} catch (...)
			{
				throw;
			}
		}
		else
		{
			try
			{
				LN a = ln0 - ln;
				LN b('+', a.digit);
				return b;
			} catch (...)
			{
				throw;
			}
		}
	}
	else
	{
		Vector digit;
		int bit_per = 0;
		for (int i = 0; i < min(ln1.digit.length(), ln2.digit.length()); i++)
		{
			int num;
			ln1 > ln2 ? num = ln1.digit[i] - ln2.digit[i] + bit_per : num = ln2.digit[i] - ln1.digit[i] + bit_per;
			bit_per = 0;
			if (num < 0)
			{
				num += 10;
				bit_per--;
			}
			try
			{
				digit.push_back(num);
			} catch (...)
			{
				throw;
			}
		}
		for (int i = min(ln1.digit.length(), ln2.digit.length()); i < max(ln1.digit.length(), ln2.digit.length()); i++)
		{
			int num;
			ln1.digit.length() > ln2.digit.length() ? num = bit_per + ln1.digit[i] : num = bit_per + ln2.digit[i];
			bit_per = 0;
			if (num < 0)
			{
				num += 10;
				bit_per--;
			}
			try
			{
				digit.push_back(num);
			} catch (...)
			{
				throw;
			}
		}
		int k = digit.length() - 1;
		while (digit[k] == 0)
		{
			if (k == 0)
			{
				break;
			}
			digit.pop_back();
			k--;
		}
		if (ln1 > ln2)
		{
			try
			{
				LN b('+', digit);
				return b;
			} catch (...)
			{
				throw;
			}
		}
		else
		{
			try
			{
				LN b('-', digit);
				return b;
			} catch (...)
			{
				throw;
			}
		}
	}
}

LN operator*(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		try
		{
			LN b;
			b.isNaN = true;
			b.ch = 'N';
			b.digit = ln1.digit;
			return b;
		} catch (...)
		{
			throw;
		}
	}
	try
	{
		Vector digit(max(ln1.digit.length(), ln2.digit.length()) * 2 + 1);
		for (int i = 0; i < ln2.digit.length(); i++)
		{
			int bit_per = 0;
			for (int j = 0; j < ln1.digit.length(); j++)
			{
				digit[i + j] += ln1.digit[j] * ln2.digit[i] + bit_per;
				bit_per = digit[i + j] / 10;
				digit[i + j] %= 10;
			}
			if (bit_per != 0)
			{
				digit[ln1.digit.length() + i] = bit_per;
			}
		}
		int k = digit.length() - 1;
		while (digit[k] == 0)
		{
			if (k == 0)
			{
				break;
			}
			digit.pop_back();
			k--;
		}
		if (ln1.ch == ln2.ch)
		{
			LN b('+', digit);
			return b;
		}
		else
		{
			LN b('-', digit);
			return b;
		}
	} catch (...)
	{
		throw;
	}
}

LN operator/(const LN &ln1, const LN &ln2)
{
	try
	{
		Vector digit(abs(ln1.digit.length() - ln2.digit.length()) + 2);
		LN a(digit);
		if (ln1.isNaN || ln2.isNaN)
		{
			LN b(true, 'N', digit);
			return b;
		}
		bool tr = false;
		for (int i = 0; i < ln2.digit.length(); i++)
		{
			if (ln2.digit[i] != 0)
			{
				tr = true;
			}
		}
		if (!tr)
		{
			LN b(true, 'N', digit);
			return b;
		}
		LN b;
		LN c;
		b.ch = '+';
		b.digit = ln1.digit;
		c.ch = '+';
		c.digit = ln2.digit;
		for (int i = digit.length() - 1; i >= 0; i--)
		{
			while ((a * c) <= b)
			{
				a.digit[i]++;
			}
			a.digit[i]--;
		}
		int k = a.digit.length() - 1;
		while (a.digit[k] == 0)
		{
			if (k == 0)
			{
				break;
			}
			a.digit.pop_back();
			k--;
		}
		if (ln1.ch == ln2.ch)
		{
			LN b('+', a.digit);
			return b;
		}
		else
		{
			LN b('-', a.digit);
			return b;
		}
	} catch (...)
	{
		throw;
	}
}

LN operator%(const LN &ln1, const LN &ln2)
{
	LN a = ln1 / ln2;
	if (a.isNaN)
	{
		try
		{
			LN b(true, 'N', a.digit);
			return b;
		} catch (...)
		{
			throw;
		}
	}
	LN b = a * ln2;
	LN c = ln1 - b;
	int k = c.digit.length() - 1;
	while (c.digit[k] == 0)
	{
		if (k == 0)
		{
			break;
		}
		c.digit.pop_back();
		k--;
	}
	try
	{
		LN b1(c.ch, c.digit);
		return b1;
	} catch (...)
	{
		throw;
	}
}

LN operator~(const LN &ln1)
{
	if (ln1.isNaN)
	{
		try
		{
			LN b;
			b.isNaN = true;
			b.ch = 'N';
			b.digit = ln1.digit;
			return b;
		} catch (...)
		{
			throw;
		}
	}
	try
	{
		Vector digit((ln1.digit.length() / 2) + 1);
		LN a(digit);
		if (ln1.ch == '-')
		{
			LN b(true, a.ch, a.digit);
			return b;
		}
		for (int i = digit.length() - 1; i >= 0; i--)
		{
			LN c = a * a;
			int t = c <= ln1;
			while (t == 1)
			{
				a.digit[i]++;
				c = a * a;
				t = c <= ln1;
			}
			a.digit[i]--;
		}
		int k = a.digit.length() - 1;
		while (a.digit[k] == 0)
		{
			if (k == 0)
			{
				break;
			}
			a.digit.pop_back();
			k--;
		}
		LN b(a.digit);
		return b;
	} catch (...)
	{
		throw;
	}
}

LN LN::operator-()
{
	if (isNaN)
	{
		try
		{
			LN b(true, 'N', digit);
			return b;
		} catch (...)
		{
			throw;
		}
	}
	ch == '-' ? ch = '+' : ch = '-';
	try
	{
		LN b(ch, digit);
		return b;
	} catch (...)
	{
		throw;
	}
}

bool operator!=(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		return true;
	}
	if (ln1 == ln2)
	{
		return false;
	}
	else
	{
		return true;
	}
}

bool operator==(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		return false;
	}
	if (ln1.ch != ln2.ch)
	{
		return false;
	}
	if (ln1.digit.length() != ln2.digit.length())
	{
		return false;
	}
	else
	{
		for (int i = 0; i < ln1.digit.length(); ++i)
		{
			if (ln1.digit[i] != ln2.digit[i])
			{
				return false;
			}
		}
	}
	return true;
}

bool check_sign1(char ch)
{
	if (ch == '-')
	{
		return false;
	}
	else
	{
		return true;
	}
}

bool check_sign2(char ch)
{
	if (ch == '-')
	{
		return true;
	}
	else
	{
		return false;
	}
}

bool operator>=(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		return false;
	}
	if (ln1.ch != ln2.ch)
	{
		return check_sign1(ln1.ch);
	}
	else
	{
		if (ln1.digit.length() > ln2.digit.length())
		{
			return check_sign1(ln1.ch);
		}
		else if (ln1.digit.length() < ln2.digit.length())
		{
			return check_sign2(ln1.ch);
		}
		else
		{
			for (int i = ln1.digit.length() - 1; i >= 0; i--)
			{
				if (ln1.digit[i] < ln2.digit[i])
				{
					return check_sign2(ln1.ch);
				}
				if (ln1.digit[i] > ln2.digit[i])
				{
					return check_sign1(ln1.ch);
				}
			}
		}
		return true;
	}
}

bool operator<=(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		return false;
	}
	if (ln1.ch != ln2.ch)
	{
		return check_sign2(ln1.ch);
	}
	if (ln1.digit.length() < ln2.digit.length())
	{
		return check_sign1(ln1.ch);
	}
	else if (ln1.digit.length() > ln2.digit.length())
	{
		return check_sign2(ln1.ch);
	}
	else
	{
		for (int i = ln1.digit.length() - 1; i >= 0; i--)
		{
			if (ln1.digit[i] > ln2.digit[i])
			{
				return check_sign2(ln1.ch);
			}
			if (ln1.digit[i] < ln2.digit[i])
			{
				return check_sign1(ln1.ch);
			}
		}
	}
	return true;
}

bool operator<(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		return false;
	}
	bool c = ln1 <= ln2;
	bool b = ln1 == ln2;
	if (c == 1 && b == 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

bool operator>(const LN &ln1, const LN &ln2)
{
	if (ln1.isNaN || ln2.isNaN)
	{
		return false;
	}
	bool c = ln1 >= ln2;
	bool b = ln1 == ln2;
	if (c == 1 && b == 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

LN &LN::operator=(const LN &ln)
{
	this->isNaN = ln.isNaN;
	this->ch = ln.ch;
	this->digit = ln.digit;
	return *this;
}

LN &LN::operator=(LN &&ln) noexcept
{
	this->isNaN = ln.isNaN;
	this->ch = ln.ch;
	this->digit = ln.digit;
	ln.isNaN = false;
	return *this;
}

void operator+=(LN &ln1, const LN &ln2)
{
	try
	{
		ln1 = ln1 + ln2;
	} catch (...)
	{
		throw;
	}
}

void operator-=(LN &ln1, const LN &ln2)
{
	try
	{
		ln1 = ln1 - ln2;
	} catch (...)
	{
		throw;
	}
}

void operator*=(LN &ln1, const LN &ln2)
{
	try
	{
		ln1 = ln1 * ln2;
	} catch (...)
	{
		throw;
	}
}

void operator/=(LN &ln1, const LN &ln2)
{
	try
	{
		ln1 = ln1 / ln2;
	} catch (...)
	{
		throw;
	}
}

void operator%=(LN &ln1, const LN &ln2)
{
	try
	{
		ln1 = ln1 % ln2;
	} catch (...)
	{
		throw;
	}
}

LN::operator long long()
{
	LN a("9223372036854775807");
	LN b("-9223372036854775808");
	if (this->digit.length() > a.digit.length())
	{
		throw "OVERFLOW";
	}
	if (this->ch == '-')
	{
		for (int i = this->digit.length() - 1; i >= 0; i--)
		{
			if (this->digit[i] > b.digit[this->digit.length() - 1 - i])
			{
				throw "OVERFLOW";
			}
		}
	}
	else
	{
		for (int i = this->digit.length() - 1; i >= 0; i--)
		{
			if (this->digit[i] > a.digit[this->digit.length() - 1 - i])
			{
				throw "OVERFLOW";
			}
		}
	}
	long long number = 0;
	if (this->ch == '+')
	{
		for (int i = 0; i < this->digit.length(); i++)
		{
			number += this->digit[i] * (long long)pow(10, i);
		}
	}
	else
	{
		for (int i = 0; i < this->digit.length(); ++i)
		{
			number -= this->digit[i] * (long long)pow(10, i);
		}
	}
	return number;
}

LN operator""_ln(const char *str)
{
	LN a(str);
	return a;
}

LN::operator bool()
{
	if (this->digit.length() == 1)
	{
		if (this->digit[0] == 0)
		{
			return false;
		}
	}
	else
	{
		for (int i = 0; i < this->digit.length(); ++i)
		{
			if (this->digit[i] == 0)
			{
				return false;
			}
		}
	}
	return true;
}

// methods

string make_reverse_string(LN &a)
{
	string s;
	if (a.isNaN)
	{
		s.append("NaN");
		return s;
	}
	if (a.ch == '-')
	{
		if (a.digit[a.digit.length() - 1] != 0)
		{
			s.append("-");
		}
	}
	for (int i = a.digit.length() - 1; i >= 0; i--)
	{
		s.append(to_string(a.digit[i]));
	}
	return s;
}

int compare_to(LN &a, LN &b)
{
	if (a == b)
	{
		return 0;
	}
	else if (a < b)
	{
		return -1;
	}
	else
	{
		return 1;
	}
}

// constructors
LN::LN(bool isNaN, char ch, Vector &digit)
{
	this->isNaN = isNaN;
	this->ch = ch;
	try
	{
		this->digit = digit;
	} catch (...)
	{
		throw;
	}
}

LN::LN(char ch, Vector &digit)
{
	this->ch = ch;
	try
	{
		this->digit = digit;
	} catch (...)
	{
		throw;
	}
}

LN::LN(const char *str)
{
	if (str[0] == 'N')
	{
		this->isNaN = true;
	}
	else
	{
		int k = 0;
		this->isNaN = false;
		if (str[0] == '-')
		{
			k = 1;
			this->ch = str[0];
		}
		else
		{
			this->ch = '+';
		}
		bool tr = false;
		while (str[k] == '0')
		{
			if (k == strlen(str) - 1)
				break;
			k++;
			tr = true;
		}
		if (tr)
			k--;
		if (!tr && (this->ch == '-'))
			k--;
		for (int i = strlen(str) - 1; i > k; i--)
		{
			try
			{
				digit.push_back(str[i] - '0');
			} catch (...)
			{
				throw;
			}
		}
		if (this->ch == '+')
		{
			try
			{
				digit.push_back(str[0] - '0');
			} catch (...)
			{
				throw;
			}
		}
	}
}

LN::LN(std::string_view str)
{
	if (str[0] == 'N')
	{
		this->isNaN = true;
	}
	else
	{
		int k = 0;
		this->isNaN = false;
		if (str[0] == '-')
		{
			k = 1;
			this->ch = str[0];
		}
		else
		{
			this->ch = '+';
		}
		bool tr = false;
		while (str[k] == '0')
		{
			if (k == str.length() - 1)
				break;
			k++;
			tr = true;
		}
		if (tr)
			k--;
		if (!tr && (this->ch == '-'))
			k--;
		for (int i = str.length() - 1; i > k; i--)
		{
			try
			{
				digit.push_back(str[i] - '0');
			} catch (...)
			{
				throw;
			}
		}
		if (this->ch == '+')
		{
			try
			{
				digit.push_back(str[0] - '0');
			} catch (...)
			{
				throw;
			}
		}
	}
}

LN::LN(const LN &ln)
{
	this->isNaN = ln.isNaN;
	this->ch = ln.ch;
	this->digit = ln.digit;
}

LN::LN(Vector &digit)
{
	this->ch = '+';
	try
	{
		this->digit = digit;
	} catch (...)
	{
		throw;
	}
}

LN::LN(long long c)
{
	this->isNaN = false;
	c < 0 ? this->ch = '-' : this->ch = '+';
	while (c != 0)
	{
		try
		{
			digit.push_back(c % 10);
			c /= 10;
		} catch (...)
		{
			throw;
		}
	}
}

LN::LN(LN &&ln) noexcept
{
	this->isNaN = ln.isNaN;
	this->ch = ln.ch;
	this->digit = ln.digit;
	ln.isNaN = false;
}
