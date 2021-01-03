package de.tum.ei.lkn.eces.commons;

public interface TestConfig extends CommonConfig {
	/* Allows to test the "Key" annotation. The other method
	 * do not use it, thereby testing if it also works without. */
	@Key("ready")
	@DefaultValue(value = "false")
	boolean isReady();

	@DefaultValue(value = "lkn")
	String name();

	@DefaultValue(value = "25")
	int age();

	@Key("probability")
	@DefaultValue(value = "0.2017")
	double probability();

	@DefaultValue(value = "This will never be overwritten")
	String definition();
}
