package activity_manager.view;

public class ViewAdmin {

    public static Integer menu() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\nADMIN MENU\n");
        Utils.println  ("[1] Time Menu");
        Utils.println  ("[2] List of Users");
        Utils.println  ("[3] List of Activities");
        Utils.println  ("[4] Add Activity");
        Utils.println  ("[5] Save Program");
        Utils.println  ("[6] Load Program");
        Utils.println  ("[7] Statistics");
        Utils.println  ("[8] Back\n");

        Integer option = Utils.inputOption(8);

        return option;
    }
}