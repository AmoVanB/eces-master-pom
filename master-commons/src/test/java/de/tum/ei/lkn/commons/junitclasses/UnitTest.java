package de.tum.ei.lkn.commons.junitclasses;

/**
 * Dummy class for classifying tests.
 * Tests categorized as unit tests (with the
 * "@Category(UnitTest.class)" annotation or those without annotations)
 * are run during unit testing and deployment (i.e., mvn test, install, deploy).
 * This is configured in the master-pom.
 *
 * @author Jochen Guck
 * @author Amaury Van Bemten
 */
public interface UnitTest {
}
