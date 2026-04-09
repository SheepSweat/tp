package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

public class TransferCommand extends Command {
    private final String modName;

    public TransferCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list, Ui ui) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                mod.setToTransferred();
                ui.showTransferredModule(mod);
                return;
            }
        }
        ui.showNoModulesFound();
    }
}
