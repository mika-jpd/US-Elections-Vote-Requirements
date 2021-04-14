# US Election Vote Requirements
Algorithms and Data Structures Final Project
    
    Grade : 100%
    Average : 69%
   
## Table of Contens
* [Description](#description)
* [Solution](#solution)

## Description

For each state in the United States presidential election, I compiled the information regarding 
*   i) The expected number of people who are projected to vote for Mr. Biden, 
*   ii) The expected number of people who are projected to vote for Mr. Trump and 
*   iii) The number of people who have not made a voting decision yet. 

The technical description of the polls makes it clear that the numbers of the first two categories are not likely to change because they are votes from people with long-time roots in a particular political party. On the other hand, people in the third category are the ones who expressed no preference and did not lean towards either of the major parties. At this point, I realized that it would be great to have a very efficient algorithm to know the minimum number of people that Mr. Biden needs to convince in order to secure the presidency of the United States of America.

Provided are files containing:
```
*       The first line of my file contains a single integer (i.e., num states) that represents the number of states taken into account by a poll.
*     • Following num states lines each contain four integers (separated by spaces) with the following information.
*         1. The number of delegates for a specific state. 
*         2. The number of people who will vote for Mr. Biden in that state. 
*         3. The number of people who will vote for Mr. Trump in that state. 
*         4. The number of people who have not made a voting decision in that state yet.
For each provided file the algorithm calculates the minimum number of people that Mr. Biden would have to convince to earn the US presidency. If it is not possible for Mr. Biden to win the election, the output is the integer −1.
```
## Solution:

I first calculate the number of votes that Biden needs to win in order to and store in an array.
For states whose outcome is still uncertain, I store in different arrays:
```
*       i) the number of electoral votes for each uncertain state
*       ii) the number of votes needed to be convinced for each uncertain state
```
To calculate the minimum number of votes needed per state I use a variant of the dynamic programming solution to the knapsack problem:
```
*        1) I setup an 2d array
*           1.i) the first column contains only zeroes, and represents each undecided state
*           1.ii) the first row, apart from [0][0] contains Integer.MAX_VALUE
*        2) If i is the number of rows and j the number of columns, each position m[i][j] will hold the minimum number of electoral votes from the first jth undecided states in order to be bigger or equal to i.
```
The starting array looks like this:
```
           [0,2147483647, 2147483647,..., 2147483647]
           [0,         0,          0,...,          0]
            .
            .
            .
            [0,         0,          0,...,          0]
```
The crux of my solution algorithm is the following section:
```
for (int i = 1; i <= potential_states; i++) {
	for (int j = 1; j < states_need + 1; j++) {
		if (potential_delegates[i-1] >= j) m[i][j] = Math.min(m[i-1][j], potential_undecided[i-1]);

		else if (  (m[i-1][j] == Integer.MAX_VALUE) && (m[i-1][j-potential_delegates[i-1]] == Integer.MAX_VALUE)  ) {
			m[i][j] = Integer.MAX_VALUE;
		}
		else {
			m[i][j] = Math.min(m[i-1][j], potential_undecided[i-1] + m[i-1][j - potential_delegates[i -1]]);
						
		}
	}
}
```
I start with Integer.MAX_VALUE so that the mnimum required voters needed for a president to get at least more than i votes will always be smaller than the values at row 0 if it is possible for the j undecided states to get at least i number of votes. Futhermore, I also use Integer.MAX_VALUE, as a signal for when the first j states cannot get at least i votes.

