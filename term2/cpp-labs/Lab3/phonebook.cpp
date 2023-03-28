#include "phonebook.h"
#include <cstring>

using namespace std;

bool operator>(Phonebook &ph1, Phonebook &ph2)
{
	if (strcmp(ph1.name, ph2.name) > 0)
	{
		return true;
	}
	if (strcmp(ph1.name, ph2.name) == 0)
	{
		if (strcmp(ph1.surname, ph2.surname) > 0)
		{
			return true;
		}
		if (strcmp(ph1.surname, ph2.surname) == 0)
		{
			if (strcmp(ph1.middle_name, ph2.middle_name) > 0)
			{
				return true;
			}
			if (strcmp(ph1.surname, ph2.surname) == 0)
			{
				if (ph1.number > ph2.number)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	else
	{
		return false;
	}
}

bool operator<(Phonebook &ph1, Phonebook &ph2)
{
	if (strcmp(ph1.name, ph2.name) < 0)
	{
		return true;
	}
	if (strcmp(ph1.name, ph2.name) == 0)
	{
		if (strcmp(ph1.surname, ph2.surname) < 0)
		{
			return true;
		}
		if (strcmp(ph1.surname, ph2.surname) == 0)
		{
			if (strcmp(ph1.middle_name, ph2.middle_name) < 0)
			{
				return true;
			}
			if (strcmp(ph1.surname, ph2.surname) == 0)
			{
				if (ph1.number < ph2.number)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	else
	{
		return false;
	}
}