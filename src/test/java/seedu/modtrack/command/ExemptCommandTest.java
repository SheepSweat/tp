package seedu.modtrack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import seedu.modtrack.module.Mod;
import seedu.modtrack.commands.ExemptCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExemptCommandTest {
    private ArrayList<Mod> list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(this.outContent));

        // Setup initial state: One incomplete module
        this.list.add(new Mod("MA1511", 1, 1, 4));
    }

    @Test
    public void execute_existingModule_setsToExemptedCorrectly() {
        ExemptCommand command = new ExemptCommand("MA1511");
        command.execute(this.list);

        Mod mod = this.list.get(0);

        // 1. Verify internal state changes
        assertTrue(mod.getIsComplete(), "Module should be marked as complete after exemption.");
        assertEquals("EXEMPTED", mod.getCompletionType(), "Completion type should be EXEMPTED.");

        // 2. Verify display logic
        assertEquals("Exempted", mod.getDisplayStatus(), "Display status should return 'Exempted'.");

        // 3. Verify console output
        String output = this.outContent.toString();
        assertTrue(output.contains("Module marked as exempted:"), "Should print success message.");
        assertTrue(output.contains("MA1511"), "Should print the module name.");
    }

    @Test
    public void execute_caseInsensitiveName_worksCorrectly() {
        // Test with lowercase input
        ExemptCommand command = new ExemptCommand("ma1511");
        command.execute(this.list);

        assertTrue(this.list.get(0).getIsComplete());
        assertEquals("EXEMPTED", this.list.get(0).getCompletionType());
    }

    @Test
    public void execute_moduleNotFound_printsErrorMessage() {
        ExemptCommand command = new ExemptCommand("CS1010");
        command.execute(this.list);

        // Verify state remains unchanged
        assertFalse(this.list.get(0).getIsComplete(), "Module should still be incomplete.");

        // Verify error message
        String output = this.outContent.toString();
        assertTrue(output.contains("Module not found."), "Should print error message when mod is missing.");
    }
}
