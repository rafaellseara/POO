package activity_manager.model.user;

import activity_manager.model.activity.Activity;
import activity_manager.model.others.Address;
import activity_manager.model.workout_plan.DailyPlan;

public class UserPro extends User{
    public UserPro(String name, String email, Address residence, int avgHeartRate) {
        super(name, email, residence, avgHeartRate, 1);
    }

    public String getUserType(){
        return "Pro";
    }

    @Override
    public String toString() {
        return "UserPro{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", residence=" + getResidence() +
                ", avgHeartRate=" + getAvgHeartRate() +
                ", caloriesFactor=" + getCaloriesFactor() +
                '}';
    }
}
