package activity_manager.model.user;

import activity_manager.model.activity.Activity;
import activity_manager.model.others.Address;
import activity_manager.model.workout_plan.DailyPlan;

public class UserAmateur extends User{
    public UserAmateur(String name, String email, Address residence, int avgHeartRate) {
        super(name, email, residence, avgHeartRate, 0.75);
    }

    public String getUserType(){
        return "Amateur";
    }

    public String toString() {
        return "UserAmateur{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", residence=" + getResidence() +
                ", avgHeartRate=" + getAvgHeartRate() +
                ", caloriesFactor=" + getCaloriesFactor() +
                '}';
    }
}
