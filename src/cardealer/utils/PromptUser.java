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
        int choice = getChoice("1. Yes\n2. No", 2);
        return choice == 1;
    }

    public static int nextInt() {
        // Necessary because Scanner.nextInt() does not consume newline.
        // which breaks the nextLine() call.
        // See https://stackoverflow.com/a/13102045/18650633
        return Integer.parseInt(Main.CONSOLE.nextLine());
    }

    public static Object presentOptions(String prompt, Object[] options) {
        System.out.println(prompt);
        int choice = getChoice(options);
        return options[choice - 1];
    }

    public static int getChoice(Object[] options) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < options.length - 1; i++) {
            stringBuilder.append(i + 1).append(". ").append(options[i]).append("\n");
        }
        stringBuilder.append(options.length).append(". ").append(options[options.length - 1]);
        return getChoice(stringBuilder.toString(), options.length);
    }

    public static int getChoice(String prompt, int maxChoice) {
        System.out.println(prompt);
        System.out.print("Enter the number of your choice: ");
        int choice = nextInt();
        while (choice < 1 || choice > maxChoice) {
            System.out.println("Invalid choice. Please try again.");
            choice = nextInt();
        }
        System.out.println();
        return choice;
    }
}
