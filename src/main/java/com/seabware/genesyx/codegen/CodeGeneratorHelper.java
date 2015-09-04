package com.seabware.genesyx.codegen;

public class CodeGeneratorHelper
{
	public static String firstToUpperCase(final String string)
	{
		final String post = string.substring(1, string.length());
		final String first = ("" + string.charAt(0)).toUpperCase();
		return first + post;
	}

    public static String toUpperCase(final String string)
    {
        return string.toUpperCase();
    }

    public static String removeLeadingChars(final String string, final int howMany)
    {
        return string.substring(howMany, string.length());
    }
}
