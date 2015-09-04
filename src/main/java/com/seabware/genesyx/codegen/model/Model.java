package com.seabware.genesyx.codegen.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/**
 * This is the root of a xxx-entities.xml file
 * 
 * @author aperez
 */
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
@XmlRootElement(name="model")
public class Model extends AbstractBaseElement
{
	private List<Entity> mEntities;

	// --------------------------------------------------------------------------------------------------------------------------
	@XmlElement(name="entity")
	public List<Entity> getEntityList()
	{
		return mEntities;
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public void setEntityList(List<Entity> entityList)
	{
		this.mEntities = entityList;
	}
}
