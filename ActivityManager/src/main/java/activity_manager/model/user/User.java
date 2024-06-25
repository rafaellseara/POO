
package activity_manager.model.user;


import activity_manager.model.activity.Activity;
import activity_manager.model.activity.Distance;
import activity_manager.model.activity.DistanceAndAltimeter;
import activity_manager.model.others.Address;
import activity_manager.model.time.Time;
import activity_manager.model.workout_plan.DailyPlan;
import activity_manager.model.workout_plan.WorkoutPlan;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class User implements Serializable {
    private final UUID id;
    private String name;
    private String email;
    private Address residence;
    private int avgHeartRate;
    private double calories;
    private int numberOfActivities;
    private WorkoutPlan workoutPlan;
    private List<Activity> activities;
    protected double caloriesFactor;

    public User(String name, String email, Address residence, int avgHeartRate, double caloriesFactor) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.residence = residence;
        this.avgHeartRate = avgHeartRate;
        this.calories = 0;
        this.numberOfActivities = 0;
        this.caloriesFactor = caloriesFactor;
        this.activities = new ArrayList<>();
    }

    public abstract String getUserType();

    public double getCaloriesSpent() {
        long daysPassed = getDaysPassed();
        double totalCalories = getTotalCalories() * daysPassed;
        if (activities != null) {
            for (Activity activity : this.activities) {
                double caloriesSpent = activity.getCaloriesSpent() * this.caloriesFactor;
                totalCalories += caloriesSpent;
            }
        }
        this.calories = totalCalories;
        return totalCalories;
    }

    private double getTotalCalories() {
        double totalCalories = 0;
        if (workoutPlan != null) {
            for (DailyPlan dailyPlan : this.workoutPlan.getWorkouts()) {
                for (Activity activity : dailyPlan.getActivities()) {
                    double caloriesSpent = activity.getCaloriesSpent() * this.caloriesFactor;
                    totalCalories += caloriesSpent;
                }
            }
        }
        return totalCalories;
    }

    public int getNumberOfActivitiesDone() {
        long daysPassed = getDaysPassed();
        int totalActivities = 0;
        if (workoutPlan != null) {
            for (DailyPlan dailyPlan : this.workoutPlan.getWorkouts()) {
                totalActivities += dailyPlan.getActivities().size();
            }
        }
        totalActivities *= (int) daysPassed;
        if (activities != null) {
            totalActivities += this.activities.size();
        }
        this.numberOfActivities = totalActivities;
        return totalActivities;
    }

    public double getKmsDone() {
        long daysPassed = getDaysPassed();
        double totalMeters = 0;
        if (workoutPlan != null) {
            for (DailyPlan dailyPlan : this.workoutPlan.getWorkouts()) {
                for (Activity activity : dailyPlan.getActivities()) {
                    if (activity instanceof Distance) {
                        Distance distanceActivity = (Distance) activity;
                        totalMeters += distanceActivity.getDistanceInMeters();
                    } else if (activity instanceof DistanceAndAltimeter) {
                        DistanceAndAltimeter distanceAndAltimeterActivity = (DistanceAndAltimeter) activity;
                        totalMeters += distanceAndAltimeterActivity.getDistanceInMeters();
                    }
                }
            }
        }
        totalMeters *= (int) daysPassed;
        if (activities != null) {
            for (Activity activity : this.activities) {
                if (activity instanceof Distance) {
                    Distance distanceActivity = (Distance) activity;
                    totalMeters += distanceActivity.getDistanceInMeters();
                } else if (activity instanceof DistanceAndAltimeter) {
                    DistanceAndAltimeter distanceAndAltimeterActivity = (DistanceAndAltimeter) activity;
                    totalMeters += distanceAndAltimeterActivity.getDistanceInMeters();
                }
            }
        }
        return totalMeters/1000;
    }

    public double getMetersDone() {
        long daysPassed = getDaysPassed();
        double totalMeters = 0;
        if (workoutPlan != null) {
            for (DailyPlan dailyPlan : this.workoutPlan.getWorkouts()) {
                for (Activity activity : dailyPlan.getActivities()) {
                    if (activity instanceof DistanceAndAltimeter) {
                        DistanceAndAltimeter distanceAndAltimeterActivity = (DistanceAndAltimeter) activity;
                        totalMeters += distanceAndAltimeterActivity.getAltimeterInMeters();
                    }
                }
            }
        }
        totalMeters *= (int) daysPassed;
        if (activities != null) {
            for (Activity activity : this.activities) {
                if (activity instanceof DistanceAndAltimeter) {
                    DistanceAndAltimeter distanceAndAltimeterActivity = (DistanceAndAltimeter) activity;
                    totalMeters += distanceAndAltimeterActivity.getAltimeterInMeters();
                }
            }
        }
        return totalMeters;
    }

    public double getTotalCaloriesSpent() {
        double totalCalories = 0;
        if (workoutPlan != null) {
            for (DailyPlan dailyPlan : this.workoutPlan.getWorkouts()) {
                for (Activity activity : dailyPlan.getActivities()) {
                    totalCalories += activity.getCaloriesSpent() * this.caloriesFactor;
                }
            }
        }
        return totalCalories;
    }

    private long getDaysPassed() {
        Time time = Time.getInstance();
        LocalDate startDate = time.getStartDate();
        LocalDate currentDate = time.getCurrentDate();
        long daysPassed = ChronoUnit.DAYS.between(startDate, currentDate);

        if (workoutPlan != null) {
            LocalDate workoutPlanEndDate = workoutPlan.getEndDate();

            if (!workoutPlanEndDate.isAfter(currentDate)) {
                daysPassed = ChronoUnit.DAYS.between(startDate, workoutPlanEndDate);
            }
        }
        return daysPassed;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getResidence() {
        return this.residence;
    }

    public void setResidence(Address residence) {
        this.residence = residence;
    }

    public int getAvgHeartRate() {
        return this.avgHeartRate;
    }

    public void setAvgHeartRate(int avgHeartRate) {
        this.avgHeartRate = avgHeartRate;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getNumberOfActivities() {
        return numberOfActivities;
    }

    public void setNumberOfActivities(int numberOfActivities) {
        this.numberOfActivities = numberOfActivities;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public  void addActivity(Activity activity){
        this.activities.add(activity);
    }

    public double getCaloriesFactor() {
        return this.caloriesFactor;
    }

    public boolean equals(User user) {
        return this.email.equals(user.getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", residence=" + residence +
                ", avgHeartRate=" + avgHeartRate +
                '}';
    }
}