//
// My F# library for exam score analysis
//
// Aaron Guevarra
// U. of Illinois, Chicago
// CS 341, Fall 2017
// Porject 04
//
module MyLibrary

#light

//
// InputScores
//
// Given the complete filepath to a text file of exam scores, 
// inputs the scores and returns them as a list of integers.
//
let InputScores filepath = 
  let L = [ for line in System.IO.File.ReadAllLines(filepath) -> line ]
  List.map (fun score -> System.Int32.Parse(score)) L


// _numScores
//
// Helper function for NumScores
//
let rec _numScores L total =
  match L with
  | []     -> total
  | hd::tl -> _numScores tl (total + 1)

//
// NumScores
//
// Recursively counts the # of scores in the list.
//
let rec NumScores L = 
  _numScores L 0


//
// getSmaller
//
// Returns the smaller number
//
let getSmaller x y =
  if (x < y) then x
  else y


// 
// _findMin
//
// FindMind helper: Recursively finds the smallest 
// value in the list.
//
let rec _findMin L minSoFar =
  match L with
  | []     -> minSoFar
  | hd::tl -> _findMin tl (getSmaller hd minSoFar)


//
// FindMin
//
// Recursively finds the min score in the list.
//
let rec FindMin L = 
  _findMin L L.Head


//
// getLarger
//
// Returns the smaller number
//
let getLarger x y =
  if (x > y) then x
  else y


// 
// _findMax
//
// FindMax helper: Recursively traverse the list to find
// the largest value
//
let rec _findMax L maxSoFar =
  match L with
  | []     -> maxSoFar
  | hd::tl -> _findMax tl (getLarger hd maxSoFar)

//
// FindMax
//
// Recursively finds the max score in the list.
//
let rec FindMax L = 
  _findMax L L.Head


//
// _sum
// 
// Sum helper: Recursively traverse the list to count
// add each head to the total
//
let rec _sum L total =
  match L with
  | [] -> total
  | hd::tl -> _sum tl (hd + total)

// 
// Sum
// 
// Computes the sum of the values in the list
//
let rec Sum L =
  _sum L 0


//
// Average
//
// Computes the average of a non-empty list of integers;
// the result is a real number (not an integer).
//
let Average L = 
  match L with
  | [] -> 0.0
  | hd::tl -> (float (Sum L)) / (float (NumScores L))


//
// _median
//
// Median helper: 
//
let rec _median L skip isEven =
  match skip with
  | 0 when isEven -> let first = List.head L
                     let second = List.head (List.tail L)
                     (first+second) / 2
  | 0 -> List.head L
  | _ -> _median (List.tail L) (skip-1) isEven


//
// Median
//
// Computes the median of a non-empty list of integers;
// the result is a real number (not an integer) since the 
// median may be the average of 2 scores if the # of scores
// is even.
//
let Median L = 
  let R = List.sort L
  let skip = ((List.length R) - 1) / 2
  let isEven = ((List.length R) % 2) = 0
  _median R skip isEven


//
// StdDev
//
// Computes the standard deviation of a complete population
// defined by the integer list L.  Returns a real number.
//
let StdDev L = 
  let values = List.map (fun s -> (float s)) L
  let mean = List.average values
  let diffs = List.map (fun x -> System.Math.Pow(x - mean, 2.0)) values
  System.Math.Sqrt( List.average diffs )


//
// Histogram
//
// Returns a list containing exactly 5 integers: [A;B;C;D;F].
// The integer A denotes the # of scores in L that fell in the
// range 90-100, inclusive.  B is the # of scores that fell in
// the range 80-89, inclusive.  C is the range 70-79, D is the
// range 60-69, and F is the range 0-59.
//
let Histogram L = 
  let numA = List.sumBy (fun x -> if (x <= 100) && (x > 89) then 1 else 0) L
  let numB = List.sumBy (fun x -> if (x < 90) && (x > 79) then 1 else 0) L
  let numC = List.sumBy (fun x -> if (x < 80) && (x > 69) then 1 else 0) L
  let numD = List.sumBy (fun x -> if (x < 70) && (x > 59) then 1 else 0) L
  let numF = List.sumBy (fun x -> if x < 60 then 1 else 0) L

  [numA; numB; numC; numD; numF]


//
// _trend
//
// Trend helper function. Recursively compares the head of each list to
// determine a pattern (trend)
//
let rec _trend L1 L2 L3 trendSoFar =
  match L1, L2, L3 with
  | [], [], [] -> List.rev trendSoFar
  | _, [], []  -> []
  | [], _, []  -> []
  | [], [], _  -> []
  | _, _, []   -> []
  | _, [], _   -> []
  | [], _, _   -> []
  | hd1::tl1, hd2::tl2, hd3::tl3 ->
    if ((hd1 < hd2) && (hd2 < hd3)) then
      _trend tl1 tl2 tl3 ('+'::trendSoFar)
    elif (hd1 > hd2 && hd2 > hd3) then
      _trend tl1 tl2 tl3 ('-'::trendSoFar)
    else
      _trend tl1 tl2 tl3 ('='::trendSoFar)


//
// Trend
//
// Trend is given 3 lists of integer scores:  L1, L2, L3.  The lists are 
// non-empty, and |L1| = |L2| = |L3|.  L1 are the scores for exam 01, L2
// are the scores for exam 02, and L3 are the scores for exam 03.  The
// lists are in "parallel", which means student i has their scores at 
// position i in each list.  Example: the first exam in each list denote
// the exams for student 0.
//
// Trend returns a new list R such that for each student, R contains a '+'
// if the exam scores were score1 < score2 < score3 --- i.e. the scores
// are trending upward.  R contains a '-' if score1 > score2 > score3, i.e.
// the scores are trending downward.  Otherwise R contains '=' (e.g. if
// score1 < score2 but then score2 > score3).  
//
let Trend L1 L2 L3 =
  _trend L1 L2 L3 []