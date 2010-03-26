package com.inflectra.spirateam.mylyn.tests.internal;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class SpiraTeamTestsPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.inflectra.spirateam.mylyn.tests";

	// The shared instance
	private static SpiraTeamTestsPlugin plugin;
	
	/**
	 * The constructor
	 */
	public SpiraTeamTestsPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static SpiraTeamTestsPlugin getDefault() {
		return plugin;
	}

}
