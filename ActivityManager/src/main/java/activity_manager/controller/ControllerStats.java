package activity_manager.controller;

import activity_manager.view.ViewStats;

public class ControllerStats {
    static void menu() {
        int option = ViewStats.menu();
        ViewStats.printStatistics(option);
    }
}
