# 1. Subsequence #

### General description ###
Given two sequences {X} and {Y} of arbitrary elements (java.lang.Object).  
Determine whether {X} can be built by removing some elements from {Y} without changing the order.

### Input and expected output ###
* Input : List X and List Y
* Output : boolean, true if {X} can be built from {Y}, false otherwise

### Example ###
{X} : "A", "B", "C", "D"  
{Y} : ~~"BD"~~, "A", ~~"ABC"~~, "B", ~~"M"~~, ~~"D"~~, ~~"M"~~,"C", ~~"DC"~~, "D"


# 2. Pyramid builder #

### General description ###

Your solution should build a pyramid from given input list of integer values.
Numbers are to be sorted ascending from top to bottom, from left to right.  
Empty spaces are to be filled with zeros.
In order to make the pyramid symmetric input numbers should alternate with zeros.

### Input and expected output ###
* Input : List with integer values
* Output : 2D array with the pyramid or CannotBuildPyramidException if it's not possible to build one

### Example ###
Input A:  
{2, 4, 3, 5, 6, 1}  
Output pyramid:  
[0,0,**1**,0,0]  
[0,**2**,0,**3**,0]  
[**4**,0,**5**,0,**6**]

# 3. Calculator #

### General description ###
Write a calculator for evaluating arithmetic expressions.  
An expression can consist of:
* Digits (0-9)
* Dots as decimal marks (valid example: 100.02, not valid example : 100..02)
* Parentheses
* Mathematical symbols (allowed are : "+", "-", "*", "/")

Rounding is to be performed to 4 decimal places, only the final result is to be rounded.
Example: 102.12356 -> 102.1236

### Input and expected output ###
* Input : String containing arithmetic expression
* Output : evaluation result or null if the expression cannot be evaluated

### Example ###
Input: (1+38)*4-5  
Output: 151