package seedu.modtrack.model;

import java.util.ArrayList;

public class AddCommand extends Command {
    private String modName;
    private int year;
    private int semester;
    private int modCredits;

    public AddCommand(String name, int year, int semester, int credits) {
        this.modName = name;
        this.year = year;
        this.semester = semester;
        this.modCredits = credits;
    }

    public void printAddMessage(ArrayList<Mod> list, Mod mod) {
        System.out.println("----------------------------------------------------");
        System.out.println("Module added:\n");
        System.out.println(mod.toString());
        System.out.printf("Total modules tracked: %d", list.size());
        System.out.println();
        System.out.println("----------------------------------------------------");
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        for (Mod existingMod : list) {
            if (existingMod.getModName().equalsIgnoreCase(this.modName)) {
                System.out.println("----------------------------------------------------");
                if (!existingMod.getIsComplete()) {
                    System.out.println("This module already exists in the list, but is currently incomplete.");
                    System.out.println("To mark this module as complete, please type in command: 'mark n/"
                            + existingMod.getModName() + "'");
                } else {
                    System.out.println("This module is already in the list!");
                }
                System.out.println("----------------------------------------------------");
                return;
            }
        }

        Mod mod = new Mod(this.modName, this.year, this.semester, this.modCredits);
        list.add(mod);
        this.printAddMessage(list, mod);
    }
}
