package de.tum.ei.lkn.commons.junitclasses;

/**
 * Dummy class for classifying tests.
 * Tests categorized as integration tests (with the
 * "@Category(IntegrationTest.class)" annotation) are only
 * run during integration (i.e., mvn integration-test, install, deploy).
 * This is configured in the master-pom.
 *
 * @author Jochen Guck
 * @author Amaury Van Bemten
 */
public interface IntegrationTest {
}
