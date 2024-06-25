package activity_manager.controller;

import activity_manager.model.others.Address;
import activity_manager.model.user.*;
import activity_manager.view.Utils;
import activity_manager.view.ViewAuthentication;

import java.util.Map;

public class    ControllerAuthentication {
    public static void login() {
        Map<String, String> userMap = ViewAuthentication.login();

        if (userMap.get("email").equals("0")) ControllerMenu.menu();

        User user = UserListings.getInstance().getUser(userMap.get("email"));

        if (user == null) {
            ViewAuthentication.userNotFound();
            ControllerMenu.menu();
        }

        ControllerUser.menu(user);
    }

    public static void signUp() {
        Map <String, String> userMap = ViewAuthentication.signUp();

        if (UserListings.getInstance().checkUser(userMap.get("email"))) {
            ViewAuthentication.userAlreadyExists();
            ControllerMenu.menu();
        }
        User user = registerUser(userMap);

        ControllerUser.menu(user);
    }

    private static User registerUser(Map<String, String> user) {
        if (UserListings.getInstance().checkUser(user.get("email"))) return null;

        Address address = new Address(user.get("country"), user.get("city"), user.get("street"), user.get("postalCode"));

        int avgHeartRate = Utils.parseStringToInt(user.get("avgHeartRate"));
        String type = user.get("type");
        switch (type) {
            case "PRO":
                UserPro newUserPro = new UserPro(user.get("name"), user.get("email"), address, avgHeartRate);
                UserListings.getInstance().addUser(newUserPro);
                return newUserPro;
            case "AMATEUR":
                UserAmateur newUserAmateur = new UserAmateur(user.get("name"), user.get("email"), address, avgHeartRate);
                UserListings.getInstance().addUser(newUserAmateur);
                return newUserAmateur;
            case "OCASIONAL":
                UserOcasional newUserOcasional = new UserOcasional(user.get("name"), user.get("email"), address, avgHeartRate);
                UserListings.getInstance().addUser(newUserOcasional);
                return newUserOcasional;
        }
        return null;
    }
}
