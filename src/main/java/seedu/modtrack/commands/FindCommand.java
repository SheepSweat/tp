package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(ArrayList<Mod> list, Ui ui) {
        ui.showDivider();
        System.out.println("Matching modules:");
        
        Mod modFound = this.modMatchFound(list);
        if (modFound != null) {
            ui.showMatchingModule(modFound);
        } else {
            ui.showNoModulesFound();
        }

    }

    public Mod modMatchFound(ArrayList<Mod> list) {
        for (Mod mod : list) {
            if (mod.getModName().toLowerCase().contains(this.keyword)) {
                return mod;
            }
        }
        return null;
    }
}
