package de.objective_partner.m2e.jaxws.internal;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator;

public class JaxWsProjectConfigurator extends AbstractJavaProjectConfigurator {
	@Override
	public AbstractBuildParticipant getBuildParticipant(
			IMavenProjectFacade projectFacade, MojoExecution execution,
			IPluginExecutionMetadata executionMetadata) {
		if ("wsimport".equalsIgnoreCase(execution.getGoal())) {
			return new WsImportBuildParticipant(execution);
		} else if ("wsimport-test".equalsIgnoreCase(execution.getGoal())) {
			return new WsImportTestBuildParticipant(execution);
		} else if ("wsgen".equalsIgnoreCase(execution.getGoal())) {
			return new WsGenBuildParticipant(execution);
		} else if ("wsgen-test".equalsIgnoreCase(execution.getGoal())) {
			return new WsGenTestBuildParticipant(execution);
		} else {
			return null;
		}
	}
}
