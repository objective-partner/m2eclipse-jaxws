package de.objective_partner.m2e.jaxws.internal;

import java.io.File;
import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
import org.codehaus.plexus.util.Scanner;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;
import org.sonatype.plexus.build.incremental.BuildContext;

public class WsGenBuildParticipant extends MojoExecutionBuildParticipant {

	public WsGenBuildParticipant(MojoExecution execution) {
		super(execution, true, true);
	}
	
	@Override
	public Set<IProject> build(int kind, IProgressMonitor monitor)
			throws Exception {
		final IMaven maven = MavenPlugin.getMaven();
		final BuildContext buildContext = getBuildContext();

		final File source = maven.getMojoParameterValue(getSession(),
				getMojoExecution(), "src/main/java", File.class);
		final Scanner ds = buildContext.newScanner(source);
		ds.scan();
		final String[] includedFiles = ds.getIncludedFiles();
		if (!appliesToBuildKind(kind)
				|| (includedFiles == null || includedFiles.length <= 0)) {
			return null;
		}

		final Set<IProject> result = super.build(kind, monitor);

		final File generatedSources = maven.getMojoParameterValue(getSession(),
				getMojoExecution(), "destDir", File.class);
		if (generatedSources != null) {
			buildContext.refresh(generatedSources);
		}

		final File generatedClasses = maven.getMojoParameterValue(getSession(),
				getMojoExecution(), "resourceDestDir", File.class);
		if (generatedClasses != null) {
			buildContext.refresh(generatedClasses);
		}

		final File generatedStaleFile = maven.getMojoParameterValue(
				getSession(), getMojoExecution(), "sourceDestDir", File.class);
		if (generatedStaleFile != null) {
			buildContext.refresh(generatedStaleFile);
		}

		return result;
	}

}
