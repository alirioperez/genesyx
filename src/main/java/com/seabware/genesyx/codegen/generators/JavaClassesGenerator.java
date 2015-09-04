package com.seabware.genesyx.codegen.generators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.seabware.genesyx.codegen.CodeGeneratorHelper;
import com.seabware.genesyx.codegen.model.AttributeConstants;
import com.seabware.genesyx.codegen.model.Entity;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class JavaClassesGenerator implements ClassesGenerator
{
    /** This ClassesGenerator's _logger */
    private final static Logger mLogger = Logger.getLogger(JavaClassesGenerator.class);

    public static final String CLASS_EXTENSION = ".java";

    public static final String ENTITY_PACKAGE = "com.seabware.domain.model";
    public static final String ENTITY_POSTFIX = "BaseGen";
    public static final String ENTITY_VELOCITY_TEMPLATE = "velocity/codegen/entity.vm";
    public static final String GENERATED_ENTITY_VELOCITY_TEMPLATE = "velocity/codegen/generatedEntity.vm";

    public static final String DAO_PACKAGE = "com.seabware.domain.dao.repositories";
    public static final String DAO_POSTFIX = "Repository";
    public static final String DAO_VELOCITY_TEMPLATE = "velocity/codegen/dao.vm";

    // --------------------------------------------------------------------------------------------------------------------------
    @Override
    public void generateEntity(final Entity entity, final String outputPath) throws IOException
	{
        final String entityName = entity.getXmlAttributes().get(AttributeConstants.Entity.NAME);

        final VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        final VelocityContext context = new VelocityContext();
        context.put("entity", entity);
        context.put("entityPostfix", ENTITY_POSTFIX);
        context.put("package", ENTITY_PACKAGE);
        context.put("utility", new CodeGeneratorHelper());

        // this generates the generated base class.

        final BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath + entityName + ENTITY_POSTFIX + CLASS_EXTENSION));

        final Template template = velocityEngine.getTemplate(GENERATED_ENTITY_VELOCITY_TEMPLATE);
        template.merge(context, writer);

        writer.flush();
        writer.close();

        // this generates the derived entity class.

        final BufferedWriter writer2 = new BufferedWriter(new FileWriter(outputPath + entityName + CLASS_EXTENSION));

        final Template template2 = velocityEngine.getTemplate(ENTITY_VELOCITY_TEMPLATE);
        template2.merge(context, writer2);

        writer2.flush();
        writer2.close();

        mLogger.info("Class " + entityName + " generated!");
	}

    // --------------------------------------------------------------------------------------------------------------------------
    @Override
    public void generateDao(Entity entity, final String outputPath) throws IOException
    {
        final String entityName = entity.getXmlAttributes().get(AttributeConstants.Entity.NAME);

        final VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        final VelocityContext context = new VelocityContext();
        context.put("entity", entity);
        context.put("daoPostfix", DAO_POSTFIX);
        context.put("package", DAO_PACKAGE);
        context.put("entityPackage", ENTITY_PACKAGE + ".*");
        context.put("utility", new CodeGeneratorHelper());

        // this generates the DAO class.

        final BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath + entityName + DAO_POSTFIX + CLASS_EXTENSION));

        final Template template = velocityEngine.getTemplate(DAO_VELOCITY_TEMPLATE);
        template.merge(context, writer);

        writer.flush();
        writer.close();

        // this generates the custom base class.

        final BufferedWriter writer2 = new BufferedWriter(new FileWriter(outputPath + entityName + CLASS_EXTENSION));

        final Template template2 = velocityEngine.getTemplate(ENTITY_VELOCITY_TEMPLATE);
        template2.merge(context, writer2);

        writer2.flush();
        writer2.close();

        mLogger.info("DAO  " + entityName + " generated!");
    }
}