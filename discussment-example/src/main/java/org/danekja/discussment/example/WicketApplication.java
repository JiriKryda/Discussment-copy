package org.danekja.discussment.example;

import org.danekja.discussment.example.page.dashboard.DashboardPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 */
public class WicketApplication extends WebApplication
{
	/**
	 * Constructor
	 */
	public WicketApplication()
	{
	}

	public Class getHomePage()
	{
		return DashboardPage.class;
	}


}
