# CS2113 T09-1 tp Developer Guide

## Acknowledgements

* **AddressBook-Level 3 (AB3):** The architectural design, Developer Guide structure, and certain Command pattern implementations were adapted from the [AddressBook-Level 3 project](https://se-education.org/addressbook-level3/) created by the SE-EDU initiative.
* **Individual Projects (iP):** The core CLI parsing logic and the task-handling structures were adapted from the team members' individual projects which served as a foundation for the Command and Parser classes in ModTrack.

## Table of Contents
1. [Setup Guide](#setup-guide)

2. [Design](#design)
    - [UI component](#ui-component)
    - [Command component](#command-component)
    - [Storage component](#storage-component)
    - [Parser component](#parser-component)

3. [Implementation](#implementation)
        - [Add feature](#1-add-feature)
        - [Delete feature](#2-delete-feature)
        - [List feature](#3-list-feature)
        - [Mark feature](#4-mark-feature)
        - [Unmark feature](#5-unmark-feature)
        - [Exit feature](#6-exit-feature)
        - [Show Graduation Requirement feature](#7-show-graduation-requirement-feature)

4. [Product Scope](#product-scope)
    - [Target User Profile](#target-user-profile)
    - [Value Proposition](#value-proposition)

5. [User Stories](#user-stories)

6. [Non-Functional Requirements](#non-functional-requirements)

7. [Glossary](#glossary)

## Setup Guide

### Steps

1. Download the modtrack.jar file from release v2.0 into the folder where you plan to run the application

2. Go to project folder where the jar file is located

3. Run the application using java
```
java -jar modtrack.jar
```

## Design

### Architecture Overview

The Architecture Diagram below provides a high-level overview of the ModTrack application.

![img_10.png](architectureDiagram.png)

The application consists of five main components:
* **`ModTrack`**: The main controller that manages the execution loop and component orchestration.
* **`UI`**: Handles the user interface, including reading input and displaying messages.
* **`Logic`**: Consists of the `Parser` and various `Command` classes to process user requests.
* **`Model`**: Holds the in-memory data, primarily an `ArrayList<Mod>` and the `ReferenceList`.
* **`Storage`**: Manages reading from and writing to the `ModTrack.txt` data file.

### Component Initialization

The **ModTrack** class (`Modtrack.java`) is responsible for launching the application and managing the lifecycle of each component.

**At program start:**
1. **Component Initialization**: `ModTrack` initializes the `UI`, `ReferenceList`, `Parser`, and `Storage`.
2. **Reference Data Loading**: It calls `ReferenceList#populateReferenceList()` to load static module data.
3. **Data Retrieval**: It calls `Storage#load()` to populate the `taskList` (the Model) with existing data from the disk.
4. **Main Loop**: It enters a `while` loop that continues until an `ExitCommand` is executed.

**During execution:**
For every user input, `ModTrack` coordinates the following sequence:
1. It passes the raw input to the **`Parser`** to generate a **`Command`**.
2. It calls the **`Command#execute()`** method to update the **`Model`**.
3. It calls **`Storage#save()`** immediately after execution to ensure data persistence.
4. It uses the **`UI`** to display the outcome of the command to the user.

### UI Component

![Structure of the UI Component](images/UiComponentDiagram.png)

**Figure X. Structure of the UI Component**

The `Ui` component is responsible for handling all user interactions in ModTrack via the Command Line Interface (CLI).

ModTrack uses a **text-based UI**, where all interactions are displayed through standard output (`System.out`).

The `Ui` component,

* displays messages to the user (e.g., welcome message, errors, confirmations).
* formats and prints module-related information (e.g., lists, prerequisites, comparisons).
* acts as the final output stage after command execution.
* does **not** handle any business logic or data manipulation.

#### Structure

The `Ui` component consists of a single main class:

- `Ui`
  - Contains methods responsible for displaying specific outputs.
  - Examples:
    - `showAddModule(...)`
    - `showDeletedModule(...)`
    - `showList(...)`
    - `showPrerequisites(...)`
    - `showComparedList(...)`

Each method is designed to handle a **single responsibility**, ensuring modularity and ease of maintenance.

#### How the UI interacts with other components

The following sequence diagram shows how the `Ui` component interacts with other components when a user executes the `add` command.

![Sequence diagram of the UI Component](images/UiSequenceDiagram.png)

### Command Component
The Command mechanism is facilitated by the abstract `Command` class. It serves as the base for all executable actions within **ModTrack**, allowing the `Parser` to delegate logic to specific command objects.

Class Diagram:
![img_10.png](  CommandClassDiagram.png)

The abstract `Command` class defines a core method: `execute(ArrayList<Mod> list)`. Concrete subclasses implement this method to perform specific operations on the module list.

While the system includes several commands (such as `MarkCommand`, `ListCommand`, and `ExitCommand`), the following classes represent the primary data-manipulation logic:

**Code Snippet: Abstract Command Structure**
```java
public abstract class Command {
    protected boolean isExit = false;
    public abstract void execute(ArrayList<Mod> list);
    public boolean isExit() { return this.isExit; }
}
```
#### Design Considerations

**Aspect: How commands interact with the module list**

* **Alternative 1 (Current implementation):** Commands receive the `ArrayList<Mod>` directly in the `execute` method.
    * **Pros:** Simple to implement and maintain for the current project scope.
    * **Cons:** High coupling between commands and the specific data structure used.
* **Alternative 2:** Use a dedicated `Model` manager class to encapsulate list operations.
    * **Pros:** Lower coupling and better separation of concerns.
    * **Cons:** Increases complexity and the number of classes, which may be unnecessary for a CLI-based tracker.

**Aspect: Data Validation**

* **Approach:** Concrete commands handle their own internal validation. For instance, `AddCommand` prevents duplicate entries by checking the existing list, while `DeleteCommand` provides feedback if a module is not found.
* **Reasoning:** This ensures that the "business rules" for a specific action are contained within the class responsible for that action, leading to high functional cohesion.

### Storage Component

Class Diagram:
![img_10.png](storageClassDiagram.png)

The `Storage` component,

* can save and load module tracking data in a pipe-delimited text format, and read them back into corresponding `Mod` objects.
* handles the initialization of the local data directory and file (`./data/ModTrack.txt`) automatically upon startup.
* depends on classes in the `Model` component (because the `Storage` component's job is to save/retrieve `Mod` objects that belong to the `Model`).

### Parser Component

The Parser component is responsible for interpreting user input and converting it into executable commands.

It performs the following steps:
1. Reads raw user input
2. Identifies the command keyword (e.g., `add`, `delete`, `list`)
3. Extracts relevant arguments
4. Constructs the corresponding `Command` object

For example:
- Input: `add n/CS2113 y/Year2 s/Sem1`
- Output: `AddCommand` object with parsed parameters

The Parser ensures that invalid inputs are handled gracefully by throwing appropriate exceptions.

Class Diagram:
![img_10.png](ParserClassDiagram.png)



## Implementation
#### 1. Add Feature
The **`AddCommand`** facilitates the addition of new modules to the application by storing details such as `name`, `year`, `semester`, and `credits`.

**Implementation**

> If the command fails its execution (e.g., a duplicate module is found), the `AddCommand` returns early. Consequently, the list remains unchanged and the `ModTrack` main loop will skip the save process or save the unmodified state, ensuring no invalid data is persisted.

**Example:**

```text
add n/CS2113 y/2 s/1 c/4
```

##### Parsing Logic

The `Parser` identifies the `add` keyword and extracts:

* the module name from the `n/` prefix
* the academic year from the `y/` prefix
* the semester from the `s/` prefix
* the modular credits from the `c/` prefix

The extracted values are used to construct an `AddCommand` object, which is then passed to the main execution loop.

##### Class Interaction

* `Parser` constructs the `AddCommand`
* `AddCommand` validates the input against the existing `ArrayList<Mod>`
* `Mod` represents the newly created module object
* `Storage` persists the updated module list after successful execution

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* duplicate module codes
* invalid or missing command arguments rejected by the parser
* failed addition does not modify the tracked list

##### Current Limitation

The current implementation relies on linear search to detect duplicate module codes. This is acceptable for the current project scope, but may become less efficient if the module list grows significantly.

##### Cross-Feature Interaction

The add feature provides the base module objects used by all other commands. Features such as `mark`, `unmark`, `transfer`, `addprereq`, and `showprereq` depend on the module having already been added to the tracked list.



The following sequence diagram shows how an add operation goes through the `Logic` component:
![img_10.png](addCommandDiagram.png)

---

#### 2. Delete Feature
The **`DeleteCommand`** allows for the removal of a module from the list using a `modName` string.

**Implementation**

The deletion mechanism is managed by the **Logic** component and persisted via the **Storage** component. Upon execution, the `DeleteCommand` iterates through the `ArrayList<Mod>` to find the matching module. If a match is found and removed, the main loop in `ModTrack` immediately calls `Storage#save()` to overwrite the data file with the updated list, ensuring the change is persisted to the disk.

**Example:**

```text
delete n/CS2113
```

##### Parsing Logic

The `Parser` identifies the `delete` keyword and extracts the module name from the `n/` prefix.

A `DeleteCommand` object is then created and passed to the execution loop.

##### Class Interaction

* `Parser` constructs the `DeleteCommand`
* `DeleteCommand` searches through the `ArrayList<Mod>`
* the matching `Mod` object is removed from the list if found
* `Storage` persists the updated module list after execution

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* deletion of a non-existent module
* failed deletion leaves the tracked list unchanged
* parser rejects malformed input before execution

##### Current Limitation

The current implementation removes modules solely by module code and does not check whether other features, such as prerequisite tracking, still reference the deleted module as a prerequisite string.

##### Cross-Feature Interaction

Deleting a module may affect commands such as `showprereq`, `transfer`, `mark`, and `unmark`, because those commands rely on the module continuing to exist in the tracked list. If a deleted module was previously referenced elsewhere as a prerequisite, that reference remains as plain string data.


The following sequence diagram shows how a delete operation goes through the `Logic` component:
![deleteCommandDiagram.png](deleteCommandDiagram.png)

#### 3. Clear Feature
The **`ClearCommand`** allows for the removal of all modules from the tracker, effectively resetting the user's data list.

**Implementation**

The clear mechanism is managed by the **Logic** component and involves a two-step verification process to prevent accidental data loss:

1. **Confirmation Prompt**: Upon execution, `ClearCommand` interacts with the `UI` to display a warning and request explicit user confirmation (e.g., typing 'yes').
2. **Execution Gate**:
    * If the user **declines** or provides invalid confirmation, the command terminates early, and the module list remains unchanged.
    * If the user **confirms**, the command calls the `clear()` method on the `ArrayList<Mod>`, removing all tracked modules from memory.

**Persistence**

Following a successful (confirmed) execution, the main loop in `ModTrack` calls `Storage#save()` to overwrite the data file with an empty list. If the operation was cancelled during the confirmation step, the save call effectively persists the original, unmodified list, ensuring no data is lost.

> [!IMPORTANT]
> This operation is irreversible once confirmed and saved, as the existing `ModTrack.txt` file is overwritten with a blank state.

**Example:**

```text
clear
```

##### Parsing Logic
The `Parser` identifies the `clear` keyword and constructs a `ClearCommand` object. No additional arguments or flags are required.

##### Class Interaction
* **`Parser`**: Constructs the `ClearCommand`.
* **`ClearCommand`**: Invokes `Ui#getClearConfirmation()` to halt execution until the user provides a "yes" input.
* **`ArrayList<Mod>`**: If confirmed, `clear()` is invoked on the list to remove all `Mod` objects.
* **`Storage`**: Overwrites the data file with the empty list state immediately after successful execution.

##### Guard Mechanism (New)
To mitigate the risk of accidental data loss, the `ClearCommand` implements a **blocking confirmation gate**. The logic checks for a case-insensitive "yes" string from the standard input. Any other input (including "no" or accidental keystrokes) will abort the command and keep the module list intact.

##### Edge Cases and Error Handling
* **Empty List**: Clearing an already empty list is allowed; the user is still prompted for confirmation, and the storage file is refreshed.
* **Aborted Confirmation**: If the user cancels the prompt, the system logs the cancellation and returns to the main execution loop without modifying the data file.

##### Cross-Feature Interaction
This command serves as a hard reset for the application state. After a successful `clear`, features like `list c/` (Graduation Comparison) will report all requirements as "Missing" until the `ReferenceList` is re-populated via new `add` commands.

The following sequence diagram shows how a clear operation goes through the system:
![img_4.png](ClearCommandDiagramNew.png)

#### 4. List Feature

User inputs `List` `List c/`

The List feature is executed by the `ListCommand.java` (`List`) or the `ListCompareCommand. java` (`List c/`) class.
It extends from the abstract class `Command` and overrides the `execute()` method.

**ListCommand implementation:**
The `execute()` method in the `ListCommand` class iterates through the list of modules tracked by the program and prints out
all modules currently tracked using the `toString()` method of the mod class.

**ListCompareCommand implementation:**
The `execute()` method in the `ListCompareCommand` class iterates through the list of modules tracked by the program
and compares it to a predefined list of all modules required to be completed by a computer engineering student. prints
output completed and uncompleted modules in 2 separate lists using the `toString()` method of the mod class.

#### Design Considerations:
* The list feature is implemented this way because we want to allow the user the ability to view their modules tracked
  as is or against the modules required to graduate.
* Under `ListCompareCommand` we compare the list of modules tracked to a predefined list, populated on start up
  to allow easy updates when there is a change in graduation requirements or for scaling the program to other majors.
* Two separate classes was chosen as we wanted to a streamlined command class where each command overrides the execute
  method in their respective command classes.
* Alternatives considered: Using a single command class and separating the executions by methods within the class.
  This was rejected as it will cause list to have a different structure from the other command classes causing confusion.

##### Parsing Logic

The `Parser` identifies the `list` keyword and determines whether the compare modifier `c/` is present:

* if absent, it constructs a `ListCommand`
* if present, it constructs a `ListCompareCommand`

##### Class Interaction

* `Parser` constructs either `ListCommand` or `ListCompareCommand`
* `ListCommand` reads from the `ArrayList<Mod>` and prints tracked modules
* `ListCompareCommand` reads from both the `ArrayList<Mod>` and `ReferenceList`
* `Mod` provides display-ready information through `toString()` and status-related methods

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* empty tracked module lists
* tracked modules that are completed, incomplete, or transferred
* comparison against predefined reference requirements

##### Current Limitation

The comparison feature depends on a predefined reference list and is currently specific to the configured degree requirements. Expanding to other programmes would require updating the reference data source.

##### Cross-Feature Interaction

The list features reflect the effects of commands such as `add`, `delete`, `mark`, `unmark`, and `transfer`. In particular, transferred modules appear as completed for requirement comparison purposes.


#### Sequence Diagram
`List` command Sequence Diagram
![img.png](ListSequenceDiagram.png)

`List c/` command Sequence Diagram 
![img.png](ListCompareSequenceDiagram.png)

#### 5. Find Feature

The FindCommand allows users to search for modules within their tracked list using a keyword. 
The search is case-insensitive and matches any module whose name contains the provided keyword.

**Implementation**
The `execute()` method in the `FindCommand` class iterates through the list of modules tracked by the program,
printing any module that contains the given module name.If no matches are found, a "No matching module found" message 
is displayed to the user.

#### Design Considerations:
* The find feature implements a linear search method of searching for the given module.
* Pros: Very simple to implement and maintain. It is perfectly efficient for a student's module list (typically < 100 modules).
* Cons:  If the list were to grow to thousands of modules, $O(n)$ time complexity might become noticeable.
* Alternative considered: Using a HashMap for $O(1)$ lookups.
* Reason for skipping: Given that the module list is less than 100 modules for the CEG curriculum, using a hash map 
serves no considerable benefit and would needlessly complicate the code hence more prone to implementation bugs.
#### Sequence Diagram
![img_4.png](FindCommandDiagram.png)

#### 6 Exempt Feature
The ExemptCommand allows users to mark a specific module as Exempted. This is typically used for modules where the 
student has received a waiver or credit transfer, meaning the module is considered "cleared" without a traditional 
grade or progress tracking.

**Implementation**

The command performs a linear search through the taskList using the provided modName. The search is case-insensitive to 
improve user experience.Once a matching module is found, the command calls mod.setToExempted(). This method 
(internal to the Mod class) typically sets the module's status to a special "Exempted" state. To optimize performance, 
the method uses a return statement immediately after finding and updating the module, preventing unnecessary iterations.
If the loop completes without finding a match, the command provides feedback to the user stating that the module was not found.

#### Design Considerations:
* The design choice was made to encapsulate the "Exemption" logic within the Mod class rather than the ExemptCommand.
* This ensures that when a module is exempted, all related data (like completion status or modular credits) is updated 
consistently in one place, preventing "divergent change" bugs where the command forgets to update a specific flag.
#### Sequence Diagram
![img.png](ExemptCommandDiagramNew.png)

#### 7. Mark Feature

The **`MarkCommand`** allows the user to mark a tracked module as completed by specifying its module code.

**Implementation**

The marking mechanism is facilitated by the `MarkCommand` class. When a user executes the `mark` command, the `execute()` method iterates through the tracked module list to find a module whose name matches the provided `modName`.

If a matching module is found:
- its completion status is updated using `Mod#setToDone()`
- a confirmation message is printed to the user

If no matching module is found, the system informs the user that the module does not exist in the current tracked list.

This feature is important because it allows users to update their academic progress after completing a module.

**Example:**
```text
mark n/CS2113
```

##### Parsing Logic

The `Parser` identifies the `mark` keyword and extracts the module name from the `n/` prefix.

A `MarkCommand` object is then created and passed to the execution loop.

##### Class Interaction

* `Parser` constructs the `MarkCommand`
* `MarkCommand` searches through the `ArrayList<Mod>`
* `Mod` updates its completion status through `setToDone()`
* `Storage` persists the updated module list after execution

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* non-existent module names
* repeated marking of an already completed module
* parser rejects malformed user input before execution

##### Current Limitation

The current implementation uses linear search to locate the module. It also does not enforce prerequisite completion before a module can be marked as done.

##### Cross-Feature Interaction

The mark feature directly affects `list c/`, because completed modules count toward graduation requirement comparison. It also interacts conceptually with `showprereq`, as users may use prerequisite information before deciding to mark a module completed.

##### Design Considerations

**Aspect: How modules are identified for marking**

* **Alternative 1 (Current implementation):** Identify the module by its module code (`modName`) using a linear search through the list.
    * **Pros:** Simple and easy to understand for the current scope of the project.
    * **Cons:** Less efficient for very large module lists.
* **Alternative 2:** Use an index-based marking system (e.g. `mark 3`) or a `HashMap<String, Mod>` for faster lookup.
    * **Pros:** Potentially faster lookup and simpler internal retrieval.
    * **Cons:** Less intuitive for users, and would require additional synchronization between displayed indices and stored data.

**Reasoning:**  
The current implementation was chosen because module codes such as `CS2113` are already unique and meaningful to the user, making command usage more natural in a CLI-based academic tracker.

##### Sequence Diagram
![img.png](MarkCommandSequenceDiagram.png)

The sequence diagram above shows how the `mark` command is handled:
1. The user enters the `mark` command
2. The `Parser` creates a `MarkCommand`
3. `MarkCommand` iterates through the tracked module list
4. If a matching module is found, its completion status is updated
5. The updated list is saved through the `Storage` component

---

#### 8. Unmark Feature

The **`UnmarkCommand`** allows the user to reverse a previously completed module and set it back to incomplete.

**Implementation**

The unmarking mechanism is facilitated by the `UnmarkCommand` class. It follows a similar implementation pattern to `MarkCommand`.

When a user executes the `unmark` command:
- the system searches the module list for a module matching the provided `modName`
- if found, the module’s completion status is reset using `Mod#setToUndone()`
- the user is informed that the module has been marked as incomplete again

This feature is useful when users make accidental updates or wish to revise their academic planning.

**Example:**
```text
unmark n/CS2113
```

##### Parsing Logic

The `Parser` identifies the `unmark` keyword and extracts the module name from the `n/` prefix.

An `UnmarkCommand` object is then created and passed to the execution loop.

##### Class Interaction

* `Parser` constructs the `UnmarkCommand`
* `UnmarkCommand` searches through the `ArrayList<Mod>`
* `Mod` updates its completion status through `setToUndone()`
* `Storage` persists the updated module list after execution

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* non-existent module names
* repeated unmarking of a module already marked incomplete
* parser rejects malformed input before execution

##### Current Limitation

The current implementation does not differentiate between reversing a standard completion and reversing a transferred status unless the underlying `Mod` methods explicitly do so.

##### Cross-Feature Interaction

The unmark feature directly affects `list c/` by removing the module from the set of completed requirements. It also interacts with `transfer`, since both features update completion-related state and should remain logically consistent in the `Mod` class.

##### Design Considerations

**Aspect: Whether to merge mark and unmark into one command**

* **Alternative 1 (Current implementation):** Implement `mark` and `unmark` as two separate command classes.
    * **Pros:** Clear separation of responsibilities and more intuitive command structure.
    * **Cons:** Slight code duplication due to similar search logic.
* **Alternative 2:** Use a single generic status command such as `status n/CS2113 s/incomplete`.
    * **Pros:** More extensible for future status types.
    * **Cons:** Adds unnecessary parsing complexity and makes the command less user-friendly.

**Reasoning:**  
The current implementation was chosen to keep user interactions simple and explicit. Since the application is intended for fast CLI usage, direct commands such as `mark` and `unmark` are easier for users to remember and use.

##### Sequence Diagram
![img.png](UnmarkCommandSequenceDiagram.png)

The sequence diagram above shows how the `unmark` command is handled:
1. The user enters the `unmark` command
2. The `Parser` creates an `UnmarkCommand`
3. `UnmarkCommand` iterates through the tracked module list
4. If a matching module is found, its completion status is reset
5. The updated list is saved through the `Storage` component

#### 9. Exit Feature

The exit mechanism is facilitated by the `ExitCommand` class, which supports both `exit` and `bye` as trigger keywords.

**How it works:**
- **Keyword Support**: The `Parser` recognizes both `exit` and `bye` aliases, instantiating an `ExitCommand` for either input.
- **Graceful Termination**: When executed, the command sets a boolean flag (`isExit = true`) which is checked by the main execution loop in `ModTrack`.
- **Data Integrity**: Any pending module data is automatically saved by the `Storage` component before the application process ends.

**Example:**
```text
exit
```
**or**
```text
bye
```
##### Sequence Diagram
![img.png](ExitSequenceDiagarm.png)

The sequence diagram above illustrates the termination flow:
1. The user enters either the `exit` or `bye` command.
2. The `Parser` identifies the alias and returns an `ExitCommand` object.
3. The `ModTrack` controller calls `execute()` on the command.
4. The `ModTrack` main loop detects the `isExit` flag is true.
5. `ModTrack` triggers the final `Storage#save()` call.
6. The `UI` displays a farewell message, and the loop terminates.

#### 10. Show Graduation Requirement Feature
This feature displays the graduation requirements tracked by the system.

**How it works:**
- Retrieves stored module data
- Compares against graduation criteria
- Displays remaining requirements to the user

**Example:**
```
show grad req
```

##### Parsing Logic

The `Parser` identifies the graduation requirement command keyword and constructs the corresponding command object.

##### Class Interaction

* `Parser` constructs the graduation requirement command
* the command reads from the `ArrayList<Mod>`
* the command compares tracked modules against `ReferenceList`
* output is printed to the user based on matched and unmatched requirements

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* empty tracked module lists
* partially completed requirement sets
* transferred modules contributing toward completed requirements when treated as completed internally

##### Current Limitation

The displayed graduation requirement logic is tied to the predefined requirement structure and may need refactoring if support for multiple degree programmes is added.

##### Cross-Feature Interaction

This feature depends heavily on `add`, `delete`, `mark`, `unmark`, and `transfer`, because all of those commands affect whether a module appears as fulfilled or outstanding in the requirement view.

#### Sequence Diagram
![img.png](ShowGradReqSequenceDiagram.png)

The sequence diagram illustrates how graduation requirements are displayed:
1. The user enters the show grad req command.
2. The Parser creates a ShowGradReqCommand.
3. The command executes and delegates directly to ui.showGradReq().
4. The UI retrieves and displays the graduation requirements to the user.
5. The task list is not modified, and the main application continues its normal save flow.

#### 11. Add Prerequisite Feature

The **`AddPrereqCommand`** allows the user to add one or more prerequisite modules to an existing tracked module.

**Implementation**

The prerequisite-adding mechanism is facilitated by the `AddPrereqCommand` class. When a user executes the `addprereq` command, the `execute()` method iterates through the module list to find a module whose name matches the provided `modName` (case-insensitive).

If a matching module is found:

* each prerequisite in the provided list is added to the module
* duplicate prerequisites are ignored to prevent redundancy

If no matching module is found:

* the system does not modify the list

This feature allows users to track module dependencies for better academic planning.

**Example:**

```text
addprereq n/CG2023 p/CG1111,CS1010
```

##### Parsing Logic

The `Parser` identifies the `addprereq` keyword and extracts:

* the module name from the `n/` prefix
* a list of prerequisite module codes from the `p/` prefix

The extracted values are used to construct an `AddPrereqCommand` object, which is then passed to the main execution loop.

##### Class Interaction

* `Parser` constructs the `AddPrereqCommand`
* `AddPrereqCommand` searches through the `ArrayList<Mod>`
* `Mod` handles prerequisite storage via its internal list
* after execution, the updated list is persisted by the `Storage` component

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* module names entered in lowercase or mixed case
* duplicate prerequisite insertion (ignored)
* non-existent module names (no modification made)
* empty prerequisite lists (no changes applied)

##### Current Limitation

The current implementation stores prerequisites as strings and does not verify whether each prerequisite module itself exists in the tracked module list or in the reference list.

##### Cross-Feature Interaction

The `addprereq` feature complements `showprereq` by supplying the prerequisite data later displayed to the user. It also interacts conceptually with `mark` and `transfer`, because prerequisite information may influence a user's decision to complete or transfer a module even though the current implementation does not enforce eligibility checks.

##### Sequence Diagram
![img.png](AddPreReqSequenceDiagram.png)

#### 12. Show Prerequisite Feature

The **`ShowPrereqCommand`** allows the user to view all prerequisites of a specified module.

**Implementation**

The display mechanism is facilitated by the `ShowPrereqCommand` class. When executed, the command searches the module list using case-insensitive matching.

If the module is found:

* the system prints `Prerequisites for <modName>:`
* if prerequisites exist, they are printed as a comma-separated list
* if no prerequisites exist, `None` is displayed

If no matching module is found:

* the system prints `Module not found.`

This feature helps users quickly check module requirements.

**Example:**

```text
showprereq n/CS2113
```

**Output:**

```text
Prerequisites for CS2113:
CS1010, CS2040C
```

##### Parsing Logic

The `Parser` identifies the `showprereq` keyword and extracts the module name from the `n/` prefix.

A `ShowPrereqCommand` object is then created and passed to the execution loop.

##### Class Interaction

* `Parser` constructs the `ShowPrereqCommand`
* `ShowPrereqCommand` searches through the `ArrayList<Mod>`
* `Mod` provides access to its stored prerequisite list
* Output is printed directly via the command (UI layer interaction)

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* case-insensitive module name matching
* modules with no prerequisites (prints `None`)
* non-existent module names (prints `Module not found.`)

##### Current Limitation

The current implementation only displays direct prerequisites and does not recursively display prerequisite chains.

##### Cross-Feature Interaction

This feature depends directly on `addprereq`, since prerequisite data must first be stored before it can be displayed. It also supports user decisions around `mark`, `transfer`, and semester planning by making dependency information visible.

##### Sequence Diagram
![img.png](ShowPreReqSequenceDiagram.png)

#### 13. Transfer Feature

The **`TransferCommand`** allows the user to mark a module as transferred, treating it as completed.

**Implementation**

The transfer mechanism is facilitated by the `TransferCommand` class. When executed, the command searches for a module using case-insensitive matching.

If the module is found:

* the module is marked as completed
* its completion type is set to `"TRANSFERRED"`
* its display status is updated to `"Transferred"`
* a confirmation message is printed

If no matching module is found:

* the system prints `Module not found.`
* no changes are made

This feature is useful for representing transferred or exempted modules.

**Example:**

```text
transfer n/CS2040C
```

**Output:**

```text
Module marked as transferred:
CS2040C
```

##### Parsing Logic

The `Parser` identifies the `transfer` keyword and extracts the module name from the `n/` prefix.

A `TransferCommand` object is then created and passed to the execution loop.

##### Class Interaction

* `Parser` constructs the `TransferCommand`
* `TransferCommand` searches through the `ArrayList<Mod>`
* `Mod` updates its internal state (`isComplete`, `completionType`, display status)
* `Storage` persists the updated module list after execution

##### Edge Cases and Error Handling

The feature currently handles the following cases:

* case-insensitive module name matching
* attempting to transfer a non-existent module (no state change)
* ensuring transferred modules are consistently marked as completed internally

##### Current Limitation

The current implementation treats transferred modules as completed, but does not store any additional metadata such as transfer source, approval status, or institution of origin.

##### Cross-Feature Interaction

The transfer feature directly affects `list c/` and graduation requirement display, because transferred modules count as completed. It also interacts with prerequisite-related planning, because a transferred prerequisite module may satisfy a dependency even though it was not completed locally.

##### Sequence Diagram
![img.png](TransferSequenceDiagram.png)

## Product scope
### Target user profile

The target user for **ModTrack** is a **National University of Singapore (NUS) Computer Engineering student** who:
* prefers a **Command Line Interface (CLI)** over a Graphical User Interface (GUI) for speed and efficiency.
* manages a complex curriculum involving both School of Computing and Faculty of Engineering requirements.
* needs to track technical electives, core modules, and breadth requirements across multiple semesters.
* is comfortable with terminal-based workflows and seeks a lightweight tool for academic planning.

### Value proposition

**ModTrack** solves the problem of navigating the complex graduation requirements of the Computer Engineering degree. While official university portals are useful for formal registration, they can be cumbersome for quick "what-if" planning or tracking progress toward a degree.

This application provides:
* **Requirement Tracking:** A centralized view to see which specific graduation requirements (e.g., General Education, Core Modules, and Electives) have been fulfilled and which are still outstanding.
* **Academic Logging:** A historical record of modules completed, including year, semester, and credit details.
* **Efficiency:** Rapid data entry and retrieval using short, optimized commands specifically designed for busy engineering students.
* **Clarity:** Instant feedback on current progress, helping students ensure they are on track to graduate by their target date without needing to manually cross-reference various PDFs or websites.


## User Stories

| Version | As a ... | I want to ... | So that I can ... |
|--------|----------|---------------|------------------|
| v1.0 | new user | see usage instructions | refer to them when I forget how to use the application |
| v1.0 | user | add a module with its module code and title | record modules I have taken |
| v1.0 | user | delete a module entry | remove modules I added by mistake |
| v1.0 | user | mark a module as completed | track my academic progress |
| v1.0 | user | view all completed modules | know what I have fulfilled |
| v1.0 | user | view all uncompleted required modules | plan future semesters |
| v1.0 | user | view the total modular credits earned | track my graduation progress |
| v1.0 | user | view the remaining modular credits required | plan my remaining semesters |
| v1.0 | user | compare my completed modules with graduation requirements | see unmet requirement |
| v1.0 | user | assign a module to a specific semester | track when I took it |
| v1.0 | user | mark a module as uncompleted | correct mistakes in my progress tracking |
||
| v2.0 | user | exempt a module | reflect credit exemptions (e.g. APC) in my graduation progress |
| v2.0 | user | transfer a module | record modules taken from SEP or other institutions |
| v2.0 | user | add prerequisites to a module | track dependencies between modules |
| v2.0 | user | view prerequisites of a module | plan my module schedule more effectively |
| v2.0 | user | compare my module list against graduation requirements | identify missing modules more accurately |
| v2.0 | user | clear all my data | reset my tracker when starting fresh |
| v2.0 | user | exit the application safely | ensure my data is saved before closing |


## Non-Functional Requirements

1. The application should run on any system with Java installed
2. The application should respond within 1 second for typical commands
3. Data should be persisted across sessions
4. The system should handle invalid input gracefully
5. The application should be usable entirely via CLI

## Glossary

* *Module* - A course taken by a student
* *Command* - An executable instruction entered by the user
* *Parser* - Component that interprets user input

## Instructions for manual testing

1. Initial Launch and Setup

Clear existing data: If you have used the app before, delete the data.txt file (or the directory specified in your Storage settings) to start with a clean state.

Launch the app: Run the program.

Expected Result: The UI should display a welcome message and indicate that a new data file has been created.

2. Loading Sample Data

Close the app.

Open the data.txt file in a text editor.

Paste the following sample lines (adjust the format to match your specific toFileFormat logic):

0 | CS1010 | 1 | 1 | 4 | NORMAL | -
0 | MA1511 | 1 | 1 | 2 | NORMAL | -
0 | CS2113 | 2 | 2 | 4 | NORMAL | -

Relaunch the app.

Expected Result: Run the list command; it should now display these three modules correctly.

3. Testing Core Commands

Find Functionality
(Test Case: find n/CS)

Expected Result: Displays CS1010 and CS2113.

Test Case: find non-existent

Expected Result: Displays "No matching module found."

Exemption Logic
(Test Case: exempt n/CS2113)

Expected Result: Confirmation message "Module marked as exempted: CS2113". Running list should show the status has changed.

Graduation Requirements
(Test Case: grad)

Expected Result: Displays the static list of CEG requirements as defined in the Ui class.

4. Data Persistence (Storage)

Add a new module: add n/MA1508E y/YEAR1 s/SEM2.

Exit the app using exit.

Re-open the app.

Expected Result: The module MA1508E should still be present in the list.
