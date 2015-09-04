package com.seabware.genesyx.codegen.model;

import javax.xml.namespace.QName;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/**
 * Constants used for representing all XML attributes in the domain.
 *
 * @author aperez
 */
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
public interface AttributeConstants
{
	interface Model
	{
		static final QName PACKAGE = QName.valueOf("package"); 
	}

    interface Entity
    {
        static final QName NAME = QName.valueOf("name");
    }

    interface Attribute
    {
        static final QName NAME = QName.valueOf("name");
        static final QName TYPE = QName.valueOf("type");
        static final QName LENGTH = QName.valueOf("length");
    }
}
