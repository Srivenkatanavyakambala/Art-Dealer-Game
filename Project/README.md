# The Art Dealer Game

The **Art Dealer Game** is an educational computer simulation designed to help K-8 students develop computational thinking, math concepts, and computer science skills. The game features increasing complexity based on the students' grade levels (K-2, 3-5, and 6-8) and uses playing cards to represent paintings that a virtual art dealer is interested in buying. Students must figure out the card pattern the art dealer is looking for by selecting cards and making guesses.

## Table of Contents
- [Game Description](#game-description)
- [Installation](#installation)
- [How to Play](#how-to-play)
- [Game Levels](#game-levels)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)

## Game Description

The game challenges students to identify patterns in a deck of cards (represented by poker cards). Players act as gallery owners and attempt to sell 4 cards to an art dealer, who is represented by the software. The goal is to discover the pattern of cards the art dealer wants to buy. 

### Key Features:
- **Interactive UI**: Built using Java Swing to allow students to select cards and make guesses.
- **Difficulty Levels**: Supports three different difficulty levels for students from grades K-8.
  - **K-2**: Simple patterns like color or suit.
  - **3-5**: More complex patterns involving number-based logic.
  - **6-8**: Introduces poker-like combinations such as flushes and straights.

The game can be played by one or two students and includes real-time feedback to guide players through the game.

## Installation

1. **Prerequisites**:
   - Ensure you have Java installed (version 8 or higher).
   - You can check your Java version by running:
     ```sh
     java -version
     ```
   - Install a Java IDE like IntelliJ IDEA or Eclipse, or use the command line.

2. **Clone the Repository**:
   - Clone this repository to your local machine using Git:
     ```sh
     git clone "repo_link"
     ```

3. **Compile and Run**:
   - If you’re using the command line, navigate to the project directory and compile the Java file:
     ```sh
     javac ArtDealerGameUI.java
     ```
   - Run the compiled program:
     ```sh
     java ArtDealerGameUI
     ```
   - If using an IDE, simply open the project and click on "Run".

## How to Play

1. **Select a Game Level**: When the game starts, you can choose between three difficulty levels: `K-2`, `3-5`, and `6-8`.
   
2. **View Cards**: The game will display 4 random cards. You can observe the suit, rank, and color of these cards.

3. **Make a Guess**: 
   - Use the dropdown menu to select a pattern that you believe matches the cards.
   - Click the "Guess Pattern" button to see if your guess is correct.

4. **Feedback**: 
   - If your guess is correct, balloons will fly over the screen as a reward, and you'll be prompted to play again.
   - If the guess is incorrect, you can try up to 3 times before the game resets.

5. **Win Condition**: The game is won when the player correctly identifies the pattern in the set of 4 cards.

## Game Levels

### K-2 Level:
- Simple patterns like:
  - All red cards
  - All black cards
  - All hearts
  - All queens

### 3-5 Level:
- More complex patterns like:
  - Prime number cards (2, 3, 5, 7)
  - Cards that sum to 9
  - An Ace and a black Jack

### 6-8 Level:
- Includes poker-like combinations such as:
  - Flush (all cards of the same suit)
  - Straight (consecutive numbers in any suit)
  - Full House (3 cards of one rank and 2 of another)

### Extra Credit:
- In the 6-8 level, students can play against each other. One student can take the role of the art dealer (choosing a pattern), while the other student attempts to guess the pattern.

## Development

This project is developed using **Java Swing** for the user interface and basic game logic. The deck of cards is modeled as an array of `Card` objects, and random cards are generated for each round of the game. The program checks the player's guesses based on the difficulty level.

### Requirements
- **Java 8** or higher.
- **Java Swing** for GUI development.
- Basic understanding of Java programming and object-oriented design.

# Art Dealer Game: Input and Output Examples

## K-2 Level

### Correct Inputs and Outputs
| Input                       | Output                                         |
|-----------------------------|------------------------------------------------|
| All red cards               | **Correct!** Balloons fly across the screen.  |
| All hearts                  | **Correct!** Balloons fly across the screen.  |
| All black cards             | **Correct!** Balloons fly across the screen.  |
| All queens                  | **Correct!** Balloons fly across the screen.  |

### Wrong Inputs and Outputs
| Input                       | Output                                         |
|-----------------------------|------------------------------------------------|
| All black cards             | **Incorrect!** You have 2 tries left.        |
| All hearts and diamonds     | **Incorrect!** You have 1 try left.          |
| Three red and one black     | **Incorrect!** Game resets.                   |

---

## 3-5 Level

### Correct Inputs and Outputs
| Input                       | Output                                         |
|-----------------------------|------------------------------------------------|
| Prime number cards (2, 3, 5, 7) | **Correct!** Balloons fly across the screen.  |
| Cards that sum to 9 (2, 3, 4) | **Correct!** Balloons fly across the screen.  |
| Ace and black Jack         | **Correct!** Balloons fly across the screen.  |

### Wrong Inputs and Outputs
| Input                       | Output                                         |
|-----------------------------|------------------------------------------------|
| Cards that sum to 10       | **Incorrect!** You have 2 tries left.        |
| Prime number cards (2, 4, 5, 7) | **Incorrect!** You have 1 try left.          |
| A red card and a black card | **Incorrect!** Game resets.                   |

---

## 6-8 Level

### Correct Inputs and Outputs
| Input                       | Output                                         |
|-----------------------------|------------------------------------------------|
| All cards of the same suit (flush) | **Correct!** Balloons fly across the screen.  |
| Consecutive numbers (straight) | **Correct!** Balloons fly across the screen.  |
| Full House (3 Kings, 2 Aces) | **Correct!** Balloons fly across the screen.  |

### Wrong Inputs and Outputs
| Input                       | Output                                         |
|-----------------------------|------------------------------------------------|
| Cards of different suits    | **Incorrect!** You have 2 tries left.        |
| Non-consecutive numbers     | **Incorrect!** You have 1 try left.          |
| A pair of cards            | **Incorrect!** Game resets.                   |

## Multiplayer Scenario

In the multiplayer mode, one player takes on the role of the Art Dealer, while the other player tries to guess the pattern. The Art Dealer selects a pattern from the available options, and the guessing player attempts to identify it.

### Art Dealer Inputs and Outputs

#### Correct Inputs and Outputs
| Input (Art Dealer)         | Output (Guessing Player)                        |
|-----------------------------|-------------------------------------------------|
| Pattern: All red           | **Correct!** Balloons fly across the screen.   |
| Pattern: All hearts        | **Correct!** Balloons fly across the screen.   |
| Pattern: Full House (3 Kings, 2 Aces) | **Correct!** Balloons fly across the screen.   |

#### Wrong Inputs and Outputs
| Input (Art Dealer)         | Output (Guessing Player)                        |
|-----------------------------|-------------------------------------------------|
| Pattern: All black         | **Incorrect!** You have 2 tries left.         |
| Pattern: Straight          | **Incorrect!** You have 1 try left.           |
| Pattern: A single pair     | **Incorrect!** Game resets.                    |

---

### Guessing Player Inputs and Outputs

#### Correct Inputs and Outputs
| Input (Guessing Player)    | Output (Art Dealer)                             |
|-----------------------------|-------------------------------------------------|
| Guess: All red              | **Correct!** Balloons fly across the screen.   |
| Guess: All hearts           | **Correct!** Balloons fly across the screen.   |
| Guess: Full House (3 Kings, 2 Aces) | **Correct!** Balloons fly across the screen.   |

#### Wrong Inputs and Outputs
| Input (Guessing Player)    | Output (Art Dealer)                             |
|-----------------------------|-------------------------------------------------|
| Guess: All black            | **Incorrect!** You have 2 tries left.         |
| Guess: Three consecutive numbers (not a straight) | **Incorrect!** You have 1 try left.           |
| Guess: A mix of cards (not matching any pattern) | **Incorrect!** Game resets.                    |

---

### Example Game Flow

1. **Art Dealer chooses a pattern**:
   - Input: `Pattern: All Red`
   - Output: (No immediate output; waiting for guessing player)

2. **Guessing Player makes a guess**:
   - Input: `Guess: All Red`
   - Output: `**Correct!** Balloons fly across the screen.`

3. **Art Dealer chooses a new pattern**:
   - Input: `Pattern: Full House`
   - Output: (No immediate output; waiting for guessing player)

4. **Guessing Player makes a wrong guess**:
   - Input: `Guess: All Black`
   - Output: `**Incorrect!** You have 2 tries left.`

5. **Guessing Player makes another guess**:
   - Input: `Guess: Three consecutive cards`
   - Output: `**Incorrect!** You have 1 try left.`

6. **Guessing Player final guess**:
   - Input: `Guess: Full House`
   - Output: `**Correct!** Balloons fly across the screen.`
