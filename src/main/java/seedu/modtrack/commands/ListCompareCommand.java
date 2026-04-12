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
            boolean isFulfilled = false;

            for (Mod task : taskList) {
                String reqName = reqMod.getModName().trim().toLowerCase();
                String taskName = task.getModName().trim().toLowerCase();
                if (reqName.contains(taskName)&& task.getIsComplete()) {
                    boolean isNormalComplete = task.getIsComplete();
                    boolean isTransferred = task.getCompletionType().equals("TRANSFERRED");
                    boolean isExempted = task.getCompletionType().equals("EXEMPTED");
                    if (isNormalComplete || isTransferred || isExempted) {
                        isFulfilled = true;
                        reqMod.setToDone();
                        reqMod.setCompletionType(task.getCompletionType());

                    }
                    break;
                }
            }

            if (isFulfilled) {
                completed.add(reqMod);
            } else {
                reqMod.setToUndone();
                missing.add(reqMod);
            }
        }

        assert (completed.size() + missing.size()) == requiredModules.size()
                : "Total categorized modules must equal total required modules";

        ui.showComparedList(completed, missing);
    }
}
