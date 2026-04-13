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
                boolean isAnyPrereqAdded = false;
                for (String prereq : this.prerequisites) {
                    if (prereq.equalsIgnoreCase(this.modName)) {
                        continue;
                    }
                    Mod existingPrereqMod = findModule(list, prereq);
                    if (existingPrereqMod != null && existingPrereqMod.getPrerequisites().contains(this.modName)) {
                        ui.showCircularDependencyWarning(prereq, this.modName);
                        continue;
                    }
                    mod.addPrerequisite(prereq);
                    isAnyPrereqAdded = true;
                }
                if (isAnyPrereqAdded) {
                    ui.showUpdatedPrerequisites(mod);
                    ui.listPrerequisite(mod);
                    return;
                }

                if (mod.getPrerequisites().isEmpty()) {
                    ui.showNoModulesFound();
                } else {
                    ui.showPrerequisites(mod);
                }
                return;
            }
        }
        ui.showNoModulesFound();
    }

    private Mod findModule(ArrayList<Mod> list, String name) {
        for (Mod m : list) {
            if (m.getModName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

}
