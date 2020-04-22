# UnitTestExample1

This is a basic Unit Test example for Java using JUnit 3.

It is expected to work in JDK 8 and above.



Here are some useful Maven commands, the generated files are in "target" folder.

```shell
# clean up generated files.
mvn clean

# run all unit tests.
mvn test

# build the release jar file.
mvn package

# clean up, then build and install this JAR into local Maven repository.
mvn clean install
```



To generate the unit testing coverage report.

```shell
# generate the covertura coverage report (target/site/cobertura/index.html).
mvn cobertura:cobertura

# generate the surefire report (target/site/surefire-report.html).
mvn surefire-report:report
```



To generate the project site, with above reports integrated.

```shell
# generate the site (target/site/index.html).
mvn site
```



----

Last modified by bobyuan on 2019-04-06. 

