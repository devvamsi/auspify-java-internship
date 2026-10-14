import java.util.Random;
import java.util.Scanner;

/**
 * NumberGuessingGame.java
 * A console-based interactive game where the player guesses
 * a randomly generated number between 1 and a chosen maximum.
 *
 * Features:
 *   - Random number generation
 *   - Higher/Lower hints
 *   - Attempt counter
 *   - Play again option
 *   - Difficulty levels
 */
public class NumberGuessingGame {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    // Stats across games
    private static int gamesPlayed = 0;
    private static int bestScore = Integer.MAX_VALUE;

    public static void main(String[] args) {

        printBanner();

        boolean playAgain = true;

        while (playAgain) {
            int maxRange = chooseDifficulty();
            playGame(maxRange);
            playAgain = askPlayAgain();
        }

        printFinalStats();
        scanner.close();
    }

    // ---------- Banner ----------
    private static void printBanner() {
        System.out.println("================================================");
        System.out.println("           NUMBER GUESSING GAME                 ");
        System.out.println("   Guess the number, get hints, win big!        ");
        System.out.println("================================================");
    }

    // ---------- Difficulty & Range ----------
    private static int chooseDifficulty() {
        System.out.println("\n--- Choose Difficulty ---");
        System.out.println("1. Easy    (1 - 50)");
        System.out.println("2. Medium  (1 - 100)");
        System.out.println("3. Hard    (1 - 500)");
        System.out.println("4. Insane  (1 - 1000)");

        while (true) {
            System.out.print("Enter choice (1-4): ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": return 50;
                case "2": return 100;
                case "3": return 500;
                case "4": return 1000;
                default:  System.out.println("[!] Enter a number between 1 and 4.");
            }
        }
    }

    // ---------- Main Game Loop ----------
    private static void playGame(int maxRange) {

        // Step 1: Generate random number
        int secretNumber = random.nextInt(maxRange) + 1;
        int attempts = 0;
        boolean guessedCorrectly = false;

        int maxAttempts = calculateMaxAttempts(maxRange);

        System.out.println("\nI'm thinking of a number between 1 and " + maxRange + ".");
        System.out.println("You have " + maxAttempts + " attempts. Good luck!");

        // Steps 2-4: Loop until correct or attempts exhausted
        while (!guessedCorrectly && attempts < maxAttempts) {
            int guess = readInt("Attempt " + (attempts + 1) + "/" + maxAttempts + " -> Enter your guess: ");
            attempts++;

            // Validate range
            if (guess < 1 || guess > maxRange) {
                System.out.println("[!] Please guess a number between 1 and " + maxRange + ".");
                attempts--; // don't count invalid input
                continue;
            }

            // Step 3: Provide hints
            if (guess < secretNumber) {
                System.out.println("[LOW]  Too LOW! Try a higher number.");
                printWarmthHint(guess, secretNumber);
            } else if (guess > secretNumber) {
                System.out.println("[HIGH] Too HIGH! Try a lower number.");
                printWarmthHint(guess, secretNumber);
            } else {
                guessedCorrectly = true;
            }
        }

        // Step 5: Display result
        displayResult(guessedCorrectly, secretNumber, attempts, maxAttempts);

        gamesPlayed++;
        if (guessedCorrectly && attempts < bestScore) {
            bestScore = attempts;
            System.out.println("[NEW BEST] Only " + attempts + " attempts!");
        }
    }

    // ---------- Hint: Warmth Indicator ----------
    private static void printWarmthHint(int guess, int secret) {
        int diff = Math.abs(guess - secret);
        if (diff <= 5) {
            System.out.println("[HOT]     You're BURNING HOT!");
        } else if (diff <= 15) {
            System.out.println("[WARM]    Getting warm...");
        } else if (diff <= 30) {
            System.out.println("[COLD]    Cold...");
        } else {
            System.out.println("[FREEZE]  Freezing cold!");
        }
    }

    // ---------- Result ----------
    private static void displayResult(boolean won, int secret, int attempts, int maxAttempts) {
        System.out.println("\n===============================================");
        if (won) {
            System.out.println("CONGRATULATIONS! You guessed it!");
            System.out.println("Secret Number : " + secret);
            System.out.println("Attempts Used : " + attempts + " / " + maxAttempts);
            System.out.println("Rating        : " + getRating(attempts, maxAttempts));
        } else {
            System.out.println("GAME OVER! You ran out of attempts.");
            System.out.println("The number was: " + secret);
            System.out.println("Attempts Used : " + attempts + " / " + maxAttempts);
        }
        System.out.println("===============================================");
    }

    private static String getRating(int attempts, int max) {
        double ratio = (double) attempts / max;
        if (ratio <= 0.3) return "*** EXCELLENT";
        if (ratio <= 0.6) return "**  GOOD";
        if (ratio <= 0.85) return "*   AVERAGE";
        return "    KEEP PRACTICING";
    }

    // ---------- Max Attempts Logic ----------
    private static int calculateMaxAttempts(int range) {
        // log2(range) rounded up — theoretical minimum guesses
        return (int) Math.ceil(Math.log(range) / Math.log(2)) + 2;
    }

    // ---------- Play Again ----------
    private static boolean askPlayAgain() {
        System.out.print("\nPlay again? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    // ---------- Final Stats ----------
    private static void printFinalStats() {
        System.out.println("\n================================================");
        System.out.println("              FINAL STATISTICS                  ");
        System.out.println("================================================");
        System.out.printf ("  Games Played : %-28d %n", gamesPlayed);
        if (bestScore != Integer.MAX_VALUE) {
            System.out.printf("  Best Score   : %-28s %n", bestScore + " attempts");
        } else {
            System.out.printf("  Best Score   : %-28s %n", "No wins yet");
        }
        System.out.println("================================================");
        System.out.println("\nThanks for playing! See you next time.");
    }

    // ---------- Safe Integer Input ----------
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input! Please enter a whole number.");
            }
        }
    }
}