package activity_manager.model.activity;

import java.io.Serializable;
import java.util.UUID;

public abstract class Activity implements Serializable {
    private final UUID id;
    private String name;
    private boolean isHard;
    protected double calorieFactor;

    public Activity(String name, boolean isHard, double calorieFactor) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.isHard = isHard;
        this.calorieFactor = calorieFactor;
    }

    public abstract String getType();

    public abstract double getCaloriesSpent();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHard() {
        return isHard;
    }

    public void setHard(boolean hard) {
        isHard = hard;
    }

    public double getCalorieFactor() {
        return calorieFactor;
    }

    public void setCalorieFactor(double calorieFactor) {
        this.calorieFactor = calorieFactor;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isHard=" + isHard +
                ", calorieFactor=" + calorieFactor +
                '}';
    }
}
