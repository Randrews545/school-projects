/*
  Filename   : Lab2.cc
  Author     : Ryan Andrews
  Course     : CSCI 362-01
  Assignment : Assignment 3: A Program of Sorts
  Description: Creates a vector based on user input and uses one of three 
               algorithms to sort it while also keeping track of the
               amount of comparisons and swap each is doing
*/

/************************************************************/
// System includes

#include <algorithm>
#include <iostream>
#include <stdio.h>
#include <vector>
#include <string>
#include <random>

/************************************************************/
//using declarations

using std::cin;
using std::cout;
using std::endl;
using std::vector;
using std::string;
using std::minstd_rand;

/************************************************************/
//Function prototypes

void 
bubbleSort (vector<int>& v, size_t& numCompares, size_t& numSwaps);

size_t 
insertionSort (vector<int>& v);

void 
selectionSort (vector<int>& v, size_t& numCompares, size_t& numSwaps);

/************************************************************/
int 
main (int argc, char* argv[]) 
{    
    int N, seed;
    string algorithm, type;
    size_t numCompares = 0;
    size_t numSwaps = 0;
    vector<int> A;

    printf ("%-10s ", "N");
    cout << "==> ";
    cin >> N;

    printf ("%-10s ", "Algorithm");
    cout << "==> ";
    cin >> algorithm;

    printf ("%-10s ", "Type");
    cout << "==> ";
    cin >> type;

    if (type == "a") 
    {
      for (int i = 1; i <= N; ++ i)
      A.push_back (i);
    }

    else if (type == "d") 
    {
      for (int i = N; i >= 0; -- i)
      A.push_back (i);
    }

    else if (type == "r") 
    {
      printf ("%-10s ", "Seed");
      cout << "==> ";
      cin >> seed;
      minstd_rand gen;
      gen.seed (seed);
      for (int i = 0; i <= N; ++ i) 
      {
      A.push_back (gen() % 9999);
      }
    }

    vector<int> ACopy (A);
    std::sort (ACopy.begin(), ACopy.end());

    if (algorithm == "bubble") 
    {
    bubbleSort (A, numCompares, numSwaps);
    }
    else if (algorithm == "insertion")
    {
      numCompares = insertionSort (A);
    }
    else if (algorithm == "selection") 
    {
      selectionSort (A, numCompares, numSwaps);
    }

    printf ("%-10s: ", "# Compares");
    cout << numCompares << endl;
    printf ("%-10s: ", "# Swaps");
    cout << numSwaps << endl;
    printf ("%-10s ", "Sort ok?");

    if (ACopy == A) 
    {
      cout << " Yes" << endl;
    }
    else if (ACopy != A) 
    {
      cout << " No" << endl;
    }

    return 0;
}

/************************************************************/
//Sorts the vector using the 'bubble' algorithm and returns 
//the number of comparisons and swaps

void 
bubbleSort (vector<int>& v, size_t& numCompares, size_t& numSwaps)
{
  for (size_t i = v.size() - 1; i >= 1; -- i) 
  {
        bool didSwap = false;
        for (size_t j = 0; j < i; ++ j)
          if (v[j] > v[j + 1]) 
          {
            numCompares++;
            numSwaps++;
            std::swap (v[j], v[j + 1]);
            didSwap = true;
          }
          else 
          {
            numCompares++;
          }
    if (!didSwap)
    {
      break;
    }
  }
}

/************************************************************/
//Sorts a vector using the 'insertion' algorithm and returns 
//the number of swaps
 

size_t 
insertionSort (vector<int>& v)
{
  size_t compar = 0;
  size_t i, j;
  int x;

for (i = 1; i < v.size(); ++ i)
{
  x = v[i];
  j = i;
while (j >= 1 && x < v[j - 1])
{
  compar ++;
  v[j] = v[j - 1];
  -- j;
}
  v[j] = x;
}
  return (compar);
}

/************************************************************/
//Sorts a vector using the 'selection' algorithm and returns 
//the amount of comparisons and swaps

void 
selectionSort (vector<int>& v, size_t& numCompares, size_t& numSwaps)
{
  size_t i, j;
  int min;
  for (i = 0; i < v.size() - 1; ++ i)
  {
    min = i;
    for (j = i + 1; j < v.size(); ++ j)
      if (v[j] < v[min]){
          min = j;
          numCompares ++;
      } 
      else
      {
        numCompares ++;
      }   
    std::swap (v[i], v[min]);
    numSwaps ++;
    }  
  }

