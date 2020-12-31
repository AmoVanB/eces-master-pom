package de.tum.ei.lkn.commons.junitclasses;

/**
 * Dummy class for classifying tests.
 * Tests categorized as performance tests (with the
 * "@Category(PerformanceTest.class)" annotation) are only
 * run during integration (i.e., mvn integration-test, install, deploy).
 * This is configured in the master-pom.
 *
 * @author Jochen Guck
 * @author Amaury Van Bemten
 */
public interface PerformanceTest {
}
