package seedu.modtrack.commands;

import java.util.ArrayList;

import seedu.modtrack.module.Mod;

public class ShowGradReqCommand extends Command {

    @Override
    public void execute(ArrayList<Mod> list) {
        System.out.println("===== Computer Engineering Graduation Requirements =====");
        System.out.println("Total MCs Required: 160");
        System.out.println("Core Modules:");
        System.out.println("- CS1010");
        System.out.println("- CG1111A");
        System.out.println("- MA1511");
        System.out.println("- MA1512");
        System.out.println("- CG2111A");
        System.out.println("- MA1508E");
        System.out.println("- EE2026");
        System.out.println("- CS2040C");
        System.out.println("- CS2107");
        System.out.println("- CG2023");
        System.out.println("- CS2113");
        System.out.println("- CS1231");
        System.out.println("- CG3201");
        System.out.println("- CG2271");
        System.out.println("- CG2027");
        System.out.println("- CG2028");
        System.out.println("- EE4204");
        System.out.println("- CG3207");
        System.out.println();
        System.out.println("Project/Internship:");
        System.out.println("- EG3611A/CP3880");
        System.out.println("- CG4002/CG4001");
        System.out.println();
        System.out.println("Engineering Common Curriculum:");
        System.out.println("- EG1311");
        System.out.println("- PF1101");
        System.out.println("- DTK1234");
        System.out.println("- GEA1000");
        System.out.println("- ES2631");
        System.out.println("- CDE2501");
        System.out.println("- EE2211");
        System.out.println("- EG2401A");
        System.out.println();
        System.out.println("General Education Curriculum:");
        System.out.println("- GENXXXX");
        System.out.println("- GECXXXX");
    }
}
