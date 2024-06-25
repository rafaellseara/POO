package activity_manager.model.user;

import activity_manager.model.activity.Activity;
import activity_manager.model.others.Address;
import activity_manager.model.workout_plan.DailyPlan;

public class UserOcasional extends User{
    public UserOcasional(String name, String email, Address residence, int avgHeartRate) {
        super(name, email, residence, avgHeartRate, 0.5);
    }

    public String getUserType(){
        return "Ocasional";
    }

    @Override
    public String toString() {
        return "UserOcasional{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", residence=" + getResidence() +
                ", avgHeartRate=" + getAvgHeartRate() +
                ", caloriesFactor=" + getCaloriesFactor() +
                '}';
    }
}
