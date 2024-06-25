package activity_manager.model.activity;

import java.io.Serializable;

public class Chest extends RepetitionsWithWeights implements WeightedActivity {
    private int sets;
    private int repetitions;
    private int weight;

    public Chest(String name, boolean isHard) {
        super(name, isHard);
        this.sets = 0;
        this.repetitions = 0;
        this.weight = 0;
    }

    // será usado para os workout plans, copiar a atividade que já existe e depois damos add sets e reps
    public Chest(Chest other) {
        super(other.getName(), other.isHard());
        this.sets = other.sets;
        this.repetitions = other.repetitions;
        this.weight = other.weight;
    }

    @Override
    public String getType() {
        return "Chest";
    }

    @Override
    public double getCaloriesSpent() {
        return sets * repetitions * weight * 0.05;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getSets() {
        return sets;
    }

    @Override
    public void setSets(int sets) {
        this.sets = sets;
    }

    @Override
    public int getRepetitions() {
        return repetitions;
    }

    @Override
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return "name: " + getName() +
                ", sets: " + getSets() +
                ", repetitions: " + getRepetitions() +
                ", weight: " + getWeight() +
                ", hard: " + isHard();
    }
}
