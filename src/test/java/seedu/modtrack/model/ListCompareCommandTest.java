package seedu.modtrack.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.modtrack.commands.ListCompareCommand;
import seedu.modtrack.module.Mod;
import seedu.modtrack.referencelist.ReferenceList;

class ListCompareCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Redirect System.out to our "bucket" to capture the printed output
        System.setOut(new PrintStream(outContent));
        ReferenceList ref = new ReferenceList();
        ref.populateReferenceList(ref.list);
    }

    @AfterEach
    void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
    }

    @Test
    void execute_emptyTaskList_showsAllAsMissing() {
        // Setup
        ListCompareCommand command = new ListCompareCommand();
        ArrayList<Mod> emptyTaskList = new ArrayList<>();

        // Act
        command.execute(emptyTaskList);

        // Assert
        String output = outContent.toString();
        assertTrue(output.contains("COMPLETED MODULES:"));
        assertTrue(output.contains("(None yet)"));
        assertTrue(output.contains("MISSING/UNCOMPLETED MODULES:"));
    }

    @Test
    void execute_partialCompletion_showsCorrectCategories() {
        // 1. Setup - Create one completed and one incomplete mod
        ListCompareCommand command = new ListCompareCommand();
        ArrayList<Mod> taskList = new ArrayList<>();

        Mod finishedMod = new Mod("CS1010", 1, 1, 4);
        finishedMod.setToDone(); // This should go to "Completed"

        Mod unfinishedMod = new Mod("CS2113", 2, 2, 4);
        // Default is incomplete - This should go to "Missing"

        taskList.add(finishedMod);
        taskList.add(unfinishedMod);

        // 2. Act
        command.execute(taskList);
        String output = outContent.toString();

        // 3. Assert - Use indexes to ensure modules are in the right sections
        int completedHeader = output.indexOf("✔ COMPLETED MODULES:");
        int missingHeader = output.indexOf("✘ MISSING/UNCOMPLETED MODULES:");

        int cs1010Index = output.indexOf("CS1010");
        int cs2113Index = output.indexOf("CS2113");

        // Verify Headers exist
        assertTrue(completedHeader != -1, "Completed header missing");
        assertTrue(missingHeader != -1, "Missing header missing");

        // Verify CS1010 is AFTER the Completed header but BEFORE the Missing header
        assertTrue(cs1010Index > completedHeader && cs1010Index < missingHeader,
                "CS1010 should be in the Completed section");

        // Verify CS2113 is AFTER the Missing header
        assertTrue(cs2113Index > missingHeader,
                "CS2113 should be in the Missing section");

        // Verify detail accuracy
        assertTrue(output.contains("Status: Complete"), "Complete status label missing");
        assertTrue(output.contains("Status: Incomplete"), "Incomplete status label missing");
    }
}
