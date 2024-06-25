package activity_manager.model.workout_plan;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WorkoutPlanListings implements Serializable {
    private static WorkoutPlanListings instance = null;
    private Map<String, WorkoutPlan> workoutPlansMap;

    public static WorkoutPlanListings getInstance() {
        if (instance == null){
            instance = new WorkoutPlanListings();
        }
        return instance;
    }

    private WorkoutPlanListings() {
        workoutPlansMap = new HashMap<String, WorkoutPlan>();
    }

    public Map<String, WorkoutPlan> getWorkoutPlansMap() {
        return workoutPlansMap;
    }

    public void addWorkoutPlan(WorkoutPlan workoutPlan){
        workoutPlansMap.put(workoutPlan.getName(), workoutPlan);
    }

    public boolean deleteWorkoutPlan(String name){
        if (checkWorkoutPlan(name)){
            workoutPlansMap.remove(name);
            return true;
        }
        else return false;
    }

    public boolean checkWorkoutPlan(String name){
        return workoutPlansMap.containsKey(name);
    }

    public WorkoutPlan getWorkoutPlan(String name){
        return workoutPlansMap.get(name);
    }

    public void saveWorkoutPlans(String folderName) {
        try {
            File file = new File("saves/" + folderName + "/workout_plans.ser");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fileOut = new FileOutputStream("saves/" + folderName + "/workout_plans.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWorkoutPlans(String folderName) {
        try (FileInputStream fileIn = new FileInputStream("saves/" + folderName + "/workout_plans.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            WorkoutPlanListings workoutPlanListings = (WorkoutPlanListings) in.readObject();
            instance = workoutPlanListings;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
