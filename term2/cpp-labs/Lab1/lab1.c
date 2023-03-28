#include "return_codes.h"

#include <math.h>
#include <stdio.h>
#include <stdlib.h>

void swap(int n, int j, float (*arr)[n + 1])
{
	float tmp;
	int k = -1;
	for (int i = j + 1; i < n; i++)
	{
		if (arr[i][j] != 0)
		{
			k = i;
			break;
		}
	}
	if (k >= 0)
	{
		for (int i = 0; i < n; i++)
		{
			tmp = arr[j][i];
			arr[j][i] = arr[k][i];
			arr[k][i] = tmp;
		}
	}
}

int to_triangle(int m, int n, float (*arr)[m + 1])
{
	int cnt = 0;
	for (int k = 0; k < n - 1; k++)
	{
		for (int i = cnt + 1; i < n; i++)
		{
			float kf;
			if (arr[cnt][cnt] == 0)
			{
				swap(m, cnt, arr);
				if (arr[cnt + 1][cnt] == 0)
				{
					kf = 1;
				}
				else
				{
					kf = arr[i][cnt] / arr[cnt][cnt];
				}
			}
			else
			{
				kf = arr[i][cnt] / arr[cnt][cnt];
			}
			for (int j = 0; j < n; j++)
			{
				if ((fabsf(arr[i][j] - arr[cnt][j] * kf)) < 0.001)
				{
					arr[i][j] = 0;
				}
				else
				{
					arr[i][j] -= arr[cnt][j] * kf;
				}
			}
		}
		cnt += 1;
	}
	int ct = 0;
	int ct1 = 0;
	for (int i = 0; i < n; i++)
	{
		int c = 0;
		int c1 = 0;
		for (int j = 0; j < n; j++)
		{
			if (arr[i][j] == 0)
			{
				c++;
			}
		}
		for (int j = 0; j < n - 1; j++)
		{
			if (arr[i][j] == 0)
			{
				c1 += 1;
			}
		}
		if (c1 == n - 1)
		{
			ct += 1;
		}
		if (c == n)
		{
			ct1 += 1;
		}
	}
	if (ct1 == ct && ct != 0)
	{
		return 1;
	}
	if (ct1 != ct)
	{
		return 0;
	}
	if (ct1 == ct && ct == 0)
	{
		return -1;
	}
	return ct;
}

void get_minor_mat(int n, int j, float (*arr)[n + 1], int m, float (*minor)[m + 1])
{
	for (int i = 0; i < j; i++)
	{
		for (int k = 0; k < m; k++)
		{
			minor[k][i] = arr[k + 1][i];
		}
	}
	for (int i = j + 1; i < n; i++)
	{
		for (int k = 0; k < m; k++)
		{
			minor[k][i - 1] = arr[k + 1][i];
		}
	}
}

void get_all_det(int n, int cnt, float (*arr)[n + 1], float (*tmp)[n + 1])
{
	for (int i = 0; i < cnt; i++)
	{
		for (int k = 0; k < n; k++)
		{
			tmp[k][i] = arr[k][i];
		}
	}
	for (int k = 0; k < n; k++)
	{
		tmp[k][cnt] = arr[k][n];
	}

	for (int i = cnt + 1; i < n; i++)
	{
		for (int k = 0; k < n; k++)
		{
			tmp[k][i] = arr[k][i];
		}
	}
}

float get_det(int n, float (*arr)[n + 1])
{
	int m = n - 1;
	float(*minor)[n + 1] = malloc(sizeof(float) * (n + 1) * n);
	if (minor == NULL)
	{
		return ERROR_NOT_ENOUGH_MEMORY;
	}
	if (n == 1)
	{
		return arr[0][0];
	}
	else if (n == 2)
	{
		return (arr[0][0] * arr[1][1]) - (arr[0][1] * arr[1][0]);
	}
	else
	{
		float ans = 0;
		for (int i = 0; i < n; i++)
		{
			get_minor_mat(n, i, arr, m, minor);
			if (i % 2 == 0)
			{
				ans += arr[0][i] * get_det(n - 1, minor);
			}
			else
			{
				ans += -arr[0][i] * get_det(n - 1, minor);
			}
		}
		return ans;
	}
}

int main(int argc, char *argv[])
{
	if (argc != 3)
	{
		printf("Error: Wrong number of arguments");
		return ERROR_INVALID_PARAMETER;
	}
	FILE *in = fopen(argv[1], "r");
	if (in == NULL)
	{
		printf("Error: File not opened");
		return ERROR_NOT_FOUND;
	}
	FILE *out = fopen(argv[2], "w");
	if (out == NULL)
	{
		fclose(in);
		printf("Error: File not opened");
		return ERROR_NOT_FOUND;
	}
	int n;
	int k = fscanf_s(in, "%d", &n);
	if (k == 0)
	{
		return ERROR_INVALID_DATA;
	}
	float(*arr)[n + 1] = malloc(sizeof(float) * (n + 1) * n);
	if (arr == NULL)
	{
		return ERROR_NOT_ENOUGH_MEMORY;
	}
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n + 1; j++)
		{
			fscanf_s(in, "%f", &arr[i][j]);
		}
	}
	fclose(in);
	float *ans = malloc(sizeof(float) * n);
	if (ans == NULL)
	{
		return ERROR_NOT_ENOUGH_MEMORY;
	}
	int s = to_triangle(n, n + 1, arr);
	if (s == 1)
	{
		fprintf(out, "many solutions");
		fclose(out);
	}
	if (s == 0)
	{
		fprintf(out, "no solution");
		fclose(out);
	}
	if (s == -1)
	{
		ans[0] = get_det(n, arr);
		int cnt = 0;
		for (int i = 1; i <= n; i++)
		{
			float(*tmp)[n + 1] = malloc(sizeof(float) * (n + 1) * n);
			get_all_det(n, cnt, arr, tmp);
			ans[i] = get_det(n, tmp);
			cnt++;
			free(tmp);
			fprintf(out, "%g\n", ans[i] / ans[0]);
		}
		fclose(out);
	}
	free(ans);
	free(arr);
}