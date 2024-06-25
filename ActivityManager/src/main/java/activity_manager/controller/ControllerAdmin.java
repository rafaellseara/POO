package activity_manager.controller;

import activity_manager.view.ViewAdmin;

public class ControllerAdmin {

    public static void menu() {
        int option = ViewAdmin.menu();

        switch (option) {
            case 1:
                ControllerTime.menuTime();
                break;
            case 2:
                ControllerUser.listUsers();
            case 3:
                ControllerActivity.listActivities();
                break;
            case 4:
                ControllerActivity.addActivity();
                break;
            case 5:
                ControllerSerializer.save();
                break;
            case 6:
                ControllerSerializer.load();
                break;
            case 7:
                ControllerStats.menu();
                break;
            case 8:
                ControllerMenu.menu();
                break;
        }
    }
}