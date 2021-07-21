# Lab 7

| Outline | Value |
| --- | --- |
| Course | SEG 3103 |
| Date | Summer 2021 |
| Professor | Andrew Forward, aforward@uottawa.ca |
| Team | Guillaume Labasse 300132984 |

### Table of Contents  
[System & Environment](#system--environment)  
Lab Proceedings:
* [1 — Running Things](#1--running-things)  
* [2 — Spotting Bugs](#2--spotting-bugs)  
* [3 — Fixing Bugs](#3--fixing-bugs)  
<br><br><br>

### System & Environment

**OS**: Windows 10<br>
**Java**: 1.8.0_261 (Java 8)<br>
**JUnit**: 5 (via Standalone)

<br><br><br>

### 1 — Running Things

<details>
<summary>Click to expand!</summary>

<br>Let's first compile and run the application:

```bash
$ javac -d dist src/*.java
$ java -cp ./dist Main
```

![Compiling & running](assets/comp_run.png)

The application runs correctly and appears mostly bug-free from playing around with it for a few minutes.
<br>I had to lower the width of the window for it to render the buttons correctly.
<br>Even though it wasn't asked for, let's also run the provided tests:

```bash
$ javac -d dist -cp lib/junit-platform-console-standalone-1.7.1.jar src/*.java test/*.java
$ java -jar lib/junit-platform-console-standalone-1.7.1.jar --class-path dist --scan-class-path
```

![Tests output](assets/tests.png)

All tests ran successfully.<br>
An [initial commit](https://github.com/Guy-L/seg3103_playground/commit/f307a19d274cdf63ad277619fcbe51cf5e5e1afe#diff-173996138866d41583e457187fb735b238d2eed897ac330f49b17c60e40b754c) has been made before any code changes, as requested.
</details>

<br><br><br>

### 2 — Spotting Bugs

<details>
<summary>Click to expand!</summary>

<br>TODO
</details>

<br><br><br>

### 3 — Fixing Bugs

<details>
<summary>Click to expand!</summary>

<br>TODO
</details>

