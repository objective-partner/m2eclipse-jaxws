package de.objective_partner.m2e.jaxws.internal;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;

public class WsImportBuildParticipant extends MojoExecutionBuildParticipant {

	public WsImportBuildParticipant(MojoExecution execution) {
		super(execution, true, true);
	}

}
