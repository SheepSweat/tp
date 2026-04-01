package seedu.modtrack.model;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        boolean found = false;

        System.out.println("Matching modules:");
        for (Mod mod : list) {
            if (mod.getModName().toLowerCase().contains(keyword)) {
                System.out.println("--------------------");
                System.out.print(mod);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching module found.");
        }
    }
}