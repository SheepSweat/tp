package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;

public class AddPrereqCommand extends Command {
    private final String modName;
    private final ArrayList<String> prerequisites;

    public AddPrereqCommand(String modName, ArrayList<String> prerequisites) {
        this.modName = modName;
        this.prerequisites = prerequisites;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                for (String prereq : this.prerequisites) {
                    mod.addPrerequisite(prereq);
                }
                System.out.println("Prerequisites updated for " + mod.getModName() + ":");
                if (mod.getPrerequisites().isEmpty()) {
                    System.out.println("None");
                } else {
                    System.out.println(String.join(", ", mod.getPrerequisites()));
                }
                return;
            }
        }
        System.out.println("Module not found.");
    }
}
