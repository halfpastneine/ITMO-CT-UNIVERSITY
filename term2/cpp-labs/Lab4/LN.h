#include "Vector.h"
#include <string_view>
#pragma once
#include <cstring>
#include <string>
#include <vector>

using namespace std;
class LN
{
  private:
	bool isNaN = false;
	int ch;
	Vector digit;

  public:
	LN(bool isNaN, char ch, Vector &digit);
	LN(char ch, Vector &digit);
	LN(const LN &ln);
	LN(LN &&ln) noexcept;
	explicit LN(const char *str);
	explicit LN(std::string_view str);
	explicit LN(Vector &digit);
	explicit LN(long long c = 0);

	friend string make_reverse_string(LN &a);
	friend int compare_to(LN &a, LN &b);

	friend LN operator+(const LN &ln1, const LN &ln2);
	friend LN operator-(const LN &ln1, const LN &ln2);
	friend LN operator*(const LN &ln1, const LN &ln2);
	friend LN operator/(const LN &ln1, const LN &ln2);
	friend LN operator%(const LN &ln1, const LN &ln2);
	friend LN operator~(const LN &ln1);
	LN operator-();
	operator bool();
	LN &operator=(const LN &ln);
	LN &operator=(LN &&ln) noexcept;
	friend void operator+=(LN &ln1, const LN &ln2);
	friend void operator-=(LN &ln1, const LN &ln2);
	friend void operator/=(LN &ln1, const LN &ln2);
	friend void operator*=(LN &ln1, const LN &ln2);
	friend void operator%=(LN &ln1, const LN &ln2);
	friend bool operator==(const LN &ln1, const LN &ln2);
	friend bool operator>=(const LN &ln1, const LN &ln2);
	friend bool operator<=(const LN &ln1, const LN &ln2);
	friend bool operator<(const LN &ln1, const LN &ln2);
	friend bool operator>(const LN &ln1, const LN &ln2);
	friend bool operator!=(const LN &ln1, const LN &ln2);
	operator long long();
};

LN operator""_ln(const char *str);
