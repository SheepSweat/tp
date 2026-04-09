package seedu.modtrack.commands;

import seedu.modtrack.module.Mod;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends Command {
    // Initialize Logger
    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());

    private String modName;

    public DeleteCommand(String modName) {
        // Assertion to ensure the parser didn't pass a null/empty string
        assert modName != null && !modName.trim().isEmpty() : "Module name to delete cannot be null or empty";
        this.modName = modName;
    }

    @Override
    public void execute(ArrayList<Mod> list) {
        logger.log(Level.INFO, "Attempting to delete module: {0}", this.modName);

        // Internal sanity check: The list should not be null
        assert list != null : "Module list should not be null during deletion";

        int initialSize = list.size();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModName().equalsIgnoreCase(this.modName)) {
                logger.log(Level.INFO, "Module found at index {0}. Proceeding with removal.", i);

                System.out.println("----------------------------------------------------");
                System.out.println("Noted. I've removed this mod:\n");
                System.out.println(list.get(i).toString());

                list.remove(i);

                // Assertion: The list must have exactly one fewer item now
                assert list.size() == initialSize - 1 : "List size should decrease by exactly 1";

                System.out.printf("Now you have %d mods in the list.\n", list.size());
                System.out.println("----------------------------------------------------");
                return;
            }
        }

        // If we reach here, the module wasn't found
        logger.log(Level.WARNING, "Module {0} was not found in the list.", this.modName);
        System.out.println("Module not found.");

        // Assertion: Ensure the list size remained unchanged if deletion failed
        assert list.size() == initialSize : "List size changed even though no module was found";
    }
}
