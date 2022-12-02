package cardealer.utils;

import cardealer.Main;

/**
 * @author Moussa
 */
public final class PromptUser {
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

    @SafeVarargs
    public static <T> T getChoice(String prompt, T... options) {
        System.out.println(prompt);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s%n", i + 1, options[i]);
        }

        int minChoice = 1;
        int maxChoice = options.length;
        int choice = 0;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = nextBoundedInt(minChoice, maxChoice);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (isNotInBounds(choice, minChoice, maxChoice));
        return options[choice - 1];
    }

    public static int nextBoundedInt(int min, int max) throws InvalidInputException {
        int choice = nextInt();
        if (isNotInBounds(choice, min, max)) {
            throw new ChoiceOutOfBoundsException(String.format("Please enter an integer between %d and %d, inclusive.", min, max));
        }
        return choice;
    }

    private static boolean isNotInBounds(int choice, int minChoice, int maxChoice) {
        return choice < minChoice || choice > maxChoice;
    }

    public static int nextInt() throws InvalidInputException {
        // This is necessary because Scanner.nextInt() does not read newline
        // which will break the subsequent nextLine() call.
        // See https://stackoverflow.com/a/13102045/18650633
        String input = Main.CONSOLE.nextLine();

        if (input.isEmpty()) {
            throw new EmptyInputException("Input is empty. Please enter an integer.");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input could not be parsed as an integer. Please enter a valid integer.", e);
        }
    }
}
