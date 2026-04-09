package seedu.modtrack.ui;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;

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

    public void showDivider() {
        System.out.println("----------------------------------------------------");
    }

    public void showNoModulesFound() {
        System.out.println("No modules found in the list.");
    }

    public void showExistingIncompleteModuleError(Mod existingMod) {
        System.out.println("This module already exists in the list, but is currently incomplete.");
        System.out.println("To mark this module as complete, use: mark n/" + existingMod.getModName());
    }

    public void showDuplicateModuleError() {
        System.out.println("This module is already in the list!");
    }

    public void showAddModule(ArrayList<Mod> list, Mod mod) {
        this.showDivider();
        System.out.println("Module added:\n");
        System.out.println(mod.toString());
        System.out.println("Total modules tracked: " + list.size());
        this.showDivider();
    }

    public void showUpdatedPrerequisites(Mod mod) {
        System.out.println("Prerequisites updated for " + mod.getModName() + ":");
    }

    public void listPrerequisite(Mod mod) {
        System.out.println(String.join(", ", mod.getPrerequisites()));
    }

    public void showPrerequisites(Mod mod) {
        System.out.println("Prerequisites for " + mod.getModName() + ":");
        if (mod.getPrerequisites().isEmpty()) {
            System.out.println("None");
        } else {
            this.listPrerequisite(mod);
        }
    }

    public void showClearConfirmation() {
        System.out.println("Noted. All modules have been cleared. Now you have an empty list.");
    }

    public void showDeletedModule(Mod mod, int remainingModules) {
        this.showDivider();
        System.out.println("Noted. I've removed this mod:\n");
        System.out.println(mod.toString());
        System.out.println("Now you have " + remainingModules + " mods in the list.");
        this.showDivider();
    }

    public void showExemptedModule(Mod mod) {
        System.out.println("Module marked as exempted:");
        System.out.println(mod.getModName());
    }

    public void showMatchingModule(Mod mod) {
        System.out.println("--------------------");
        System.out.println(mod);
    }

    public void showTransferredModule(Mod mod) {
        System.out.println("Module marked as transferred:");
        System.out.println(mod.getModName());
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

    public void showSaveError() {
        System.out.println("Error saving data. Please try again.");
    }

    public void showMarkedCourse(Mod mod) {
        System.out.println("Module marked as completed:");
        System.out.println(mod.getModName());
    }

    public void showUnmarkedCourse(Mod mod) {
        System.out.println("Module marked as incomplete:");
        System.out.println(mod.getModName());
    }

    public void showList(ArrayList<Mod> list) {
        System.out.println("===== Your Tracked Modules =====");
        if (list.isEmpty()) {
            System.out.println("No modules tracked yet.");
        } else {
            for (Mod mod : list) {
                System.out.println(mod);
            }
        }
    }

    public void showComparedList(ArrayList<Mod> completedModules, ArrayList<Mod> missingModules) {
        System.out.println("____________________________________________________________");
        System.out.println("Comparison with Graduation Requirements (CE):");

        System.out.println("\n✔ COMPLETED MODULES:");
        if (completedModules.isEmpty()) {
            System.out.println("  (None yet)");
        } else {
            for (Mod mod : completedModules) {
                System.out.println(mod);
            }
        }

        System.out.println("\n✘ MISSING/UNCOMPLETED MODULES:");
        if (missingModules.isEmpty()) {
            System.out.println("  Congratulations! All requirements met.");
        } else {
            for (Mod mod : missingModules) {
                System.out.println(mod);
            }
        }
        System.out.println("____________________________________________________________");
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
