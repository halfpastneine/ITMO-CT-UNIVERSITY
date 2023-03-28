#include "queue"
#include "vector"
#include <algorithm>
#include <iostream>
#include <variant>

//A and B -> A or B
template<typename A, typename B>
std::variant<A, B> f(std::pair<A, B> x) {
    return std::variant(x.first, x.second);
}

// A -> B -> A
template<typename A, typename B>
B f1(A a) {
    return B(a);
}

template<typename A, typename B>
A f2(A a) {
    return A(f1(a));
}

using namespace std;
template<typename A, typename B, typename C>
variant<pair<A, B>, pair<A, C>> func(pair<A, variant<B, C>> x) {
    return variant(pair(x.first, x.second.first), pair(x.first, x.second.second));
}


int main() {

}