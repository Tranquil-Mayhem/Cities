import java.util.Scanner;

/***
 * This class determines if the user input is valid for the different types.
 */
public class ConsoleUtil {
    /***
     * This determines if the user input a long value
     * @param prompt prompts the user to enter a long integer value
     * @return the user input as a long
     */
    public static long getInteger(String prompt) {
        System.out.printf(prompt);
        Scanner scanner = new Scanner(System.in);

        long userInputSymbol = scanner.nextLong();
        return userInputSymbol;
    }
    /***
     * This determines if the user input an int value
     * @param prompt prompts the user to enter an integer value
     * @param min the min value
     * @param max the max value
     * @return the user input as an int
     */
    public static int getInteger(String prompt, int min, int max) {
        System.out.printf(prompt);
        Scanner scanner = new Scanner(System.in);

        int userInputSymbol = scanner.nextInt();

        while (userInputSymbol < min || userInputSymbol > max) {
            System.out.println("Invalid, try again");
            System.out.print(prompt);
            userInputSymbol = scanner.nextInt();

        }
        return userInputSymbol;
    }
    /***
     * This determines if the user input a string
     * @param prompt prompts the user to enter a string value
     * @return the user input as a string
     */
    public static String getString(String prompt) {
        System.out.printf(prompt);
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextLine()) {
            scanner.nextLine();
            System.out.println("Invalid, try again");
            System.out.print(prompt);
        }
        return scanner.nextLine();
    }
}
