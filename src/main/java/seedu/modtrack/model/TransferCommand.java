package seedu.modtrack.model;

import java.util.ArrayList;

public class TransferCommand extends Command {
    private final String modName;

    public TransferCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (Mod mod : list) {
            if (mod.getModName().equalsIgnoreCase(this.modName)) {
                mod.setToTransferred();
                System.out.println("Module marked as transferred:");
                System.out.println(mod.getModName());
                return;
            }
        }
        System.out.println("Module not found.");
    }
}
