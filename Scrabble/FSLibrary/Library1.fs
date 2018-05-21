//
// My F# library for scrabble word generation
//
// Aaron Guvarra
// U. of Illinois, Chicago
// CS 341, Fall 2017
// Project #05
//
module MyLibrary

#light


//
// explode a string into a list of characters.
// Example: "cat" -> ['c'; 'a'; 't']
//
let explode(s:string) =
  [ for c in s -> c ]


//
// implode a list L of characters back into a string.
// Example: implode ['c'; 'a'; 't'] -> "cat"
//
let implode L =
  let sb = System.Text.StringBuilder()
  let ignore = List.map (fun c -> sb.Append (c:char)) L
  sb.ToString()


//
// Initialize:
//
// This function is called ONCE at program startup to initialize any
// data structures in the library.  We use this function to input the
// Scrabble dictionary and build a list of legal Scrabble words.
//
let mutable WordList = []

let Initialize folderPath =
  let alphabetical = System.IO.Path.Combine(folderPath, "alphabetical.txt")
  WordList <- [ for line in System.IO.File.ReadAllLines(alphabetical) -> line ]
  printfn "%A" (List.length WordList)


//
// remove:
//
// Removes an item from a list
//
let rec _remove item list listSoFar =
  match list with
  | []                    -> List.rev listSoFar
  | hd::tl when hd = item -> List.append (List.rev listSoFar) tl
  | hd::tl                -> _remove item tl (hd::listSoFar)

let rec remove item list = 
  _remove item list []


//
// PF:
//
// Determine if the word can be spelled with the given letters
// 
let rec _PF word letters =
  match word with
  | []     -> true
  | hd::tl -> 
    if List.contains hd letters then 
      _PF tl (remove hd letters)
    else
      false

let rec PF word letters =
  let w = explode word
  let l = explode letters
  _PF w l


//
// possibleWords:
//
// Finds all Scrabble words in the Scrabble dictionary that can be 
// spelled with the given letters.  The words are returned as a list
// in alphabetical order.
//
// Example:  letters = "tca" returns the list
//   ["act"; "at"; "cat"; "ta"]
//
let possibleWords letters = 
  List.filter (fun word -> PF word letters) WordList


//
// getScore:
// 
// Returns the score for a given letter (char)
let rec getScore char = 
  if char = 'a' || char = 'e' || char = 'i' || char = 'l' || char = 'n' || 
     char = 'o' || char = 'r' || char = 's' || char = 't' || char = 'u' 
    then 1
  elif char = 'd' || char = 'g' 
    then 2
  elif char = 'b' || char = 'c' || char = 'm' || char = 'p'
    then 3
  elif char = 'f' || char = 'h' || char = 'v' || char = 'w' || char = 'y'
    then 4
  elif char = 'k'
    then 5
  elif char = 'j' || char = 'x'
    then 8
  elif char = 'q' || char = 'z'
    then 10
  else
    0


//
// scoreWord:
// 
// Determines the score for a given word
//
let rec _scoreWord word score = 
  match word with
  | [] -> score
  | hd::tl -> _scoreWord tl (score + getScore hd)

let rec scoreWord word = 
  let w = explode word
  _scoreWord w 0


// 
// scoreList:
//
// Traverses through a list of strings of words then
// Creates a list of tuples in the the form of (word, score)
//
let rec _scoreList wordList tupleList =
  match wordList with
  | []     -> List.rev tupleList
  | hd::tl -> _scoreList tl ( (hd, scoreWord hd) :: tupleList )

let rec scoreList wordList = 
  _scoreList wordList []


//
// wordsWithScores:
//
// Finds all Scrabble words in the Scrabble dictionary that can be 
// spelled with the given letters.  The words are then "scored"
// based on the value of each letter, and the results returned as
// a list of tuples in the form (word, score).  The list is ordered
// in descending order by score; if 2 words have the same score,
// they are ordered in alphabetical order.
//
// Example:  letters = "tca" returns the list
//   [("act",5); ("cat",5); ("at",2); ("ta",2)]
//
let wordsWithScores letters =
  let wordList = possibleWords letters
  let wordScores = scoreList wordList
  List.sortBy (fun (_,y) -> -y) wordScores


//
// patternMatch:
//
// Determine if the word can be spelled with the given letters
// Except star(*) is always a valid character, but checks if
// the chacter in the word is in the list of letters
// 
let rec _patternMatch word pattern letters =
  match word, pattern, letters with
  | [], [], [] -> true
  | _,  [], [] -> false
  | [],  _, [] -> false
  | [], [],  _ -> true
  |  _,  _, [] -> false
  | [],  _,  _ -> true
  |  _, [],  _ -> true
  | hd1::tl1, hd2::tl2, hd3::tl3 -> 
    if hd1 = hd2 then
      _patternMatch tl1 tl2 letters
    elif hd2 = '*' && (List.contains hd1 letters) then
      _patternMatch tl1 tl2 (remove hd1 letters)
    else
      false

let rec patternMatch word pattern letters = 
  _patternMatch (explode word) (explode pattern) (explode letters)

 
//
// sameLen:
//
// Determines if two strings are the same length
//
let rec sameLen word1 word2 = 
  if String.length word1 = String.length word2 then
    true
  else
    false

//
// wordsThatFitPattern:
//
// Finds all Scrabble words in the Scrabble dictionary that can be 
// spelled with the given letters + the letters in the pattern, such
// that those words all fit into the pattern.  The results are 
// returned as a list of tuples (word, score), in descending order by
// score (with secondary sort on the word in alphabetical order).
//
// Example:  letters = "tca" and pattern = "e**h" returns the list
//   [("each",9); ("etch",9); ("eath",7)]
//
let wordsThatFitPattern letters pattern = 
  // get all the words with the same length as the pattern
  let equalLen = List.filter (fun word -> sameLen word pattern) WordList

  // pattern match each word with equal length to the pattern and containing the given letters
  let samePattern = List.filter (fun word -> patternMatch word pattern letters) equalLen

  // create and sort tuple list 
  let wordsThatFit = scoreList samePattern 
  List.sortBy (fun (_,y) -> -y) wordsThatFit 