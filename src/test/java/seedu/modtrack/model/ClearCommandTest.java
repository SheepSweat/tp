package seedu.modtrack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearCommandTest {
    private ArrayList<Mod> list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        // Redirect System.out to capture the confirmation message
        System.setOut(new PrintStream(outContent));

        // Pre-fill with some modules
        list.add(new Mod("CS2113", 2, 1, 4));
        list.add(new Mod("MA1511", 1, 1, 4));
    }

    @Test
    public void execute_populatedList_clearsAllModules() {
        ClearCommand command = new ClearCommand();
        command.execute(list);

        // Verify list state
        assertTrue(list.isEmpty(), "List should be empty after clear command");
        assertEquals(0, list.size(), "List size should be 0");

        // Verify console output
        String output = outContent.toString();
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
        ArrayList<Mod> originalReference = list;
        ClearCommand command = new ClearCommand();

        command.execute(list);

        assertTrue(originalReference == list, "The list reference should remain the same");
        assertTrue(originalReference.isEmpty());
    }
}