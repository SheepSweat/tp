package seedu.modtrack.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.modtrack.commands.*;
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
        // Setup
        ListCompareCommand command = new ListCompareCommand();
        ArrayList<Mod> taskList = new ArrayList<>();

        // Add a module that exists in your ReferenceList
        taskList.add(new Mod("CS1010", 1, 1, 4));
        taskList.add(new Mod("CS2113", 2, 2, 4));

        // Act
        command.execute(taskList);

        // Assert
        String output = outContent.toString();

        assertTrue(output.contains("✔ COMPLETED MODULES:"));
        // Check if CS1010 appears under the "Completed" header
        assertTrue(output.contains("Name: CS1010"), "Module name formatted incorrectly");
        assertTrue(output.contains("Year: YEAR1"), "Year formatting incorrect");
        assertTrue(output.contains("Semester: SEM1"), "Semester formatting incorrect");
        assertTrue(output.contains("Modular Credits: 4"), "Credits missing or wrong");
        assertTrue(output.contains("Status: Incomplete"), "Default status should be Incomplete");
        // Check if CS2113 appears under the "Completed" header
        assertTrue(output.contains("Name: CS2113"), "Module name formatted incorrectly");
        assertTrue(output.contains("Year: YEAR2"), "Year formatting incorrect");
        assertTrue(output.contains("Semester: SEM2"), "Semester formatting incorrect");
        assertTrue(output.contains("Modular Credits: 4"), "Credits missing or wrong");
        assertTrue(output.contains("Status: Incomplete"), "Default status should be Incomplete");

        // Ensure the "Missing" header is still present
        assertTrue(output.contains("✘ MISSING/UNCOMPLETED MODULES:"));
    }
}