package activity_manager.controller;

import activity_manager.model.activity.ActivityListings;
import activity_manager.model.time.Time;
import activity_manager.model.user.UserListings;
import activity_manager.model.workout_plan.WorkoutPlanListings;
import activity_manager.view.ViewSerializer;

public class ControllerSerializer {

    public static void save() {
        String folderName = ViewSerializer.save();

        UserListings.getInstance().saveUsers(folderName);
        ActivityListings.getInstance().saveActivities(folderName);
        Time.getInstance().saveTime(folderName);
        //WorkoutPlanListings.getInstance().saveWorkoutPlans(folderName);

        ViewSerializer.saved();

        ControllerAdmin.menu();
    }

    public static void load() {
        String folderName = ViewSerializer.load();

        UserListings.getInstance().loadUsers(folderName);
        ActivityListings.getInstance().loadActivities(folderName);
        Time.getInstance().loadTime(folderName);
        //WorkoutPlanListings.getInstance().loadWorkoutPlans(folderName);

        ViewSerializer.loaded();

        ControllerAdmin.menu();
    }
}