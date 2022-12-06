package cardealer.utils;

import cardealer.exceptions.EmptyInputException;
import cardealer.exceptions.InvalidInputException;

import java.util.List;
import java.util.Scanner;

/**
 * @author Moussa
 */
public final class PromptUser {
    public static final Scanner CONSOLE = new Scanner(System.in);

    private PromptUser() {
        throw new UnsupportedOperationException("Utility class.");
    }

    /**
     * @param prompt The prompt to display to the user.
     * @return true if the user selects yes, false if the user selects no.
     */
    public static boolean yesOrNoPrompt(String prompt) {
        String choice = getChoice(prompt, "Yes", "No");
        return "Yes".equals(choice);
    }

    /**
     * Overload of {@link #getChoice(String, List)} that allows the user to use varargs.
     *
     * @see #getChoice(String, List)
     */
    @SafeVarargs
    public static <T> T getChoice(String prompt, T... options) {
        return getChoice(prompt, List.of(options));
    }

    /**
     * @param prompt  The prompt to display to the user.
     * @param options The options to display to the user.
     * @return The option selected by the user.
     * @throws IllegalArgumentException if the options array is empty.*
     */
    public static <T> T getChoice(String prompt, List<T> options) {
        if (options.isEmpty()) {
            throw new IllegalArgumentException("No options input or options array is empty.");
        }

        System.out.println(prompt);
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, options.get(i));
        }

        int minChoice = 1;
        int maxChoice = options.size();
        int choice = 0;
        boolean isChoiceOutOfBounds;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = nextInt();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }

            isChoiceOutOfBounds = isNotInBounds(choice, minChoice, maxChoice);
            if (isChoiceOutOfBounds) {
                System.out.printf("Please enter an integer between %d and %d, inclusive.%n", minChoice, maxChoice);
            }
        } while (isChoiceOutOfBounds);
        System.out.println();
        return options.get(choice - 1);
    }

    private static boolean isNotInBounds(int choice, int minChoice, int maxChoice) {
        return choice < minChoice || choice > maxChoice;
    }

    public static int nextInt() throws InvalidInputException {
        // This is necessary because Scanner.nextInt() does not read newline
        // which will break any subsequent nextLine() call.
        // See https://stackoverflow.com/a/13102045/18650633.
        String input = CONSOLE.nextLine();

        if (input.isEmpty()) {
            throw new EmptyInputException("Input is empty.");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input could not be parsed as an integer.", e);
        }
    }
}
