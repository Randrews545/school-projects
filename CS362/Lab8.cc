/************************************************************/
//Table of Average Times
//
//N:        20,000,000                40,000,000                 80,000,000
//===========================================================================================
//Merge      2,310.38 (40,000,108)     4,851.13 (80,000,112)     10,113.28 (160,000,116)
//Quick      1,574.34 (22,589,590)     3,302.17 (46,683,568)      6,724.80 (88,129,223)
//Shell      3,650.40 (2,095,041,348)  8,269.88 (4,986,609,597)  19,820.95 (12,221,553,792)             
//std        1,409.85                  2,954.90                     6,100.84
//
//Discussion
// The algorithms all sorted correctly and the times were as expected with quick sort outperforming
// both merge and shell. With merge beating out shell sort by a good amount. 
// Along with that std sort beat out all of them as far as speed unsurprisingly.

/*
  Filename   : Lab8.cc
  Author     : Ryan Andrews
  Course     : CSCI 362-01
  Assignment : Assignment 8: Out of Sorts
  Description: Times three different sorting algorithms and obtains the amount of comparisons
*/

/************************************************************/
// System includes

#include <algorithm>
#include <array>
#include <iostream>
#include <stdio.h>
#include <vector>
#include <random>
#include <cmath>
#include <iomanip>
#include <set>
#include <string>

/************************************************************/
// Local Includes

#include "Timer.hpp"

/************************************************************/
// using declarations

using std::vector;
using std::uniform_int_distribution;
using std::string;
using std::cout;
using std::cin;
using std::endl;
using std::mt19937;
using std::random_device;

/************************************************************/
//Function prototypes

//Starts mergesort and returns number of comparisons
size_t
mergeSort (vector<int>& v);

//Helper for merge sort
size_t
mergeHelp (vector<int>& v, size_t first, size_t last, size_t counter);

//Performs the out of place merge
void
out_of_place_merge(vector<int>& v, size_t first, size_t mid, size_t last, size_t& counter);

//Starts quicksort and returns number of comparisons
size_t
quickSort (vector<int>& v);

//Performs insertion sort
size_t
insertionSort (vector<int>& v, size_t begin, size_t end);

//Performs shell sort and returns number of comparisons
size_t
shellSort (vector<int>& v);

//Performs partition for quicksort
size_t
partition (vector<int>& v, size_t begin, size_t end, size_t& counter);

//Helper for quicksort
size_t
quickHelp (vector<int>& v, size_t first, size_t last, size_t counter);


/************************************************************/
int 
main (int argc, char* argv[]) 
{
    size_t N, mergeCount, quickCount, shellCount;
    cout << "N" << "==> ";
    cin >> N;
    cout << endl;
    Timer<> mergeSet, quickSet, shellSet, stdSet;
    vector<int> mergeVec;
    int number;

    cout << std::fixed;
    cout << std::setprecision(2);
    

    random_device device;
    mt19937 generator(device());
    uniform_int_distribution<int> distribution(0, 1000000000);

    for (size_t i = 0; i<N; ++i)
    {
        number = distribution(generator);
        mergeVec.push_back(number);
    }

    vector<int>quickVec (mergeVec);
    vector<int>shellVec (mergeVec);
    vector<int>stdVec (mergeVec);

    mergeSet.start();
    mergeCount = mergeSort(mergeVec);
    mergeSet.stop();
    quickSet.start();
    quickCount = quickSort(quickVec);
    quickSet.stop();
    shellSet.start();
    shellCount = shellSort(shellVec);
    shellSet.stop();
    stdSet.start();
    std::sort (stdVec.begin(), stdVec.end());
    stdSet.stop();
    
    double mergeRad = mergeSet.getElapsedMs();
    double quickRad = quickSet.getElapsedMs();
    double shellRad = shellSet.getElapsedMs();
    double stdRad = stdSet.getElapsedMs();

    cout << "Merge time: " << mergeRad << " ms" << endl;
    cout << "Merge compares: " << mergeCount << endl;
    cout << endl;
    cout << "Quick time: " << quickRad << " ms" << endl;
    cout << "Quick compares: " << quickCount << endl;
    cout << endl;
    cout << "Shell time: " << shellRad << " ms" << endl;
    cout << "Shell compares: " << shellCount << endl;
    cout << endl;
    cout << "std   time: " << stdRad << " ms" << endl;
    cout << endl;

    if (mergeVec == stdVec)
    {
        cout << "Merge ok? " << "true" << endl;
    }
    else
    {
        cout<< "Merge ok? " << "false" << endl;
    }

    if (quickVec == stdVec)
    {
        cout << "Quick ok? " << "true" << endl;
    }
    else
    {
        cout<< "Quick ok? " << "false" << endl;
    }

    if (shellVec == stdVec)
    {
        cout << "Shell ok? " << "true" << endl;
    }
    else
    {
        cout<< "Shell ok? " << "false" << endl;
    }

}

/************************************************************/
//Starts mergesort on 'v' and returns the number of comparisons

size_t
mergeSort (vector<int>& v)
{
    size_t first = 0, last = v.size() - 1;
    size_t counter = 0; 
    counter = mergeHelp(v, first, last, counter);
    return counter;
}

/************************************************************/
//Helper for mergesort

size_t
mergeHelp (vector<int>& v, size_t first, size_t last, size_t counter)
{
    if (first < last) 
    {
        size_t mid = (last + first)/2;
        mergeHelp (v, first, mid, counter);
        mergeHelp (v, mid + 1, last, counter);
        out_of_place_merge (v, first, mid, last, counter);
    }
    return counter;
}


/************************************************************/
//Performs out of place merge on range

void
out_of_place_merge(vector<int>& v, size_t first, size_t mid, size_t last, size_t& counter)
{
    size_t x = first, y = mid + 1, z = 0;
    vector<int> tempVec(last - first + 1, 0);  
  
    ++counter;

    while (x <= mid && y <= last)
	{
        ++counter;
		if (v[x] < v[y])
		{
			tempVec[z] = v[x];
			++x;
			++z;
		}
		else
		{
		    tempVec[z] = v[y];
			++y;
			++z;
		}
	}
    ++counter;
	while (x <= mid)
	{
        ++counter;
		tempVec[z] = v[x];
		++x;
		++z;
	}
    ++counter;
	while (y <= last)
	{
        ++counter;
		tempVec[z] = v[y];
		++y;
		++z;
	}
 
	for (x = first; x <= last; ++x)
	{
		v[x] = tempVec[x-first];
	}
} 


/************************************************************/
//Starts quicksort

size_t
quickSort (vector<int>& v)
{
    size_t first = 0, last = v.size()-1;
    size_t counter = 0;
    if(v.size() <= 20)
    {
    counter = insertionSort(v,first, last);
    }
    counter = quickHelp(v, first, last, counter);
    return counter;
}


/************************************************************/
//Helper for quicksort

size_t
quickHelp (vector<int>& v, size_t first, size_t last, size_t counter)
{
    int lower = first, upper = last;
    counter++;
    if (upper - lower <= 20 && upper - lower > 0)
    {
        counter = insertionSort(v,first, last);
        return counter;
    }
    if (lower < upper)
    {
    auto i = partition(v, first, last, counter);
    quickHelp(v, first, i, counter);
    quickHelp(v, i + 1, last, counter);
    }
    return counter;
}
/************************************************************/
//Performs partition on range

size_t
partition (vector<int>& v, size_t begin, size_t end, size_t& counter)
{
    int pivot = v[begin];
    int low = begin - 1;
    int high = end + 1;
    int temp;

        for(;;) 
        {
            do 
            {
                ++low;
                ++counter;
            }
            while (v[low] < pivot);

            do 
            {
                --high;
                ++counter;
            }
            while (v[high] > pivot);

            ++counter;
            
            if (low >= high) {
                return high;
            }

            temp = v[low];
            v[low] = v[high];
            v[high] = temp;

        }
}


/************************************************************/
//Perfoms insertion sort

size_t
insertionSort (vector<int>& v, size_t begin, size_t end)
{
  size_t compare = 0;
  size_t x, y;
  int tempNum;
for (x = begin; x < end; ++x)
{
  tempNum = v[x];
  y = x;
while (y >= begin && tempNum < v[y - 1])
{
  ++compare;
  v[y] = v[y - 1];
  --y;
}
  v[y] = tempNum;
}
  return (compare);
}


/************************************************************/
//Performs shell sort

size_t
shellSort (vector<int>& v)
{
    size_t counter = 0, x, y;
    size_t n = v.size();
    int tempNum;

    for(size_t movement = n/2; movement > 0; movement = movement/2)
    {   
    for(x = movement; x<n; ++x)
    {
        tempNum = v[x];
        for(y = x; y >= movement; y-=movement)
        {
            ++counter;
            if(tempNum < v[y-movement])
            {
                v[y] = v[y-movement];
            }
            else
            {
                break;
            }
        }
        v[y] = tempNum;
    }
    }
        return counter;
    }