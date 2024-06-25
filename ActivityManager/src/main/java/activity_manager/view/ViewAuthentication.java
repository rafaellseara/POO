package activity_manager.view;

import java.util.HashMap;
import java.util.Map;

public class ViewAuthentication {
    public static Map<String, String> login() {
        Utils.clearScreen();
        Utils.printHeader();
        Map<String, String> user = new HashMap<String, String>();

        Utils.println("\nLogin\n");
        Utils.println("Type your email or 0 to go back.");
        String email = Utils.inputEmail();
        user.put("email", email);

        return user;
    }

    public static void userNotFound() {
        Utils.println("\nUser doesn't exist, try logging in with a different email.");
        Utils.input("Press enter to go back to the Authentication page");
    }

    public static Map<String, String> signUp() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("SignUp");

        Map<String, String> userInput = new HashMap<String, String>();

        userInput.put("email", Utils.inputEmail());
        userInput.put("name", Utils.input("Name: "));
        userInput.put("country", Utils.input("Country: "));
        userInput.put("city", Utils.input("City: "));
        userInput.put("street", Utils.input("Street: "));
        userInput.put("postalCode", Utils.inputPostalCode());
        userInput.put("avgHeartRate", Utils.inputAvgHeartRate());
        userInput.put("type", Utils.inputUserType());

        return userInput;
    }

    public static void userAlreadyExists() {
        Utils.println("\nUser already exists, try signing up with a different email.");
        Utils.input("Press enter to go back to the Authentication page");
    }
}
