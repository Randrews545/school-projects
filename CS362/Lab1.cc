/*
  Filename   : Lab1.cc
  Author     : Ryan Andrews
  Course     : CSCI 362-02
  Assignment : Assignment 2: CD Interest
  Description: Calculates the interest earned on a CD and prints out a table showing 
               the balance, interest, and balance with this added interest
*/

/************************************************************/
// System includes

#include <iostream>
#include <locale.h>
#include <stdio.h>

/************************************************************/
//using declarations

using std::cin;
using std::cout;
using std::endl;

/************************************************************/
//Function prototypes

void 
printIntro();

double 
calcInterest (double balance, double rate);

void 
printRow (int rowNum, double balance, double interest);

void 
printTable (int numRows, double balance, double rate);

/************************************************************/
int 
main (int argc, char* argv[]) 
{       
  int rows;
  double balance, rate;
  setlocale (LC_NUMERIC, "");
  printIntro();

  printf ("%-32s: ", "Please enter the initial balance");
  cin >> balance;
  cout << endl;

  printf ("%-32s: ", "Please enter the interest rate");
  cin >> rate;
  cout << endl;

  printf ("%-32s: ", "Please enter the number of years");
  cin >> rows;
  cout << endl;

  printTable(rows, balance, rate);

  return 0;
}

/************************************************************/
//Prints out the description of what the program will do

void 
printIntro()
{
    cout << "This program will calculate the interest earned" << endl; 
    cout << "  on a CD over a period of several years." << endl;
    cout << endl;
}

/************************************************************/
//Calculates and returns the new amount of interest 
// based on balance and interest rate

double 
calcInterest (double balance, double rate)
{
  return(balance * (rate / 100));
}

/************************************************************/
//Prints out a formatted row using balance, interest, and the new balance

void 
printRow(int rowNum, double balance, double interest)
{
  interest = calcInterest(balance, interest);
  double newBalance = balance + interest;
  printf ("%'-4d", rowNum);
  printf ("%'14.2f", balance);
  printf ("%'14.2f", interest);
  printf ("%'14.2f \n", newBalance);
}

/************************************************************/
//Prints out the first two rows of the table and then calls printRow for every other row

void 
printTable(int numRows, double balance, double rate)
{
  printf ("%-4s", "Year");
  printf ("%14s", "Balance");
  printf ("%14s", "Interest");
  printf ("%14s \n", "New Balance");
  printf ("%-4s", "----");
  printf ("%14s", "-------");
  printf ("%14s", "--------");
  printf ("%14s \n", "-----------");

  for(int i = 1; i<=numRows; ++i) 
  {
    printRow (i, balance, rate);
    balance += calcInterest(balance, rate);
  }

}