package activity_manager.view;

import activity_manager.model.time.Time;

import java.util.HashMap;
import java.util.Map;

public class ViewTime {

    public static int menuTime () {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\n[1] Show Time");
        Utils.println("[2] Jump Time");
        Utils.println("[3] Back Time");
        Utils.println("[4] Go Back");
        Utils.println("[5] Exit");

        return Utils.inputOption(5);
    }

    public static Map<String, Integer> jumpTime() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\nJump in Time");

        Map<String, Integer> times = new HashMap<String, Integer>();
        times.put("years", Utils.inputInteger("How many years do you want to jump? "));
        times.put("months", Utils.inputInteger("How many months do you want to jump? "));
        times.put("days", Utils.inputInteger("How many days do you want to jump? "));

        return times;
    }

    public static Map<String, Integer> backTime() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\nBack in Time");
        Utils.println("The minimum it goes is start Date whatever the input may be");

        Map<String, Integer> times = new HashMap<String, Integer>();
        times.put("years", Utils.inputInteger("How many years do you want to back? "));
        times.put("months", Utils.inputInteger("How many months do you want to back? "));
        times.put("days", Utils.inputInteger("How many days do you want to back? "));

        return times;
    }

    public static void timeJumped() {
        Utils.println("\nTime jumped successfully!");
        Utils.input("Press enter to go back");
    }

    public static void timeBacked() {
        Utils.println("\nTime backed successfully!");
        Utils.input("Press enter to go back");
    }

    public static void showTime(Time time){
        Utils.println("\nApp Start Time: " + time.getStartDate());
        Utils.println("\nApp Current Time: " + time.getCurrentDate());
        Utils.input("Press enter to go back");
    }
}