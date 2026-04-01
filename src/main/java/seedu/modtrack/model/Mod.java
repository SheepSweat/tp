package seedu.modtrack.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Mod {
    protected String modName;
    protected int year;
    protected int semester;
    protected int modCredits;
    protected boolean isComplete;

    protected int progressPercentage;
    protected String completionType; // NORMAL, EXEMPTED, TRANSFERRED
    protected ArrayList<String> prerequisites;

    public Mod(String name, int year, int semester, int credits) {
        this.modName = name;
        this.year = year;
        this.semester = semester;
        this.modCredits = credits;
        this.isComplete = false;
        this.progressPercentage = 0;
        this.completionType = "NORMAL";
        this.prerequisites = new ArrayList<>();
    }

    public void setToDone() {
        this.isComplete = true;
        this.progressPercentage = 100;
        this.completionType = "NORMAL";
    }

    public void setToUndone() {
        this.isComplete = false;
        this.progressPercentage = 0;
        this.completionType = "NORMAL";
    }

    public void setToExempted() {
        this.isComplete = true;
        this.progressPercentage = 100;
        this.completionType = "EXEMPTED";
    }

    public void setToTransferred() {
        this.isComplete = true;
        this.progressPercentage = 100;
        this.completionType = "TRANSFERRED";
    }

    public void setProgressPercentage(int percentage) {
        this.progressPercentage = percentage;

        if (this.completionType.equals("NORMAL")) {
            this.isComplete = (percentage == 100);
        }
    }

    public int getProgressPercentage() {
        return this.progressPercentage;
    }

    public String getCompletionType() {
        return this.completionType;
    }

    public void setCompletionType(String completionType) {
        this.completionType = completionType;
    }

    public void addPrerequisite(String prerequisite) {
        if (!this.prerequisites.contains(prerequisite.toUpperCase())) {
            this.prerequisites.add(prerequisite.toUpperCase());
        }
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public ArrayList<String> getPrerequisites() {
        return this.prerequisites;
    }

    public String getModName() {
        return this.modName;
    }

    public int getYear() {
        return this.year;
    }

    public int getSemester() {
        return this.semester;
    }

    public int getModCredits() {
        return this.modCredits;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public String getDisplayStatus() {
        if (this.completionType.equals("EXEMPTED")) {
            return "Exempted";
        }
        if (this.completionType.equals("TRANSFERRED")) {
            return "Transferred";
        }
        return this.isComplete ? "Completed" : "Incomplete";
    }

    @Override
    public String toString() {
        String modString = "";
        modString += "Name: " + this.modName + "\n";
        modString += "Year: YEAR" + this.year + "\n";
        modString += "Semester: SEM" + this.semester + "\n";
        modString += "Status: " + this.getDisplayStatus() + "\n";
        modString += "Progress: " + this.progressPercentage + "%\n";
        modString += "Modular Credits: " + this.modCredits + "\n";
        modString += "Prerequisites: "
                + (this.prerequisites.isEmpty() ? "None" : String.join(", ", this.prerequisites)) + "\n";
        return modString;
    }

    public String toFileFormat() {
        String prereqText = this.prerequisites.isEmpty() ? "-" : String.join(",", this.prerequisites);
        return (this.isComplete ? "1 | " : "0 | ")
                + this.modName + " | "
                + this.year + " | "
                + this.semester + " | "
                + this.modCredits + " | "
                + this.progressPercentage + " | "
                + this.completionType + " | "
                + prereqText;
    }

    public static ArrayList<String> parsePrerequisites(String prereqText) {
        ArrayList<String> result = new ArrayList<>();
        if (prereqText.equals("-") || prereqText.trim().isEmpty()) {
            return result;
        }
        result.addAll(Arrays.asList(prereqText.split(",")));
        return result;
    }
}
