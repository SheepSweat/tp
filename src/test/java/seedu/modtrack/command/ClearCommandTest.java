package seedu.modtrack.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import seedu.modtrack.module.Mod;
import seedu.modtrack.ui.Ui;
import seedu.modtrack.commands.ClearCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearCommandTest {
    private ArrayList<Mod> list;
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
        // Redirect System.out to capture the confirmation message
        System.setOut(new PrintStream(this.outContent));

        // Pre-fill with some modules
        this.list.add(new Mod("CS2113", 2, 1, 4));
        this.list.add(new Mod("MA1511", 1, 1, 4));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(this.originalOut);
        System.setIn(this.originalIn); // Restore System.in after every test
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    @Test
    public void execute_populatedList_clearsAllModules() {

        this.provideInput("yes\n");
        ClearCommand command = new ClearCommand();
        command.execute(this.list, this.ui);

        // Verify list state
        assertTrue(this.list.isEmpty(), "List should be empty after clear command");
        assertEquals(0, this.list.size(), "List size should be 0");

        // Verify console output
        String output = this.outContent.toString();
        assertTrue(output.contains("Confirmed. Proceeding to clear all modules."), "Should print confirmation message");
    }

    @Test
    public void execute_emptyList_remainsEmpty() {
        this.provideInput("yes\n");
        ArrayList<Mod> emptyList = new ArrayList<>();
        ClearCommand command = new ClearCommand();

        command.execute(emptyList, this.ui);

        assertTrue(emptyList.isEmpty());
    }

    @Test
    public void execute_confirmNo_doesNotClearModules() {
        // 1. Simulate the user typing "no"
        this.provideInput("no\n");

        int originalSize = this.list.size();
        ClearCommand command = new ClearCommand();
        command.execute(this.list, this.ui);

        // Verify list state - Should NOT be empty
        assertEquals(originalSize, this.list.size(), "List should NOT be cleared if user says no");
        String output = this.outContent.toString();
        assertTrue(output.contains("Clear operation cancelled."));
    }
}
