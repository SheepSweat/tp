package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(ArrayList<Mod> list, Ui ui) {
        ui.showList(list);
    }
}
