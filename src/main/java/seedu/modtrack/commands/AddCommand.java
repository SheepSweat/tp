package seedu.modtrack.commands;

import seedu.modtrack.module.Mod;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddCommand.class.getName());

    private String modName;
    private int year;
    private int semester;
    private int modCredits;

    public AddCommand(String name, int year, int semester, int credits) {
        assert name != null && !name.trim().isEmpty() : "Module name cannot be null or empty";
        assert year >= 1 && year <= 6 : "Year should be within university range";
        assert credits == 2 || credits == 4 : "Module credits must be 2 or 4";

        this.modName = name;
        this.year = year;
        this.semester = semester;
        this.modCredits = credits;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        logger.log(Level.INFO, "Attempting to add module: {0}", this.modName);

        // Defensive check: If list is null, we log a severe error but return to avoid
        // crash
        if (list == null) {
            logger.log(Level.SEVERE, "Module list provided to AddCommand was null.");
            return;
        }

        int initialSize = list.size();

        for (Mod existingMod : list) {
            if (existingMod.getModName().trim().equalsIgnoreCase(this.modName.trim())) {
                logger.log(Level.WARNING, "Duplicate detected for module: {0}", this.modName);

                System.out.println("----------------------------------------------------");
                if (!existingMod.getIsComplete()) {
                    System.out.println("This module already exists in the list, but is currently incomplete.");
                    System.out.println("To mark this module as complete, use: mark n/" + existingMod.getModName());
                } else {
                    System.out.println("This module is already in the list!");
                }
                System.out.println("----------------------------------------------------");

                assert list.size() == initialSize : "List size should not change on duplicate";
                return;
            }
        }

        Mod mod = new Mod(this.modName, this.year, this.semester, this.modCredits);
        list.add(mod);

        // Assertions to verify internal state updates correctly
        assert list.size() == initialSize + 1 : "List size should increment after successful add";

        logger.log(Level.INFO, "Successfully added {0}. New total: {1}", new Object[] { this.modName, list.size() });
        this.printAddMessage(list, mod);
    }

    public void printAddMessage(ArrayList<Mod> list, Mod mod) {
        System.out.println("----------------------------------------------------");
        System.out.println("Module added:\n");
        System.out.println(mod.toString());
        System.out.printf("Total modules tracked: %d\n", list.size());
        System.out.println("----------------------------------------------------");
    }
}
