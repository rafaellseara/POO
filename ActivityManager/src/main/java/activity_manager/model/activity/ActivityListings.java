package activity_manager.model.activity;

import activity_manager.model.user.UserListings;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class ActivityListings implements Serializable{

    private static ActivityListings instance = null;

    private Map<String, Activity> activities;

    public static ActivityListings getInstance() {
        if (instance == null){
            instance = new ActivityListings();
        }
        return instance;
    }

    private ActivityListings() {
        activities = new HashMap<String, Activity>();
    }

    public Map<String, Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity){
        activities.put(activity.getName(), activity);
    }

    public boolean deleteActivity(String name){
        if (checkActivity(name)){
            activities.remove(name);
            return true;
        }
        else return false;
    }

    public boolean checkActivity(String name){
        return activities.containsKey(name);
    }

    public Activity getActivity(String name){
        return activities.get(name);
    }

    public void saveActivities(String folderName) {
        try {
            File file = new File("saves/" + folderName + "/activities.ser");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fileOut = new FileOutputStream("saves/" + folderName + "/activities.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadActivities(String folderName) {
        try (FileInputStream fileIn = new FileInputStream("saves/" + folderName + "/activities.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            ActivityListings activityListings = (ActivityListings) in.readObject();
            instance = activityListings;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkActivities() {
        return !activities.isEmpty();
    }
}
