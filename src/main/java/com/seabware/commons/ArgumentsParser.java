package com.seabware.commons;

import java.util.TreeMap;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/**
 * Helper class to parse name=value name=value style command line arguments.
 */
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
public final class ArgumentsParser
{
	/** Our arguments map. */
	private final TreeMap<String, String> mArguments = new TreeMap<String, String>();

	// ---------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds the given parameter to this parser.
	 * 
	 * @param arg The argument to add. This has one of the following forms: 'name' or 'name=' or 'name=value'. The names are not case
	 *            sensitive.
	 */
	// ---------------------------------------------------------------------------------------------------------------------------
	public void add(String arg)
	{
		String name;
		String value;

		int pos = arg.indexOf("=");

		if (pos == -1)
		{
			name = arg;
			value = "";
		}
		else
		{
			name = arg.substring(0, pos);
			value = arg.substring(pos + 1);
		}

		mArguments.put(name.toUpperCase(), value);
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds the given parameter list to this parser.
	 * 
	 * @param args An array of strings to add. These have one of the following forms: 'name' or 'name=' or 'name=value'. The names are not
	 *            case sensitive.
	 */
	// ---------------------------------------------------------------------------------------------------------------------------
	public void add(String[] args)
	{
		for (int a = 0; a < args.length; ++a)
			add(args[a]);
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	/**
	 * Returns the argument for the given name. If the argument is not found a specified default is returned.
	 * 
	 * @param name The argument name to look for. Not case sensitive.
	 * @param def The default value to return if the given argument is not found.
	 * @return The value for the given argument name or the default value if it's not found.
	 */
	// ---------------------------------------------------------------------------------------------------------------------------
	public String get(String name, String def)
	{
		String found = mArguments.get(name.toUpperCase());

		if (found == null)
			found = def;

		return(found);
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	/**
	 * Returns the argument for the given name, but also check system properties and environment variabls. If the argument is not found a specified default is returned.
	 * The content of this command line argument parser are checked first, then the system properties, then the environment variables. Whichever has the value wins.
	 * 
	 * @param name The argument name to look for. Not case sensitive for command line arguments but case sensitive for system properties and environment variables.
	 * @param def The default value to return if the given argument is not found.
	 * @return The value for the given argument name or the default value if it's not found.
	 */
	// ---------------------------------------------------------------------------------------------------------------------------
	public String getEffectiveValue(String name, String def)
	{
		String ret;

		// Command line arguments always win.
		ret = get(name, null);
		if (ret != null)
			return ret;

		// Then the system properties (-Dxxx=xxx).
		ret = System.getProperty(name);
		if (ret != null)
			return ret;

		// Finally the environment variables.
		ret = System.getenv(name);
		if (ret != null)
			return ret;
		
		return def;
	}
}
