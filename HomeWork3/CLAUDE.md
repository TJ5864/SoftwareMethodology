# CS 213 Spring 2026 – Project 3

**Due:** Monday, March 23, 11:59 pm
**Points:** 120 total (100 code + 15 test spec + 5 Javadoc)

## Project Goal

Revamp the command-line `FrontEnd` from Project 2 into a JavaFX GUI using the **MVC design pattern**. The software's functionality does not change — only the interface.

## Required Deliverables (zip entire project folder)

| Item | Points |
|---|---|
| All Project 2 source files (EXCEPT `Frontend.java`, `RunProject2.java`, JUnit tests) | — |
| `Launcher.java` – contains `main()` | — |
| `Main.java` – contains `start()` / GUI loading | — |
| `Controller.java` – contains all event handlers | — |
| `view.fxml` – scene graph / GUI layout | — |
| Test Specification (≥15 test cases, table template) | 15 pts |
| Javadoc folder (navigable via `index.html`) | 5 pts |

## MVC Structure

```
Model   →  packages: project2 + util  (all non-excluded Project 2 classes)
View    →  view.fxml                  (ONE fxml file only)
Controller → Controller.java          (ONE controller only)
Launcher + Main → launch/start entry points
```

## GUI Requirements

- **Required JavaFX components** (lose 3 pts each if missing):
  - `TextField`, `Button`, `RadioButton`, `TextArea`, `TabPane`, `GridPane`
- Must set a window **title** on the Stage (lose 2 pts if missing)
- **NO** `System.out` or `System.in` anywhere — all I/O through GUI (lose 3 pts/violation, max 10)
- Do NOT use only a TextField+Button to take command lines — you get 0 pts

## Coding Rules

1. Follow the Coding Standard (Modules/Week #1 on Canvas)
2. Repository must be **private** (academic integrity)
3. One public class per `.java` file (lose 2 pts per violation)
4. Do NOT import or call `Frontend` class directly in `Controller.java` (lose 10 pts)
5. Fix any Project 2 bugs — same test cases are used for grading

## Javadoc Rules

- Comment all constructors + private/public methods in every `.java` file
- **Skip:** `Main.java`, `Launcher.java` (no Javadoc needed)
- **Must comment:** `Controller.java`
- Do NOT include `.fxml` in Javadoc generation
- Generate into a single folder; graders navigate via `index.html`

## Functional Testing

- Design ≥15 test cases using the table template from the Coding Standard
- Use `Project2TestCases.txt` as a reference
- GUI must reject invalid data with proper error messages (lose 2 pts each missed)
- Handle ALL exceptions; software must never crash (lose 2 pts per unhandled exception)

## Project 3 Source Files

```
src/
  project2/         ← Model (all Project 2 classes except excluded ones)
    Student.java
    Resident.java
    NonResident.java
    TriState.java
    International.java
    Profile.java
    Major.java
    Standing.java
    StudentList.java
    Schedule.java
    Section.java
    Course.java
    Classroom.java
    Instructor.java
    Time.java
  util/             ← Model utilities
    Date.java
    List.java
    Sort.java
  project2/         ← MVC entry + controller
    Launcher.java
    Main.java
    Controller.java
    view.fxml
```

## JavaFX Setup (IntelliJ)

1. Download JavaFX SDK from https://gluonhq.com/products/javafx/ (match your JDK version)
2. In IntelliJ: **File → Project Structure → Libraries → + → Java** → select JavaFX `lib/` folder
3. Add VM options to run config: `--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml`
4. The `Launcher.java` workaround is needed because IntelliJ may not recognize `Application` subclass as a main class directly

## Commands Mapped to GUI (from Project 2 FrontEnd)

| Command | Action |
|---|---|
| AR/AN/AT/AI | Add Resident/NonResident/TriState/International student |
| R | Remove student |
| O | Offer (add) a course section |
| C | Close a course section |
| E | Enroll student in section |
| D | Drop student from section |
| L | Load students from file |
| S | Set scholarship |
| PS | Print student list |
| PL | Print schedule by classroom |
| PC | Print schedule by course |
| PT | Print tuition |
| PG | Print graduating students |
