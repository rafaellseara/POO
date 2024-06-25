package activity_manager.model.workout_plan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class WorkoutPlan implements Serializable {
    private final UUID id;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private List<DailyPlan> workouts;

    public WorkoutPlan(String name, List<DailyPlan> workouts , LocalDate endDate) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.workouts = workouts;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<DailyPlan> getWorkouts() {
        return List.copyOf(workouts);
    }

    public void setWorkouts(List<DailyPlan> workouts) {
        this.workouts = workouts;
    }

    public void addDailyPlan(DailyPlan dailyPlan) {
        this.workouts.add(dailyPlan);
    }

    public void removeDailyPlan(DailyPlan dailyPlan) {
        this.workouts.remove(dailyPlan);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WorkoutPlan{id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", startDate=").append(startDate)
                .append(", endDate=").append(endDate)
                .append(", workouts=[\n");

        for (DailyPlan dailyPlan : workouts) {
            stringBuilder.append(dailyPlan).append("\n");
        }

        stringBuilder.append("]}");

        return stringBuilder.toString();
    }
}
