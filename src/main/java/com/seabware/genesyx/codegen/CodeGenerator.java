package com.seabware.genesyx.codegen;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.seabware.commons.ArgumentsParser;
import com.seabware.commons.log.LogUtil;
import com.seabware.genesyx.codegen.generators.ClassesGenerator;
import com.seabware.genesyx.codegen.model.Entity;
import com.seabware.genesyx.codegen.model.Model;
import com.seabware.genesyx.xml.JaxbHelper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/**
 * Code Generator for the SeabWare genesyx.
 *
 * @author aperez
 */
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
public class CodeGenerator
{
    /** This CodeGenerator's _logger */
    private final static Logger mLogger = Logger.getLogger(CodeGenerator.class);

    /** The big guy containing all domain data */
    private final DomainSingleton mDomainSingleton = DomainSingleton.getInstance();

    /** The java classes generator, injected by Spring*/
    private ClassesGenerator mClassesGenerator;

    // ---------------------------------------------------------------------------------------------------------------------------
    /**
     * PROPERTIES
     */
    // ---------------------------------------------------------------------------------------------------------------------------

    // TODO
    private String mCustomPath = "C:\\Users\\Alirio.Perez\\Development\\sources\\personal\\genesyx\\sample-data\\";

    // --------------------------------------------------------------------------------------------------------------------------
    /**
     * Launches the generation code process.
     */
    // --------------------------------------------------------------------------------------------------------------------------
    public void launch(String[] args) throws JAXBException, IOException
    {
        // Create, configure beans and start Spring.

        String[] configs = new String[] { "spring/beans-spring.xml" };
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configs);
        context.start();

        mClassesGenerator = (ClassesGenerator) context.getBean("javaClassesGenerator");

        // Get properties from the command line.

        final ArgumentsParser argumentsParser = new ArgumentsParser();
        argumentsParser.add(args);

        // Add a console appender if we have none at all.

        LogUtil.checkAndAddBasicLog4jAppender();

        // Make sure all loggers are in debug if needed.

        boolean debug = argumentsParser.getEffectiveValue("codegen.debug", "0").equals("1");
        if (debug)
        {
            mLogger.setLevel(Level.DEBUG);
        }

        mLogger.info("Code Generator started...");

        parseArguments(argumentsParser);
        loadDomain();
        validateDomain();
        generateDomain();
    }

    // --------------------------------------------------------------------------------------------------------------------------
    private void parseArguments(ArgumentsParser args)
    {
        mLogger.info("Parsing arguments...");

        // path were custom modules are.
        mCustomPath = args.getEffectiveValue("codegen.custompath", this.mCustomPath);
    }

    // --------------------------------------------------------------------------------------------------------------------------
    /**
     * Loads all domain data in memory.
     */
    // --------------------------------------------------------------------------------------------------------------------------
    private void loadDomain() throws JAXBException
    {
        mLogger.info("Domain loading process started...");

        for (File file : listEntitiesFiles(this.mCustomPath))
        {
            Model model = JaxbHelper.marshall(Model.class, file.getAbsolutePath());

            mDomainSingleton.getModels().add(model);
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    /**
     * Here you can validate/adjust the model, if needed.
     */
    // --------------------------------------------------------------------------------------------------------------------------
    private void validateDomain()
    {
        mLogger.info("Domain validation process started...");
    }

    // --------------------------------------------------------------------------------------------------------------------------
    /**
     * Writes all data in domain into proper generated files
     */
    // --------------------------------------------------------------------------------------------------------------------------
    private void generateDomain() throws IOException
    {
        mLogger.info("Domain writing process started...");

        generatePojos();
		generateDaos();
//		generateServices()
//		generateRestServices();
//		generateSpringFiles();
    }

    // --------------------------------------------------------------------------------------------------------------------------
    /**
     * Creates classes for all entities within the model.
     */
    // --------------------------------------------------------------------------------------------------------------------------
    private void generatePojos() throws IOException
    {
       mLogger.info("Entity's creation process started...");

        for(Model model : mDomainSingleton.getModels())
        {
            for (Entity entity : model.getEntityList())
            {
               mClassesGenerator.generateEntity(entity, "");
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    /**
     * Creates Daos for all entities within the model.
     */
    // --------------------------------------------------------------------------------------------------------------------------
    private void generateDaos() throws IOException
    {
        mLogger.info("DAOs creation process started...");

        for(Model model : mDomainSingleton.getModels())
        {
            for (Entity entity : model.getEntityList())
            {
                mClassesGenerator.generateDao(entity, "");
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    /**
     * Convenient method for get all entities.xml files in the deployment.
     */
    // --------------------------------------------------------------------------------------------------------------------------
    private File[] listEntitiesFiles(String customPath)
    {
        FileFilter fileFilter = new FileFilter()
        {

            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".xml");
            }

        };

        File[] files = new File(customPath).listFiles(fileFilter);

        return files;
    }

//    // --------------------------------------------------------------------------------------------------------------------------
//    public void setJavaClassGenerator(ClassesGenerator javaClassGenerator)
//    {
//        _javaClassGenerator = javaClassGenerator;
//    }
//
//    // --------------------------------------------------------------------------------------------------------------------------
//    public ClassesGenerator getJavaClassGenerator()
//    {
//        return _javaClassGenerator;
//    }

    // ---------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        try
        {
            // Run the CodeGenerator
            CodeGenerator codeGenerator = new CodeGenerator();
            codeGenerator.launch(args);
        }
        catch (Throwable ex)
        {
            // Also output on the raw console just in case the logging engines get screwed up...
            System.err.println("");
            ex.printStackTrace();
        }
    }
}
