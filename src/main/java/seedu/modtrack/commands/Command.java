package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;

public abstract class Command {
    protected boolean isExit = false;

    public Command() {
    }

    public abstract void execute(ArrayList<Mod> list);

    public boolean isExit() {
        return this.isExit;
    }
}
