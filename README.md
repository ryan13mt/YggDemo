# YggDemo


## Task 1

Please run RoundServiceTestTask1.getFullGamePlaythroughExpectedValue(). This test will create a list ten million randomized choices for a full game.
Each entry will have these:

1. First main round
2. First bonus round
3. Second main round
4. Second bonus round without the possibility of "Second Chance"

The game logic is found in the class RoundServiceTask1.

Since this was a test i found it simpler and cheaper to just create a full entry even tho most of them will stop half way through or when a game over appears. The game logic will take care of stopping and returning the reward without having to go through the redundant choices.

For example each entry will contain:

[FIVE, FIVE, FIVE, TWENTY, TWENTY, GAMEOVER, FIVE, GAMEOVER, GAMEOVER, EXTRALIFE, ONEHUNDRED, FIVE],TWENTY,[GAMEOVER, FIVE, TWENTY, GAMEOVER, GAMEOVER, FIVE, FIVE, FIVE, FIVE, ONEHUNDRED, EXTRALIFE, TWENTY],TEN

Splitting it up gives us:

1. First main round: [FIVE, FIVE, FIVE, TWENTY, TWENTY, GAMEOVER, FIVE, GAMEOVER, GAMEOVER, EXTRALIFE, ONEHUNDRED, FIVE]
2. First bonus round: TWENTY
3. Second main round: [GAMEOVER, FIVE, TWENTY, GAMEOVER, GAMEOVER, FIVE, FIVE, FIVE, FIVE, ONEHUNDRED, EXTRALIFE, TWENTY]
4. Second bonus round without the possibility of "Second Chance": TEN

As can be seen here, the game will stop on the 6th choice(the first GAMEOVER) in the first round and then adding twenty to the reward due to the first bonus round and that's it. It will stop there and not continue.

The test will take care of putting all ten million rewards in a list and then calculating the average.
The expected value is around the 73.528 region which comes to 73.5.



## Task 2

Please run RoundServiceTestTask2.calculateExpectedValue(). This test will calculate the expected value of the game.

The game logic is found in the class RoundServiceTask2. This was done separate from Task 1 only to avoid adding unnecessary code for Task 1. The same logic still applies but the code does further calculations and returns it.

Here is the reasoning and steps i took to calculate the expected value of the game:

1. Getting all the possible combinations of the twelve boxes for the FIRST MAIN ROUND ONLY. This was done with the help of a library called combinatorics3.
 * Sample input: [ONEHUNDRED, TWENTY, TWENTY, FIVE, GAMEOVER, GAMEOVER, FIVE, EXTRALIFE, GAMEOVER, FIVE, FIVE, FIVE]
2. Running all these combinations in the game logic found in RoundServiceTask2. This will return the cleaned up path the game logic took, the reward for that path and the probability of that path.
 * Sample output for the above example would be: reward = 145, probability = 3.1565656565656574E-4, choiceCombination = [ONEHUNDRED, TWENTY, TWENTY, FIVE, GAMEOVER]
3. These records where then filtered to remove all the duplicate paths and leaving only one of each possible path. With this we will have all the possible unique ways the player can play the game.
4. Collecting the combinations with the same reward and summing up their probability. This will give the total probability of a player getting that reward from the main round.
5. Multiplying the reward with the probability and summing all of them up will give us the expected value of just the main round.
6. I calculate the expected value of just the bonus round without the second chance option (only three choices possible: 20, 10, 5).
7. The expected value of the second chance option on it's own will be the expected values of the second main round(which is exactly the same as any other main round) and the second bonus round summed up together since this choice will give the player the ability to go through another main round and bonus round.
8. Therefore we can now calculate the expected value of the bonus round with the second chance.
9. Adding up the expected values of the main round and the bonus round with second chance will give us the expected value of the whole game

The expected value is around the 73.542 region which comes to 73.5 confirming the simulation in task 1.


## Design decisions

The Round class contains three lists. These are only used by the tests to avoid duplicating code:

1. Main Round list containing the specified twelve possible "boxes" that can be chosen by a player
2. Bonus Round list containing the four choices that can be chosen by a player including the Second Chance
3. Second Bonus Round list containing the three choices in (2) above excluding the Second Chance

MainRoundEnum and BonusRoundEnum contain the distinct choices a player has in each round. If the values need to be tweaked to raise or lower the expected value of the game, they can be changed from these enums directly. This helps by having a central place to tweak and not split in multiple classes.

The DataModel class holds three object. This class is only used for Task 2 to facilitate the calculations:

1. choiceCombination: the path the game logic took removing all the redundant steps
2. reward: how much the player has won
3. probability: the probability value of the choiceCombination
