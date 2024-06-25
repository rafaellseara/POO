package activity_manager.controller;


import activity_manager.model.time.Time;
import activity_manager.view.ViewMenu;
import activity_manager.view.ViewTime;

import java.util.Map;

public class ControllerTime {

    public static void menuTime() {
        int option = ViewTime.menuTime();

        switch (option) {
            case 1:
                ControllerTime.showTime();
                break;
            case 2:
                ControllerTime.jumpTime();
                break;
            case 3:
                ControllerTime.backTime();
                break;
            case 4:
                ControllerAdmin.menu();
                break;
            case 5:
                System.exit(0);
        }
    }

    public static void jumpTime () {
        Map<String, Integer> times = ViewTime.jumpTime();

        Time time = Time.getInstance();
        time.jumpYears(times.get("years"));
        time.jumpMonths(times.get("months"));
        time.jumpDays(times.get("days"));

        ViewTime.timeJumped();
        ControllerAdmin.menu();
    }

    public static void backTime () {
        Map<String, Integer> times = ViewTime.backTime();

        Time time = Time.getInstance();
        time.backYears(times.get("years"));
        time.backMonths(times.get("months"));
        time.backDays(times.get("days"));

        ViewTime.timeBacked();
        ControllerAdmin.menu();
    }

    public static void showTime(){
        Time time = Time.getInstance();

        ViewTime.showTime(time);
        ControllerAdmin.menu();
    }
}