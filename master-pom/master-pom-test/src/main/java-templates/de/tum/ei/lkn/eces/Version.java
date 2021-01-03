package de.tum.ei.lkn.eces;

import de.tum.ei.lkn.eces.commons.ModuleVersion;
/**
 * Version file automatically parsed by maven to contain the version, group
 * id and artifact id of the project.
 *
 * @author Jochen Guck
 * @author Amaury Van Bemten
 */
public final class Version implements ModuleVersion {
	public static final String VERSION = "${project.version}";
	public static final String GROUPID = "${project.groupId}";
	public static final String ARTIFACTID = "${project.artifactId}";
	public static final String FQID = GROUPID + "." + ARTIFACTID;

	@Override
	public String getGroupId() {
		return GROUPID;
	}
	@Override
	public String getVersion() {
		return VERSION;
	}
	@Override
	public String getArtifactId() {
		return ARTIFACTID;
	}
	@Override
	public String getFqid() {
		return GROUPID;
	}
}
