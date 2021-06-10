# Lab 3

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
* [2 — Chosen Project](#2--chosen-project)
* [3 — Highlights & Conclusion](#3--highlights--conclusion)  

<br><br><br>

### System & Environment

**OS**: Windows 10<br>
**Java**: 1.8.0_291 (Java 8)<br>
**JUnit**: 5 (via Standalone)<br>
**Eclipse**: 4.13.0<br>
**Elexir**: 1.12.0 (with Erlang 22.3)

<br><br><br>

### 1 — Running Things

<details>
<summary>Click to expand!</summary>

<br>Let's first compile and run the tests for the provided programs.<br>
For `fizzbuzz`:

``` bash
$ mix compile
$ mix test
```

![Compile test, fizzbuzz](assets/fizzbuzz_run.png)

For `tic`:

``` bash
$ mix compile
$ mix test
```

![Compile test, tic](assets/tic_run.png)

All tests ran successfully. 
</details>

<br><br><br>
### 2 — Chosen Project

<details>
<summary>Click to expand!</summary>

<br>I had trouble deciding which problem to do. I thought FizzBuzz was too simple to take an hour, and wasn't sure exactly what the requirements were for TicTacToe, so I came up with my own program idea. This allows me to set clear goals to work towards, and add additional goals if necessary. I hope this is okay!

The idea is to make a custom calculator which uses strange alternative arithmetic rules. These rules are not consistent throughout, we just want to meet our specifications. The specification are as follows:
	
- **Calculating the opposite of a number**: the opposite of a number in our system is its reverse in decimal notation, times -1 (e.g. 51 = -15, 307 = -703, -54 = 45).
	
- **Calculating the sum of two numbers**: the sum of two numbers is the (regular) sum of all primes between the two numbers, inclusively (e.g. 10+16 = 24 since 11 and 13 are between).
	
- **Calculating the max between two numbers**: the max between two numbers is the number which has the most divisors (e.g. max(30, 32) = 30)
	
- **Calculating the product of two positive numbers**: the product of two numbers is the square root their concatenation (e.g. 2✕5 = 5, 5✕2 = 7.2111...)
	
I'll take things step by step using the TDD method, and hopefully these requirements will be enough to do a full hour of work.<br>If not, this section will be edited to add some more goals.
</details>

<br><br><br>
### 3 — Highlights & Conclusion

<details>
<summary>Click to expand!</summary>

<br>TODO
</details>
