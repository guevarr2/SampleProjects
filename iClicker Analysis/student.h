/*student.h*/

//
// Represents one student, who is identified by 
// their clicker ID.
//
// Aaron Guevarra
// U. of Illinois, Chicago
// CS 341: Fall 2017
// Project 02
//
#pragma once

using namespace std;

class Student
{
private:
  string ClickerID;
  int    NumAnswered;
  int    NumCorrect;

public:
  //
  // default constructor:
  //
  Student(string clickerid)
    : ClickerID(clickerid), NumAnswered(0), NumCorrect(0)
  { }

  //
  // getters and setters:
  //
  string getClickerID() const
  {
    return ClickerID;
  }

  int getNumAnswered() const
  {
    return NumAnswered;
  }

  void updateNumAnswered(int byAmt)
  {
    NumAnswered += byAmt;
  }

  int getNumCorrect() const
  {
    return NumCorrect;
  }

  void updateNumCorrect(int byAmt)
  {
    NumCorrect += byAmt;
  }

};
