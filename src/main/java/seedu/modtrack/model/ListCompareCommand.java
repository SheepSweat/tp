package seedu.modtrack.model;

import java.util.ArrayList;

public class ListCompareCommand extends Command {

    @Override
    public void execute(ArrayList<Mod> taskList) {
        ArrayList<Mod> requiredModules = ReferenceList.getReferenceList();

        ArrayList<Mod> completed = new ArrayList<>();
        ArrayList<Mod> missing = new ArrayList<>();

        for (Mod reqMod : requiredModules) {
            for (Mod task : taskList) {
                if (task.getModName().equals(reqMod.getModName())) {
                    completed.add(reqMod);
                } else {
                    missing.add(reqMod);
                }
            }
        }

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
