package com.seabware.genesyx.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.seabware.genesyx.codegen.model.AbstractBaseElement;


public class JaxbHelper
{
	public static <T extends AbstractBaseElement> T marshall(Class<T> clazz, String file) throws JAXBException
	{
		JAXBContext jc = JAXBContext.newInstance(clazz);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        T element = (T) unmarshaller.unmarshal(new File(file));

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        //marshaller.marshal(element, System.out);
        
        return element;
	}
}
