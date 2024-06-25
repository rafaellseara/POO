package activity_manager.model.activity;

public class DistanceAndAltimeter extends Activity {
    private int distanceInMeters;
    private int altimeterInMeters;
    private double paceInKmH;


    public DistanceAndAltimeter(String name, boolean isHard) {
        super(name, isHard, 3);
        this.distanceInMeters = 0;
        this.altimeterInMeters = 0;
        this.paceInKmH = 0;
    }

    // será usado para os workout plans, copiar a atividade que já existe e depois damos add sets e reps
    public DistanceAndAltimeter(DistanceAndAltimeter other) {
        super(other.getName(), other.isHard(), other.getCalorieFactor());
        this.distanceInMeters = other.distanceInMeters;
        this.altimeterInMeters = other.altimeterInMeters;
        this.paceInKmH = other.paceInKmH;
    }

    @Override
    public double getCaloriesSpent() {
        return ((double) distanceInMeters /1000) *  altimeterInMeters * paceInKmH * getCalorieFactor() * 0.025;
    }

    @Override
    public String getType(){
        return "DistanceAndAltimeter";
    }

    public int getAltimeterInMeters() {
        return altimeterInMeters;
    }

    public void setAltimeterInMeters(int altimeterInMeters) {
        this.altimeterInMeters = altimeterInMeters;
    }

    public int getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(int distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public double getPace() {
        return paceInKmH;
    }

    public void setPace(double pace) {
        this.paceInKmH = pace;
    }


    @Override
    public String toString() {
        return "name: " + getName() +
                ", distance:" + getDistanceInMeters() +
                ", altimeter: " + getAltimeterInMeters() +
                ", pace: " + getPace() +
                ", hard: " + isHard();
    }
}
