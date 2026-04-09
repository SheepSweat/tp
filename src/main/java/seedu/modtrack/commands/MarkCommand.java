package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

public class MarkCommand extends Command {
    private String modName;

    public MarkCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list, Ui ui) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                mod.setToDone();
                ui.showMarkedCourse(mod);
                return;
            }
        }
        ui.showNoModulesFound();
    }
}
