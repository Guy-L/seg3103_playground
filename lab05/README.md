# Lab 5

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
* [2 — Running the Site & Stubs]()  

<br><br><br>

### System & Environment

**OS**: Windows 10<br>
**Java**: 1.8.0_291 (Java 8)<br>
**JUnit**: 5 (via Standalone)<br>
**Elexir**: 1.12.0 (with Erlang 22.3)

<br><br><br>

### 1 — Running Things

<details>
<summary>Click to expand!</summary>

<br>Let's first compile and run the tests for the provided programs.<br>
For `grades`:

``` bash
$ mix compile
$ mix test
```

![Compile test, grades](assets/grades_run.png)

I had a lot of difficulties figuring out how to do this next part as the slides gave no precise indications; I had to ask for help. For example, one of the libraries necessary to run these tests from the command line, Objenesis, was never mentioned (though it was included).
For `twitter`:

``` bash
$ javac -d dist -cp lib/easymock-4.3.jar;lib/junit-platform-console-standalone-1.7.1.jar src/*.java test/*.java
$ java -jar lib/junit-platform-console-standalone-1.7.1.jar --class-path "lib/easymock-4.3.jar;lib/objenesis-3.2.jar;dist" --scan-class-path
```

![Compile test, twitter](assets/twitter_run.png)

All tests ran successfully. 
</details>

<br><br><br>

### 2 — Running the Site & Stubs

After doing the setup as described by the slides, I run:

```bash
$ mix phx.server 
```

And I can see the site up on the port specified by `config/dev.exs`:

![Site running](assets/site_run.png)

As expected, clicking the Calculate button causes server-side errors due to undefined functions:

![Site errors](assets/site_error.png)

To fix these errors, I implement method stubs in [`calculator.ex`]():

![Stubs](assets/code_stubs.png)
![Running with stubs](assets/site_stubs.gif)

 
