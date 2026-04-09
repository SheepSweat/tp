package seedu.modtrack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddPrereqCommandTest {
    private ArrayList<Mod> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        // Pre-fill list with a module to add prerequisites to
        list.add(new Mod("CG2023", 2, 1, 4));
    }

    @Test
    public void execute_validModule_addsPrerequisites() {
        ArrayList<String> newPrereqs = new ArrayList<>(Arrays.asList("CG1111", "CS1010"));
        AddPrereqCommand command = new AddPrereqCommand("CG2023", newPrereqs);

        command.execute(list);

        Mod targetMod = list.get(0);
        assertEquals(2, targetMod.getPrerequisites().size());
        assertTrue(targetMod.getPrerequisites().contains("CG1111"));
        assertTrue(targetMod.getPrerequisites().contains("CS1010"));
    }

    @Test
    public void execute_duplicatePrerequisites_avoidsRedundancy() {
        // First add one prerequisite
        Mod targetMod = list.get(0);
        targetMod.addPrerequisite("CS1010");

        // Try to add the same one again along with a new one
        ArrayList<String> duplicatePrereqs = new ArrayList<>(Arrays.asList("CS1010", "MA1511"));
        AddPrereqCommand command = new AddPrereqCommand("CG2023", duplicatePrereqs);

        command.execute(list);

        // Size should be 2 (CS1010 + MA1511), not 3
        assertEquals(2, targetMod.getPrerequisites().size());
        assertTrue(targetMod.getPrerequisites().contains("MA1511"));
    }

    @Test
    public void execute_moduleNotFound_noChangesMade() {
        ArrayList<String> prereqs = new ArrayList<>(Arrays.asList("CS2040C"));
        AddPrereqCommand command = new AddPrereqCommand("NON_EXISTENT", prereqs);

        command.execute(list);

        // Verify the existing module was not affected
        assertTrue(list.get(0).getPrerequisites().isEmpty());
    }

    @Test
    public void execute_caseInsensitiveName_updatesCorrectly() {
        ArrayList<String> prereqs = new ArrayList<>(Arrays.asList("EE2026"));
        // Using lowercase "cg2023" to test string matching logic
        AddPrereqCommand command = new AddPrereqCommand("cg2023", prereqs);

        command.execute(list);

        assertEquals(1, list.get(0).getPrerequisites().size());
        assertEquals("EE2026", list.get(0).getPrerequisites().get(0));
    }
}
