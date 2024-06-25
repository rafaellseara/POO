package activity_manager.model.activity;

public abstract class RepetitionsWithWeights extends Activity {
    public RepetitionsWithWeights(String name, boolean isHard) {
        super(name, isHard, 3);
    }

    public String getType(){
        return "RepetitionsWithWeights";
    }

    @Override
    public String toString() {
        return "RepetitionsWithWeights{" +
                "name=" + getName() +
                ", isHard=" + isHard() +
                '}';
    }
}
