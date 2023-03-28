#include <cstdlib>
#include <stdexcept>
#pragma once

class Vector
{
  private:
	char *arr;
	int capacity;
	int size;

  public:
	Vector()
	{
		this->arr = (char *)malloc(sizeof(char) * 1);
		if (!this->arr)
		{
			throw "NOT ENOUGH MEMORY";
		}
		this->capacity = 1;
		this->size = 0;
	}

	explicit Vector(int size)
	{
		this->arr = (char *)malloc(sizeof(char) * size);
		if (!this->arr)
		{
			throw "NOT ENOUGH MEMORY";
		}
		this->capacity = size;
		this->size = size;
		for (int i = 0; i < size; i++)
			this->arr[i] = 0;
	}

	Vector(const Vector &vec)
	{
		this->size = vec.size;
		this->capacity = vec.capacity;
		this->arr = (char *)malloc(sizeof(char) * vec.capacity);
		if (!arr)
		{
			throw "NOT ENOUGH MEMORY";
		}
		for (int i = 0; i < vec.size; i++)
			this->arr[i] = vec.arr[i];
	}

	~Vector() { free(arr); }

	int length() const { return size; }

	char &operator[](int index) const { return arr[index]; }

	Vector &operator=(const Vector &vec)
	{
		if (this == &vec)
		{
			return *this;
		}
		free(this->arr);
		this->capacity = vec.capacity;
		this->size = vec.size;
		this->arr = (char *)malloc(sizeof(char) * capacity);
		if (!arr)
		{
			throw "NOT ENOUGH MEMORY";
		}
		for (int i = 0; i < vec.size; i++)
		{
			this->arr[i] = vec.arr[i];
		}
		return *this;
	}

	Vector &operator=(Vector &&vec) noexcept
	{
		if (this == &vec)
		{
			return *this;
		}
		free(this->arr);
		this->capacity = vec.capacity;
		this->size = vec.size;
		this->arr = vec.arr;
		vec.capacity = 0;
		vec.size = 0;
		vec.arr = nullptr;
		return *this;
	}

	void push_back(int key)
	{
		update_size();
		arr[size] = key;
		size++;
	}

	void pop_back() { size--; }

	void update_size()
	{
		if (size == capacity)
		{
			capacity *= 3;
			capacity /= 2;
			capacity++;
			char *q = arr;
			arr = (char *)realloc(arr, sizeof(char) * capacity);
			if (!arr)
			{
				free(q);
				throw "NOT ENOUGH MEMORY";
			}
		}
	}
};
