struct Phonebook
{
	char name[21];
	char surname[21];
	char middle_name[21];
	long long number;
};

bool operator>(Phonebook &ph1, Phonebook &ph2);
bool operator<(Phonebook &ph1, Phonebook &ph2);
