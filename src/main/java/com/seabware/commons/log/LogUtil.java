package com.seabware.commons.log;

import org.apache.log4j.*;

// -------------------------------------------------------------------------------------------------------------------------------
/**
 * A collection of log utilities.
 */
// -------------------------------------------------------------------------------------------------------------------------------
public final class LogUtil
{
	// ---------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds a console appender if no appenders are found in the root logger.
	 */
	// ---------------------------------------------------------------------------------------------------------------------------
	public static void checkAndAddBasicLog4jAppender()
	{
		// If we have no root log4j appender, it means we're run from the console (dev, tests, ...).
		// Just add a simple console-based appender with the proper debug flag.
		Logger rootLogger = Logger.getRootLogger();
		if (!rootLogger.getAllAppenders().hasMoreElements())
		{
			rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d [%p] %m%n"), ConsoleAppender.SYSTEM_OUT));
			rootLogger.setLevel(Level.INFO);
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	/**
	 * Takes a list of name=value contexts and returns a comma separated string.
	 * 
	 * @param context The list of context strings.
	 * @return The corresponding  comma separated string.
	 */
	// ---------------------------------------------------------------------------------------------------------------------------
	public static String contextToString(String[] context)
	{
		if (context == null)
			return "";
		
		StringBuilder buff = new StringBuilder();
		for (String c : context)
		{
			if (buff.length() != 0)
				buff.append(", ");
			buff.append(c);
		}
		
		return buff.toString();
	}
}