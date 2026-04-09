### **GGbondxX - Project Portfolio Page**

## **Overview**

I am a Year 2 Computer Engineering student at NUS, and this portfolio highlights my technical contributions to **ModTrack**, a CLI-based module management application. My primary focus was establishing the foundational architecture for command execution and ensuring data persistence through the storage layer.

---

## **Summary of Contributions**

### **1. Command Pattern Architecture & Logic**
* **What it is:** I designed and implemented the abstract `Command` class and its concrete implementations, specifically `AddCommand` and `DeleteCommand`.
* **Technical Detail:** I utilized the **Command Design Pattern** to decouple the `Parser` from the `Model`. Each command encapsulates its own execution logic, interacting with the `ArrayList<Mod>` to update the state.
* **Impact:** This architecture allows the team to add new features by simply extending the `Command` class, adhering to the **Open-Closed Principle**. I also implemented duplicate detection logic to ensure data integrity.

### **2. Data Persistence (Storage Class)**
* **What it is:** I developed the `Storage` component responsible for reading and writing data to the hard disk.
* **Technical Detail:** I created a custom file-formatting protocol using a pipe-separated syntax (e.g., `Status | Name | Year | Sem...`) to serialize `Mod` objects into plain text.
* **Robustness:** The class includes automated directory and file creation (e.g., `/data/modtrack.txt`) and handles corrupted data during the loading process using defensive programming techniques to prevent application crashes.

### **3. Quality Assurance & Testing**
* I authored comprehensive **JUnit 5** test suites for the `Parser`, `Mod`, and `Command` classes.
* I utilized `ByteArrayOutputStream` to verify that console-based feedback (like the "Module already exists" hint) was correctly displayed to the user, ensuring a seamless UX for the CLI interface.

---

## **Contributions to Documentation**

* **Developer Guide:** I drafted the "Design" sections for the **Command** and **Storage** components, including UML class diagrams and sequence diagrams to illustrate the flow from user input to data persistence.
* **User Guide:** I authored the documentation for the `add` and `delete` features, specifically explaining the prefix-based syntax (`n/`, `y/`, `s/`) to help users navigate the CLI effectively.