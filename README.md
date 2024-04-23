# TennisKata
[![javaKataTennis](https://miro.medium.com/v2/resize:fit:1358/1*K3xJ3FWtK-8NQq9Nj_3DsA.png)](#)

## Description
This Kata goal is to implement a simple tennis score computer.

The scoring system consist in one game, divided by points:

- Each player starts a game with 0 point.
- If the player wins the 1st ball, he will have 15 points. 2nd balls won : 30 points. 3rd ball won : 40points.
- If a player have 40 points and wins the ball, he wins the game, however there are special rules.
- If both players have 40 points the players are “deuce”.
- If the game is in deuce, the winner of the ball will have advantage.
- If the player with advantage wins the ball he wins the game.
- If the player without advantage wins the ball they are back at “deuce”.

You can find more details about the rules [here](http://en.wikipedia.org/wiki/Tennis#Scoring)

The program takes a String as input containing the character ‘A’ or ‘B’. 
The character ‘A’ corresponding to “player A won the ball”, and ‘B’ corresponding to “player B won the ball”. 
The program should print the score after each won ball (for example : “Player A : 15 / Player B : 30”) and print the winner of the game.

For example the following input “ABABAA” should print :

```
Player A : 15 / Player B : 0
Player A : 15 / Player B : 15
Player A : 30 / Player B : 15
Player A : 30 / Player B : 30
Player A : 40 / Player B : 30
Player A wins the game
```

## Technologies
- Apache Maven 3
- Java 17
- JUnit 5
- Mockito

## Architecture
- Hexagonal

## Install
`mvn package`

## Run
`java -jar target/tennis-<version>.jar`

The program will wait for **user input** *(game rally)* to start the game.