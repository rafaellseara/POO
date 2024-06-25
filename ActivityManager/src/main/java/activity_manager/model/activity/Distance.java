package activity_manager.model.activity;

public class Distance extends Activity{
    private int distanceInMeters;
    private double paceInKmH;

    public Distance(String name, boolean isHard) {
        super(name, isHard, 2);
        this.distanceInMeters = 0;
        this.paceInKmH = 0;
    }

    // será usado para os workout plans, copiar a atividade que já existe e depois damos add sets e reps
    public Distance(Distance other) {
        super(other.getName(), other.isHard(), other.getCalorieFactor());
        this.distanceInMeters = other.distanceInMeters;
        this.paceInKmH = other.paceInKmH;
    }

    @Override
    public double getCaloriesSpent() {
        return ((double) distanceInMeters /1000) * paceInKmH * getCalorieFactor();
    }

    @Override
    public String getType(){
        return "Distance";
    }

    public int getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(int distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public double getPaceInKmH() {
        return paceInKmH;
    }

    public void setPaceInKmH(double paceInKmH) {
        this.paceInKmH = paceInKmH;
    }

    @Override
    public String toString() {
        return "name: " + getName() +
                ", distance:" + getDistanceInMeters() +
                ", pace: " + getPaceInKmH() +
                ", hard: " + isHard();
    }
}
