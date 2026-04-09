package seedu.modtrack.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import seedu.modtrack.module.Mod;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        this.ui = new Ui();
        // Redirect System.out to capture output
        System.setOut(new PrintStream(this.outContent));
    }

    @AfterEach
    public void tearDown() {
        // Restore original System.out
        System.setOut(this.originalOut);
    }

    private String getOutput() {
        return this.outContent.toString();
    }

    @Test
    public void showOpeningText_printsOpeningText() {
        this.ui.showOpeningText();
        String output = this.getOutput();
        assertTrue(output.contains("Hello! I'm [ModTrack]"));
        assertTrue(output.contains("Type a module code and I will record it for you!"));
    }

    @Test
    public void showClosingText_printsClosingText() {
        this.ui.showClosingText();
        String output = this.getOutput();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    public void showEmptyDescriptionError_printsCorrectMessage() {
        String command = "add";
        this.ui.showEmptyDescriptionError(command);
        String output = this.getOutput();
        assertTrue(output.contains("Number cannot be empty"));
        assertTrue(output.contains(command));
    }

    @Test
    public void showInvalidCommandError_printsCorrectMessage() {
        this.ui.showInvalidCommandError();
        String output = this.getOutput();
        assertTrue(output.contains("Invalid command"));
        assertTrue(output.contains("begining with 'bye'")); // Keep typo to match original
    }

    @Test
    public void showMarkedCourse_printsCorrectMessage() {
        String taskInfo = "CS2113";
        String modularCredits = "4";
        this.ui.showMarkedCourse(taskInfo, modularCredits);
        String output = this.getOutput();
        assertTrue(output.contains("Module marked as completed"));
        assertTrue(output.contains(taskInfo));
        assertTrue(output.contains(modularCredits));
    }

    @Test
    public void showUnmarkedCourse_printsCorrectMessage() {
        String taskInfo = "CS2113";
        this.ui.showUnmarkedCourse(taskInfo);
        String output = this.getOutput();
        assertTrue(output.contains("Module marked as incomplete"));
        assertTrue(output.contains(taskInfo));
    }

    @Test
    public void showList_printsCompletedAndRemainingModules() {
        ArrayList<Mod> completedList = new ArrayList<>();
        ArrayList<Mod> allModules = new ArrayList<>();

        Mod mod1 = new Mod("CS1010", 1, 1, 4); // completed
        Mod mod2 = new Mod("CS1231", 2, 2, 4); // not completed
        completedList.add(mod1);
        allModules.add(mod1);
        allModules.add(mod2);

        this.ui.showList(completedList, allModules);
        String output = this.getOutput();

        assertTrue(output.contains("✔ CS1010"));
        assertTrue(output.contains("✘ CS1231"));
    }

    @Test
    public void showGradReq_printsGraduationRequirements() {
        this.ui.showGradReq();
        String output = this.getOutput();
        assertTrue(output.contains("Computer Engineering Graduation Requirements"));
        assertTrue(output.contains("Total MCs Required: 160"));
        assertTrue(output.contains("Core Modules:"));
        assertTrue(output.contains("Project/Internship:"));
        assertTrue(output.contains("Engineering Common Curriculum:"));
        assertTrue(output.contains("General Education Curriculum:"));
    }
}
