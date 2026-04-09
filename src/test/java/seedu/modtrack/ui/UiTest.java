package seedu.modtrack.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        System.setOut(new PrintStream(this.outContent));
    }

    @AfterEach
    public void tearDown() {
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
        assertTrue(output.contains("What can I do for you?"));
    }

    @Test
    public void showClosingText_printsClosingText() {
        this.ui.showClosingText();
        assertTrue(this.getOutput().contains("Bye. Hope to see you again soon!"));
    }

    @Test
    public void showDivider_printsDivider() {
        this.ui.showDivider();
        assertTrue(this.getOutput().contains("----------------------------------------------------"));
    }

    @Test
    public void showNoModulesFound_printsMessage() {
        this.ui.showNoModulesFound();
        assertTrue(this.getOutput().contains("No modules found in the list."));
    }

    @Test
    public void showDuplicateModuleError_printsMessage() {
        this.ui.showDuplicateModuleError();
        assertTrue(this.getOutput().contains("already in the list"));
    }

    @Test
    public void showAddModule_printsCorrectOutput() {
        ArrayList<Mod> list = new ArrayList<>();
        Mod mod = new Mod("CS2113", 2, 1, 4);
        list.add(mod);

        this.ui.showAddModule(list, mod);
        String output = this.getOutput();

        assertTrue(output.contains("Module added"));
        assertTrue(output.contains("CS2113"));
        assertTrue(output.contains("Total modules tracked: 1"));
    }

    @Test
    public void showDeletedModule_printsCorrectOutput() {
        Mod mod = new Mod("CS2113", 2, 1, 4);

        this.ui.showDeletedModule(mod, 0);
        String output = this.getOutput();

        assertTrue(output.contains("removed this mod"));
        assertTrue(output.contains("CS2113"));
        assertTrue(output.contains("0 mods"));
    }

    @Test
    public void showMarkedCourse_printsCorrectMessage() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.setToDone();

        this.ui.showMarkedCourse(mod);
        String output = this.getOutput();

        assertTrue(output.contains("Module marked as completed"));
        assertTrue(output.contains("CS2113"));
    }

    @Test
    public void showUnmarkedCourse_printsCorrectMessage() {
        Mod mod = new Mod("CS2113", 2, 1, 4);

        this.ui.showUnmarkedCourse(mod);
        String output = this.getOutput();

        assertTrue(output.contains("Module marked as incomplete"));
        assertTrue(output.contains("CS2113"));
    }

    @Test
    public void showList_emptyList_printsEmptyMessage() {
        ArrayList<Mod> list = new ArrayList<>();

        this.ui.showList(list);
        String output = this.getOutput();

        assertTrue(output.contains("No modules tracked yet."));
    }

    @Test
    public void showList_nonEmptyList_printsModules() {
        ArrayList<Mod> list = new ArrayList<>();
        Mod mod = new Mod("CS2113", 2, 1, 4);
        list.add(mod);

        this.ui.showList(list);
        String output = this.getOutput();

        assertTrue(output.contains("CS2113"));
    }

    @Test
    public void showPrerequisites_empty_printsNone() {
        Mod mod = new Mod("CS2113", 2, 1, 4);

        this.ui.showPrerequisites(mod);
        String output = this.getOutput();

        assertTrue(output.contains("Prerequisites for CS2113"));
        assertTrue(output.contains("None"));
    }

    @Test
    public void showPrerequisites_withData_printsPrereqs() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.addPrerequisite("CS1010");

        this.ui.showPrerequisites(mod);
        String output = this.getOutput();

        assertTrue(output.contains("CS1010"));
    }

    @Test
    public void showGradReq_printsRequirements() {
        this.ui.showGradReq();
        String output = this.getOutput();

        assertTrue(output.contains("Computer Engineering Graduation Requirements"));
        assertTrue(output.contains("Total MCs Required: 160"));
        assertTrue(output.contains("Core Modules"));
    }
}
