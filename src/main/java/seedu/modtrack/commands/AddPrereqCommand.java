package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

public class AddPrereqCommand extends Command {
    private final String modName;
    private final ArrayList<String> prerequisites;

    public AddPrereqCommand(String modName, ArrayList<String> prerequisites) {
        this.modName = modName;
        this.prerequisites = prerequisites;
    }

    @Override
    public void execute(ArrayList<Mod> list, Ui ui) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                for (String prereq : this.prerequisites) {
                    mod.addPrerequisite(prereq);
                }
                ui.showUpdatedPrerequisites(mod);
                if (mod.getPrerequisites().isEmpty()) {
                    ui.showNoModulesFound();
                } else {
                    ui.listPrerequisite(mod);
                }
                return;
            }
        }
        ui.showNoModulesFound();
    }
}
