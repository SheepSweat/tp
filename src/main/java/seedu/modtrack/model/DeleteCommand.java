package seedu.modtrack.model;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private String modName;

    public DeleteCommand(String modName) {
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModName().equalsIgnoreCase(this.modName)) {
                System.out.println("----------------------------------------------------");
                System.out.println("Noted. I've removed this mod:\n");
                System.out.println(list.get(i).toString());
                list.remove(i);
                System.out.printf("Now you have %d mods in the list.\n", list.size());
                System.out.println("----------------------------------------------------");
                return;
            }
        }
        System.out.println("Module not found.");
    }
}
