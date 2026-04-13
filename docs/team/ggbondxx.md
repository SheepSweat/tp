# Project Portfolio Page - GGbondxX

## Overview
ModTrack is a command-line module management application designed to help users track their academic progress.

My main focus was establishing the foundational architecture for command execution, ensuring data persistence through the storage layer, and building robust testing frameworks for the CLI interface.

## Summary of Contributions

### Code Contributed
- Code Dashboard link: *https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=GGBondxX&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=*

### Enhancements Implemented
- Designed and implemented the abstract `Command` class and its concrete implementations, specifically `AddCommand` and `DeleteCommand`.
- Utilized the Command Design Pattern to decouple the `Parser` from the `Model`, encapsulating execution logic within individual commands that interact with the `ArrayList<Mod>`.
- Established an architecture that adheres to the Open-Closed Principle, allowing the team to easily add new features by extending the `Command` class.
- Implemented duplicate detection logic within commands to maintain data integrity and prevent redundant entries.

### Bug Fixes and Technical Improvements
- Developed the `Storage` component to handle all data persistence operations (reading and writing to the hard disk).
- Engineered a custom file-formatting protocol using a pipe-separated syntax (e.g., `Status | Name | Year | Sem...`) to serialize `Mod` objects into plain text.
- Implemented automated directory and file creation logic (e.g., ensuring `/data/modtrack.txt` is generated if missing).
- Hardened the storage loading process using defensive programming techniques to gracefully handle corrupted data and prevent application crashes.
- Authored comprehensive JUnit 5 test suites covering the `Parser`, `Mod`, and `Command` classes.
- Utilized `ByteArrayOutputStream` to capture and verify console-based feedback (such as "Module already exists" warnings), ensuring accurate UI outputs in the CLI environment.

### Contributions to User Guide
- Wrote and refined documentation for the core features:
    - `add`
    - `delete`
    - `clear`
- Documented the prefix-based syntax (`n/`, `y/`, `s/`) with clear explanations to help users navigate the CLI effectively.

### Contributions to Developer Guide
- Drafted the "Design" sections detailing the Command and Storage components.
- Documented the architectural flow detailing how user input is processed, executed, and ultimately persisted to the hard disk.
- Drafted the description for features like add, delete, clear, mark, unmark and exit.
- Tasked to do most of the sequence diagrams.

**UML diagrams contributed:**

**UML diagrams contributed:**

| Diagram | Type |
|---|---|
| `addCommandDiagram.png` | Sequence |
| `AddPreReqSequenceDiagram.png` | Sequence |
| `architectureDiagram.png` | Architecture |
| `ClearCommandDiagramNew.png` | Sequence |
| `CommandClassDiagram.png` | Class |
| `deleteCommandDiagram.png` | Sequence |
| `ExemptCommandDiagramNew.png` | Sequence |
| `ExitSequenceDiagarm.png` | Sequence |
| `FindCommandDiagram.png` | Sequence |
| `ListCompareSequenceDiagram.png` | Sequence |
| `ListSequenceDiagram.png` | Sequence |
| `MarkCommandSequenceDiagram.png` | Sequence |
| `ShowGradReqSequenceDiagram.png` | Sequence |
| `ShowPreReqSequenceDiagram.png` | Sequence |
| `storageClassDiagram.png` | Class |
| `TransferSequenceDiagram.png` | Sequence |
