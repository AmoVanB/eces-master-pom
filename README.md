## Master POM

The project defines the configuration from which all projects should inherit.

### `pre-master-pom`

POM configuration which does not require the `commons` module.

This includes:
- _Java 8_.
- _JAR plugin_.
- _`Version.java` generation plugin_. This plugin generates, from a `Version.java` template file (see the `project-template` project for an example), a `Version.java` containing the group ID, artifact ID and version of the compiled project.
- _Gitflow plugin_. See the `project-template` documentation for usage guidelines.
- _Configuration framework dependency_ (`org.aeonbits.owner`).
- _Logging framework dependency_ (`log4j`).
- _Testing framework dependency_ (`junit`).
- Configuration of the LKN repository/artifactory server (`http://maven.forschung.lkn.ei.tum.de:8081`).

### `commons`

Common Java code files for all the projects.

This includes:
- Configuration of the configuration framework (`CommonConfig.java`).
- JUnit classes for separating tests in unit, integration and performance tests. The `master-pom` then configures when each type of test should be run. 

### `master-pom`

POM configuration which requires the `commons` module.

This includes:
- _`commons` dependency_ for configuration of the tests and configuration frameworks.
- Configuration of when which type of test should be run.
