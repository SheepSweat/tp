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
        Mod mod = new Mod(modName, year, semester, modCredits);
        mod.setToDone();
        list.add(mod);
        printAddMessage(list, mod);
    }
}
