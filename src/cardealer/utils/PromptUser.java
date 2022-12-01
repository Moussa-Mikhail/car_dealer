package cardealer.utils;

import cardealer.Main;

/**
 * @author Moussa
 */
public class PromptUser {
    private PromptUser() {
        throw new UnsupportedOperationException("Utility class.");
    }

    /**
     * @param prompt The prompt to display to the user
     * @return true if the user selects yes, false otherwise
     */
    public static boolean yesOrNoPrompt(String prompt) {
        System.out.println(prompt);
        int choice = getChoice("Yes", "No");
        return choice == 1;
    }

    public static int nextInt() {
        // Necessary because Scanner.nextInt() does not consume newline.
        // which breaks the nextLine() call.
        // See https://stackoverflow.com/a/13102045/18650633
        return Integer.parseInt(Main.CONSOLE.nextLine());
    }

    public static Object presentOptions(String prompt, Object... options) {
        System.out.println(prompt);
        int choice = getChoice(options);
        return options[choice - 1];
    }

    public static int getChoice(Object... options) {
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s%n", i + 1, options[i]);
        }
        System.out.print("Enter your choice: ");
        int choice = nextInt();
        while (choice < 1 || choice > options.length) {
            System.out.println("Invalid choice. Please try again.");
            choice = nextInt();
        }
        return choice;
    }
}
