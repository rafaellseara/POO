package activity_manager.controller;

import activity_manager.model.activity.*;
import activity_manager.model.user.User;
import activity_manager.view.Utils;
import activity_manager.view.ViewActivity;

import java.util.*;

public class ControllerActivity {

    public static void listActivities() {
        Map<String, Activity> activitiesMap = ActivityListings.getInstance().getActivities();
        Map<String, List<String>> activitiesByType = new HashMap<>();

        // Group activities by type
        for (Activity activity : activitiesMap.values()) {
            String type = activity.getType();
            StringBuilder simplifiedActivityString = new StringBuilder();
            simplifiedActivityString.append("name: ").append(activity.getName()).append(", hard: ").append(activity.isHard());
            String activityString = simplifiedActivityString.toString();
            activitiesByType.computeIfAbsent(type, k -> new ArrayList<>()).add(activityString);
        }

        ViewActivity.listActivities(activitiesByType);

        ControllerAdmin.menu();
    }

    public static void viewUserActivities(User user){
        List<Activity> activities = user.getActivities();
        Utils.println("Activities: ");
        for (Activity activity : activities) {
            switch (activity) {
                case DistanceAndAltimeter distanceAndAltimeter -> {
                    Utils.println(distanceAndAltimeter.toString() + ", " +
                            distanceAndAltimeter.getDistanceInMeters() + "meters, " +
                            distanceAndAltimeter.getAltimeterInMeters() + "meters, " +
                            distanceAndAltimeter.getPace() + "km/h");
                }
                case Distance distance -> {
                    Utils.println(distance.toString() + ", " +
                            distance.getDistanceInMeters() + "meters, " +
                            distance.getPaceInKmH() + "km/h");
                }
                case Repetitions repetitions -> {
                    Utils.println(repetitions.toString() + ", " +
                            repetitions.getSets() + "sets, " +
                            repetitions.getRepetitions() + "repetitions");
                }
                case RepetitionsWithWeights repsWithWeight -> {
                    switch (repsWithWeight) {
                        case Chest chest -> {
                            Utils.println(chest.toString() + ", " +
                                    chest.getSets() + "sets, " +
                                    chest.getRepetitions() + "repetitions, " +
                                    chest.getWeight() + "weight");
                        }
                        case Arms arms -> {
                            Utils.println(arms.toString() + ", " +
                                    arms.getSets() + "sets, " +
                                    arms.getRepetitions() + "repetitions, " +
                                    arms.getWeight() + "weight");
                        }
                        case Back back -> {
                            Utils.println(back.toString() + ", " +
                                    back.getSets() + "sets, " +
                                    back.getRepetitions() + "repetitions, " +
                                    back.getWeight() + "weight");
                        }
                        case Legs legs -> {
                            Utils.println(legs.toString() + ", " +
                                    legs.getSets() + "sets, " +
                                    legs.getRepetitions() + "repetitions, " +
                                    legs.getWeight() + "weight");
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + repsWithWeight);
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + activity);
            }
        }
        Utils.input("Press enter to continue...");
        ControllerUser.menu(user);
    }

    public static void addActivity() {
        Map<String, String> activityMap = ViewActivity.addActivity();

        if (ActivityListings.getInstance().checkActivity(activityMap.get("name"))) {
            ViewActivity.activityAlreadyExists();
            ControllerAdmin.menu();
        }

        Boolean isTypeHard = false;
        if ("y".equals(activityMap.get("isHard"))) isTypeHard = true;

        String type = activityMap.get("type");
        switch (type) {
            case "DistanceAndAltimeter":
                DistanceAndAltimeter distanceAndAltimeterActivity = new DistanceAndAltimeter(activityMap.get("name"), isTypeHard);
                ActivityListings.getInstance().addActivity(distanceAndAltimeterActivity);
                break;
            case "Distance":
                Distance distanceActivity = new Distance(activityMap.get("name"), isTypeHard);
                ActivityListings.getInstance().addActivity(distanceActivity);
                break;
            case "Repetitions":
                Repetitions repetitionsActivity = new Repetitions(activityMap.get("name"), isTypeHard);
                ActivityListings.getInstance().addActivity(repetitionsActivity);
                break;
            case "RepetitionsWithWeights":
                handleRepetitionsWithWeights(activityMap.get("name"), isTypeHard);
                break;
        }
        ViewActivity.activityAddSuccessfully();
        ControllerAdmin.menu();
    }

    public static void handleRepetitionsWithWeights(String name, boolean isTypeHard){
        String bodyPart = Utils.getBodyPart();
        switch (bodyPart){
            case "Chest":
                Chest chestActivity = new Chest(name, isTypeHard);
                ActivityListings.getInstance().addActivity(chestActivity);
                break;
            case "Back":
                Back backActivity = new Back(name, isTypeHard);
                ActivityListings.getInstance().addActivity(backActivity);
                break;
            case "Arms":
                Arms armsActivity = new Arms(name, isTypeHard);
                ActivityListings.getInstance().addActivity(armsActivity);
                break;
            case "Legs":
                Legs legsActivity = new Legs(name, isTypeHard);
                ActivityListings.getInstance().addActivity(legsActivity);
                break;
        }
    }
}


