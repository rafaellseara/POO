package activity_manager.model.activity;

public interface WeightedActivity {
    int getWeight();
    void setWeight(int weight);

    int getSets();
    void setSets(int sets);

    int getRepetitions();
    void setRepetitions(int repetitions);
}
