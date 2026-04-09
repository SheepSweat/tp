package seedu.modtrack.referencelist;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;

public class ReferenceList {

    public static ArrayList<Mod> list;

    public ReferenceList() {
        ReferenceList.list = new ArrayList<>();
    }

    public void populateReferenceList(ArrayList<Mod> list) {
        Mod newmod;
        newmod = new Mod("CS1010", 1, 1, 4);
        list.add(newmod);
        newmod = new Mod("CG1111A", 1, 1, 4);
        list.add(newmod);
        newmod = new Mod("MA1511", 1, 1, 2);
        list.add(newmod);
        newmod = new Mod("MA1512", 1, 1, 2);
        list.add(newmod);
        newmod = new Mod("CG2111A", 1, 2, 4);
        list.add(newmod);
        newmod = new Mod("MA1508E", 1, 2, 4);
        list.add(newmod);
        newmod = new Mod("EE2026", 2, 1, 4);
        list.add(newmod);
        newmod = new Mod("CS2040C", 2, 1, 4);
        list.add(newmod);
        newmod = new Mod("CS2107", 2, 1, 4);
        list.add(newmod);
        newmod = new Mod("CG2023", 2, 2, 4);
        list.add(newmod);
        newmod = new Mod("CS2113", 2, 2, 4);
        list.add(newmod);
        newmod = new Mod("CS1231", 2, 2, 4);
        list.add(newmod);
        newmod = new Mod("CG3201", 3, 2, 4);
        list.add(newmod);
        newmod = new Mod("CG2271", 3, 2, 4);
        list.add(newmod);
        newmod = new Mod("CG2027", 3, 2, 2);
        list.add(newmod);
        newmod = new Mod("CG2028", 3, 2, 2);
        list.add(newmod);
        newmod = new Mod("EE4204", 4, 1, 4);
        list.add(newmod);
        newmod = new Mod("CG3207", 4, 1, 4);
        list.add(newmod);
        newmod = new Mod("EG3611A/CP3880", 3, 1, 10);
        list.add(newmod);
        newmod = new Mod("CG4002/CG4001", 4, 2, 8);
        list.add(newmod);
        newmod = new Mod("EG1311", 1, 1, 4);
        list.add(newmod);
        newmod = new Mod("PF1101", 1, 2, 4);
        list.add(newmod);
        newmod = new Mod("DTK1234", 1, 2, 4);
        list.add(newmod);
        newmod = new Mod("GEA1000", 1, 2, 4);
        list.add(newmod);
        newmod = new Mod("ES2631", 2, 1, 4);
        list.add(newmod);
        newmod = new Mod("CDE2501", 3, 2, 4);
        list.add(newmod);
        newmod = new Mod("EE2211", 2, 2, 4);
        list.add(newmod);
        newmod = new Mod("EG2401A", 3, 1, 2);
        list.add(newmod);
    }

    public static ArrayList<Mod> getReferenceList() {
        return ReferenceList.list;
    }
}
