package seedu.modtrack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;
import seedu.modtrack.commands.TransferCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TransferCommandTest {
    private ArrayList<Mod> list;
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
        // Capture console output
        System.setOut(new PrintStream(this.outContent));

        // Initial state: Add a module that hasn't been completed yet
        this.list.add(new Mod("CS2040C", 1, 2, 4));
    }

    @Test
    public void execute_validModule_setsToTransferred() {
        TransferCommand command = new TransferCommand("CS2040C");
        command.execute(this.list, this.ui);

        Mod mod = this.list.get(0);

        // Verify internal state: Transferred modules should be considered complete
        assertTrue(mod.getIsComplete(), "Transferred module should be marked as complete.");
        assertEquals("TRANSFERRED", mod.getCompletionType());

        // Verify UI logic
        assertEquals("Transferred", mod.getDisplayStatus());

        // Verify console output
        String output = this.outContent.toString();
        assertTrue(output.contains("Module marked as transferred:"));
        assertTrue(output.contains("CS2040C"));
    }

    @Test
    public void execute_caseInsensitive_findsModule() {
        // Testing with lowercase "cs2040c"
        TransferCommand command = new TransferCommand("cs2040c");
        command.execute(this.list, this.ui);

        assertTrue(this.list.get(0).getIsComplete());
        assertEquals("TRANSFERRED", this.list.get(0).getCompletionType());
    }

    @Test
    public void execute_moduleNotFound_printsErrorMessage() {
        TransferCommand command = new TransferCommand("EE2026");
        command.execute(this.list, this.ui);

        // Verify state did not change
        assertFalse(this.list.get(0).getIsComplete());

        // Verify feedback
        String output = this.outContent.toString();
        assertTrue(output.contains("No modules found in the list."));
    }
}
