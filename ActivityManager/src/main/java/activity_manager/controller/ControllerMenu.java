package activity_manager.controller;

import activity_manager.view.ViewMenu;

public class ControllerMenu {
    public static void menu() {
        int option = ViewMenu.menu();

        switch (option) {
            case 1:
                ControllerAuthentication.login();
                break;
            case 2:
                ControllerAuthentication.signUp();
                break;
            case 3:
                ControllerAdmin.menu();
                break;
            case 4:
                System.exit(0);
        }
    }
}
