package activity_manager.model.activity;

public class Repetitions extends Activity {
    private int sets;
    private int repetitions;

    public Repetitions(String name, boolean isHard) {
        super(name, isHard, 1);
        this.sets = 0;
        this.repetitions = 0;
    }

    // será usado para os workout plans, copiar a atividade que já existe e depois damos add sets e reps
    public Repetitions(Repetitions other) {
        super(other.getName(), other.isHard(), other.getSets());
        this.sets = other.sets;
        this.repetitions = other.repetitions;
    }

    @Override
    public double getCaloriesSpent() {
        return sets * repetitions * 0.05;
    }

    public String getType(){
        return "Repetitions";
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return "name: " + getName() +
                ", sets: " + getSets() +
                ", repetitions: " + getRepetitions() +
                ", hard: " + isHard();
    }
}
