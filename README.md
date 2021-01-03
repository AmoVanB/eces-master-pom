## Master POM

The project defines the configuration from which all projects using the ECES framework should inherit.
More details about the ECES framework can be found in the [ECES core repository](https://github.com/AmoVanB/eces-core).

### Usage

A project willing to use the ECES framework must use this master pom as parent pom.
This can be done by adding the following tag in the `pom.xml` file of the project:

```xml
	<parent>
		<groupId>de.tum.ei.lkn.eces</groupId>
		<artifactId>master-pom</artifactId>
		<version>X.Y.Z</version>
	</parent>
```

Additionally, the project must define a Java template file (under `src/main/java-templates/package/name/`) with the following content:

```java
public final class Version {
	public static final String VERSION = "${project.version}";
	public static final String GROUPID = "${project.groupId}";
	public static final String ARTIFACTID = "${project.artifactId}";
	public static final String FQID = GROUPID + "." + ARTIFACTID;
}
```

See other ECES repositories, e.g., the [ECES core repository](https://github.com/AmoVanB/eces-core), for examples.

### `pre-master-pom`

POM configuration which does not require the `commons` module.

This includes:
- _Java 8_.
- _JAR plugin_.
- _`Version.java` generation plugin_.
  This plugin generates, from a `Version.java` template file, a `Version.java` containing the group ID, artifact ID and version of the compiled project.
- _Gitflow plugin_.
- _Configuration framework dependency_ (`org.aeonbits.owner`).
- _Logging framework dependency_ (`log4j`).
- _Testing framework dependency_ (`junit`).

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
