package seedu.modtrack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {
    private ArrayList<Mod> list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        // Redirect System.out to capture console output for verification of printAddMessage
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void execute_newModule_addsToListAndPrintsSuccess() {
        AddCommand command = new AddCommand("CS2113", 2, 1, 4);
        command.execute(list);

        // Verify Model update
        assertEquals(1, list.size());
        assertEquals("CS2113", list.get(0).getModName());

        // Verify UI output
        String output = outContent.toString();
        assertTrue(output.contains("Module added:"));
        assertTrue(output.contains("Name: CS2113"));
        assertTrue(output.contains("Total modules tracked: 1"));
    }

    @Test
    public void execute_duplicateIncompleteModule_doesNotAddAndPrintsMarkHint() {
        // Pre-fill list with an incomplete module
        Mod existing = new Mod("CS2113", 2, 1, 4);
        list.add(existing);

        AddCommand command = new AddCommand("CS2113", 2, 1, 4);
        command.execute(list);

        // Size should remain 1
        assertEquals(1, list.size());

        // Verify hint for 'mark' command
        String output = outContent.toString();
        assertTrue(output.contains("currently incomplete"));
        assertTrue(output.contains("mark n/CS2113"));
    }

    @Test
    public void execute_duplicateCompletedModule_printsAlreadyInList() {
        // Pre-fill list with a completed module
        Mod existing = new Mod("CS2113", 2, 1, 4);
        existing.setToDone();
        list.add(existing);

        AddCommand command = new AddCommand("cs2113", 2, 1, 4); // Test case-insensitivity
        command.execute(list);

        assertEquals(1, list.size());

        String output = outContent.toString();
        assertTrue(output.contains("already in the list!"));
    }

    @Test
    public void execute_multipleModules_incrementsTotalCount() {
        list.add(new Mod("CS1010", 1, 1, 4));

        AddCommand command = new AddCommand("CS2113", 2, 1, 4);
        command.execute(list);

        String output = outContent.toString();
        assertTrue(output.contains("Total modules tracked: 2"));
    }
}