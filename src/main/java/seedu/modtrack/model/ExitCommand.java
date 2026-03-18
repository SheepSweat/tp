package seedu.modtrack.model;

import java.util.ArrayList;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
