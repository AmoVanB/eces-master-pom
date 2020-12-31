package de.tum.ei.lkn.commons;

import de.tum.ei.lkn.commons.junitclasses.UnitTest;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

@Category(UnitTest.class)
public class ConfigTest {
	/* Cannot test with a file in the configuration folder because no rights to write there.
	 * However, if these tests work, it should also be fine for this file. */
	private boolean propertiesFileInProcessDirectoryCreated = false;
	private boolean propertiesFileInHomeFolderCreated = false;
	private boolean xmlFileInProcessDirectoryCreated = false;
	private boolean xmlFileInHomeFolderCreated = false;

	@Test
	public void defaultValue() {
		TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
		assertTrue("If the file is found, should be defined", CONFIG.definition().equals("This will never be overwritten"));
	}

	@Test
	public void processDirectoryFirst() throws IOException {
		createPropertiesFileInProcessDirectory();
		createXMLFileInProcessDirectory();
		createPropertiesFileInHomeFolder();
		TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
		assertTrue("If the file is found, should be FIFA but is " + CONFIG.name(), CONFIG.name().equals("FIFA"));
		assertTrue("If the file is found, should be 10 but is " + CONFIG.age(), CONFIG.age() == 10);

		// Since we have a FIRST strategy, all other files should not be checked
		assertTrue("The probability should be the default value (0.2017), not " + CONFIG.probability(), CONFIG.probability() == 0.2017);
		assertTrue("Ready should be the default value (false)", !CONFIG.isReady());

		cleanCreatedFiles();
	}

	@Test
	public void homeFolderSecond() throws IOException {
		createPropertiesFileInHomeFolder();
		createXMLFileInHomeFolder();
		TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
		assertTrue("If the file is found, should be HOME but is " + CONFIG.name(), CONFIG.name().equals("HOME"));
		assertTrue("If the file is found, should be 98 but is " + CONFIG.age(), CONFIG.age() == 98);
		assertTrue("If the file is found, should be 0.1 but is " + CONFIG.probability(), CONFIG.probability() == 0.1);

		// Since we have a FIRST strategy, all other files should not be checked
		assertTrue("Ready should be the default value (false)", !CONFIG.isReady());

		cleanCreatedFiles();
	}

	@Test
	public void classPathThird() throws IOException {
		// Should never check the xml config
		TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
		assertTrue("If the file is found, should be TUM but is " + CONFIG.name(), CONFIG.name().equals("TUM"));
		assertTrue("If the file is found, should be 100 but is " + CONFIG.age(), CONFIG.age() == 100);
		assertTrue("If the file is found, should be true but is " + CONFIG.isReady(), CONFIG.isReady());
		assertTrue("If the file is found, should be 0.9 but is " + CONFIG.probability(), CONFIG.probability() == 0.9);

		cleanCreatedFiles();
	}

	@Test
	public void xmlProcessDirectoryFirst() throws IOException {
		createPropertiesFileInHomeFolder();
		createXMLFileInProcessDirectory();
		createXMLFileInHomeFolder();
		TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
		assertTrue("If the file is found, should be BELgium but is " + CONFIG.name(), CONFIG.name().equals("BELgium"));
		assertTrue("If the file is found, should be 1895 but is " + CONFIG.age(), CONFIG.age() == 1895);
		assertTrue("If the file is found, should be true but is " + CONFIG.isReady(), CONFIG.isReady());

		// Since we have a FIRST strategy, all other files should not be checked
		assertTrue("Ready should be the default value (0.2017)", CONFIG.probability() == 0.2017);
		cleanCreatedFiles();
	}

	@Test
	public void xmlHomeFolderSecond() throws IOException {
		createXMLFileInHomeFolder();
		TestConfig CONFIG = ConfigFactory.create(TestConfig.class);
		assertTrue("If the file is found, should be Freddie but is " + CONFIG.name(), CONFIG.name().equals("Freddie"));
		assertTrue("If the file is found, should be 70 but is " + CONFIG.age(), CONFIG.age() == 70);
		assertTrue("If the file is found, should be false but is " + CONFIG.isReady(), !CONFIG.isReady());

		// Since we have a FIRST strategy, all other files should not be checked
		assertTrue("Ready should be the default value (0.2017)", CONFIG.probability() == 0.2017);
		cleanCreatedFiles();
	}

	private void createPropertiesFileInProcessDirectory() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("lkn.properties", "UTF-8");
		writer.println("name=FIFA");
		writer.println("age=10");
		writer.close();

		propertiesFileInProcessDirectoryCreated = true;
	}

	private void createPropertiesFileInHomeFolder() throws FileNotFoundException, UnsupportedEncodingException {
		String configurationFileName = System.getProperty("user.home") + File.separator + ".lkn.properties";
		PrintWriter writer = new PrintWriter(configurationFileName, "UTF-8");
		writer.println("name=HOME");
		writer.println("age=98");
		writer.println("probability=0.1");
		writer.close();

		propertiesFileInHomeFolderCreated = true;
	}

	private void createXMLFileInProcessDirectory() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("lkn.config", "UTF-8");
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
		writer.println("<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">");
		writer.println("<properties>");
		writer.println("name=BELgium");
		writer.println("age=1895");
		writer.println("ready=true");
		writer.println("</properties>");
		writer.close();

		xmlFileInProcessDirectoryCreated = true;
	}

	private void createXMLFileInHomeFolder() throws FileNotFoundException, UnsupportedEncodingException {
		String configurationFileName = System.getProperty("user.home") + File.separator + ".lkn.config";
		PrintWriter writer = new PrintWriter(configurationFileName, "UTF-8");
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
		writer.println("<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">");
		writer.println("<properties>");
		writer.println("name=Freddie");
		writer.println("age=70");
		writer.println("ready=false");
		writer.println("</properties>");
		writer.close();

		xmlFileInHomeFolderCreated = true;
	}

	// Run after each test to clean the created files for the next tests.
	@After
	public void cleanCreatedFiles() throws IOException {
		if(propertiesFileInProcessDirectoryCreated)
			Files.delete(Paths.get("lkn.properties"));

		if(propertiesFileInHomeFolderCreated)
			Files.delete(Paths.get(System.getProperty("user.home") + File.separator + ".lkn.properties"));

		if(xmlFileInProcessDirectoryCreated)
			Files.delete(Paths.get("lkn.config"));

		if(xmlFileInHomeFolderCreated)
			Files.delete(Paths.get(System.getProperty("user.home") + File.separator + ".lkn.config"));

		propertiesFileInProcessDirectoryCreated = false;
		propertiesFileInHomeFolderCreated = false;
		xmlFileInProcessDirectoryCreated = false;
		xmlFileInHomeFolderCreated = false;
	}
}
