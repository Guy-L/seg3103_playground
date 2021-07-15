# Lab 6

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
* [2 — Writing a Selenium Test](#2--writing-a-selenium-test)  
<br><br><br>

### System & Environment

**OS**: Windows 10<br>
**Java**: 1.8.0_291 (Java 8)<br>
**JUnit**: 5 (via Standalone)<br>
**Maven**: 3.8.1
**GeckoDriver**: 0.29.1 (unused)
**Firefox**: 89.0 (unused)
**Chrome**: 91.0 

<br><br><br>

### 1 — Running Things

<details>
<summary>Click to expand!</summary>

Let's go through the environment setup as described by the lab.<br>
First of all, I installed Maven 3.8.1:

![Setup, maven version](assets/mvn_version.png)

Let's then compile the project: 

![Setup, compilation](assets/mvn_compile.png)

Then we package the application: 

![Setup, packaging](assets/mvn_package.png)

Finally, let's now run our newly packaged application: 

![Setup, running app](assets/project_run.png)

And on the browser, we see:

![Server error](assets/serv_error.png)

... Huh. Like [many others](https://piazza.com/class/knxg0zgsce5jp?cid=350), I wasn't succesful in running the packaged application as-is. I could run the base `bookstore5.jar` which was provided, but as [another student](https://piazza.com/class/knxg0zgsce5jp?cid=358) indicated, we are not meant to do this. Using the `.inheritIO()` fix, I see this after recompiling:

![Setup, running app again](assets/project_run2.png)

![Server running](assets/serv_run.png)

The server is now properly running.<br>
Finally, I run `mvn test`: 

![Setup, testing](assets/mvn_test.png)

All tests ran successfully.<br>
Note that since my machine was a bit too slow, I had to run `mvn test` *while* running the server in another terminal - the test program did not finish creating a server quickly enough. Even when doing this, tests would not pass when using the Firefox driver, so despite it being my preferred browser, I will be using Chrome. 

</details>

<br><br><br>

### 2 — Writing a Selenium Test

<details>
<summary>Click to expand!</summary>

blah
</details>
