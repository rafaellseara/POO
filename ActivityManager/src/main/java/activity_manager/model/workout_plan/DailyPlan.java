package activity_manager.model.workout_plan;

import activity_manager.model.activity.Activity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class DailyPlan implements Serializable {
    public enum DayOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }
    private final UUID id;
    private DayOfWeek dayOfWeek;
    private List<Activity> activities;

    public DailyPlan(DayOfWeek dayOfWeek, List<Activity> activities) {
        this.id = UUID.randomUUID();
        this.dayOfWeek = dayOfWeek;
        this.activities = activities;
    }

    public UUID getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<Activity> getActivities() {
        return List.copyOf(activities);
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        this.activities.remove(activity);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Day of Week: ").append(dayOfWeek).append("\n");
        stringBuilder.append("Activities:\n");

        if (activities.isEmpty()) {
            stringBuilder.append("none\n");
        } else {
            for (Activity activity : activities) {
                stringBuilder.append(activity).append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
