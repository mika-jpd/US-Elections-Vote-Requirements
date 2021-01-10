# US Election Vote Requirements
Algorithms and Data Structures Final Project
    taught by Dr. Jérôme Waldispühl (McGill University)
    
    Grade : 100%
    Average : 69%

Instructions (as described by the instructor):

For each state in the United States presidential election, I compiled the information regarding 
    i) The expected number of people who are projected to vote for Mr. Biden, 
    ii) The expected number of people who are projected to vote for Mr. Trump and 
    iii) The number of people who have not made a voting decision yet. 
The technical description of the polls makes it clear that the numbers of the first two categories are not likely to change because they are votes from people with long-time roots in a particular political party. On the other hand, people in the third category are the ones who expressed no preference and did not lean towards either of the major parties. At this point (also of my procrastination), I realized that it would be great to have a very efficient algorithm to know the minimum number of people that Mr. Biden needs to convince in order to secure the presidency of the United States of America.

The idea is that I would provide you the files (some of them will be open cases and others will be blind cases) containing the following information:
The first line of my file contains a single integer (i.e., num states) that represents the number of states taken into account by a poll.
      • Following num states lines each contain four integers (separated by spaces) with the following information.
          1. The number of delegates for a specific state. 
          2. The number of people who will vote for Mr. Biden in that state. 
          3. The number of people who will vote for Mr. Trump in that state. 
          4. The number of people who have not made a voting decision in that state yet.
For each provided file you must calculate the minimum number of people that Mr. Biden would have to convince to earn the US presidency. If it is not possible for Mr. Biden to win the election, you must output the integer −1.

No further instruction was given.

Solution:

I first calculate the number of votes that Biden needs to win in order to and store in an array.
For states whose outcome is still uncertain, I store in different arrays:
        i) the number of electoral votes for each uncertain state
        ii) the number of votes needed to be convinced for each uncertain state
To calculate the minimum number of votes needed per state I use a variant of the dynamic programming solution to the knapsack problem:
         1) I setup an 2d array
            1.i) the first row contains only zeroes,
            1.ii) the first column contains Integer.MAX_VALUE
         2) If i is the number of columns and j the number of columns, each position m[i][j] will hold the minimum number of
         electoral votes from the first jth undecided states in order to be bigger or equal to i.
         
        
