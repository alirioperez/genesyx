
package com.seabware.domain.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.seabware.framework.domain.model.AbstractBaseEntity;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// Generated class. Don't modify it as you can lose your changes in the build process.
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
@Entity
public abstract class PersonBaseGen extends AbstractBaseEntity
{

	// ----------
	// ATTRIBUTES
	// ----------

	private String firstName;
	private String lastName;

	// ------------------------------------
	// GETTERS & SETTERS FOR REGULAR FIELDS
	// ------------------------------------

    @Column(length=255)
	public String getFirstName()
	{
		return this.firstName;
	}	

	public void setFirstName(String firstName)
	{    
		this.firstName = firstName;
	}

    @Column(length=255)
	public String getLastName()
	{
		return this.lastName;
	}	

	public void setLastName(String lastName)
	{    
		this.lastName = lastName;
	}


    // ----------------------------------
    // GETTERS & SETTERS FOR ASSOCIATIONS
    // ----------------------------------

}