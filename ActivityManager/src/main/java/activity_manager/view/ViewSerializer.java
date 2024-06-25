package activity_manager.view;

public class ViewSerializer {

    public static String save() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\nSave Program");

        Utils.println("\nSaving in this program works by saving three files (Users, Activities and Time) in a folder with a name of your choice (The folder needs to exist).");

        return Utils.input("\nIndicate the name of the folder you want to save this program state: ");
    }

    public static void saved() {
        Utils.println("\nProgram saved successfully!");
        Utils.input("Press enter to go back");
    }

    public static String load() {
        Utils.clearScreen();
        Utils.printHeader();

        Utils.println("\nLoad Program");

        Utils.println("\nLoading in this program works by loading three files (Users, Activities and Time) from a folder with a name of your choice.");

        return Utils.input("\nIndicate the name of the folder with the program state you want to load: ");
    }

    public static void loaded() {
        Utils.println("\nProgram loaded successfully!");
        Utils.input("Press enter to go back");
    }
}