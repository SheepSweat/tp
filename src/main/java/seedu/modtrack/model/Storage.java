package seedu.modtrack.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/ModTrack.txt";
    private static final String DIRECTORY_PATH = "./data/";

    public Storage() {
        this.prepareFile();
    }

    private void prepareFile() {
        try {
            File folder = new File(DIRECTORY_PATH);
            if (!folder.exists()) {
                folder.mkdir(); // Creates folder if missing
            }

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile(); // Creates file if missing
            }
        } catch (IOException e) {
            System.out.println("Error creating storage: " + e.getMessage());
        }
    }

    public void save(ArrayList<Mod> list) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Mod mod : list) {
            fw.write(mod.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }

    // public ArrayList<Mod> load() throws FileNotFoundException {
    //
    // File f = new File(FILE_PATH);
    // Scanner s = new Scanner(f);
    // ArrayList<Mod> list = new ArrayList<>();
    // String line, status, name, year, semester, credits;
    // String[] words;
    // while(s.hasNext()) {
    // Mod newMod = null;
    // line = s.nextLine();
    // words = line.split("\\|");
    // status = words[0].trim();
    // name = words[1].trim();
    // year = words[2].trim();
    // semester = words[3].trim();
    // credits = words[4].trim();
    //
    // newMod = new Mod(name, year, semester, credits);
    //
    // if (status.equals("1") && newMod != null) {
    // newMod.setToDone();
    // }
    // list.add(newMod);
    // }
    // return list;
    // }
}
