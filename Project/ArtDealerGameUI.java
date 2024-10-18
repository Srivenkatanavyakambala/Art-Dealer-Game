import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*; // Importing utilities including java.util.List
import javax.sound.sampled.*; // Import for playing sound

class Card {
    String suit, rank, color;

    public Card(String suit, String rank, String color) {
        this.suit = suit;
        this.rank = rank;
        this.color = color;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}

public class ArtDealerGameUI extends JFrame {
    private JComboBox<String> levelSelector;
    private JComboBox<String> patternSelector;
    private JComboBox<String> roleSelector; // For multiplayer role selection
    private JTextArea selectedCardsArea;
    private int guessAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;
    private java.util.List<Card> deck;
    private java.util.List<Card> selectedCards;
    private JPanel cardPanel;
    private JButton startGameButton;
    private boolean gameStarted = false; // Flag to check if the game has started

    // Patterns for different levels
    private String[] patternsK2 = {"All Red", "All Black", "All Hearts", "All Queens"};
    private String[] patterns35 = {"All Red", "All Prime Numbers", "Sum to 9", "Ace and Black Jack"};
    private String[] patterns68 = {"Flush", "Straight", "Full House"};

    public ArtDealerGameUI() {
        setTitle("Art Dealer Game");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize deck of cards
        initializeDeck();

        // UI Components
        JPanel topPanel = new JPanel();
        JLabel levelLabel = new JLabel("Select Level:");
        levelSelector = new JComboBox<>(new String[]{"K-2", "3-5", "6-8"});
        levelSelector.addActionListener(new LevelSelectorListener());

        patternSelector = new JComboBox<>();
        selectedCardsArea = new JTextArea(5, 30);
        selectedCardsArea.setEditable(false);

        topPanel.add(levelLabel);
        topPanel.add(levelSelector);
        topPanel.add(new JLabel("Guess the Pattern:"));
        topPanel.add(patternSelector);

        // Start Game Button
        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new StartGameListener());
        topPanel.add(startGameButton);

        JButton guessButton = new JButton("Guess Pattern");
        guessButton.addActionListener(new GuessPatternListener());

        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(4, 13)); // Create a grid for the cards

        // Display cards as buttons for selection
        displayCardsAsButtons();

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(selectedCardsArea), BorderLayout.SOUTH);
        add(cardPanel, BorderLayout.CENTER);
        add(guessButton, BorderLayout.EAST);

        updatePatternSelector(); // Set initial pattern options based on default level (K-2)
    }

    // Initialize deck of cards
    private void initializeDeck() {
        deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] colors = {"Red", "Black"};

        for (int i = 0; i < suits.length; i++) {
            String color = (i < 2) ? colors[0] : colors[1]; // Hearts and Diamonds are Red, Clubs and Spades are Black
            for (String rank : ranks) {
                deck.add(new Card(suits[i], rank, color));
            }
        }
    }

    // Display the full deck of cards as buttons for selection
    private void displayCardsAsButtons() {
        cardPanel.removeAll();
        for (Card card : deck) {
            JButton cardButton = new JButton(card.toString());
            cardButton.addActionListener(new CardButtonListener(card));
            cardPanel.add(cardButton);
        }
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    // Listener for the level selection changes
    private class LevelSelectorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatePatternSelector();
        }
    }

    // Update pattern selector based on level
    private void updatePatternSelector() {
        String level = (String) levelSelector.getSelectedItem();
        if (level != null) {
            switch (level) {
                case "K-2":
                    patternSelector.setModel(new DefaultComboBoxModel<>(patternsK2));
                    break;
                case "3-5":
                    patternSelector.setModel(new DefaultComboBoxModel<>(patterns35));
                    break;
                case "6-8":
                    patternSelector.setModel(new DefaultComboBoxModel<>(patterns68));
                    break;
            }
        }
    }

    // Listener for pattern guessing
    private class GuessPatternListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gameStarted) {
                JOptionPane.showMessageDialog(null, "Please start the game first!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (selectedCards == null || selectedCards.size() < 4) {
                JOptionPane.showMessageDialog(null, "Please select 4 cards first!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String selectedPattern = (String) patternSelector.getSelectedItem();
            boolean isCorrect = checkPattern(selectedPattern, selectedCards);

            if (isCorrect) {
                playSound("/audio/win_sound.wav");
                JOptionPane.showMessageDialog(null, "Correct! You won!", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            } else {
                guessAttempts++;
                selectedCardsArea.setText(""); // Clear the displayed selected cards
                selectedCards = new ArrayList<>(); // Reset the selected cards list
                if (guessAttempts >= MAX_ATTEMPTS) {
                    JOptionPane.showMessageDialog(null, "Out of attempts! Game reset.", "Game Over", JOptionPane.ERROR_MESSAGE);
                    resetGame();
                } else {
                    playSound("/audio/incorrect_guess.wav");
                    JOptionPane.showMessageDialog(null, "Incorrect! " + (MAX_ATTEMPTS - guessAttempts) + " attempts left.", "Try Again", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }


    // Listener for card buttons (click to select cards)
    private class CardButtonListener implements ActionListener {
        private Card card;

        public CardButtonListener(Card card) {
            this.card = card;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gameStarted) {
                JOptionPane.showMessageDialog(null, "Please start the game first!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (selectedCards == null) {
                selectedCards = new ArrayList<>();
            }

            if (selectedCards.size() < 4) {
                selectedCards.add(card);
                selectedCardsArea.append(card.toString() + "\n");
                playSound("/audio/card_click.wav"); // Play sound on card click
                JOptionPane.showMessageDialog(null, selectedCards.size() + " card(s) selected.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "You have already selected 4 cards. Make your guess!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Listener for the Start Game button
    private class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetGame();
            playSound("/audio/start_stage.wav"); // Play sound when starting the game
            gameStarted = true;
            JOptionPane.showMessageDialog(null, "Game started! Please select cards.", "Game Started", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Reset the game state
    private void resetGame() {
        selectedCardsArea.setText("");
        selectedCards = null;
        guessAttempts = 0;
        displayCardsAsButtons();
    }

    // Method to check if the selected cards match the guessed pattern
    private boolean checkPattern(String pattern, java.util.List<Card> cards) {
        switch (pattern) {
            case "All Red":
                return cards.stream().allMatch(card -> card.color.equals("Red"));
            case "All Black":
                return cards.stream().allMatch(card -> card.color.equals("Black"));
            case "All Hearts":
                return cards.stream().allMatch(card -> card.suit.equals("Hearts"));
            case "All Queens":
                return cards.stream().allMatch(card -> card.rank.equals("Queen"));
            case "All Prime Numbers":
                return cards.stream().allMatch(card -> isPrime(card.rank));
            case "Sum to 9":
                return cards.stream().mapToInt(card -> cardValue(card.rank)).sum() == 9;
            case "Ace and Black Jack":
                return cards.stream().anyMatch(card -> card.rank.equals("Ace"))
                        && cards.stream().anyMatch(card -> card.rank.equals("Jack") && card.color.equals("Black"));
            case "Flush":
                return cards.stream().allMatch(card -> card.suit.equals(cards.get(0).suit));
            case "Straight":
                return isStraight(cards);
            case "Full House":
                return isFullHouse(cards);
            default:
                return false;
        }
    }

    // Helper methods for checking patterns
    private boolean isPrime(String rank) {
        int value = cardValue(rank);
        return value == 2 || value == 3 || value == 5 || value == 7;
    }

    private int cardValue(String rank) {
        switch (rank) {
            case "Jack": return 11;
            case "Queen": return 12;
            case "King": return 13;
            case "Ace": return 14;
            default: return Integer.parseInt(rank);
        }
    }

    private boolean isStraight(java.util.List<Card> cards) {
        Set<Integer> values = new HashSet<>();
        for (Card card : cards) {
            values.add(cardValue(card.rank));
        }
        return values.size() == 4; // To form a straight, we need 4 unique values
    }

    private boolean isFullHouse(java.util.List<Card> cards) {
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : cards) {
            rankCount.put(card.rank, rankCount.getOrDefault(card.rank, 0) + 1);
        }
        return rankCount.size() == 2 && rankCount.containsValue(3);
    }

    // Method to play sound
    private void playSound(String soundFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArtDealerGameUI game = new ArtDealerGameUI();
            game.setVisible(true);
        });
    }
}
