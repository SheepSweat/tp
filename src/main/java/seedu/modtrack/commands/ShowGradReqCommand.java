package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

public class ShowGradReqCommand extends Command {

    @Override
    public void execute(ArrayList<Mod> list, Ui ui) {
        ui.showGradReq();
    }
}
