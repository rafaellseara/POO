package activity_manager.view;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.List;

public class Utils {

    public static void printHeader() {
        Utils.println("    _             _     _____   _   _     _ ");
        Utils.println("   / \\      ___  | |_  |  ___| (_) | |_  | |");
        Utils.println("  / _ \\    / __| | __| | |_    | | | __| | |");
        Utils.println(" / ___ \\  | (__  | |_  |  _|   | | | |_  |_|");
        Utils.println("/_/   \\_\\  \\___|  \\__| |_|     |_|  \\__| (_)");


    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static List<String> getPage(List<String> items, int pageNumber) {
        int startIndex = (pageNumber - 1) * 5;
        if (startIndex < 0) startIndex = 0;
        int endIndex = Math.min(startIndex + 5, items.size());
        return items.subList(startIndex, endIndex);
    }

    public static void giveError(String message) {
        System.err.println(message);
    }

    public static int inputOption(int options) {
        Integer num = null;
        String text = "";
        boolean state = true;
        Scanner scanner = new Scanner(System.in);

        do {
            print("Select one of the options above: ");
            try {
                text = scanner.nextLine();
                num = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                giveError(text + " is not a valid option!");
                state = false;
            }

            if ((num == null || num < 0 || num > options) && state) {
                num = null;
                giveError(text + " is not a valid option!");
            }
            state = true;
        } while (num == null);

        return num;
    }

    public static int inputOptionV2(int options) {
        Integer num = null;
        String text = "";
        boolean state = true;
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                text = scanner.nextLine();
                num = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                giveError(text + " is not a valid option!");
                state = false;
            }

            if ((num == null || num < 0 || num > options) && state) {
                num = null;
                giveError(text + " is not a valid option!");
            }
            state = true;
        } while (num == null);

        return num;
    }

    public static String input(String message) {
        print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String inputEmail() {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String email = Utils.input("Email: ");

        while (!email.matches(emailRegex) && !email.equals("0")) {
            email = Utils.input("You need to type a correct email: ");
        }
        return email;
    }

    public static String inputPostalCode() {
        String postalCodeRegex = "^\\d{4}-\\d{3}$";
        String postalCode = Utils.input("Postal Code (XXXX-XXX): ");

        while (!postalCode.matches(postalCodeRegex) && !postalCode.equals("0")) {
            postalCode = Utils.input("You need to type a correct Postal Code (XXXX-XXX): ");
        }
        return postalCode;
    }


    public static String inputUserType() {
        Scanner scanner = new Scanner(System.in);
        Utils.println("Choose user type:");
        Utils.println("1 - PRO");
        Utils.println("2 - AMATEUR");
        Utils.println("3 - OCASIONAL");

        String userTypeInput;
        do {
            Utils.println("Enter your choice (1/2/3): ");
            userTypeInput = scanner.nextLine();
        } while (!userTypeInput.matches("[123]"));

        int userTypeChoice = Integer.parseInt(userTypeInput);
        return switch (userTypeChoice) {
            case 1 -> "PRO";
            case 2 -> "AMATEUR";
            case 3 -> "OCASIONAL";
            default -> null;
        };
    }

    public static int parseStringToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static double parseStringToDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;  // Return 0.0 as a default value in case of parsing failure
        }
    }


    public static String inputActivityType() {
        Scanner scanner = new Scanner(System.in);
        Utils.println("Choose an activity type:");
        Utils.println("1 - Distance And Altimeter");
        Utils.println("2 - Distance");
        Utils.println("3 - Repetitions");
        Utils.println("4 - Repetitions With Weights");

        String userTypeInput;
        do {
            Utils.println("Enter your choice (1/2/3/4): ");
            userTypeInput = scanner.nextLine();
        } while (!userTypeInput.matches("[1234]"));

        return getUserTypeChoice(userTypeInput);
    }

    public static String inputActivityTypeV2() {
        Scanner scanner = new Scanner(System.in);
        Utils.println("Choose the activity type:");
        Utils.println("1 - Distance And Altimeter");
        Utils.println("2 - Distance");
        Utils.println("3 - Repetitions");
        Utils.println("4 - Repetitions With Weights");

        String userTypeInput;
        do {
            Utils.println("Enter your choice (1/2/3/4) or 0 for no activity: ");
            userTypeInput = scanner.nextLine();
        } while (!userTypeInput.matches("[01234]"));

        return getUserTypeChoice(userTypeInput);
    }

    private static String getUserTypeChoice(String userTypeInput) {
        int userTypeChoice = Integer.parseInt(userTypeInput);
        return switch (userTypeChoice) {
            case 1 -> "DistanceAndAltimeter";
            case 2 -> "Distance";
            case 3 -> "Repetitions";
            case 4 -> "RepetitionsWithWeights";
            default -> null;
        };
    }

    public static String getBodyPart() {
        Scanner scanner = new Scanner(System.in);
        Utils.println("Choose a body part to target:");
        Utils.println("1 - Chest");
        Utils.println("2 - Back");
        Utils.println("3 - Arms");
        Utils.println("4 - Legs");

        String userBodyPartInput;
        do {
            Utils.println("Enter your choice (1/2/3/4): ");
            userBodyPartInput = scanner.nextLine();
        } while (!userBodyPartInput.matches("[1-4]"));

        int userBodyPartChoice = Integer.parseInt(userBodyPartInput);
        return switch (userBodyPartChoice) {
            case 1 -> "Chest";
            case 2 -> "Back";
            case 3 -> "Arms";
            case 4 -> "Legs";
            default -> null;
        };
    }

    public static String inputAvgHeartRate() {
        String avgHeartRateStr = "";
        boolean validInput = false;

        while (!validInput) {
            String input = Utils.input("Average Heart Rate: ");
            try {
                int avgHeartRate = Integer.parseInt(input);
                if (avgHeartRate < 0) {
                    System.out.println("Heart rate cannot be negative.");
                } else {
                    avgHeartRateStr = String.valueOf(avgHeartRate);
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for Average Heart Rate.");
            }
        }
        return avgHeartRateStr;
    }

    public static String inputYesOrNo(String message) {
        String validInputRegex = "^[yn]$";
        String userInput = Utils.input(message);

        while (!userInput.matches(validInputRegex)) {
            userInput = Utils.input("Please enter 'y' for Yes or 'n' for No: ");
        }

        return userInput;
    }

    public static String inputDuration(){
        String durationStr = "";
        boolean validInput = false;

        while(!validInput){
            String input = Utils.input("Duration(min): ");
            try {
                int duration = Integer.parseInt(input);
                if (duration < 0) {
                    System.out.println("Duration cannot be negative.");
                } else {
                    durationStr = String.valueOf(duration);
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for the Duration.");
            }
        }
        return durationStr;
    }

    public static int inputInteger(String message) {
        Integer num = null;
        String text;
        Scanner scanner = new Scanner(System.in);

        do {
            print(message);
            text = scanner.nextLine();

            try {
                num = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                giveError(text + " is not a valid Integer.");
            }

        } while (num == null);

        return num;
    }

    public static String inputInt(String message) {
        Integer num = null;
        String text;
        Scanner scanner = new Scanner(System.in);

        do {
            print(message);
            text = scanner.nextLine();

            try {
                num = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                giveError(text + " is not a valid Integer.");
            }

        } while (num == null);

        return text;
    }

    public static String inputName(String message) {
        String userInput;
        Scanner scanner = new Scanner(System.in);

        do {
            print(message);
            userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
            }
        } while (userInput.isEmpty());

        return userInput;
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }

}
