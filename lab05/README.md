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
* [2 — Running the Site & Stubs](2--running-the-site--stubs)  
* [3 — Using Assignment 2 Code & Observations](3--using-assignment-2-code--observations)  

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

<details>
<summary>Click to expand!</summary>

After doing the setup as described by the slides, I run:

```bash
$ mix phx.server 
```

And I can see the site up on the port specified by `config/dev.exs`:

![Site running](assets/site_run.png)

As expected, clicking the Calculate button causes server-side errors due to undefined functions:

![Site errors](assets/site_error.png)

To fix these errors, I implement method stubs in [`calculator.ex`](https://github.com/Guy-L/seg3103_playground/commit/c26195b25a8fa4a73ccff39f64c10f2f42fc4cda#diff-68c01fac5a076d8f3fe2835cdfd8c3f0299804dca4b6e4b3e0f51c7ef062376b):

![Stubs](assets/code_stubs.png)
![Running with stubs](assets/site_stubs.gif)
</details>

<br><br><br>

### 3 — Using Assignment 2 Code & Observations

<details>
<summary>Click to expand!</summary>

I then [substitute in](https://github.com/Guy-L/seg3103_playground/blob/98647f9a1106d71cc617d25e6cc8445f13530c92/lab05/grades/lib/grades/calculator.ex) my refactored Assignment 2 code:

![Assignment 2 code](assets/code_assignment.png)

After restarting the site, clicking the Calculate button yields a new error:

![Site error w/ assignment 2 code](assets/site_error_final.png)

More specifically, we see an ArithmeticError which occurs on this line:

```
	Enum.sum(list) / Enum.count(list)
```

It's hard to tell from the Elixir error syntax, but it seems the error specifically occurs when attempting to sum the provided list. From the stack trace, we can tell that this list is the Homework grades list, since its average is calculated on line 57. I conclude that there must be a fault with the code responsible for calling the calculator methods with the provided value, which either sends an empty list or an otherwise invalid one. We did not observe these errors when working with stubs as the input values were completely ignored (as warned by Elixir in the gif above).
</details>

<br><br><br>
 
