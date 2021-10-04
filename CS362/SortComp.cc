/*
  Filename   : Sorts.hpp
  Author     :Quadirah Jones
  Course     : CSCI 362-01
  Assignment : A Problem Of Sorts
  Description: Comparing the running time of different sorting algorithms
*/   
/************************************************************/


// N:       20,000,000            40,000,000             80,000,000    
// ==========================================================================
// Merge    64048.01 (651011265)  131877.89 (1359397628) 273870.38 (2857920165)
// Quick     4735.09 (1382331)      9840.59 (2760819)     20571.12 (5536801)
// Shell    16050.51 (770518533)   31244.10 (1626963986)  67824.87 (18446744072750)
// std       1243.65                2492.45                5145.03

//Merge sort was the slowest out of all four sorts, and seemed to be the most complex
//because of the extra out of place method. Shell sort was the second slowest but performed
//much better than merge. It was no surpise that the std sort was much faster than all of
// them because it is a combination of sorts and quicksort itself is one of them. Quick was not
//extremely far behind standard though because it gets help from insertion sort after the unsorted
//amount of elements are less than 20. 
/************************************************************/
// System includes

#include <iostream>
#include <cstdlib>
#include <string>
#include <algorithm>
#include <random>
#include <vector>

/************************************************************/
// Local includes

#include "Timer.hpp"

/************************************************************/
// Using declarations

using std::ostream;
using std::cout;
using std::endl;
using std::cin;
using std::random_device;
using std::uniform_int_distribution;
using std::mt19937;
using std::swap;
using std::sort;
using std::vector;

/************************************************************/
// Function prototypes/global vars/type definitions

// Perform a merge sort on 'v'
// Return the number of comparisons performed
// Additionally, you'll want a helper function to allow for recursion
size_t
mergeSort (vector<int>& v);

size_t
mergeHelp (vector <int>& v, size_t begin, size_t end);

size_t
outOfPlace_merge(vector <int>& v, size_t begin, size_t end);

// Perform a quicksort on 'v'
// Return the number of comparisons performed
// Additionally, you'll want a helper function to allow for recursion
size_t
quickSort (vector<int>& v);

size_t
quickHelp (vector <int>& v, size_t begin, size_t end);

// Perform an insertion sort from 'begin' up to but NOT including 'end'
// Return the number of comparisons performed
size_t
insertionSort (vector<int>& v, size_t begin, size_t end);

// Perform a Shell sort 
// Return the number of comparisons performed
size_t
shellSort (vector<int>& v);

size_t
partition (vector<int>& v, size_t begin, size_t end);

int
median3 (vector<int>& v, size_t begin, size_t end);

/************************************************************/

int
main (int argc, char* argv[])
{
 cout << "N ==> ";
 size_t N;
 cin >> N;

 vector<int> mergeVec(N);
 random_device rand;
 mt19937 generator(0);
 for (int& elem: mergeVec)
 {
   elem = generator()%999;
 }
      
 vector<int> stdVec(mergeVec);
 vector<int> shellVec(mergeVec);
 vector<int> quickVec(mergeVec);

 Timer<> t;

 t.start();
 size_t mergeCompNum = mergeSort(mergeVec);
 t.stop();
 printf("Merge time: %.2f ms \n", t.getElapsedMs());
 cout << "merge compares:" << mergeCompNum; 
 cout << endl;

 cout << endl;
 t.start();
 size_t quickCompNum = quickSort(quickVec);
 t.stop();
 printf("Quick time: %.2f ms \n", t.getElapsedMs());
 cout << "Quick compares:" << quickCompNum; 
 cout << endl;

 cout << endl;
 t.start();
 size_t shellCompNum = shellSort(shellVec);
 t.stop();
 printf("Shell time: %.2f ms \n", t.getElapsedMs());
 
 cout << "Shell compares:" << shellCompNum;; 
 cout << endl;

 cout << endl;
 
 t.start();
 std::sort(stdVec.begin(), stdVec.end());
 t.stop();
 printf("std time: %.2f ms \n", t.getElapsedMs()); 

 
 cout << endl << "Merge ok? " << std::boolalpha << ( mergeVec == stdVec);
 cout << endl << "Quick ok? " << std::boolalpha << ( quickVec == stdVec);
 cout << endl << "Shell ok? " << std::boolalpha << ( shellVec == stdVec);
 cout << endl;

 return EXIT_SUCCESS;

}
/************************************************************/

// Perform a merge sort on 'v'
// Return the number of comparisons performed
// Additionally, you'll want a helper function to allow for recursion
size_t
mergeSort (vector<int>& v)
{
  return mergeHelp (v, 0, v.size());
}

size_t
mergeHelp (vector <int>& v, size_t begin, size_t end)
{
    if (end - begin > 1) 
    {
    size_t mid = begin + (end - begin) / 2; 
    size_t rh = mergeHelp (v, begin, mid);
    size_t lh =  mergeHelp (v, mid, end);
    size_t last = outOfPlace_merge (v, begin, end);
    return (rh + lh + last);

    }
    return 0;
}

size_t
outOfPlace_merge(vector <int>& v, size_t begin, size_t end)
{
  vector <int> temp;
  size_t comp = 0;
  size_t mid = begin + (end - begin) / 2;

  while (mid < end && begin < mid)
  {
    if (v[begin] < v[mid])
    {
      temp.push_back (v[begin]);
      ++begin;
    }
    else
    {
      temp.push_back (v[mid]);
      ++mid;
    }
    ++comp;
  }

  if (mid == end)
  {
    ++comp;
    for (auto i = begin; i < mid; ++ i)
    {
      ++comp;
      temp.push_back (v[i]);
    }
  }
  else
  {
    ++comp;
    for(auto i = mid; i < end; ++i)
    {
      ++comp;
      temp.push_back (v[i]);
    }
  }
  for (size_t i = begin; i < end; ++ i)
  {
    v[i] = temp [i - begin];
  }
  return comp;
}


// Perform a quicksort on 'v' and return the number of comparisons performed
// Additionally, you'll want a helper function to allow for recursion
size_t
quickSort (vector<int>& v)
  {
    return quickHelp(v, 0, v.size());
  }

// quickSort helper
size_t
quickHelp (vector <int>& v, size_t begin, size_t end)
  {
    size_t comp = 0;
    if ((end - begin) > 20)
    {  
    ++comp;
    if (end - begin > 1) {
      auto i = partition (v, begin, end); 
      // Pivot is placed in v[i]
      comp += quickHelp (v, begin, i);
      comp += quickHelp(v, i + 1, end);
    }
  }
  else
  {
    comp += insertionSort(v, begin , end);
  }
  return comp;
  }

// Perform an insertion sort from 'begin' up to but NOT including 'end'
// Return the number of comparisons performed
size_t
insertionSort (vector<int>& v, size_t begin, size_t end)
{
  int comp = 0;
  for(size_t i = begin; i < end; ++i)
  {
    size_t index = i;
    int elem = v[i];
  while (index >= begin && elem < v[index - 1]) 
  {
      v[index] = v[index - 1];
       ++comp;
      index = index - 1;
  }
  v[index] = elem;
  }
  return comp;  
}

size_t
partition (vector<int>& v, size_t begin, size_t end)
{
  int comp = 0;
  median3 (v, begin, end - 1);
  int pivot = v[end - 2];
  size_t up = begin, down = end - 2;
  while (true) 
  {
    while (v[++up] < pivot) 
    {
      ++comp;
    }
    while (v[--down] > pivot) 
    { 
      ++comp;
    }
    if (up >= down)
    break;
    swap (v[up], v[down]);
  }
  swap (v[end - 2], v[up]);
  return up;
}

int
median3 (vector<int>& v, size_t begin, size_t end)
{
   int center = ( begin + end ) / 2;

   if ( v[ center ] < v[ begin ] )
       swap( v[ begin ], v[ center ]);

   if ( v[ end ] < v[ begin ] )
       swap( v[ begin ], v[ end ]);

   if ( v[ end ] < v[ center ] )
       swap( v[ center ], v[ end ]);
//Places the pivot 1 before the right
   swap( v[ center ], v[ end - 1] );
   return v[ end - 1];
}

// Perform a Shell sort 
// Return the number of comparisons performed
size_t
shellSort (vector<int>& v)
{
  int comp = 0;
  size_t h = 0;
  for (h = 1; h <= v.size() / 6; h = h * 3 + 1){}
    while (h > 0)
    {
      for (size_t i = h; i < v.size(); ++i)
      {
        size_t index = i;
        int elem = v[i]; 

        while (index >= h && elem < v[index - h])
        {
          v[index] = v[index - h];
          index = index - h;
           ++comp;
        }
        v[index] = elem;
      }
      h = h / 3;
    }
    return comp;
}


