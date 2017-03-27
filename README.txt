USAGE:
makefile included(type make in terminal)
_mainTest is included to test certain functions.
5 test files included(t1.txt .... t5.txt).
./exe filename datatype classtype
• fılename: file that contains the sequence to read, 
there is no limit to the number of elements in the sequence.
• Data type: 1 for int, 2 for strings, 3 for doubles
• Class type: 1 for BigramMap , 2 for BigramDyn
For example: myProg datafile 1 2 will read file name datafile that should 
contain integers and my program would use BigramDyn class to run all the tests.
-------------------------------------------------------------------------
A bigram is a specialization of N-Gram which
“is a contiguous sequence of N items from a given data sequence”. 
For the bigram we take N=2. 
For example if we have a sequence of integers {1 4 6 3 7 1 4 7 2} then the number
of {1 4} bigrams is 2,
the number of {4 6} bigrams is 1, and the number of {3 4} bigrams is 0.
Similarly, if the sequence is
{qwe asd fgh sdf sdf} then the number of {qwe asd} bigrams is 0.
-------------------------------------------------------------------------
• readFile: takes a filename as a string parameter, reads the file,
calculates all the bigrams.
Throws exceptions if there are problems with opening and reading the file.
• numGrams: returns the total number of bigrams calculated so far. 
For example, if we read an integer sequence file that contains
{1 234 346 343 7234 341 434 72 234}, then
numGrams() returns 8.
• numOfGrams: takes two elements as bigrams and returns 
the number of that bigram read so far. 
For example, if we read an integer sequence file that contains
 {1 4 6 3 7 1 4 7 2},
then numOfGrams(1,4) returns 2.
• operator<< prints all the bigrams and their occurences in decreasing occurent order.
• maxGrams: returns the bigram that has occurred most frequently. 
For example, if we read an integer sequence file that contains 
{1 4 6 3 7 1 4 7 2}, then maxGrams() returns std::pair<int, int>(4,3).
