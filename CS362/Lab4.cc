/*
  Filename   : Lab4.cc
  Author     : Ryan Andrews
  Course     : CSCI 362-01
  Assignment : Assignment 4: A Program of a Different Sort
  Description: Performs a radix sort of a randomly generated vector of ints
               and a std::sort on a copy of that vecotr and times how long it 
               took to sort both
*/

/************************************************************/
// System includes

#include <algorithm>
#include <queue>
#include <array>
#include <iostream>
#include <stdio.h>
#include <vector>
#include <random>
#include <cmath>
#include <iomanip>

/************************************************************/
// Local Includes

#include "Timer.hpp"

/************************************************************/
// using declarations

using std::queue;
using std::array;
using std::cout;
using std::cin;
using std::minstd_rand;
using std::vector;
using std::uniform_int_distribution;
using std::endl;
using std::pow;

/************************************************************/
//Function prototypes

void
radixSort (vector<int>& v, int maxDigits);

/************************************************************/
int 
main (int argc, char* argv[]) 
{
int d;
size_t N;
Timer<> tRad, tSort;
vector<int> A;

cout << "N ==> ";
cin >> N;
cout << "d ==> ";
cin >> d;
cout << endl;

minstd_rand gen;
gen.seed(0);
uniform_int_distribution<int> distribution(0, pow(10.0, d) - 1);

for (size_t i = 0; i<N; ++i)
{
  int number = distribution(gen);
  A.push_back(number);
}

vector<int>ACopy (A);

tSort.start();
std::sort (ACopy.begin(), ACopy.end());
tSort.stop();

tRad.start();
radixSort(A, d);
tRad.stop();

double eRad = tRad.getElapsedMs();
double eSort = tSort.getElapsedMs();

cout << std::fixed;
cout << std::setprecision(2);
printf ("%-16s", "Radix time: ");
cout << eRad << " ms" << endl;
printf ("%-16s", "std::sort time: "); 
cout << eSort << " ms" << endl;
cout << endl;

cout << "Sorted? ";
if (ACopy == A)
{
  cout << "yes" << endl;
}
else 
{
  cout << "no";
}
}

/************************************************************/
//Performs a radix sort on 'v'

void
radixSort(vector<int>& v, int maxDigits)
{
  int x = 1;
  int count = 0;
  int test;
  int f = 0;
  queue<int> one,two,thr,four,five,six,seven,eight,nine,ten;
  array<queue<int>, 10> a{one,two,thr,four,five,six,seven,eight,nine,ten};
  
  while(f < maxDigits)
  {
    count = 0;

  for(size_t i = 0; i < v.size(); ++i)
  {
    test = (v[i]/x)%10;
    a[test].push(v[i]);
  }

  for(size_t k = 0; k < a.size(); ++k)
  {
    if(!a[k].empty())
    {
      size_t aSize = a[k].size();
        for(size_t j = 0; j < aSize; ++j)
        {
          v[count] = a[k].front();
          a[k].pop();
          ++count;
        } 
    }
  }
  x = x*10;
  ++f;
  }

}

/************************************************************/
//Table of Average Times
//
//N     d=2 (ms)  d=4 (ms)  d=8 (ms)  std (d=8)
//====  ========  ========  ========  =========
//100e6 1529.93   3001.40   5883.74   7730.85
//200e6 3083.04   6021.33   11841.84  16127.37
//400e6 6142.55   12034.74  23588.98  33367.68
//
//Discussion
//The sort times were very consistent with the complexity of radix sort
//with the run time doubling when N doubled.
//Radix sort was consistently faster than std::sort.
//Radix sort was most likely faster than std::sort because std::sort has an average
//complexity of O(N log N) while radix sort has an average complexity of O(d*N)