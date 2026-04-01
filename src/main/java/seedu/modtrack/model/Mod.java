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

        if (completionType.equals("NORMAL")) {
            this.isComplete = (percentage == 100);
        }
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public String getCompletionType() {
        return completionType;
    }

    public void setCompletionType(String completionType) {
        this.completionType = completionType;
    }

    public void addPrerequisite(String prerequisite) {
        if (!prerequisites.contains(prerequisite.toUpperCase())) {
            prerequisites.add(prerequisite.toUpperCase());
        }
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public String getModName() {
        return modName;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public int getModCredits() {
        return modCredits;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public String getDisplayStatus() {
        if (completionType.equals("EXEMPTED")) {
            return "Exempted";
        }
        if (completionType.equals("TRANSFERRED")) {
            return "Transferred";
        }
        return isComplete ? "Completed" : "Incomplete";
    }

    @Override
    public String toString() {
        String modString = "";
        modString += "Name: " + this.modName + "\n";
        modString += "Year: YEAR" + this.year + "\n";
        modString += "Semester: SEM" + this.semester + "\n";
        modString += "Status: " + getDisplayStatus() + "\n";
        modString += "Progress: " + this.progressPercentage + "%\n";
        modString += "Modular Credits: " + this.modCredits + "\n";
        modString += "Prerequisites: "
                + (prerequisites.isEmpty() ? "None" : String.join(", ", prerequisites)) + "\n";
        return modString;
    }

    public String toFileFormat() {
        String prereqText = prerequisites.isEmpty() ? "-" : String.join(",", prerequisites);
        return (isComplete ? "1 | " : "0 | ")
                + modName + " | "
                + year + " | "
                + semester + " | "
                + modCredits + " | "
                + progressPercentage + " | "
                + completionType + " | "
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