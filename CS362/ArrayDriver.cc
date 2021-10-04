/*
  Filename   : ArrayDriver.cc
  Author     : Gary M. Zoppetti
  Course     : CSCI 362-01
  Assignment : N/A
  Description: Test some, but NOT ALL, methods of the Array class.
*/   

/************************************************************/
// System includes

#include <cstdlib>
#include <iostream>
#include <string>
#include <iterator>
#include <sstream>
#include <cassert>

/************************************************************/
// Local includes

#include "Array.hpp"

/************************************************************/
// Using declarations

using std::cin;
using std::cout;
using std::endl;
using std::ostream_iterator;
using std::string;
using std::ostringstream;

/************************************************************/
// Function prototypes/global vars/typedefs

void
printTestResult (const string& test,
		 const string& expected,
		 const ostringstream& actual);

/************************************************************/

int      
main (int argc, char* argv[]) 
{        
  Array<int> A;

  // For holding the actual result
  ostringstream output;
  // Put the actual result into the output stream
  output << A;
  printTestResult ("no-arg ctor", "[ ]", output);

  // Must clear the output stream each time
  output.str ("");
  output << A.empty ();
  // "1" for true, "0" for false
  printTestResult ("empty", "1", output);
  
  A.push_back (5);
  A.push_back (10);
  A.push_back (15);

  output.str ("");
  output << A;
  printTestResult ("push_back", "[ 5 10 15 ]", output);

  output.str ("");
  output << A.size ();
  printTestResult ("size", "3", output);
  
  A.pop_back ();
  A.pop_back ();

  output.str ("");
  output << A;
  printTestResult ("pop_back", "[ 5 ]", output);

  /************************************************************/
  // Convert the following tests to use printTestResult
  /************************************************************/
  for (int i = 0; i < 10; ++i)
    A.insert (A.begin (), i);
  
  output.str ("");
  output << A;
  printTestResult ("insert", "[ 9 8 7 6 5 4 3 2 1 0 5 ]", output);

  for (Array<int>::iterator i = A.begin (); i != A.end (); )
    i = A.erase (i);

  output.str ("");
  output << A;
  printTestResult ("erase", "[ ]", output);

  // Size ctor, with a fill value. 
  Array<int> B (3, 9);
  output.str ("");
  output << B;
  printTestResult ("Size ctor", "[ 9 9 9 ]", output);

  // Range ctor. 
  Array<int> C (B.begin (), B.begin () + 2);
  output.str ("");
  output << C;
  printTestResult ("Range ctor", "[ 9 9 ]", output);

  // Assignment operator. 
  B = A;
  output.str ("");
  output << B;
  printTestResult ("Assignment operator", "[ ]", output);

  for (int i = 0; i < 5; ++i)
    B.insert (B.begin (), i);
  output.str ("");
  output << B;
  printTestResult ("insert", "[ 4 3 2 1 0 ]", output);

  /************************************************************/
  // START WRITING YOUR TESTS HERE
  /************************************************************/
  // Test range ctor (a different case than I test above)

  Array<int> D (B.begin (), B.begin () + 4);
  output.str ("");
  output << D;
  printTestResult ("Range ctor", "[ 4 3 2 1 ]", output);

  Array<int> F (D.begin (), D.begin () + 2);
  output.str ("");
  output << F;
  printTestResult ("Range ctor", "[ 4 3 ]", output);

  // Test copy ctor
  Array<int> G(B);
  output.str ("");
  output << G;
  printTestResult ("Copy ctor", "[ 4 3 2 1 0 ]", output);

  // Test capacity
  G.push_back(8);
  G.push_back(6);
  G.push_back(7);
  G.push_back(5);
  G.push_back(3);
  G.push_back(0);
  G.push_back(9);
  output.str ("");
  output << G.capacity();
  printTestResult ("Capacity increase", "20", output);

  // Test subscript operator
  output.str ("");
  output << G[0];
  printTestResult ("Subscript operator", "4", output);

  // Test capacity
  G.reserve(100);
  output.str ("");
  output << G.capacity();
  printTestResult ("Capacity increase", "100", output);
  G.reserve(50);
  output.str ("");
  output << G.capacity();
  printTestResult ("Capacity unchanged", "100", output);

  //Test resize
  G.resize(20, 0);
  output.str ("");
  output << G.size();
  printTestResult ("Size increase", "20", output);
  G.resize(5,0);
  output.str ("");
  output << G.size();
  printTestResult ("Size decrease", "5", output);
}

/************************************************************/

void
printTestResult (const string& test,
		 const string& expected,
		 const ostringstream& actual)
{
  cout << "Test: " << test << endl;
  cout << "==========================" << endl;
  cout << "Expected: " << expected << endl;
  cout << "Actual  : " << actual.str () << endl;
  cout << "==========================" << endl << endl;

  // Ensure the two results are the same
  assert (expected == actual.str ());
}

/************************************************************/