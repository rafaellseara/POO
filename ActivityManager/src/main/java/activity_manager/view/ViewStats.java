package activity_manager.view;

import activity_manager.controller.ControllerAdmin;
import activity_manager.model.others.Stats;
import activity_manager.model.user.User;
import activity_manager.model.user.UserListings;
import activity_manager.model.workout_plan.WorkoutPlan;

public class ViewStats {
    public static int menu(){
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\n[1] User with most calories spent");
        Utils.println("[2] User with most Activities done");
        Utils.println("[3] Type of Activity performed the most");
        Utils.println("[4] Most Kms done by User");
        Utils.println("[5] Most Meters climbed by User");
        Utils.println("[6] Most demanding WorkOut by calories spent");
        Utils.println("[7] Back");
        Utils.println("[8] Exit");
        int option = Utils.inputOption(8);
        return option;
    }

    public static void printStatistics(int option){
        UserListings userListings = UserListings.getInstance();
        switch (option) {
            case 1:
                User user = Stats.getUserWithMostCaloriesSpent(userListings);
                if (user != null) {
                    Utils.println("The User is: ");
                    Utils.println(user.toString());
                    Utils.println("With calories: " + user.getCalories());
                } else {
                    Utils.println("No User in database!");
                }
                Utils.input("Press enter to continue...");
                ControllerAdmin.menu();
                break;
            case 2:
                User user2 = Stats.getUserWithMostActivitiesDone(userListings);
                if (user2 != null) {
                Utils.println("The User is: ");
                Utils.println(user2.toString());
                Utils.println("With: " + user2.getNumberOfActivities() + " Activities");
                } else {
                    Utils.println("No User in database!");
                }

                Utils.input("Press enter to continue...");
                ControllerAdmin.menu();
                break;
            case 3:
                String activityType = Stats.getMostPerformedActivityType(userListings);
                Utils.println("The ActivityType is: ");
                Utils.println(activityType);

                Utils.input("Press enter to continue...");
                ControllerAdmin.menu();
                break;
            case 4:
                String email = Utils.input("Enter the email of the user: ");
                User user3 = userListings.getUser(email);
                if (user3 == null) {
                    Utils.println("User not found.");
                } else {
                    double kmsDone = Stats.getKmsDoneByUser(user3);
                    Utils.println("The User: ");
                    Utils.println(user3.toString());
                    Utils.println("Has done " + kmsDone + " kms since the beginning.");
                }
                Utils.input("Press enter to continue...");
                ControllerAdmin.menu();
                break;
            case 5:
                String email2 = Utils.input("Enter the email of the user: ");
                User user4 = userListings.getUser(email2);
                if (user4 == null) {
                    Utils.println("User not found.");
                } else {
                    double metersClimbed = Stats.getMetersClimbedByUser(user4);
                    Utils.println("The User: ");
                    Utils.println(user4.toString());
                    Utils.println("Has climbed " + metersClimbed + " meters since the beginning.");
                }
                Utils.input("Press enter to continue...");
                ControllerAdmin.menu();
                break;
            case 6:
                WorkoutPlan workoutPlan = Stats.getMostDemandingWorkoutPlanByCaloriesSpent(userListings);
                if (workoutPlan != null) {
                    Utils.println("The WorkOutPlan is: ");
                    Utils.println(workoutPlan.toString());
                } else {
                    Utils.println("No workout plans in database!");
                }

                Utils.input("Press enter to continue...");
                ControllerAdmin.menu();
                break;
            case 7:
                ControllerAdmin.menu();
                break;
            case 8:
                System.exit(0);
                break;
        }
    }
}
