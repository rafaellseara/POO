package activity_manager.view;

public class ViewMenu {
    public static int menu () {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\n[1] LOGIN");
        Utils.println("[2] SIGNUP");
        Utils.println("[3] ADMIN");
        Utils.println("[4] Exit");

        return Utils.inputOption(4);
    }
}
