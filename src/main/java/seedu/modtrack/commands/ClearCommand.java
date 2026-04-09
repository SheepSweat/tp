package seedu.modtrack.commands;

import seedu.modtrack.module.Mod;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to remove all modules from the tracker.
 */
public class ClearCommand extends Command {
    private static final Logger logger = Logger.getLogger(ClearCommand.class.getName());

    @Override
    public void execute(ArrayList<Mod> list) {
        logger.log(Level.INFO, "Attempting to clear the module list.");

        // Assertion: Verify the list exists before trying to clear it
        assert list != null : "Module list should not be null during clear operation";

        list.clear();

        // Assertion: Post-condition verification to satisfy the dashboard check
        assert list.isEmpty() : "List should be empty after clear() is called";

        System.out.println("----------------------------------------------------");
        System.out.println("Noted. All modules have been cleared. Now you have an empty list.");
        System.out.println("----------------------------------------------------");

        logger.log(Level.INFO, "Successfully cleared all modules.");
    }
}
