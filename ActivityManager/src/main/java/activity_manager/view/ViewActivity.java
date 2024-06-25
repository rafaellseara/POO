package activity_manager.view;

import activity_manager.model.workout_plan.DailyPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewActivity {
    public static void listActivities(Map<String, List<String>> activitiesByType) {
        int pageNumber = 1, option = 1;
        while (option > 0) {
            Utils.clearScreen();
            Utils.printHeader();
            Utils.println("\nList of Activities:\n");

            // Display activities for each type
            for (Map.Entry<String, List<String>> entry : activitiesByType.entrySet()) {
                String type = entry.getKey();
                List<String> activities = entry.getValue();

                Utils.println("====================================");
                Utils.println(type);

                for (String activity : activities) {
                    Utils.println(activity);
                }
            }

            Utils.println("\n[1] <-");
            Utils.println("[2] ->");
            Utils.println("[3] Back");

            option = Utils.inputOption(3);

            if (option == 1 && pageNumber > 1) {
                pageNumber--;
            } else if (option == 2) {
                pageNumber++;
            } else if (option == 3) {
                break;
            }
        }
    }

    public static String listActivitiesV2(Map<String, List<String>> activitiesByType) {
        int pageNumber = 1, option = 1;
        List<String> allActivities = new ArrayList<>();
        String inputActivityName = null;

        for (List<String> activities : activitiesByType.values()) {
            allActivities.addAll(activities);
        }

        while (option > 0) {
            Utils.clearScreen();
            Utils.printHeader();
            Utils.println("\nList of Activities:\n");

            List<String> currentActivities = Utils.getPage(allActivities, pageNumber);

            Utils.println("====================================");
            int activityNumber = (pageNumber - 1) * 5 + 1; // Starting activity number for the current page
            for (String activity : currentActivities) {
                Utils.println(activityNumber + ". " + activity);
                activityNumber++;
            }
            Utils.println("====================================");

            Utils.println("\n[1] <-");
            Utils.println("[2] ->");
            Utils.println("[3] Select activity by number");

            option = Utils.inputOption(3);

            if (option == 1 && pageNumber > 1) {
                pageNumber--;
            } else if (option == 2 && allActivities.size() > pageNumber * 5) {
                pageNumber++;
            } else if (option == 3) {
                int totalActivities = allActivities.size();
                int selectedActivityNumber = Integer.parseInt(Utils.inputInt("Enter the number of the activity you want to do: "));

                if (selectedActivityNumber >= 1 && selectedActivityNumber <= totalActivities) {
                    inputActivityName = allActivities.get(selectedActivityNumber - 1);
                    Utils.println("Selected activity: " + inputActivityName);
                    return inputActivityName;
                } else {
                    Utils.input("Invalid activity number. Please enter a number within the range.");
                }
            }
        }

        return null;
    }

    public static Map<String, String> addActivity() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("Add a new Activity");

        Map<String, String> activityInput = new HashMap<String, String>();

        activityInput.put("name", Utils.input("Name: "));
        activityInput.put("type", Utils.inputActivityType());
        activityInput.put("isHard", Utils.inputYesOrNo("Is it a Hard Activity?(y/n): "));

        return activityInput;
    }

    public static Map<String, String> addDistanceAndAltimeterActivity(){
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("Distance and Altimeter Activity:");

        Map<String, String> activityInput = new HashMap<String, String>();

        activityInput.put("distance", Utils.input("Distance(m): "));
        activityInput.put("altimeter", Utils.input("Altimeter(m): "));
        activityInput.put("pace", Utils.input("Pace(Km/h): "));

        return activityInput;
    }

    public static Map<String, String> addDistanceActivity(){
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("Distance Activity:");

        Map<String, String> activityInput = new HashMap<String, String>();

        activityInput.put("distance", Utils.input("Distance(m): "));
        activityInput.put("pace", Utils.input("Pace(Km/h): "));

        return activityInput;
    }

    public static Map<String, String> addRepetitionsActivity(){
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("Repetitions Activity:");

        Map<String, String> activityInput = new HashMap<String, String>();

        activityInput.put("sets", Utils.input("Sets: "));
        activityInput.put("repetitions", Utils.input("Repetitions: "));

        return activityInput;
    }

    public static Map<String, String> addRepetitionsWithWeightsActivity(String bodyPart){
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("Repetitions with Weights Activity for " + bodyPart + ": ");

        Map<String, String> activityInput = new HashMap<String, String>();

        activityInput.put("sets", Utils.input("Sets: "));
        activityInput.put("repetitions", Utils.input("Repetitions: "));
        activityInput.put("weight", Utils.input("Weight(Kg): "));

        return activityInput;
    }

    public static void activityAlreadyExists() {
        Utils.println("\nActivity already exists, try a different name.");
        Utils.input("Press enter to go back to the Admin menu");
    }

    public static void activityAddSuccessfully() {
        Utils.println("\nActivity add Successfully.");
        Utils.input("Press enter to go back to the Admin menu");
    }

    public static String addType() {
        Utils.clearScreen();
        return Utils.inputActivityType();
    }

    public static String addTypeV2(DailyPlan.DayOfWeek day) {
        Utils.clearScreen();
        Utils.println("\n" + day + ":\n");
        return Utils.inputActivityTypeV2();
    }

    public static void noActivitiesForTheType() {
        Utils.println("No activities available for the selected type.");
        Utils.input("Press enter to go back to the User menu");
    }

    public static String addSubType(DailyPlan.DayOfWeek day) {
        Utils.clearScreen();
        Utils.println("\n" + day + ":\n");
        return Utils.getBodyPart();
    }
}
