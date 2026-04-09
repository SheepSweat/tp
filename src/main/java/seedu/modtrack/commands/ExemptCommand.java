package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

public class ExemptCommand extends Command {
    private final String modName;

    public ExemptCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list, Ui ui) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                mod.setToExempted();
                ui.showExemptedModule(mod);
                return;
            }
        }
        ui.showNoModulesFound();
    }
}
