package activity_manager.view;

import java.util.List;

public class ViewUser {

    public static Integer menu() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\nMENU\n");
        Utils.println  ("[1] View my Plan");
        Utils.println  ("[2] View my Activities");
        Utils.println  ("[3] Create a new plan");
        Utils.println  ("[4] Do an Activity");
        Utils.println  ("[5] Create a Plan for me");
        Utils.println  ("[6] Logout");
        Utils.println  ("[7] Exit\n");

        Integer option = Utils.inputOption(7);

        return option;
    }

    public static void listUsers(List<String> userStrings) {
        int pageNumber = 1, option = 1;
        while (option > 0) {
            Utils.clearScreen();
            Utils.printHeader();
            Utils.println("\nList of Users:\n");

            List<String> currentUsers = Utils.getPage(userStrings, pageNumber);

            Utils.println("====================================");
            for(String user : currentUsers) {
                Utils.println(user);
                Utils.println("====================================");
            }

            Utils.println("\n[1] <-");
            Utils.println  ("[2] ->");
            Utils.println  ("[3] Back");

            option = Utils.inputOption(3);

            if (option == 1 && pageNumber > 1) {
                pageNumber--;
            } else if (option == 2 && userStrings.size() > pageNumber * 5) {
                pageNumber++;
            }
            else if (option == 3){
                break;
            }
        }
    }
}