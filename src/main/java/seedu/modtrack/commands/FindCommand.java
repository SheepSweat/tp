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

        ArrayList<Mod> matchingModules = this.findAllMatches(list);
        if (!matchingModules.isEmpty()) {
            for  (Mod modFound : matchingModules) {
                ui.showMatchingModule(modFound);
            }
        } else {
            ui.showNoModulesFound();
        }
    }

    public ArrayList<Mod> findAllMatches(ArrayList<Mod> list) {
        ArrayList<Mod> results = new ArrayList<>();
        for (Mod mod : list) {
            if (mod.getModName().toLowerCase().contains(this.keyword)) {
                results.add(mod);
            }
        }
        return results;
    }
}
