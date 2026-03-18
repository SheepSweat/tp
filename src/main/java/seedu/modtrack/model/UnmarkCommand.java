package seedu.modtrack.model;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private String modName;

    public UnmarkCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(modName)) {
                mod.setToUndone();
                System.out.println("Module marked as incomplete:");
                System.out.println(mod.getModName());
                return;
            }
        }
        System.out.println("Module not found.");
    }
}
