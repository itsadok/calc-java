# A simple calculator with variable support

This is a somewhat over-engineered example of a math-expression parser implenmented from scratch. The project has no
dependencies other than JDK 1.8+.

## To use

```
$ git clone https://github.com/itsadok/calc-java
$ cd calc-java
$ ./gradlew test
$ ./gradlew jar
$ java -jar build/libs/calc-1.0-SNAPSHOT.jar <<END
	i = 0
	j = ++i
	x = i++ + 5
	y = 5 + 3 * 10
	i += y
END
(i=37,j=1,x=6,y=35)
```

You can also run it interactively, in a REPL-like environment:

```
$ java -jar build/libs/calc-1.0-SNAPSHOT.jar
>>> i = 0
(i=0)
>>> j = ++i
(i=1,j=1)
>>> 8 * j
8
(i=1,j=1)
```

You get it.

# Author

Israel Tsadok. Code license - [CC0](http://creativecommons.org/publicdomain/zero/1.0/).
