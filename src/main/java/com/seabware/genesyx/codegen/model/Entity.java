package com.seabware.genesyx.codegen.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/**
 * This is the JAXB representation of an Entity in the xml file.
 *
 *  Example:
 *
 *  <entity name="User" table="user" extends="Person">
 *      <description>User entity</description>
 *          <attributes>
 *              <attribute name="username" type="String" length="255" required="true"/>
 *              <attribute name="password" type="String" length="255" required="true" encrypted="true"/>
 *              <attribute name="supervisor" type="User" required="false"/>
 *          </attributes>
 *
 *          <associations>
 *              <association relationship="many-to-one" name="department" type="Department" column="department_id" not-null="true"/>
 *          </associations>
 *  </entity>
 *
 * @author aperez
 */
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
@XmlRootElement(name="entity")
public class Entity extends AbstractBaseElement
{
    private List<Attribute> mAttributes = new ArrayList<>();
    private List<Association> mAssociations = new ArrayList<>();

    // ---------------------------------------------------------------------------------------------------------------------------
    @XmlElementWrapper(name="attributes")
    @XmlElement(name="attribute")
    public List<Attribute> getAttributes()
    {
        return mAttributes;
    }

    // ---------------------------------------------------------------------------------------------------------------------------
    public void setAttributes(List<Attribute> attributes)
    {
        this.mAttributes = attributes;
    }

    // ---------------------------------------------------------------------------------------------------------------------------
    @XmlElementWrapper(name="associations")
    @XmlElement(name="association")
    public List<Association> getAssociations()
    {
        return mAssociations;
    }

    // ---------------------------------------------------------------------------------------------------------------------------
    public void setAssociations(List<Association> associations)
    {
        this.mAssociations = associations;
    }
}
