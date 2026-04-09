package seedu.modtrack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TransferCommandTest {
    private ArrayList<Mod> list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        // Capture console output
        System.setOut(new PrintStream(outContent));

        // Initial state: Add a module that hasn't been completed yet
        list.add(new Mod("CS2040C", 1, 2, 4));
    }

    @Test
    public void execute_validModule_setsToTransferred() {
        TransferCommand command = new TransferCommand("CS2040C");
        command.execute(list);

        Mod mod = list.get(0);

        // Verify internal state: Transferred modules should be considered complete
        assertTrue(mod.getIsComplete(), "Transferred module should be marked as complete.");
        assertEquals("TRANSFERRED", mod.getCompletionType());

        // Verify UI logic
        assertEquals("Transferred", mod.getDisplayStatus());

        // Verify console output
        String output = outContent.toString();
        assertTrue(output.contains("Module marked as transferred:"));
        assertTrue(output.contains("CS2040C"));
    }

    @Test
    public void execute_caseInsensitive_findsModule() {
        // Testing with lowercase "cs2040c"
        TransferCommand command = new TransferCommand("cs2040c");
        command.execute(list);

        assertTrue(list.get(0).getIsComplete());
        assertEquals("TRANSFERRED", list.get(0).getCompletionType());
    }

    @Test
    public void execute_moduleNotFound_printsErrorMessage() {
        TransferCommand command = new TransferCommand("EE2026");
        command.execute(list);

        // Verify state did not change
        assertFalse(list.get(0).getIsComplete());

        // Verify feedback
        String output = outContent.toString();
        assertTrue(output.contains("Module not found."));
    }
}
