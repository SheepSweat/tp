package seedu.modtrack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;
import seedu.modtrack.commands.AddCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {
    private ArrayList<Mod> list;
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
        // Redirect System.out to capture console output for verification of
        // printAddMessage
        System.setOut(new PrintStream(this.outContent));
    }

    @Test
    public void execute_newModule_addsToListAndPrintsSuccess() {
        AddCommand command = new AddCommand("CS2113", 2, 1, 4);
        command.execute(this.list, this.ui);

        // Verify Model update
        assertEquals(1, this.list.size());
        assertEquals("CS2113", this.list.get(0).getModName());

        // Verify UI output
        String output = this.outContent.toString();
        assertTrue(output.contains("Module added:"));
        assertTrue(output.contains("Name: CS2113"));
        assertTrue(output.contains("Total modules tracked: 1"));
    }

    @Test
    public void execute_duplicateIncompleteModule_doesNotAddAndPrintsMarkHint() {
        // Pre-fill list with an incomplete module
        Mod existing = new Mod("CS2113", 2, 1, 4);
        this.list.add(existing);

        AddCommand command = new AddCommand("CS2113", 2, 1, 4);
        command.execute(this.list, this.ui);

        // Size should remain 1
        assertEquals(1, this.list.size());

        // Verify hint for 'mark' command
        String output = this.outContent.toString();
        assertTrue(output.contains("currently incomplete"));
        assertTrue(output.contains("mark n/CS2113"));
    }

    @Test
    public void execute_duplicateCompletedModule_printsAlreadyInList() {
        // Pre-fill list with a completed module
        Mod existing = new Mod("CS2113", 2, 1, 4);
        existing.setToDone();
        this.list.add(existing);

        AddCommand command = new AddCommand("cs2113", 2, 1, 4); // Test case-insensitivity
        command.execute(this.list, this.ui);

        assertEquals(1, this.list.size());

        String output = this.outContent.toString();
        assertTrue(output.contains("already in the list!"));
    }

    @Test
    public void execute_multipleModules_incrementsTotalCount() {
        this.list.add(new Mod("CS1010", 1, 1, 4));

        AddCommand command = new AddCommand("CS2113", 2, 1, 4);
        command.execute(this.list, this.ui);

        String output = this.outContent.toString();
        assertTrue(output.contains("Total modules tracked: 2"));
    }

    @Test
    public void execute_customCredits_addsWithCorrectCredits() {
        // Testing a 2MC module like a DYOM or specific lab
        AddCommand command = new AddCommand("CS2113", 2, 1, 2);
        command.execute(this.list, this.ui);

        assertEquals(2, this.list.get(0).getModCredits(), "Module should be saved with 2 MCs");
    }
}
