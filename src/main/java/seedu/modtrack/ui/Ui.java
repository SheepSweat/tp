package seedu.modtrack.ui;

import java.util.ArrayList;

import seedu.modtrack.model.Mod;

public class Ui {

    public void showOpeningText() {
        String openingText = " Hello! I'm [ModTrack]\n"
                + " What can I do for you?\n"
                + " Ready to go Graduate? Nah wgt ord loh :D"
                + " Type a module code and I will record it for you!";
        System.out.println(openingText);
    }

    public void showClosingText() {
        String closingText = " Bye. Hope to see you again soon!";
        System.out.println(closingText);
    }

    public void showEmptyDescriptionError(String command) {
        System.out.println(" " + command + " Number cannot be empty, ensure a Module code is written after '" + command
                + "'. Thankssssss!");
    }

    public void showInvalidCommandError() {
        System.out.println(
                "Invalid command. Please input the instruction again begining with 'bye'.");
    }

    public void showInvalidNumberError() {
        System.out.println("Module does not exist. Choose another number.");
    }

    public void showMarkedCourse(String taskInfo, String modularCredits) {
        System.out.println("Module marked as completed:");
        System.out.println("Name: " + taskInfo);
        System.out.println("Modular Credits Earned :" + modularCredits);
    }

    public void showUnmarkedCourse(String taskInfo) {
        System.out.println("Module marked as incomplete:");
        System.out.println("Name: " + taskInfo);
    }

    public void showList(ArrayList<Mod> taskList, ArrayList<Mod> fullModuleList) {
        System.out.println("===== Module Tracker =====");
        System.out.println("Completed Modules:");
        // for completed modules
        for (Mod task : taskList) {
            if (task.getIsComplete()) {
                System.out.println("✔ " + task.getModName() + "(" + task.getSemester() + ")"
                        + " - " + task.getModCredits() + "MCs");
            }
        }
        System.out.println("\nRemaining Reference Modules:");
        for (Mod module : fullModuleList) {
            boolean alreadyAdded = false;

            // Check if this reference module is already in the user's taskList
            for (Mod task : taskList) {
                if (module.getModName().equals(task.getModName())) {
                    alreadyAdded = true;
                    break;
                }
            }

            // Only print "✘" if it was NOT found in the taskList
            if (!alreadyAdded) {
                System.out.println("✘ " + module.getModName()
                        + " (" + module.getSemester() + ") - " + module.getModCredits() + "MCs");
            }
        }
    }

    public void showGradReq() {
        System.out.println("===== Computer Engineering Graduation Requirements =====");
        System.out.println("Total MCs Required: 160");
        System.out.println("Core Modules:");
        System.out.println("- CS1010");
        System.out.println("- CG1111A");
        System.out.println("- MA1511");
        System.out.println("- MA1512");
        System.out.println("- CG2111A");
        System.out.println("- MA1508E");
        System.out.println("- EE2026");
        System.out.println("- CS2040C");
        System.out.println("- CS2107");
        System.out.println("- CG2023");
        System.out.println("- CS2113");
        System.out.println("- CS1231");
        System.out.println("- CG3201");
        System.out.println("- CG2271");
        System.out.println("- CG2027");
        System.out.println("- CG2028");
        System.out.println("- EE4204");
        System.out.println("- CG3207");
        System.out.println();
        System.out.println("Project/Internship:");
        System.out.println("- EG3611A/CP3880");
        System.out.println("- CG4002/CG4001");
        System.out.println();
        System.out.println("Engineering Common Curriculum:");
        System.out.println("- EG1311");
        System.out.println("- PF1101");
        System.out.println("- DTK1234");
        System.out.println("- GEA1000");
        System.out.println("- ES2631");
        System.out.println("- CDE2501");
        System.out.println("- EE2211");
        System.out.println("- EG2401A");
        System.out.println();
        System.out.println("General Education Curriculum:");
        System.out.println("- GENXXXX");
        System.out.println("- GECXXXX");

    }
}
