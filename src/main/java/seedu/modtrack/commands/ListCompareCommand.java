package seedu.modtrack.commands;

import seedu.modtrack.referencelist.ReferenceList;
import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;
import java.util.ArrayList;

public class ListCompareCommand extends Command {

    @Override
    public void execute(ArrayList<Mod> taskList, Ui ui) {
        assert taskList != null : "taskList passed to ListCompareCommand should not be null";
        ArrayList<Mod> requiredModules = ReferenceList.getReferenceList();

        ArrayList<Mod> completed = new ArrayList<>();
        ArrayList<Mod> missing = new ArrayList<>();

        for (Mod reqMod : requiredModules) {
            boolean isFoundAndComplete = false;

            for (Mod task : taskList) {
                if (task.getModName().equalsIgnoreCase(reqMod.getModName())&& task.getIsComplete()) {
                    isFoundAndComplete = true;
                    break;
                }
            }

            if (isFoundAndComplete) {
                completed.add(reqMod);
                reqMod.setToDone();
            } else {
                missing.add(reqMod);
            }
        }

        assert (completed.size() + missing.size()) == requiredModules.size()
                : "Total categorized modules must equal total required modules";

        ui.showComparedList(completed, missing);
    }
}
