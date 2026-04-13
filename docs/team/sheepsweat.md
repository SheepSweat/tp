### **SheepSweat - Project Portfolio Page**

## **Overview**

ModTrack is a CLI (Command Line Interface) application designed for NUS students to track their modular requirements. 
It is optimized for users who prefer typing over graphical interfaces, built using a Logic-heavy Java architecture.

---

## **Summary of Contributions**

### **1. Setting up project repository**
* **Infrastructure:** Established the centralized GitHub repository and configured the build automation using Gradle.
* **Workflow:** Implemented a branching strategy and protected master branch rules to ensure that the 
team could develop features (Add, Delete, List) concurrently without merge conflicts.
* **Impact:** Provided a stable environment that allowed the team to scale from a basic skeleton to a full-featured application within a single semester.

### **2. Cross-Referencing Engine (List & ListCompare)**
* **Feature:** Engineered the logic for ListCommand and ListCompareCommand.
* **Technical Detail:** Developed a comparison algorithm that validates a user’s current ModuleList against a static 
ReferenceList (the "Source of Truth" for graduation requirements).
  * **Dynamic Categorization of UEs:** Implemented logic to recognize modules that do not exist in the static 
  requirement list. The system automatically classifies them as Unrestricted Electives (UEs),
  ensuring a comprehensive view of a student's academic progress.
* **Logic Separation:** Implemented categorization logic that dynamically filters modules into Completed, Uncompleted, 
and UE lists. This prevents data duplication and provides a clean, bifurcated UI output for better readability.

### **3. Software Quality Assurance (JUnit 5 & Assertions)**
* **Automated Testing:** Authored comprehensive test suites for List, Mark, Unmark, and Find.
* **Defensive Programming:** Integrated Java Assertions within the command execution flow to catch logic errors during 
development (e.g., ensuring ModuleList is never null before a search).
* **I/O Testing:** Implemented a specialized testing harness using ByteArrayOutputStream to capture and verify console outputs. 
This ensured that the UI remained consistent across different OS environments.
* **Edge Case Coverage:** Designed "Negative Path" tests to gracefully handle invalid user inputs, such as incorrect module formats or out-of-bounds indices. 

### **4. Bug Fixes & Logic Refinement**
* **Dependency Integrity Logic:** Engineered a multi-stage validation system for the prereq command to prevent illogical academic structures. 
  * **Circular Dependency Detection:** Implemented a cross-module lookup to block mutual dependencies ($A \leftrightarrow B$), ensuring the graduation path remains valid.
  * **Self-Dependency Filtering:** Integrated a Parser-level check to prevent a module from being its own prerequisite.
  * **Non-Breaking Execution:** Utilized continue flow-control to allow valid prerequisites to be processed even when invalid ones were detected in the same command string.
* **Parser Robustness & Sanitization:** Hardened the input handling to eliminate edge-case failures and "trailing word" bugs.
  * **Strict Argument Enforcement:** Configured the Parser to strictly reject orphaned arguments for commands like clear and exit, preventing unintended state changes.
* **State-Aware UI Synchronization:** Refined the command-to-UI communication to ensure the user is never misinformed about the application's internal state.
  * Dynamic Feedback: Adjusted the execution flow of the AddPrereqCommand to distinguish between a "Successful Update" and a "Current State View" based on whether new data was actually added or blocked by validation logic.

---

## **Contributions to Documentation**

* **Developer Guide:**
  * **Logic Mapping:** Authored the implementation section for the list, find, and exempt commands. Included Sequence Diagrams 
  to illustrate how the Parser interacts with the Command and Storage classes.
  * **Design Trade-offs:** Documented the rationale for choosing specific data structures for 
  module storage, balancing memory efficiency against search speed.
  * **Manual Test Script:** Created a rigorous manual testing protocol for features that are difficult to automate, ensuring 100% feature coverage for the release.
* **User Guide:**
  * Standardization: Designed the visual layout and Markdown structure of the UG to ensure professional consistency.
  * Onboarding: Wrote the "Quick Start" and "Introduction" sections, translating technical jargon into user-friendly instructions.
  * Command Reference: Compiled the "Command Summary Table," which became the primary troubleshooting resource.

## **Overall Contribution**
I developed of core command logic while establishing the project’s JUnit 5 testing framework to ensure cross-platform 
stability and data integrity. I also led the documentation strategy, designing the User Guide’s structure and Quick Start 
guide, and authored the technical design specifications and manual testing protocols in the Developer Guide.
