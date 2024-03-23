import java.util.Scanner;

public class PackageMeasurementConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the measurement string:");
        String encodedValues = scanner.nextLine();
        scanner.close();


    }

    /* Gets the value of the given input string */
    public static int getMeasurementValue(String input) {
        int value = 0;
        for (char letter : input.toCharArray()) { // Convert the character to lowercase and calculate its numerical value
            if (letter >= 'a' && letter <= 'z') { // by subtracting the ASCII value of 'a' and adding 1 to align with
                value += letter - 'a' + 1; // the position of the letter in the alphabet (starting from 1)
            }
        }
        return value;
    }
}
