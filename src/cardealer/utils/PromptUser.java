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
        System.out.print("Enter your choice: ");
        int choice = nextInt();
        while (choice < 1 || choice > options.length) {
            System.out.println("Invalid choice. Please enter a valid number.");
            choice = nextInt();
        }
        return options[choice - 1];
    }

    public static int nextInt() {
        // Necessary because Scanner.nextInt() does not read newline
        // which breaks the subsequent nextLine() call.
        // See https://stackoverflow.com/a/13102045/18650633
        return Integer.parseInt(Main.CONSOLE.nextLine());
    }
}
