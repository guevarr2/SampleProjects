/*main.cpp*/

//
// iClicker Analysis program in modern C++.
//
// Aaron Guevarra
// U. of Illinois, Chicago
// CS 341: Fall 2017
// Project 02
//

#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <functional>
#include <map>

#include "student.h"
#include "session.h"

using namespace std;


//
// FileExists:
//
// Returns true if the file exists, false if not.
//
bool FileExists(string filename)
{
  ifstream file(filename);

  return file.good();
}


// 
// ParseTextFile
//
// Parses the text file containing a list of each clicker file to process
//
// Returns a vector of strings with the file names
//
vector<string> ParseTextFile(string filename)
{
	string line;
	vector<string> filenames;
	ifstream file(filename);

	//
	// read and store filenames from the text tile
	// 
	while (getline(file, line))
	{
		filenames.push_back(line);
	}

	//
	// Echo results to user
	//
	for (string& s : filenames)
	{
		cout << ">>Parsing " << "'" << s << "'..." << endl;
	}
	cout << endl;

	return filenames;
}


//
// FindAttrPosition:
//
// Returns integer string positions of where given attribute value starts and
// ends.  Example: pass " cans=", and you would get back starting and ending
// positions of the value. 
//
// NOTE: if the attribute contains no value, i.e. "", then end will be < start.
//
void FindAttrStartEnd(string s, string attr, int& start, int &end)
{
  //
  // Example: looking for " cans=", so the start of the attribute value
  // is +7 since we have to skip the opening " as well.
  //

  start = s.find(attr);  // find start of attribute:
  start = start + attr.length() + 1;  // account for " that starts the value:
  end = s.find("\"", start);
  end--;
}


//
// ParseQuestion:
//
// Parses the input lines related to one question in the clicker 
// file.  Vector of students is updated based on whether each 
// student answered the question, and whether it was answered
// correctly.
//
void ParseQuestion(string question, ifstream& file, vector<Student>& students)
{
  string line, correctAnswer, answer, id;
  int    start, end;

  //
  // parse question to get correct answer:  <p ...>
  //
  FindAttrStartEnd(question, " cans=", start, end);
  correctAnswer = question.substr(start, end - start + 1);  // e.g. extract A from "A"

  //
  // now start reading the answers entered by students:
  //
  while (getline(file, line))
  {
    if (line.find("</p>") != string::npos)  // end of question
      break;

    // line represents answer from a student's clicker:
    // <v att="4" scr="0.00" ... >
    FindAttrStartEnd(line, " ans=", start, end);
    answer = line.substr(start, end - start + 1);  // NOTE: could be ""

    FindAttrStartEnd(line, " id=", start, end);
    id = line.substr(start, end - start + 1);
    // NOTE: clicker id value starts with #, strip that off:
    id = id.substr(1, id.length() - 1);

    // did they answer correctly?
    bool answered, correct;

    if (answer == "")  // not answered
    {
      answered = false;
      correct = false;
    }
    else  // answered, then check correctness:
    {
      answered = true;

      // does correct answer *contain* student's answer?
      if (correctAnswer.find(answer) == string::npos)  // not correct
        correct = false;
      else
        correct = true;
    }

    //
    // lookup student to see if they already exist in the vector:
    //
    auto iter = find_if(students.begin(), students.end(),
      [=](const Student& s)
      {
        if (s.getClickerID() == id)
          return true;
        else
          return false;
      });

    if (iter == students.end()) // not found, add to vector:
    {
      Student s(id);

      if (answered)
        s.updateNumAnswered(1);
      if (correct)
        s.updateNumCorrect(1);

      students.push_back(s);
    }
    else  // found, update existing student object:
    {
      if (answered)
        iter->updateNumAnswered(1);  // one more answered

      if (correct)
        iter->updateNumCorrect(1);
    }
  }
}


//
// ReadClickerFile
//
// Reads the clicker data into a vector of Students, which is returned.
// For each student, we are collecting the # of questions they answered,
// along with # answered correctly.
//
// Returns the vector of students, as well as session name and # of 
// questions via reference parameters.
//
vector<Student> ReadClickerFile(string filename, string& sessionName, int& numQuestions)
{
  vector<Student> students;
  ifstream file(filename);
  string line;
  int start, end;

  //
  // skip header lines:
  //
  while (getline(file, line))
  {
    if (line.substr(0, 4) == "<ssn")
      break;
  }

  //
  // we now have the start of the clicker session data: <ssn ... >
  //
  // parse session to get session name:
  //
  FindAttrStartEnd(line, " ssnn=", start, end);
  sessionName = line.substr(start-1, end - start + 3);  // include "..."

  //
  // now let's start reading each question:
  //
  while (getline(file, line))
  {
    if (line.substr(0, 5) == "</ssn")
      break;

    ParseQuestion(line, file, students);
    numQuestions++;
  }

  //for (Student& s : students)
  //{
  //  cout << s.getClickerID() << ": " << s.getNumAnswered() << "," << s.getNumCorrect() << endl;
  //}

  return students;
}


//
// GetTotalAnswered:
//
// Traverse the vector to count the number of questions the students have
// answered for that given session
//
int GetTotalAnswered(const vector<Student>& students)
{
	int totalAnswered = 0;

	totalAnswered = accumulate(students.begin(), students.end(), 0,
		[=](int total, const Student& s)
	{
		return total + s.getNumAnswered();
	});


	return totalAnswered;
}


//
// GetTotalCorrect:
//
// Traverse the vector to count the number of questions the students have
// answered correctly for that given session
//
int GetTotalCorrect(const vector<Student>& students)
{
	int totalCorrect = 0;

	totalCorrect = accumulate(students.begin(), students.end(), 0,
		[=](int total, const Student& s)
	{
		return total + s.getNumCorrect();
	});

	return totalCorrect;
}


//
// GetTotalQuestions:
//
// Traverses the session vector to compute the total of questions
//
int GetTotalQuestions(const vector<Session>& sessions)
{
	int totalQuestions = 0;

	totalQuestions = accumulate(sessions.begin(), sessions.end(), 0, 
		[=](int total, const Session& s)
	{
		return total + s.getNumQuestions();
	});

	return totalQuestions;
}


//
// ProcessSessionData: 
//
// Processes all clicker data found in a vector of filenames and store 
// them into a vector of sessions.
//
void ProcessSessionData(const vector<string>& filenames, vector<Session>& sessions)
{
	//
	// Store clicker data:
	//
	for (const string& filename : filenames)
	{
		string name;
		int numQuestions = 0;

		//
		// Read file, collect data into vector of student clickers:
		//
		vector<Student> students = ReadClickerFile(filename, name, numQuestions);
		
		//
		// Store the name, number of questions, total questions answered, 
		// and total questions answered correctly:
		//
		Session session(name);
		session.addStudentVector(students);
		session.updateNumQuestions(numQuestions);
		session.updateNumClickers(students.size());

		int totalAnswered = GetTotalAnswered(students);
		session.updateTotalAnswered(totalAnswered);

		int totalCorrect = GetTotalCorrect(students);
		session.updateTotalCorrect(totalCorrect);

		// Add session to vector:
		sessions.push_back(session);
	}
}


//
// OutputSessionAnalysis:
//
// Outputs the session name, percentage of answered questions, 
// number of questions, number of answers for the answered category.
//
// Outputs the session name, percentage of answered questions correctly, 
// number of questions, number of answers correct for the answered 
// correctly category.
// 
void OutputSessionAnalysis(const vector<Session>& sessions)
{
	//
	// Output the analysis results:
	//
	cout << "**Class Analysis**" << endl;
	cout << ">>Total sessions:  " << sessions.size() << endl;
	cout << ">>Total questions: " << GetTotalQuestions(sessions) << endl;

	//
	// Output the data for questions answered:
	//
	cout << ">>Answered: " << endl;
	for (const Session& s : sessions)
	{
		cout << "  " << s.getName() << ": ";
		cout << s.getAnswerPentage() << "% (";
		cout << s.getNumQuestions() << " questions, ";
		cout << s.getNumClickers() << " clickers, ";
		cout << s.getTotalAnswered() << " answers)";
		cout << endl;
	}

	//
	// Output the data for questions answered correctly:
	//
	cout << ">>Correctly:" << endl;
	for (const Session& s : sessions)
	{
		cout << "  " << s.getName() << ": ";
		cout << s.getPercentCorrect() << "% (";
		cout << s.getNumQuestions() << " questions, ";
		cout << s.getNumClickers() << " clickers, ";
		cout << s.getTotalCorrect() << " correct answers)";
		cout << endl;
	}
}


//
// SearchStudent:
//
// Searches each session using the student's clicker id and outputs
// an analysis based on the search results.
// Returns true if the student was found.
//
//
bool SearchStudent(const vector<Session>& sessions, string id)
{
	//
	// Search for the clicker id:
	//
	bool found = false;
	for (const Session& s : sessions)
	{
		vector<Student> students = s.getStudentVector();
		auto iter = find_if(students.begin(), students.end(),
			[=](const Student& student)
		{
			if (student.getClickerID() == id)
				return true;
			else
				return false;

		});

		//
		// If iter is not at the end, output the student's performance:
		//
		if (iter != students.end())
		{
			float decimal = iter->getNumCorrect() / (float)s.getNumQuestions();
			float percent = decimal * 100;

			cout << s.getName() << ": " << iter->getNumAnswered() <<
				" out of " << s.getNumQuestions() << " answered, " <<
				percent << "% correctly" << endl;

			found = true;
		}
	}

	return found;
}

//
// ProcessStudentAnalysis:
// 
// Prompts the user for a clicker id, and output an analysis of how that 
// student performed acros the different sessions.
//
void ProcessStudentAnalysis(const vector<Session>& sessions)
{
	//
	// Input clicker ids from the user, and for each clicker
	// id, output an analysis of how that student performed
	// across different sessions :
	//
	string id;
	cout << endl << endl;
	cout << "**Student Analysis**" << endl;
	cout << ">> Enter a clicker id (# to quit): ";

	//
	// Get input from user:
	//
	while (getline(cin, id) && id != "#")
	{
		//
		// Search for the clicker id:
		//

		//
		// Search for the clicker id and check if id is found: 
		//
		if (!SearchStudent(sessions, id))
			cout << "** not found..." << endl;

		cout << endl;
		cout << ">> Enter a clicker id (# to quit): ";
	}

	cout << endl;
}


int main()
{
	string textFile = "files.txt"; // file with the list of session filenames

	cout << "**Starting**" << endl;
	if (!FileExists(textFile))
	{
		cout << "Error: '" << textFile << "' not found, exiting..." << endl;
		return -1;
	}

	//
	// Read file, collect filenames to process:
	//
	vector<string> filenames = ParseTextFile(textFile);

	//
	// Parse, and store each session data from each filename:
	//
	vector<Session> sessions;
	ProcessSessionData(filenames, sessions);

	//
	// Outpit Analysis:
	//
	OutputSessionAnalysis(sessions);
	ProcessStudentAnalysis(sessions);

	cout << "**END**" << endl;
	return 0;
}
