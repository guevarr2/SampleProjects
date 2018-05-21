/*session.h*/

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

#include <string>

using namespace std;

class Session
{
private:
	string Name;
	int    NumQuestions;
	int	   NumClickers;
	int    TotalAnswered;
	int    TotalCorrect;

	vector<Student> Students;

public:
	//
	// default constructor
	//
	Session(string name)
		: Name(name), NumQuestions(0), NumClickers(0), 
		TotalAnswered(0), TotalCorrect(0)
	{}

	//
	// getters and setters:
	//
	string getName() const
	{
		return Name;
	}

	int getNumQuestions() const
	{
		return NumQuestions;
	}

	int getNumClickers() const
	{
		return NumClickers;
	}

	int getTotalAnswered() const
	{
		return TotalAnswered;
	}

	int getTotalCorrect() const
	{
		return TotalCorrect;
	}

	vector<Student> getStudentVector() const
	{
		return Students;
	}

	//
	// Returns the percentage of students who answered 
	// in the session
	//
	float getAnswerPentage() const
	{
		float total = NumQuestions * NumClickers;
		float percent = (TotalAnswered / total) * 100;

		return percent;
	}

	//
	// Returns the percentage of students who answered
	// correctly in the session
	//
	float getPercentCorrect() const
	{
		float total = NumQuestions * NumClickers;
		float percent = (TotalCorrect / total) * 100;

		return percent;
	}

	void updateNumQuestions(int amt)
	{
		NumQuestions += amt;
	}

	void updateNumClickers(int amt)
	{
		NumClickers += amt;
	}

	void updateTotalAnswered(int amt)
	{
		TotalAnswered += amt;
	}

	void updateTotalCorrect(int amt)
	{
		TotalCorrect += amt;
	}

	void addStudentVector(vector<Student> students)
	{
		Students = students;
	}
};