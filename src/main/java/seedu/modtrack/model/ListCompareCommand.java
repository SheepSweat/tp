package seedu.modtrack.model;

import java.util.ArrayList;

public class ListCompareCommand extends Command {

    @Override
    public void execute(ArrayList<Mod> taskList) {
        assert taskList != null : "taskList passed to ListCompareCommand should not be null";
        ArrayList<Mod> requiredModules = ReferenceList.getReferenceList();

        ArrayList<Mod> completed = new ArrayList<>();
        ArrayList<Mod> missing = new ArrayList<>();

        for (Mod reqMod : requiredModules) {
            boolean isFound = false;

            for (Mod task : taskList) {
                if (task.getModName().equalsIgnoreCase(reqMod.getModName())) {
                    isFound = true;
                    break; // Stop looking once we find it
                }
            }

            if (isFound) {
                completed.add(reqMod);
            } else {
                missing.add(reqMod);
            }
        }

        assert (completed.size() + missing.size()) == requiredModules.size()
                : "Total categorized modules must equal total required modules";

        printComparison(completed, missing);
    }

    private void printComparison(ArrayList<Mod> completed, ArrayList<Mod> missing) {
        System.out.println("____________________________________________________________");
        System.out.println("Comparison with Graduation Requirements (CE):");

        System.out.println("\n✔ COMPLETED MODULES:");
        if (completed.isEmpty()) {
            System.out.println("  (None yet)");
        } else {
            for (Mod mod : completed) {
                System.out.println(mod);
            }
        }

        System.out.println("\n✘ MISSING/UNCOMPLETED MODULES:");
        if (missing.isEmpty()) {
            System.out.println("  Congratulations! All requirements met.");
        } else {
            for (Mod mod : missing) {
                System.out.println(mod);
            }
        }
        System.out.println("____________________________________________________________");
    }
}
