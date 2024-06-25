package activity_manager.model.others;

import activity_manager.model.activity.Activity;
import activity_manager.model.time.Time;
import activity_manager.model.user.User;
import activity_manager.model.user.UserListings;
import activity_manager.model.workout_plan.WorkoutPlan;
import activity_manager.model.workout_plan.DailyPlan;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Stats {

    public static User getUserWithMostCaloriesSpent(UserListings users) {
        User userWithMostCalories = null;
        double maxCalories = -1;

        for (User user : users.getUsers().values()) {
            double caloriesSpent = user.getCaloriesSpent();
            if (caloriesSpent > maxCalories) {
                maxCalories = caloriesSpent;
                userWithMostCalories = user;
            }
        }
        return userWithMostCalories;
    }

    public static User getUserWithMostActivitiesDone(UserListings users) {
        Time time = Time.getInstance();
        LocalDate startDate = time.getStartDate();
        LocalDate currentDate = time.getCurrentDate();
        long daysPassed = ChronoUnit.DAYS.between(startDate, currentDate);

        User userWithMostActivities = null;
        int maxActivities = -1;

        for (User user : users.getUsers().values()) {
            int activitiesCount = (int) (user.getNumberOfActivitiesDone() * daysPassed);

            if (activitiesCount > maxActivities) {
                maxActivities = activitiesCount;
                userWithMostActivities = user;
            }
        }

        return userWithMostActivities;
    }

    public static String getMostPerformedActivityType(UserListings users) {
        Map<String, Integer> activityCounts = new HashMap<>();

        // Iterate over all users and their activities
        for (User user : users.getUsers().values()) {
            WorkoutPlan workoutPlan = user.getWorkoutPlan();
            for (DailyPlan dailyPlan : workoutPlan.getWorkouts()) {
                for (Activity activity : dailyPlan.getActivities()) {
                    String activityType = activity.getType();
                    // Increment the count for this activity type in the map
                    activityCounts.put(activityType, activityCounts.getOrDefault(activityType, 0) + 1);
                }
            }
            for (Activity activity : user.getActivities()) {
                String activityType = activity.getType();
                // Increment the count for this activity type in the map
                activityCounts.put(activityType, activityCounts.getOrDefault(activityType, 0) + 1);
            }
        }

        // Determine the most performed activity type
        String mostPerformedActivityType = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : activityCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPerformedActivityType = entry.getKey();
            }
        }

        return mostPerformedActivityType;
    }

    public static double getKmsDoneByUser(User user) {
        Time time = Time.getInstance();
        LocalDate startDate = time.getStartDate();
        LocalDate currentDate = time.getCurrentDate();
        long daysPassed = ChronoUnit.DAYS.between(startDate, currentDate);

        return user.getKmsDone() * daysPassed;
    }

    public static double getMetersClimbedByUser(User user) {
        Time time = Time.getInstance();
        LocalDate startDate = time.getStartDate();
        LocalDate currentDate = time.getCurrentDate();
        long daysPassed = ChronoUnit.DAYS.between(startDate, currentDate);

        return user.getMetersDone() * daysPassed;
    }

    public static WorkoutPlan getMostDemandingWorkoutPlanByCaloriesSpent(UserListings users) {
        WorkoutPlan mostDemandingWorkoutPlan = null;
        double maxCaloriesSpent = -1;

        for (User user : users.getUsers().values()) {
            double caloriesSpent = user.getTotalCaloriesSpent();
            if (caloriesSpent > maxCaloriesSpent) {
                maxCaloriesSpent = caloriesSpent;
                mostDemandingWorkoutPlan = user.getWorkoutPlan();
            }
        }
        return mostDemandingWorkoutPlan;
    }
}
