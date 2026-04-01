package seedu.modtrack.model;

import java.util.ArrayList;

public class SetProgressCommand extends Command {
    private final String modName;
    private final int percentage;

    public SetProgressCommand(String modName, int percentage) {
        this.modName = modName;
        this.percentage = percentage;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                mod.setProgressPercentage(this.percentage);
                System.out.println("Updated coursework progress:");
                System.out.println(mod.getModName() + " -> " + mod.getProgressPercentage() + "%");
                return;
            }
        }
        System.out.println("Module not found.");
    }
}
