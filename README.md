# Lab 1

| Outline | Value |
| --- | --- |
| Course | SEG 3X03 |
| Date | Summer 2021 |
| Professor | Andrew Forward, aforward@uottawa.ca |
| Team | Guillaume Labasse 300132984 |

## Deliverables

* [https://github.com/Guy-L/seg3103_playground](https://github.com/Guy-L/seg3103_playground)
* Shared repo above with TA and Professors

### Java

I am running Java 8:

```bash
$ java --version
java version "1.8.0_291"
Java(TM) SE Runtime Environment (build 1.8.0_291-b10)
Java HotSpot(TM) 64-Bit Server VM (build 25.291-b10, mixed mode)
```
![Java version](assets/java_version.png)

To run the Java program (in `newmath_java`), first I compile it

```bash
$ javac -d dist src/*.java
```

Then I run it

```bash
java -cp ./dist Main
```

Here is an output of the running program

```bash
Newmath (type 'exit' to exit program)
Numerator: 5040
Demoninator: 1008
5040 / 1008 = 5
Numerator: 284
Demoninator: 71
284 / 71 = 4
Numerator: exit
```

With a screenshot from the terminal

![Running Java in the console](assets/java_main.png)


### JUnit

I am working with JUnit 5 (via Console standalone 1.7.1)

To run JUnit, I need to compile the application (see above), and then compile the test code

```bash
javac -d dist -cp lib/junit-platform-console-standalone-1.7.1.jar src/*.java test/*.java
```

Then I run the tests using

```
java -jar lib/junit-platform-console-standalone-1.7.1.jar --class-path dist --scan-class-path
```

Here is the output of the tests

```bash
Thanks for using JUnit! Support its development at https://junit.org/sponsoring

←[36m.←[0m
←[36m+--←[0m ←[36mJUnit Jupiter←[0m ←[32m[OK]←[0m
←[36m| '--←[0m ←[36mNewmathTest←[0m ←[32m[OK]←[0m
←[36m|   +--←[0m ←[34mdiv_ok()←[0m ←[32m[OK]←[0m
←[36m|   '--←[0m ←[34mdiv_by_zero()←[0m ←[32m[OK]←[0m
←[36m'--←[0m ←[36mJUnit Vintage←[0m ←[32m[OK]←[0m

Test run finished after 54 ms
[         3 containers found      ]
[         0 containers skipped    ]
[         3 containers started    ]
[         0 containers aborted    ]
[         3 containers successful ]
[         0 containers failed     ]
[         2 tests found           ]
[         0 tests skipped         ]
[         2 tests started         ]
[         0 tests aborted         ]
[         2 tests successful      ]
[         0 tests failed          ]
```

With a screenshot from the terminal

![Running JUnit in the console](assets/junit_main.png)


### Elixir

I am running Elixir 12.0 with Erlang 22.3

```bash
$ elixir --version
Erlang/OTP 22 [erts-10.7] [64-bit] [smp:6:6] [ds:6:6:10] [async-threads:1]

Elixir 1.12.0 (compiled with Erlang/OTP 22)
```
![Elexir version](assets/elixir_version.png)

To run the Elixir program (in `newmath_ex`), first I compile it

```bash
$ mix compile
Compiling 1 file (.ex)
Generated newmath_ex app
```

Then I run it

```bash
$ iex -S mix
```

Here is an output of the running program

```elixir
iex(1)> NewmathEx.div(10, 2)
{:ok, 5.0}
iex(2)>
```

With a screenshot from the terminal

![Running Elixir in the console](assets/elixir_iex.png)

### ExUnit

ExUnit is built directly into Elixir (and compiled like above with `mix compile`)

To run the tests

```
mix test
```

Here is the output of the tests

```bash
Compiling 1 file (.ex)
...

Finished in 0.02 seconds
1 doctest, 2 tests, 0 failures

Randomized with seed 975082
```

With a screenshot from the terminal

![Running ExUnit in the console](assets/exunit_main.png)

