package activity_manager.controller;

import activity_manager.model.user.User;
import activity_manager.model.user.UserListings;
import activity_manager.view.ViewUser;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class ControllerUser {
    public static void menu(User user) {
        int option = ViewUser.menu();

        switch (option) {
            case 1:
                ControllerWorkoutPlan.viewUserWorkoutPlan(user);
                break;
            case 2:
                ControllerActivity.viewUserActivities(user);
                break;
            case 3:
                ControllerWorkoutPlan.addUserWorkoutPlan(user);
                break;
            case 4:
                ControllerWorkoutPlan.doAnActivity(user);
                break;
            case 5:
                ControllerWorkoutPlan.addUserWorkoutPlanSimple(user);
                break;
            case 6:
                ControllerMenu.menu();
                break;
            case 7:
                System.exit(0);
                break;
        }
    }

    public static void listUsers() {
        Map<String, User> usersMap = UserListings.getInstance().getUsers();
        List<String> userString = new ArrayList<String>();

        for (Map.Entry<String, User> user : usersMap.entrySet()) userString.add(user.toString());

        ViewUser.listUsers(userString);

        ControllerAdmin.menu();
    }


}