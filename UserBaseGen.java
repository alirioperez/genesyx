
package com.seabware.domain.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.seabware.framework.domain.model.AbstractBaseEntity;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// Generated class. Don't modify it as you can lose your changes in the build process.
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
@Entity
public abstract class UserBaseGen extends AbstractBaseEntity
{

	// ----------
	// ATTRIBUTES
	// ----------

	private String username;
	private String password;
	private User supervisor;
    private Set<Department> departments = new HashSet<Department>;
    private User profiles;
    private UnitOfMeasureEnum unitOfMeasure;

	// ------------------------------------
	// GETTERS & SETTERS FOR REGULAR FIELDS
	// ------------------------------------

    @NotNull
    @Column(length=255)
	public String getUsername()
	{
		return this.username;
	}	

	public void setUsername(String username)
	{    
		this.username = username;
	}

    @NotNull
    @Column(length=255)
	public String getPassword()
	{
		return this.password;
	}	

	public void setPassword(String password)
	{    
		this.password = password;
	}

	public User getSupervisor()
	{
		return this.supervisor;
	}	

	public void setSupervisor(User supervisor)
	{    
		this.supervisor = supervisor;
	}


    // ----------------------------------
    // GETTERS & SETTERS FOR ASSOCIATIONS
    // ----------------------------------

    @NotNull
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="department_id")
    public Department getDepartments()
	{
		return this.departments;
	}

	public void setDepartments(Department departments)
	{
		this.departments = departments;
	}

    @NotNull
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public User getProfiles()
	{
		return this.profiles;
	}

	public void setProfiles(User profiles)
	{
		this.profiles = profiles;
	}

    @Enumerated(EnumType.STRING)
    public UnitOfMeasureEnum getUnitOfMeasure()
	{
		return this.unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasureEnum unitOfMeasure)
	{
		this.unitOfMeasure = unitOfMeasure;
	}

}