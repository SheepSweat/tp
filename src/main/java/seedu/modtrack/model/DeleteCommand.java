package seedu.modtrack.model;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int targetIndex;

    public DeleteCommand(int index) {
        this.targetIndex = index;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        System.out.println("----------------------------------------------------");
        System.out.println("Noted. I've removed this mod:\n");
        System.out.println(list.get(this.targetIndex).toString());
        System.out.printf("Now you have %d mods in the list.\n", list.size() - 1);
        System.out.println("----------------------------------------------------");
        list.remove(this.targetIndex);
    }
}
