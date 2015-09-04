package com.seabware.genesyx.codegen;

import java.util.ArrayList;
import java.util.List;

import com.seabware.genesyx.codegen.model.Model;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/**
* This is the big guy, a singleton holding all entities and its relationships.
* This singleton will be used for generating the Hibernate annotated POJO classes, spring files and services
* 
* @author aperez
*/
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
public class DomainSingleton
{
	private static DomainSingleton INSTANCE;

    private List<Model> mModels;
	
	// ---------------------------------------------------------------------------------------------------------------------------
	private DomainSingleton()
	{
		mModels = new ArrayList<>();
	}

    // ---------------------------------------------------------------------------------------------------------------------------
    public static DomainSingleton getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new DomainSingleton();
		}
		
		return INSTANCE;
			
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public List<Model> getModels()
	{
		return mModels;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public void setModels(List<Model> models)
	{
		this.mModels = models;
	}
}
