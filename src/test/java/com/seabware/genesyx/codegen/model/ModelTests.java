package com.seabware.genesyx.codegen.model;

import java.io.File;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

public class ModelTests
{
	public static void main(String[] args) throws Exception 
	{
		JAXBContext jc = JAXBContext.newInstance(Model.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Model model = (Model) unmarshaller.unmarshal(new File("sample-entities.xml"));

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(model, System.out);
        
        System.out.println(model.getXmlAttributes());
        
        Map<QName, String> map = model.getXmlAttributes();
        System.out.println(map.get(AttributeConstants.Model.PACKAGE));
        
        for (Entity entity : model.getEntityList())
        {
        	System.out.println(entity.getAttributes());
        	System.out.println(entity.getDescription());
        }
        
	}
}

