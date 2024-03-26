import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PackageMeasurementConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the measurement string:");
        String incodedValues = scanner.nextLine();
        scanner.close();

        List<Integer> convertedMeasurements = decodeMeasurements(incodedValues.toLowerCase());

        System.out.println("Input: " + incodedValues + " >>  Measurement : " + convertedMeasurements);
    }
    // Method to convert the measurement string into numerical values
    public static List<Integer> decodeMeasurements(String convertString) {
        List<Integer> convertedValues = new ArrayList<>();
        Integer inputLength = convertString.length();
        Integer currentIndex = 0; // This index is just a pointer
        while (currentIndex < inputLength) {
            Character currentCharacter = convertString.charAt(currentIndex);
            Integer currentValueNum = 0;
            if (currentCharacter >= 'a' && currentCharacter <= 'z') {
                Integer numOfNextCharsToCount = (currentCharacter - 'a') + 1;
                currentIndex++;
                String sequence = "";
                boolean zEncountered = false; // Flag to track if 'z' is encountered within the sequence
                Integer i=0;
                while (i < numOfNextCharsToCount && currentIndex < inputLength) {
                    Character charToCount = convertString.charAt(currentIndex++);
                    if (charToCount == 'z') {
                        sequence = sequence + charToCount;
                        // Count consecutive 'z' characters and the next character
                        while (currentIndex < inputLength && convertString.charAt(currentIndex) == 'z') {
                            sequence = sequence + convertString.charAt(currentIndex++);
                        }
                        if (currentIndex < inputLength) {
                            sequence = sequence + convertString.charAt(currentIndex++);
                        }
                    } else if ((charToCount >= 'a' && charToCount <= 'z') || isNonAlphabetical(charToCount)) {
                        sequence = sequence + charToCount;
                        zEncountered = false;
                    } else {
                        currentIndex--; // Move back one step to process the non-alphabetical character again
                        break;
                    }
                    i++; // Increment i within the loop
                }
                // Calculate value for the sequence if 'z' is not encountered or if it's the last character of the sequence
                if (!zEncountered || sequence.charAt(sequence.length() - 1) == 'z') {
                    convertedValues.add(getMeasurementValue(sequence));
                }
            } else {
                currentIndex++;
            }
        }
        return convertedValues;
    }


    /* check if the character is non-alphabetical*/
    public static boolean isNonAlphabetical(Character character) {
        return !Character.isLetter(character);
    }
    /* Calculates the value of the given input string */
    public static int getMeasurementValue(String input) {
        Integer value = 0;
        for (Character letter : input.toCharArray()) { // Convert the character to lowercase and calculate its numerical value
            if (letter >= 'a' && letter <= 'z') { // by subtracting the ASCII value of 'a' and adding 1 to align with
                value =value+(letter - 'a' + 1); // the position of the letter in the alphabet (starting from 1)
            }
        }
        return value;
    }


}
