package de.objective_partner.m2e.jaxws.internal;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;

public class WsImportTestBuildParticipant extends MojoExecutionBuildParticipant {

	public WsImportTestBuildParticipant(MojoExecution execution) {
		super(execution, true, true);
	}

}
