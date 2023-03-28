prime(2).
prime(N) :- N > 1, S is sqrt(N), \+ prime(N, 2, S).
prime(N, R, S) :- R < S, R1 is R + 1, prime(N, R1, S).
prime(N, R, S) :- 0 is mod(N, R).
composite(N) :- N > 1, \+ prime(N).
inc(N, R) :- number(N), !, R is N + 1.
prime_next(N, R) :- composite(N), inc(N, N1), prime_next(N1, R).
prime_next(N, R) :- prime(N), R is N.
prime_divisors(N, Divisors) :- prime_div(N, Divisors, 2), !.
prime_div(N, [Head | Tail], R) :- 0 is mod(N, R), N1 is N / R, Head is R, prime_div(N1, Tail, R), !.
prime_div(N, [Head | Tail], R) :- N > 1, R1 is R + 1, prime_next(R1, R2), prime_div(N, [Head | Tail], R2), !.
prime_div(N, [], R) :- N = 1, !.
unique_prime_divisors(N, Divisors) :- prime_divisors(N, T), unique(T, Divisors).
unique([], []).
unique([A], [A]).
unique([A, A | REST], R) :- append([A], REST, L), unique(L, R), !.
unique([A, B | REST], R) :- append([B], REST, L), unique(L, R1), append([A], R1, R), !.

%prime(2).
%prime(N) :- N > 1, S is sqrt(N), \+ prime(N, 2, S).
%prime(N, R, S) :- R < S, R1 is R + 1, prime(N, R1, S).
%prime(N, R, S) :- 0 is mod(N, R).
%composite(N) :- N > 1, \+ prime(N).
%inc(N, R) :- number(N), !, R is N + 1.
%prime_next(N, R) :- composite(N), inc(N, N1), prime_next(N1, R).
%prime_next(N, R) :- prime(N), R is N.
%prime_divisors(N, Divisors) :- prime_div(N, Divisors, 2), !.
%prime_div(N, [Head | Tail], R) :- 0 is mod(N, R), N1 is N / R, Head is R, prime_div(N1, Tail, R), !.
%prime_div(N, [Head | Tail], R) :- N > 1, R1 is R + 1, prime_next(R1, R2), prime_div(N, [Head | Tail], R2), !.
%prime_div(N, [], R) :- N = 1, !.
%unique_prime_divisors(N, Divisors) :- unique(N, Divisors).
%un(N, [Head | Tail], R, RES) :- N > 1, 0 is mod(N, R), N1 is N / R, un(N1, Tail, R, N1), !.
%unique(N, [Head | Tail], R) :- \+ un(N, [Head | Tail], R, N1), \+ N1 = N, Head is R, unique(N1, Tail, R), !.
%unique(N, [Head | Tail], R) :- N > 1, R1 is R + 1, prime_next(R1, R2), unique(N, [Head | Tail], R2), !.
%unique(N, [], R) :- N = 1, !.
