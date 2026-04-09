package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;

public class MarkCommand extends Command {
    private String modName;

    public MarkCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                mod.setToDone();
                System.out.println("Module marked as completed:");
                System.out.println(mod.getModName());
                return;
            }
        }
        System.out.println("Module not found.");
    }
}
