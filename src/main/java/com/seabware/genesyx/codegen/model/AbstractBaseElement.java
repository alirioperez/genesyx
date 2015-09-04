package com.seabware.genesyx.codegen.model;

import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.namespace.QName;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/**
 * This is the base representation for all JAXB elements.
 *
 * @author aperez
 */
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
public abstract class AbstractBaseElement
{
	/* Holds all XML attributes for this element. */
	private Map<QName, String> mXmlAttributes;

    private String mDescription;

    // ---------------------------------------------------------------------------------------------------------------------------
    @XmlElement
    public String getDescription()
    {
        return mDescription;
    }

    // ---------------------------------------------------------------------------------------------------------------------------
    public void setDescription(String description)
    {
        this.mDescription = description;
    }
	// ---------------------------------------------------------------------------------------------------------------------------
	@XmlAnyAttribute
	public Map<QName, String> getXmlAttributes()
	{
		return mXmlAttributes;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public void setXmlAttributes(Map<QName, String> xmlAttributes)
	{
		this.mXmlAttributes = xmlAttributes;
	}

    // ---------------------------------------------------------------------------------------------------------------------------
    public String get(final String qname)
    {
        String result = null;

        if (getXmlAttributes() != null)
        {
            result = getXmlAttributes().get(new QName(qname));
        }

        return result;
    }

}