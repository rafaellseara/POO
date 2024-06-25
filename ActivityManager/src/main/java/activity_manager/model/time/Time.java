package activity_manager.model.time;

import activity_manager.model.user.UserListings;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Time implements Serializable {
    private static Time instance;
    private final LocalDate startDate;
    private LocalDate currentDate;

    private Time() {
        this.startDate = LocalDate.now();
        this.currentDate = LocalDate.now();
    }

    public static Time getInstance() {
        if (instance == null) {
            instance = new Time();
        }
        return instance;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void jumpDays(int days) {
        this.currentDate = this.currentDate.plusDays(days);
    }

    public void backDays(int days) {
        LocalDate newDate = this.currentDate.minusDays(days);
        if (newDate.isBefore(startDate)) {
            this.currentDate = startDate;
        } else {
            this.currentDate = newDate;
        }
    }

    public void jumpMonths(int months) {
        this.currentDate = this.currentDate.plusMonths(months);
    }

    public void backMonths(int months) {
        LocalDate newDate = this.currentDate.minusMonths(months);
        if (newDate.isBefore(startDate)) {
            this.currentDate = startDate;
        } else {
            this.currentDate = newDate;
        }
    }

    public void jumpYears(int years) {
        this.currentDate = this.currentDate.plusYears(years);
    }

    public void backYears(int years) {
        LocalDate newDate = this.currentDate.minusYears(years);
        if (newDate.isBefore(startDate)) {
            this.currentDate = startDate;
        } else {
            this.currentDate = newDate;
        }
    }

    public String toString() {
        return this.currentDate.toString();
    }

    public void saveTime(String folderName) {
        try {
            File file = new File("saves/" + folderName + "/time.ser");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fileOut = new FileOutputStream("saves/" + folderName + "/time.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTime(String folderName) {
        try (FileInputStream fileIn = new FileInputStream("saves/" + folderName + "/time.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Time time = (Time) in.readObject();
            instance = time;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}