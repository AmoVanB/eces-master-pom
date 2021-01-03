package de.tum.ei.lkn.eces.commons;

import org.aeonbits.owner.Config;

/**
 * Configuration interface to be used by all modules.
 * The interface defines the locations where to look
 * for the configuration file.
 *
 * @author Jochen Guck
 * @author Amaury Van Bemten
 */
@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources(value = {
		"file:lkn.properties", // 1. In the process directory
		"file:lkn.config",
		"file:~/.lkn.properties", // 2. In the home folder
		"file:~/.lkn.config",
		"file:/etc/lkn.properties", // 3. In the configuration folder
		"file:/etc/lkn.config",
		"classpath:lkn.properties", // 4. In the classpath
		"classpath:lkn.config",
})
public interface CommonConfig extends Config {
}
