package seedu.modtrack.model;

import java.util.ArrayList;

public class ShowPrereqCommand extends Command {
    private final String modName;

    public ShowPrereqCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                System.out.println("Prerequisites for " + mod.getModName() + ":");
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
