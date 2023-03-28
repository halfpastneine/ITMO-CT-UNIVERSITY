template< typename Type >

void swap(Type *arr, int i, int j)
{
	Type tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}

template< typename Type >

int descending_partition(Type *arr, int i, int j, Type value)
{
	while (i <= j)
	{
		while (arr[i] > value)
		{
			i++;
		}
		while (arr[j] < value)
		{
			j--;
		}
		if (i >= j)
		{
			break;
		}
		swap(arr, i, j);
		i++;
		j--;
	}
	return j;
}

template< typename Type >

int ascending_partition(Type *arr, int i, int j, Type value)
{
	while (i <= j)
	{
		while (arr[i] < value)
		{
			i++;
		}
		while (arr[j] > value)
		{
			j--;
		}
		if (i >= j)
		{
			break;
		}
		swap(arr, i, j);
		i++;
		j--;
	}
	return j;
}
template< typename T, bool descending >

void quicksort(T *arr, int l, int r, bool flag)
{
	while (l < r)
	{
		int mid = (r + l) / 2;
		int i;
		T value = arr[mid];
		descending ? i = descending_partition(arr, l, r, value) : i = ascending_partition(arr, l, r, value);
		if ((i - l) <= (r - i))
		{
			quicksort< T, descending >(arr, l, i, flag);
			l = i + 1;
		}
		else
		{
			quicksort< T, descending >(arr, i + 1, r, flag);
			r = i;
		}
	}
}
