### **SheepSweat - Project Portfolio Page**

## **Overview**

I am a Year 2 Computer Engineering student at NUS, and this portfolio highlights my technical contributions to **ModTrack**, a CLI-based module management application. My primary focus was establishing the foundational architecture for command execution and ensuring data persistence through the storage layer.

---

## **Summary of Contributions**

### **1. Setting up project repository**
* **What it is:** I set up the project repo and ensure a sensible workflow among my team members
* **Impact:** This allows the team to work on their distinct areas and tasks independently, while ensuring version control.

### **2. List and List Compare Command**
* **What it is:** I implemented the command structures for the list commands
* **Technical Detail:** The ListCompareCommand functions as a cross-referencing engine that validates the user's local module list against a static "source of truth" (the ReferenceList). 
* **Robustness:** The commands separates the available modules into two separate list, completed and uncompleted to be printed out at the end to ensure no duplicates.

### **3. Quality Assurance & Testing**
* I authored comprehensive **JUnit** test suites for the `List`, `Mark`, `Unmark` and `Find` commands
* To maintain a high standard of code quality, I built a comprehensive testing suite that covers both Success and Negative paths.
* Utilized ByteArrayOutputStream to capture and verify console output, allowing for automated verification of UI requirements.
* Added assertions in command execution to ensure expected behaviours. 
---

## **Contributions to Documentation**

* **Developer Guide:**
  - I authored the implementation details for the `list`, `find`, and `exempt` commands, detailing the 
  logic behind the cross-referencing engine used to compare user modules against graduation requirements.  
  - I documented a comprehensive guide for manual testing, providing specific test cases to ensure future developers can 
  verify edge cases. 
  - I analyzed and documented alternative design considerations, evaluating the trade-offs between
  different data structures for search optimization.
* **User Guide:** 
  - I spearheaded the initial structure and layout of the User Guide, establishing a consistent documentation standard 
  for the team. 
  - I authored the Introduction and Quick Start sections to ensure a smooth onboarding experience for new users. 
  - I consolidated all features into a comprehensive Command Summary Table for quick reference 
  - I oversaw the final compilation of the Version 1.0 documentation, ensuring technical accuracy and clarity across all 
  command descriptions.

## **Overall Contribution**
I developed of core command logic while establishing the project’s JUnit 5 testing framework to ensure cross-platform 
stability and data integrity. I also led the documentation strategy, designing the User Guide’s structure and Quick Start 
guide, and authored the technical design specifications and manual testing protocols in the Developer Guide.
