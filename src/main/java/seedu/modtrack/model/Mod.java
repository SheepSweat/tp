package seedu.modtrack.model;

public class Mod {
    protected String modName;
    protected int year;
    protected int semester;
    protected int modCredits;
    protected boolean isComplete;

    public Mod(String name, int year, int semester, int credits) {
        this.modName = name;
        this.year = year;
        this.semester = semester;
        this.modCredits = credits;
    }

    public void setToDone() {
        this.isComplete = true;
    }

    public void setToUndone() {
        this.isComplete = false;
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

    public String toString() {
        String modString = "";
        modString += "Name: " + this.modName + "\n";
        modString += "Year: YEAR" + this.year + "\n";
        modString += "Semester: SEM" + this.semester + "\n";
        modString += "Status: " + (this.isComplete ? "Completed" : "Incomplete") + "\n";
        modString += "Modular Credits: " + this.modCredits + "\n";
        return modString;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public String toFileFormat() {
        return (this.isComplete ? "1 | " : "0 | ")
                + this.modName + " | "
                + this.year + " | "
                + this.semester + " | "
                + this.modCredits;
    }
}
