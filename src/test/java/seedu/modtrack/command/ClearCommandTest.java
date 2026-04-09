package seedu.modtrack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import seedu.modtrack.module.Mod;
import seedu.modtrack.commands.ClearCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearCommandTest {
    private ArrayList<Mod> list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        // Redirect System.out to capture the confirmation message
        System.setOut(new PrintStream(this.outContent));

        // Pre-fill with some modules
        this.list.add(new Mod("CS2113", 2, 1, 4));
        this.list.add(new Mod("MA1511", 1, 1, 4));
    }

    @Test
    public void execute_populatedList_clearsAllModules() {
        ClearCommand command = new ClearCommand();
        command.execute(this.list);

        // Verify list state
        assertTrue(this.list.isEmpty(), "List should be empty after clear command");
        assertEquals(0, this.list.size(), "List size should be 0");

        // Verify console output
        String output = this.outContent.toString();
        assertTrue(output.contains("All modules have been cleared"), "Should print confirmation message");
    }

    @Test
    public void execute_emptyList_remainsEmpty() {
        ArrayList<Mod> emptyList = new ArrayList<>();
        ClearCommand command = new ClearCommand();

        command.execute(emptyList);

        assertTrue(emptyList.isEmpty(), "Empty list should remain empty");
        assertEquals(0, emptyList.size());
    }

    @Test
    public void execute_preservesListReference() {
        // This ensures the command clears the existing list rather than
        // assigning it to a new empty object, which could break other references.
        ArrayList<Mod> originalReference = this.list;
        ClearCommand command = new ClearCommand();

        command.execute(this.list);

        assertTrue(originalReference == this.list, "The list reference should remain the same");
        assertTrue(originalReference.isEmpty());
    }
}
