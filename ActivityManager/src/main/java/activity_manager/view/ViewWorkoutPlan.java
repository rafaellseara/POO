package activity_manager.view;

import activity_manager.model.workout_plan.DailyPlan;

import java.time.LocalDate;
import java.util.List;

public class ViewWorkoutPlan {
    public static Integer menu() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\nMENU\n");
        Utils.println  ("[1] Create new workout plan");
        Utils.println  ("[2] See workout plans");
        Utils.println  ("[3] Edit workout plans--Not Working");
        Utils.println  ("[4] Back");

        Integer option = Utils.inputOption(4);

        return option;
    }

    public static void listWorkoutPlans(List<String> workoutPlanStrings) {
        int pageNumber = 1, option = 1;
        while (option > 0) {
            Utils.clearScreen();
            Utils.printHeader();
            Utils.println("\nList of Workout Plan's:\n");

            List<String> currentWorkoutPlans = Utils.getPage(workoutPlanStrings, pageNumber);

            Utils.println("====================================");
            for(String activity : currentWorkoutPlans) {
                Utils.println(activity);
                Utils.println("====================================");
            }

            Utils.println("\n[1] <-");
            Utils.println  ("[2] ->");
            Utils.println  ("[3] Back");

            option = Utils.inputOption(3);

            if (option == 1 && pageNumber > 1) {
                pageNumber--;
            } else if (option == 1 && workoutPlanStrings.size() > pageNumber * 5) {
                pageNumber++;
            }
            else if (option == 3){
                break;
            }
        }
    }

    public static void userWorkoutPlan(String workoutPlan) {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("Here is your plan, have a good workout :)\n\n");

        Utils.println(workoutPlan);

        Utils.input("\n\nPress enter to go back to the User menu");
    }

    public static void userWorkoutPlanV2(String workoutPlan) {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("This is your generated workout plan\n\n");

        Utils.println(workoutPlan);
    }

    public static String addWorkoutPlan() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("Add a new Workout Plan");

        return Utils.inputName("Name: ");
    }

    public static void workoutPlanAlreadyExists() {
        Utils.println("\nWorkout Plan already exists, try a different name.");
        Utils.input("Press enter to go back to the Admin menu");
    }

    public static void workoutPlanAddSuccessfully() {
        Utils.println("\nWorkout Plan added successfully.");
        Utils.input("Press enter to go back to the Admin menu");
    }

    public static void noActivitiesFound() {
        Utils.println("\nNo Activities were found for a new Workout Plan.");
        Utils.input("Press enter to go back to the Admin menu");
    }

    public static void noWorkoutPlanFound() {
        Utils.clearScreen();
        Utils.printHeader();
        Utils.println("\nNo Workout plan was found.");
        Utils.input("Press enter to go back to the User menu");
    }

    public static String numOfActivities() {
        return Utils.inputInt("How many activities would you like to add? ");
    }

    public static int numOfDays() {
        Utils.println("How many days would you like to train? ");
        return Utils.inputOptionV2(7);
    }

    public static String typeOfActivities() {
        return Utils.inputActivityType();
    }

    public  static void addActivitiesToWorkoutPlan(DailyPlan.DayOfWeek dayOfWeek, List<String> activityStrings){
        Utils.clearScreen();
        Utils.println("\n" + dayOfWeek + ":\n");
        Utils.println("Choose one of the option (0 for none):\n");

        for (int i = 0; i < activityStrings.size(); i++) {
            Utils.println((i + 1) + ". " + activityStrings.get(i));
        }
    }

    public static boolean workoutPlanConfirmation(){
        return "y".equals(Utils.inputYesOrNo("\nWould you like to proceed?(y/n):"));
    }

    public static String addMoreActivities() {
        return Utils.inputYesOrNo("Do you want to add any more activities to this day?(y/n): ");
    }

    public  static void chooseNewPlan() {
        Utils.clearScreen();
        String input = Utils.input("Please create a new plan (click Enter to continue)");
        Utils.clearScreen();
        Utils.printHeader();
    }

    public static LocalDate addEndDate() {
        while (true) {
            String input = Utils.input("Enter the end date (yyyy-MM-dd): ");

            try {
                LocalDate endDate = LocalDate.parse(input);
                if (endDate.isAfter(LocalDate.now())) {
                    return endDate;
                } else {
                    Utils.println("End date must be after the current date.");
                }
            } catch (Exception e) {
                Utils.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
    }

    public static int maxNumAct() {
        Utils.println("What is you're maximum activities per day? (No more than 3): ");
        return Utils.inputOptionV2(3);
    }

    public static int objective() {
        Utils.println("\nWhats you're objective?\n");

        Utils.println("====================================");
        Utils.println("0 -> Gain Muscle");
        Utils.println("1 -> Lose Weight");
        Utils.println("2 -> Gain Resistance");
        Utils.println("====================================\n");

        return  Utils.inputOption(2);

    }
}
