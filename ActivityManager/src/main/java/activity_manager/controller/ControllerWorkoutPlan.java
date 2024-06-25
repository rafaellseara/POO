package activity_manager.controller;

import activity_manager.model.activity.*;
import activity_manager.model.user.User;
import activity_manager.model.workout_plan.DailyPlan;
import activity_manager.model.workout_plan.WorkoutPlan;
import activity_manager.model.workout_plan.WorkoutPlanListings;
import activity_manager.view.Utils;
import activity_manager.view.ViewActivity;
import activity_manager.view.ViewUser;
import activity_manager.view.ViewWorkoutPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class ControllerWorkoutPlan {
    public static void menu() {
        int option = ViewWorkoutPlan.menu();

        switch (option) {
            case 1:
                addWorkoutPlan();
                break;
            case 2:
                listWorkoutPlans();
                break;
            case 3:
                //Edit workout plan
                break;
            case 4:
                ControllerAdmin.menu();
                break;

        }
    }

    public static void listWorkoutPlans() {
        Map<String, WorkoutPlan> workoutPlansMap = WorkoutPlanListings.getInstance().getWorkoutPlansMap();
        List<String> workoutPlanStrings = new ArrayList<String>();

        for(Map.Entry<String, WorkoutPlan> workoutPlan : workoutPlansMap.entrySet()) {
            workoutPlanStrings.add(workoutPlan.toString());
        }

        ViewWorkoutPlan.listWorkoutPlans(workoutPlanStrings);

        ControllerWorkoutPlan.menu();
    }

    public static void viewUserWorkoutPlan(User user){
        WorkoutPlan userWorkoutPlan = user.getWorkoutPlan();
        if (userWorkoutPlan != null) {
            String userWorkoutPlanString = userWorkoutPlan.toString();
            ViewWorkoutPlan.userWorkoutPlan(userWorkoutPlanString);
            ControllerUser.menu(user);
        } else {
            ViewWorkoutPlan.noWorkoutPlanFound();
            ControllerUser.menu(user);
        }
    }

    public static WorkoutPlan createWorkoutPlanSimple () {
        Map<String, Activity> activitiesMap = ActivityListings.getInstance().getActivities();

        if (!(ActivityListings.getInstance().checkActivities())) {
            ViewWorkoutPlan.noActivitiesFound();
            ControllerWorkoutPlan.menu();
        }

        String workoutPlanName = ViewWorkoutPlan.addWorkoutPlan();

        LocalDate endDate = ViewWorkoutPlan.addEndDate();

        if (WorkoutPlanListings.getInstance().checkWorkoutPlan(workoutPlanName)) {
            ViewWorkoutPlan.workoutPlanAlreadyExists();
            ControllerWorkoutPlan.menu();
        }

        WorkoutPlan workoutPlan = null;

        boolean loop = true;
        while(loop){
            int numOfDays = ViewWorkoutPlan.numOfDays();

            int[] daysOfWeekArr = new int[7];

            Random random = new Random();
            for (int i = 0; i < numOfDays; i++) {
                int index = random.nextInt(7);
                while (daysOfWeekArr[index] == 1) {
                    index = random.nextInt(7);
                }
                daysOfWeekArr[index] = 1;
            }

            int objective = ViewWorkoutPlan.objective();

            int maxNumActPerDay = ViewWorkoutPlan.maxNumAct();

            int numOfActivities = Integer.parseInt(ViewWorkoutPlan.numOfActivities());

            List<Activity> activitiesOfType = new ArrayList<Activity>();

            for (Map.Entry<String, Activity> entry : activitiesMap.entrySet()) {
                Activity activity = entry.getValue();
                if (numOfActivities > 0) {
                    if (objective == 0 && (activity instanceof Back || activity instanceof Chest || activity instanceof Arms || activity instanceof Legs)) {
                        activitiesOfType.add(activity);
                        numOfActivities--;
                    } else if (objective == 1 && (activity instanceof Distance || activity instanceof DistanceAndAltimeter)) {
                        activitiesOfType.add(activity);
                        numOfActivities--;
                    } else if (objective == 2 && (activity instanceof Distance || activity instanceof Repetitions)) {
                        activitiesOfType.add(activity);
                        numOfActivities--;
                    }
                }
                if (numOfActivities == 0) {
                    break;
                }
            }

            List<DailyPlan> dailyPlanList= new ArrayList<DailyPlan>();

            int j = 0;
            for (DailyPlan.DayOfWeek day : DailyPlan.DayOfWeek.values()) {
                List<Activity> activities = new ArrayList<Activity>();

                if (daysOfWeekArr[j] == 1) {
                    Random random_for_act = new Random();

                    int numOfRandomActivities = random_for_act.nextInt(maxNumActPerDay) + 1;

                    Random randomAct = new Random();

                    numOfRandomActivities = Math.min(numOfRandomActivities, activitiesOfType.size());

                    Set<Integer> selectedIndices = new HashSet<>();

                    for (int i = 0; i < numOfRandomActivities; i++) {
                        int randomIndex;
                        do {
                            randomIndex = random_for_act.nextInt(activitiesOfType.size());
                        } while (selectedIndices.contains(randomIndex));
                        selectedIndices.add(randomIndex);
                        Activity activity = activitiesOfType.get(randomIndex);

                        if (activity instanceof DistanceAndAltimeter) {
                            ((DistanceAndAltimeter) activity).setDistanceInMeters(randomAct.nextInt(10000) + 1); // Generating distance between 1 and 10000 meters
                            ((DistanceAndAltimeter) activity).setAltimeterInMeters(randomAct.nextInt(100) + 1); // Generating altimeter between 1 and 100 meters
                            ((DistanceAndAltimeter) activity).setPace(randomAct.nextDouble() * 10 + 1); // Generating pace between 1 and 10 m/s
                        } else if (activity instanceof Distance) {
                            ((Distance) activity).setDistanceInMeters(randomAct.nextInt(10000) + 1); // Generating distance between 1 and 10000 meters
                            ((Distance) activity).setPaceInKmH(randomAct.nextDouble() * 20 + 1); // Generating pace between 1 and 20 km/h
                        } else if (activity instanceof Repetitions) {
                            ((Repetitions) activity).setSets(randomAct.nextInt(5) + 1); // Generating sets between 1 and 5
                            ((Repetitions) activity).setRepetitions(randomAct.nextInt(20) + 1); // Generating repetitions between 1 and 20
                        } else if (activity instanceof RepetitionsWithWeights repetitionsWithWeights) {
                            switch (repetitionsWithWeights) {
                                case Chest chest -> {
                                    chest.setRepetitions(randomAct.nextInt(20) + 1); // Generating repetitions between 1 and 20
                                    chest.setSets(randomAct.nextInt(5) + 1); // Generating sets between 1 and 5
                                    chest.setWeight(randomAct.nextInt(100) + 1); // Generating weight between 1 and 100 kg
                                }
                                case Back back -> {
                                    back.setRepetitions(randomAct.nextInt(20) + 1); // Generating repetitions between 1 and 20
                                    back.setSets(randomAct.nextInt(5) + 1); // Generating sets between 1 and 5
                                    back.setWeight(randomAct.nextInt(100) + 1); // Generating weight between 1 and 100 kg
                                }
                                case Arms arms -> {
                                    arms.setRepetitions(randomAct.nextInt(20) + 1); // Generating repetitions between 1 and 20
                                    arms.setSets(randomAct.nextInt(5) + 1); // Generating sets between 1 and 5
                                    arms.setWeight(randomAct.nextInt(100) + 1); // Generating weight between 1 and 100 kg
                                }
                                case Legs legs -> {
                                    legs.setRepetitions(randomAct.nextInt(20) + 1); // Generating repetitions between 1 and 20
                                    legs.setSets(randomAct.nextInt(5) + 1); // Generating sets between 1 and 5
                                    legs.setWeight(randomAct.nextInt(100) + 1); // Generating weight between 1 and 100 kg
                                }
                                default -> {
                                }
                            }
                        }

                        activities.add(activity);
                    }
                } else {
                    DailyPlan dailyPlan = new DailyPlan(day, activities);
                    dailyPlan.setDayOfWeek(day);
                }
                j++;

                DailyPlan dailyPlan = new DailyPlan(day, activities);
                dailyPlanList.add(dailyPlan);
            }

            workoutPlan = new WorkoutPlan(workoutPlanName, dailyPlanList, endDate);
            ViewWorkoutPlan.userWorkoutPlanV2(workoutPlan.toString());

            loop = (!ViewWorkoutPlan.workoutPlanConfirmation());
            if (loop) ViewWorkoutPlan.chooseNewPlan();
        }

        return workoutPlan;
    }

    public static WorkoutPlan createWorkoutPlan() {
        Map<String, Activity> activitiesMap = ActivityListings.getInstance().getActivities();
        List<String> activitiesString = new ArrayList<String>();

        if (ActivityListings.getInstance().checkActivities()) {
            for (Map.Entry<String, Activity> activity : activitiesMap.entrySet()) {
                activitiesString.add(activity.toString());
            }
        } else {
            ViewWorkoutPlan.noActivitiesFound();
            ControllerWorkoutPlan.menu();
        }

        String workoutPlanName = ViewWorkoutPlan.addWorkoutPlan();

        LocalDate endDate = ViewWorkoutPlan.addEndDate();

        if (WorkoutPlanListings.getInstance().checkWorkoutPlan(workoutPlanName)) {
            ViewWorkoutPlan.workoutPlanAlreadyExists();
            ControllerWorkoutPlan.menu();
        }

        List<DailyPlan> dailyPlanList= new ArrayList<DailyPlan>();

        for (DailyPlan.DayOfWeek day : DailyPlan.DayOfWeek.values()) {
            List<Activity> activities = new ArrayList<Activity>();

            Boolean loop = true;
            while(loop){
                String type = ViewActivity.addTypeV2(day);
                if (type == null) loop = false;
                if (loop){
                    if (type.equals("RepetitionsWithWeights")) type = ViewActivity.addSubType(day);
                    Map<String, List<String>> activitiesByType = getActivitiesByType(type);

                    if (!activitiesByType.isEmpty()){
                        int option = addActivityToDailyPlan(day, activitiesByType, type, activities);
                        if (option == 0) loop = false;
                        if (loop && "n".equals(ViewWorkoutPlan.addMoreActivities())) loop = false;
                    } else {
                        ViewActivity.noActivitiesForTheType();
                    }
                }

            }

            DailyPlan dailyPlan = new DailyPlan(day, activities);
            dailyPlanList.add(dailyPlan);
        }

        return new WorkoutPlan(workoutPlanName, dailyPlanList, endDate);
    }

    public static void addWorkoutPlan() {
        WorkoutPlan workoutPlan = createWorkoutPlan();
        WorkoutPlanListings.getInstance().addWorkoutPlan(workoutPlan);
        ViewWorkoutPlan.workoutPlanAddSuccessfully();
        ControllerAdmin.menu();
    }

    public static void addUserWorkoutPlan(User user) {
        WorkoutPlan workoutPlan = createWorkoutPlan();
        user.setWorkoutPlan(workoutPlan);
        ViewWorkoutPlan.workoutPlanAddSuccessfully();
        ControllerUser.menu(user);
    }

    public static void addUserWorkoutPlanSimple(User user) {
        WorkoutPlan workoutPlan = createWorkoutPlanSimple();
        user.setWorkoutPlan(workoutPlan);
        ViewWorkoutPlan.workoutPlanAddSuccessfully();
        ControllerUser.menu(user);
    }

    public static int addActivityToDailyPlan(DailyPlan.DayOfWeek day, Map<String, List<String>> activitiesByType, String type, List<Activity> activities){

        List<String> allActivities = new ArrayList<>();

        for (List<String> activity : activitiesByType.values()) {
            allActivities.addAll(activity);
        }

        ViewWorkoutPlan.addActivitiesToWorkoutPlan(day, allActivities);

        int activityOption = Utils.inputOption(allActivities.size());

        if (activityOption != 0){

            String activityToString = allActivities.get(activityOption - 1);

            int colonIndex = activityToString.indexOf(":");
            activityToString = activityToString.substring(colonIndex + 1).trim();
            activityToString = activityToString.split(",")[0].trim();

            Activity selectedActivity = ActivityListings.getInstance().getActivity(activityToString);

            if (selectedActivity instanceof DistanceAndAltimeter) {
                DistanceAndAltimeter copyActivity = new DistanceAndAltimeter((DistanceAndAltimeter) selectedActivity);
                Map<String, String> activityInput = ViewActivity.addDistanceAndAltimeterActivity();
                copyActivity.setDistanceInMeters(Integer.parseInt(activityInput.get("distance")));
                copyActivity.setAltimeterInMeters(Integer.parseInt(activityInput.get("altimeter")));
                copyActivity.setPace(Integer.parseInt(activityInput.get("pace")));
                activities.add(copyActivity);
            } else if (selectedActivity instanceof Distance) {
                Distance copyActivity = new Distance((Distance) selectedActivity);
                Map<String, String> activityInput = ViewActivity.addDistanceActivity();
                copyActivity.setDistanceInMeters(Integer.parseInt(activityInput.get("distance")));
                copyActivity.setPaceInKmH(Integer.parseInt(activityInput.get("pace")));
                activities.add(copyActivity);
            } else if (selectedActivity instanceof Repetitions) {
                Repetitions copyActivity = new Repetitions((Repetitions) selectedActivity);
                Map<String, String> activityInput = ViewActivity.addRepetitionsActivity();
                copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                activities.add(copyActivity);
            } else if (selectedActivity instanceof RepetitionsWithWeights) {
                switch (selectedActivity) {
                    case Chest chest -> {
                        Chest copyActivity = new Chest(chest);
                        Map<String, String> activityInput = ViewActivity.addRepetitionsWithWeightsActivity("Chest");
                        copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                        copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                        copyActivity.setWeight(Integer.parseInt(activityInput.get("weight")));
                        activities.add(copyActivity);
                    }
                    case Arms arms -> {
                        Arms copyActivity = new Arms(arms);
                        Map<String, String> activityInput = ViewActivity.addRepetitionsWithWeightsActivity("Arms");
                        copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                        copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                        copyActivity.setWeight(Integer.parseInt(activityInput.get("weight")));
                        activities.add(copyActivity);
                    }
                    case Back back -> {
                        Back copyActivity = new Back(back);
                        Map<String, String> activityInput = ViewActivity.addRepetitionsWithWeightsActivity("Back");
                        copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                        copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                        copyActivity.setWeight(Integer.parseInt(activityInput.get("weight")));
                        activities.add(copyActivity);
                    }
                    case Legs legs -> {
                        Legs copyActivity = new Legs(legs);
                        Map<String, String> activityInput = ViewActivity.addRepetitionsWithWeightsActivity("Legs");
                        copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                        copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                        copyActivity.setWeight(Integer.parseInt(activityInput.get("weight")));
                        activities.add(copyActivity);
                    }
                    default -> {
                    }
                }
            }
        }
        return activityOption;
    }

    public static void doAnActivity(User user) {
        String type = ViewActivity.addType();
        Map<String, List<String>> activitiesByType = getActivitiesByType(type);

        if (!activitiesByType.isEmpty()) {
            String selectedActivityName = ViewActivity.listActivitiesV2(activitiesByType);
            int colonIndex = selectedActivityName.indexOf(":");
            selectedActivityName = selectedActivityName.substring(colonIndex + 1).trim();
            selectedActivityName = selectedActivityName.split(",")[0].trim();

            Activity selectedActivity = ActivityListings.getInstance().getActivity(selectedActivityName);

            if (selectedActivity instanceof DistanceAndAltimeter) {
                DistanceAndAltimeter copyActivity = new DistanceAndAltimeter((DistanceAndAltimeter) selectedActivity);
                Map<String, String> activityInput = ViewActivity.addDistanceAndAltimeterActivity();
                copyActivity.setDistanceInMeters(Integer.parseInt(activityInput.get("distance")));
                copyActivity.setAltimeterInMeters(Integer.parseInt(activityInput.get("altimeter")));
                user.addActivity(copyActivity);
            } else if (selectedActivity instanceof Distance) {
                Distance copyActivity = new Distance((Distance) selectedActivity);
                Map<String, String> activityInput = ViewActivity.addDistanceActivity();
                copyActivity.setDistanceInMeters(Integer.parseInt(activityInput.get("distance")));
                user.addActivity(copyActivity);
            } else if (selectedActivity instanceof Repetitions) {
                Repetitions copyActivity = new Repetitions((Repetitions) selectedActivity);
                Map<String, String> activityInput = ViewActivity.addRepetitionsActivity();
                copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                user.addActivity(copyActivity);
            } else if (selectedActivity instanceof RepetitionsWithWeights) {
                switch (selectedActivity) {
                    case Chest chest -> {
                        Chest copyActivity = new Chest(chest);
                        Map<String, String> activityInput = ViewActivity.addRepetitionsWithWeightsActivity("Chest");
                        copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                        copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                        copyActivity.setWeight(Integer.parseInt(activityInput.get("weight")));
                        user.addActivity(copyActivity);
                    }
                    case Arms arms -> {
                        Arms copyActivity = new Arms(arms);
                        Map<String, String> activityInput = ViewActivity.addRepetitionsWithWeightsActivity("Arms");
                        copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                        copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                        copyActivity.setWeight(Integer.parseInt(activityInput.get("weight")));
                        user.addActivity(copyActivity);
                    }
                    case Back back -> {
                        Back copyActivity = new Back(back);
                        Map<String, String> activityInput = ViewActivity.addRepetitionsWithWeightsActivity("Back");
                        copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                        copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                        copyActivity.setWeight(Integer.parseInt(activityInput.get("weight")));
                        user.addActivity(copyActivity);
                    }
                    case Legs legs -> {
                        Legs copyActivity = new Legs(legs);
                        Map<String, String> activityInput = ViewActivity.addRepetitionsWithWeightsActivity("Legs");
                        copyActivity.setSets(Integer.parseInt(activityInput.get("sets")));
                        copyActivity.setRepetitions(Integer.parseInt(activityInput.get("repetitions")));
                        copyActivity.setWeight(Integer.parseInt(activityInput.get("weight")));
                        user.addActivity(copyActivity);
                    }
                    default -> {
                    }
                }
            }
            ViewActivity.activityAddSuccessfully();
        } else {
            ViewActivity.noActivitiesForTheType();
        }

        ControllerUser.menu(user);
    }

    private static Map<String, List<String>> getActivitiesByType(String type) {
        Map<String, Activity> activitiesMap = ActivityListings.getInstance().getActivities();
        Map<String, List<String>> activitiesByType = new HashMap<>();

            for (Activity activity : activitiesMap.values()) {
                if (activity.getType().equals(type)) {
                    StringBuilder simplifiedActivityString = new StringBuilder();
                    simplifiedActivityString.append("name: ").append(activity.getName()).append(", hard: ").append(activity.isHard());
                    String activityString = simplifiedActivityString.toString();
                    activitiesByType.computeIfAbsent(type, k -> new ArrayList<>()).add(activityString);
                }
            }

        return activitiesByType;
    }
}
