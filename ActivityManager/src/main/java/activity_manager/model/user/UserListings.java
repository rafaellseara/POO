package activity_manager.model.user;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class UserListings implements Serializable {
    private static UserListings instance = null;
    private Map<String, User> users; /* ! Map of Users by Email*/

    public static UserListings getInstance() {
        if (instance == null) {
            instance = new UserListings();
        }
        return instance;
    }

    private UserListings() {
        this.users = new HashMap<String, User>();
    }

    public Map<String, User> getUsers() {
        return Map.copyOf(this.users);
    }

    public void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    public boolean deleteUser(String email) {
        if (checkUser(email)) {
            users.remove(email);
            return true;
        }
        else return false;
    }

    public boolean checkUser(String email){
        return users.containsKey(email);
    }

    public User getUser(String email) {
        return users.get(email);
    }

    public User getUserByName(String name) {
        for (User user : users.values()) {
            if (user.getName().equals(name)) return user;
        }
        return null;
    }

    public boolean isEmailAvailable(String email) {
        return users.containsKey(email);
    }


    public void saveUsers(String folderName) {
        try {
            File file = new File("saves/" + folderName + "/users.ser");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fileOut = new FileOutputStream("saves/" + folderName + "/users.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUsers(String folderName) {
        try (FileInputStream fileIn = new FileInputStream("saves/" + folderName + "/users.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            UserListings userListings = (UserListings) in.readObject();
            instance = userListings;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}