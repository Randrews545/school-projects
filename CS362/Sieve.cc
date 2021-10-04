/*
  Filename   : Lab4.cc
  Author     : Ryan Andrews
  Course     : CSCI 362-01
  Assignment : Assignment 6: Sieve
  Description: 
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

using std::set;
using std::string;
using std::cout;
using std::cin;
using std::vector;
using std::endl;

/************************************************************/
//Function prototypes

set<unsigned>
sieveSet (unsigned N);

set<unsigned>
sieveVector (unsigned N);

/************************************************************/
int 
main (int argc, char* argv[]) 
{
    string arg1 (argv[1]);
    string arg2 (argv[2]);
    unsigned int N = stoul (arg2);
    set<unsigned> primeSet;
    Timer<> tSet;

    cout << std::fixed;
    cout << std::setprecision(2);
    
    if (arg1 == "set")
    {
        tSet.start();
        primeSet = sieveSet(N);
        tSet.stop();
        double tRad = tSet.getElapsedMs();
        cout << "Pi [" << N << "] = " << primeSet.size() << " (using a " << arg1 << ")" <<endl; 
        cout << "Time : " << tRad << endl;
    }
    
    else if(arg1 == "vector")
    {
        tSet.start();
        primeSet = sieveVector(N);
        tSet.stop();
        double tRad = tSet.getElapsedMs();
        cout << "Pi [" << N << "] = " << primeSet.size() << " (using a " << arg1 << ")" <<endl; 
        cout << "Time : " << tRad << endl;
    }

}

/************************************************************/
//
set<unsigned>
sieveSet (unsigned N)
{
    set<unsigned> bigSet;
    set<unsigned>::iterator it;
    unsigned int x = 2;
    unsigned int next;

    for (unsigned int i = 2; i <= N; ++i)
    {
        bigSet.insert(i);
    }

    for (it = bigSet.begin(); it != bigSet.end(); ++it)
    {
        x = *it;
        next = *it * x;
            while ((next) <= N)
            {
                if (bigSet.find(next) != bigSet.end())
                {
                bigSet.erase(bigSet.find(next));
                ++x;
                next = *it * x;
                }
                else
                {
                    ++x;
                    next = *it * x;
                }
            }
    }
    return bigSet;
}
/************************************************************/
//

set<unsigned>
sieveVector (unsigned N)
{
    set<unsigned> bigSet, finalSet;
    set<unsigned>::iterator it;
    vector<bool> v;
    unsigned int x = 2;
    unsigned int next;

    for (unsigned int i = 2; i <= N; ++i)
    {
        bigSet.insert(i);
    }

    for (size_t i = 2; i<=N; ++i)
    {
        v.push_back(true);
    }

    for (it = bigSet.begin(); it != bigSet.end(); ++it)
    {
        x = *it;
        next = *it * x;
            while ((next) <= N)
            {
                if (bigSet.find(next) != bigSet.end())
                {
                v[next] = false;
                ++x;
                next = *it * x;
                }
                else
                {
                    ++x;
                    next = *it * x;
                }
            }
    }

    for(unsigned int j = 2; j < v.size(); ++j)
    {
        if(v[j] == true)
        {
            finalSet.insert(*bigSet.find(j));
        }
    }
    return finalSet;
}

/************************************************************/
//Table of Average Times
//
//N         10,000,000     20,000,000     40,000,000
//==================================================
//set         9,360.29      20,685.40      46,438.91
//vector     32,097.11      71,934.35     165,697.27
//
//
//Discussion
//Set performs better overall being almost 3-4 times as fast
//as vector during the tests. The first reason is that Set
//does not have to create a vector. This already makes Vector
//more complex but the second reason makes it even moreso.
//This second reason is that Vector needs to create a whole
//new Set to return to main.