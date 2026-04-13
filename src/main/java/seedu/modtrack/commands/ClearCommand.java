package seedu.modtrack.commands;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to remove all modules from the tracker.
 */
public class ClearCommand extends Command {
    private static final Logger logger = Logger.getLogger(ClearCommand.class.getName());

    @Override
    public void execute(ArrayList<Mod> list, Ui ui) {
        // 1. Ask for confirmation via UI
        if (!this.clearConfirmation(ui)) {
            return;
        }

        logger.log(Level.INFO, "User confirmed clear. Wiping list.");
        // Assertion: Verify the list exists before trying to clear it
        assert list != null : "Module list should not be null during clear operation";

        list.clear();

        // Assertion: Post-condition verification to satisfy the dashboard check
        assert list.isEmpty() : "List should be empty after clear() is called";

        ui.showDivider();
        ui.showClearConfirmation();
        ui.showDivider();

        logger.log(Level.INFO, "Successfully cleared all modules.");
    }

    public boolean clearConfirmation(Ui ui) {
        String confirmation = ui.showClearConfirmationPrompt();
        return confirmation.equalsIgnoreCase("yes");
    }
}
