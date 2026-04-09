package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;

public class ListCommand extends Command {

    @Override
    public void execute(ArrayList<Mod> list) {
        System.out.println("===== Your Tracked Modules =====");
        if (list.isEmpty()) {
            System.out.println("No modules tracked yet.");
            return;
        }

        for (Mod mod : list) {
            System.out.println(mod);
        }
    }
}
