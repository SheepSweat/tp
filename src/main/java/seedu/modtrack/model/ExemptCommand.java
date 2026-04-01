package seedu.modtrack.model;

import java.util.ArrayList;

public class ExemptCommand extends Command {
    private final String modName;

    public ExemptCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(modName)) {
                mod.setToExempted();
                System.out.println("Module marked as exempted:");
                System.out.println(mod.getModName());
                return;
            }
        }
        System.out.println("Module not found.");
    }
}