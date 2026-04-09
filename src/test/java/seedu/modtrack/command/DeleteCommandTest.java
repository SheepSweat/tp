package seedu.modtrack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import seedu.modtrack.module.Mod;
import seedu.modtrack.commands.DeleteCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {
    private ArrayList<Mod> list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        // Redirect System.out to capture console output for verification
        System.setOut(new PrintStream(this.outContent));

        // Pre-fill with some data for deletion tests
        this.list.add(new Mod("CS2113", 2, 1, 4));
        this.list.add(new Mod("CS2101", 2, 1, 4));
    }

    @Test
    public void execute_existingModule_removesFromList() {
        DeleteCommand command = new DeleteCommand("CS2113");
        command.execute(this.list);

        // Verify list size decreased
        assertEquals(1, this.list.size());

        // Verify the correct module was removed
        assertEquals("CS2101", this.list.get(0).getModName());

        // Verify output message
        String output = this.outContent.toString();
        assertTrue(output.contains("Noted. I've removed this mod:"));
        assertTrue(output.contains("Name: CS2113"));
        assertTrue(output.contains("Now you have 1 mods in the list."));
    }

    @Test
    public void execute_caseInsensitiveName_removesFromList() {
        // Test with lowercase input
        DeleteCommand command = new DeleteCommand("cs2101");
        command.execute(this.list);

        assertEquals(1, this.list.size());
        assertEquals("CS2113", this.list.get(0).getModName());
    }

    @Test
    public void execute_nonExistentModule_printsErrorMessage() {
        DeleteCommand command = new DeleteCommand("MA1511");
        command.execute(this.list);

        // Size should remain 2
        assertEquals(2, this.list.size());

        // Verify error message
        String output = this.outContent.toString();
        assertTrue(output.contains("Module not found."));
    }

    @Test
    public void execute_emptyList_printsErrorMessage() {
        ArrayList<Mod> emptyList = new ArrayList<>();
        DeleteCommand command = new DeleteCommand("CS2113");
        command.execute(emptyList);

        String output = this.outContent.toString();
        assertTrue(output.contains("Module not found."));
    }
}
