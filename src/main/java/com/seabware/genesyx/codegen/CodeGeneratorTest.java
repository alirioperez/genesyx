package com.seabware.genesyx.codegen;


import com.seabware.genesyx.codegen.model.*;

import javax.xml.namespace.QName;
import java.util.Map;

public class CodeGeneratorTest
{

	public static void main(String[] args) throws Exception
	{
		CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.launch(new String[]{"codegen.custompath=C:\\Users\\Alirio.Perez\\Development\\sources\\personal\\genesyx\\sample-data\\"});

        DomainSingleton domainSingleton = DomainSingleton.getInstance();

        for (Model model : domainSingleton.getModels()) {
            System.out.println("XML Attributes for model:" + model.getXmlAttributes());

            Map<QName, String> map = model.getXmlAttributes();
            System.out.println("XML Attribute (PACKAGE):" + map.get(AttributeConstants.Model.PACKAGE));

            for (Entity entity : model.getEntityList())
            {
                System.out.println("Entity attributes for entity:" + entity.getAttributes());
                System.out.println(entity.getDescription());

                for (Attribute attribute : entity.getAttributes())
                {
                    System.out.println(attribute.getXmlAttributes());
                }

                for (Association association : entity.getAssociations())
                {
                    System.out.println(association.getXmlAttributes());
                }
            }
        }
		
	}
}
