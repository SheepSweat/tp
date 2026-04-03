package seedu.modtrack.model;

import java.util.ArrayList;

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
