package seedu.modtrack.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ModTest {

    @Test
    public void constructor_validInput_initializesCorrectly() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        assertEquals("CS2113", mod.getModName());
        assertEquals(2, mod.getYear());
        assertEquals(1, mod.getSemester());
        assertEquals(4, mod.getModCredits());
        assertFalse(mod.getIsComplete());
        assertEquals("NORMAL", mod.getCompletionType());
    }

    @Test
    public void setProgressPercentage_hundredPercent_marksAsComplete() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.setProgressPercentage(100);
        assertTrue(mod.getIsComplete());
    }

    @Test
    public void setProgressPercentage_lessThanHundred_marksAsIncomplete() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.setProgressPercentage(99);
        assertFalse(mod.getIsComplete());
    }

    @Test
    public void setToExempted_updatesStatusAndProgress() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.setToExempted();
        assertTrue(mod.getIsComplete());
        assertEquals("EXEMPTED", mod.getCompletionType());
        assertEquals("Exempted", mod.getDisplayStatus());
    }

    @Test
    public void addPrerequisite_newPrereq_addsToArrayList() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.addPrerequisite("CS1010");

        ArrayList<String> prereqs = mod.getPrerequisites();
        assertEquals(1, prereqs.size());
        assertEquals("CS1010", prereqs.get(0));
    }

    @Test
    public void addPrerequisite_duplicatePrereq_doesNotDuplicate() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.addPrerequisite("cs1010"); // Test case insensitivity
        mod.addPrerequisite("CS1010");

        assertEquals(1, mod.getPrerequisites().size());
    }

    @Test
    public void toFileFormat_validMod_returnsCorrectString() {
        Mod mod = new Mod("CS2113", 2, 1, 4);
        mod.addPrerequisite("CS1010");
        // Manual string building to verify format
        String expected = "0 | CS2113 | 2 | 1 | 4 | NORMAL | CS1010";
        assertEquals(expected, mod.toFileFormat());
    }

    @Test
    public void parsePrerequisites_hyphen_returnsEmptyList() {
        ArrayList<String> result = Mod.parsePrerequisites("-");
        assertTrue(result.isEmpty());
    }
}